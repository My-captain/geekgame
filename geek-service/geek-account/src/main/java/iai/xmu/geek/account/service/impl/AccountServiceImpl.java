package iai.xmu.geek.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import iai.xmu.geek.account.exception.AccountNotExistException;
import iai.xmu.geek.account.exception.IllegalAccountException;
import iai.xmu.geek.account.exception.InsufficientBalanceException;
import iai.xmu.geek.account.mapper.AccountMapper;
import iai.xmu.geek.account.model.db.AccountDO;
import iai.xmu.geek.account.model.param.AccountParam;
import iai.xmu.geek.account.model.param.TransferParam;
import iai.xmu.geek.account.model.query.AccountQuery;
import iai.xmu.geek.account.model.vo.AccountVO;
import iai.xmu.geek.account.service.AccountService;
import iai.xmu.geek.account.utils.ResultUtils;
import iai.xmu.geek.account.utils.SnowFlakeIdUtils;
import iai.xmu.geek.commom.constant.Constant;
import iai.xmu.geek.commom.exception.GeekException;
import iai.xmu.geek.commom.utils.DateUtils;
import iai.xmu.geek.commom.utils.StringUtils;
import iai.xmu.geek.commom.web.ErrorCode;
import iai.xmu.geek.commom.web.PageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: iai.xmu.edu.cn

 */
@Slf4j
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountDO> implements AccountService {

    private static Map<String, String> typeMap = Maps.newHashMap();

    private static List<String> typeList = Arrays.asList("01", "02", "03");
    private static List<String> yearList = Arrays.asList("2020", "2021", "2022");

    static {
        typeMap.put("01", "个人活期");
        typeMap.put("02", "个人定期");
        typeMap.put("03", "对公户");
    }

    @Autowired
    private SnowFlakeIdUtils snowFlakeIdUtils;

    @Override
    public PageModel<AccountVO> page(AccountQuery param) {
        LambdaQueryWrapper<AccountDO> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isEmpty(param.getType())) {
            wrapper.in(AccountDO::getType, typeList);
        } else {
            wrapper.eq(AccountDO::getType, param.getType());
        }
        if (StringUtils.isEmpty(param.getStartTime()) && StringUtils.isEmpty(param.getEndTime())) {
            wrapper.in(AccountDO::getYear, yearList);
        } else {
            wrapper.in(AccountDO::getYear, DateUtils.betweenYears(param.getStartTime().substring(0, 4), param.getEndTime().substring(0, 4)));
            wrapper.between(AccountDO::getOpenTime,
                    DateUtils.toDate(param.getStartTime(), Constant.LONG_TIME_PATTERN),
                    DateUtils.toDate(param.getEndTime(), Constant.LONG_TIME_PATTERN));
        }

        IPage<AccountDO> rst = this.page(new Page<>(param.getPageNum(), param.getPageSize()), wrapper);
        if (rst.getRecords().size() > 0) {
            rst.getRecords().forEach(i -> i.setType(typeMap.get(i.getType())));
        }
        return ResultUtils.toPageModel(rst, AccountVO.class);
    }

    @Override
    public AccountVO find(String account) {
        if (!checkAccount(account)) {
            throw new IllegalAccountException("账户非法");
        }
        AccountDO rst = this.findAccount(account);
        if (null == rst) {
            throw new AccountNotExistException("账户不存在");
        }
        rst.setType(typeMap.get(rst.getType()));
        return ResultUtils.toBean(rst, AccountVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccountVO addAccount(AccountParam param) {
        if (!typeList.contains(param.getType())) {
            throw new GeekException(ErrorCode.BUSINESS_ERROR, "账户类型非法");
        }
        if (param.getBalance().compareTo(Constant.DECIMAL_ZERO) < 0) {
            throw new GeekException(ErrorCode.BUSINESS_ERROR, "余额非法");
        }

        Date openTime = new Date();
        String year = DateUtils.fromDate(openTime, "yyyy");
//        String date = DateUtils.fromDate(openTime, Constant.SHORT_DATE_PATTERN);
//
//        LambdaQueryWrapper<AccountDO> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(AccountDO::getType, param.getType());
//        wrapper.eq(AccountDO::getYear, year);
//        wrapper.orderByDesc(AccountDO::getAccount);
//        wrapper.last(" limit 1");
//        AccountDO last = this.baseMapper.selectOne(wrapper);

//         优化点：账户生成需要考虑高并发情况
//        String serialNo = "0001";
//        if (null != last) {
//            String lastDate = last.getAccount().substring(2, 10);
//            serialNo = lastDate.equals(date) ?
//                    String.format("%04d", Integer.valueOf(last.getAccount().substring(10)) + 1) : serialNo;
//        }


        String snowflake_id = snowFlakeIdUtils.getSnowflakeId();


        // 生成账户： 类型 + yyyyMMdd日期 + 4位流水号
        String account = param.getType() + year + snowflake_id;
        AccountDO entity = new AccountDO();
        entity.setAccount(account);
        entity.setOwner(param.getOwner());
        entity.setType(param.getType());
        entity.setYear(year);
        entity.setOpenTime(openTime);
        entity.setCurrency("CNY");
        entity.setBalance(param.getBalance());
        entity.setUpdateTime(openTime);
        this.baseMapper.insert(entity);

        entity.setType(typeMap.get(entity.getType()));
        return ResultUtils.toBean(entity, AccountVO.class);
    }

    private AccountDO findAccount(String account) {
        LambdaQueryWrapper<AccountDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AccountDO::getType, account.substring(0, 2));
        wrapper.eq(AccountDO::getYear, account.substring(2, 6));
        wrapper.eq(AccountDO::getAccount, account);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transfer(TransferParam param) {
        if (null == param.getAmount() || param.getAmount().compareTo(Constant.DECIMAL_ZERO) <= 0) {
            throw new GeekException(ErrorCode.BUSINESS_ERROR, "转账金额非法");
        }
        if (!checkAccount(param.getOrigin())) {
            throw new IllegalAccountException("来源账户非法");
        }
        if (!checkAccount(param.getTarget())) {
            throw new IllegalAccountException("目标账户非法");
        }
        AccountDO origin = this.findAccount(param.getOrigin());
        if (null == origin) {
            throw new AccountNotExistException("来源账户不存在");
        }
        AccountDO target = this.findAccount(param.getTarget());
        if (null == target) {
            throw new AccountNotExistException("目标账户不存在");
        }
        if (origin.getBalance().compareTo(param.getAmount()) < 0) {
            throw new InsufficientBalanceException("来源账户余额不足");
        }

        // 来源账户扣款
        LambdaUpdateWrapper<AccountDO> originWrapper = new LambdaUpdateWrapper<>();
        originWrapper.eq(AccountDO::getType, param.getOrigin().substring(0, 2));
        originWrapper.eq(AccountDO::getYear, param.getOrigin().substring(2, 6));
        originWrapper.eq(AccountDO::getAccount, param.getOrigin());
        originWrapper.set(AccountDO::getBalance, origin.getBalance().subtract(param.getAmount()));
        this.baseMapper.update(null, originWrapper);

        // 目标账户收款
        LambdaUpdateWrapper<AccountDO> targetWrapper = new LambdaUpdateWrapper<>();
        targetWrapper.eq(AccountDO::getType, param.getTarget().substring(0, 2));
        targetWrapper.eq(AccountDO::getYear, param.getTarget().substring(2, 6));
        targetWrapper.eq(AccountDO::getAccount, param.getTarget());
        targetWrapper.set(AccountDO::getBalance, target.getBalance().add(param.getAmount()));
        this.baseMapper.update(null, targetWrapper);
    }


    private boolean checkAccount(String account) {
        if (StringUtils.isEmpty(account) || account.length() != 14) {
            return false;
        }
        if (!typeList.contains(account.substring(0, 2))) {
            return false;
        }
        return yearList.contains(account.substring(2, 6));
    }

}

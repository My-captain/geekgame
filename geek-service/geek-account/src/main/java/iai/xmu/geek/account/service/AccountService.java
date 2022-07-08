package iai.xmu.geek.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import iai.xmu.geek.account.model.db.AccountDO;
import iai.xmu.geek.account.model.param.AccountParam;
import iai.xmu.geek.account.model.param.TransferParam;
import iai.xmu.geek.account.model.query.AccountQuery;
import iai.xmu.geek.account.model.vo.AccountVO;
import iai.xmu.geek.commom.web.PageModel;

/**
 * @Author: iai.xmu.edu.cn

 */
public interface AccountService extends IService<AccountDO> {

    /**
     * 分页查询
     *
     * @param param
     * @return
     */
    PageModel<AccountVO> page(AccountQuery param);

    /**
     * 根据账户查询
     *
     * @param account
     * @return
     */
    AccountVO find(String account);

    /**
     * 新增账户
     * @param param
     * @return
     */
    AccountVO addAccount(AccountParam param);

    /**
     * 转账
     *
     * @param param
     */
    void transfer(TransferParam param);
}

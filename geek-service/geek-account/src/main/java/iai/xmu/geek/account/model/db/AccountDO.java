package iai.xmu.geek.account.model.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账户库
 *
 * @Author: iai.xmu.edu.cn

 */
@Getter
@Setter
@ToString
@TableName("tb_account")
public class AccountDO {

    /**
     * 账户：类型 + yyyyMMdd日期 + 4位流水号 （暂定）
     */
    @TableId(type = IdType.INPUT)
    private String account;

    /**
     * 开户人
     */
    private String owner;

    /**
     * 账户类型：01-个人活期，02-个人定期，03-对公户
     */
    private String type;

    /**
     * 开立年份
     */
    private String year;

    /**
     * 开立时间
     */
    private Date openTime;

    /**
     * 币种
     */
    private String currency;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 更新时间
     */
    private Date updateTime;
}

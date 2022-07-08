package iai.xmu.geek.account.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import iai.xmu.geek.commom.constant.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: iai.xmu.edu.cn

 */
@Getter
@Setter
@ToString
@ApiModel("账户视图对象")
public class AccountVO {

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("开户人")
    private String owner;

    @ApiModelProperty("账户类型")
    private String type;

    @ApiModelProperty("开立年份")
    private String year;

    @ApiModelProperty("开立时间")
    @JsonFormat(pattern = Constant.LONG_TIME_PATTERN)
    private Date openTime;

    @ApiModelProperty("币种")
    private String currency;

    @ApiModelProperty("余额")
    private BigDecimal balance;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = Constant.LONG_TIME_PATTERN)
    private Date updateTime;

}

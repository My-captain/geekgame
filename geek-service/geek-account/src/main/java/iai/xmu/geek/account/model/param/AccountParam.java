package iai.xmu.geek.account.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author: iai.xmu.edu.cn

 */
@Getter
@Setter
@ToString
@ApiModel("新增账户参数")
public class AccountParam {

    @ApiModelProperty("开户人")
    @NotNull(message = "开户人不能为空")
    private String owner;

    @ApiModelProperty("账户类型")
    @NotNull(message = "账户类型不能为空")
    private String type;

    @ApiModelProperty("余额")
    @NotNull(message = "余额不能为空")
    private BigDecimal balance;

}

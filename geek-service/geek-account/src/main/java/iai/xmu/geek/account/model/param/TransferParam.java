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
@ApiModel("转账参数")
public class TransferParam {

    @ApiModelProperty(value = "来源账户")
    @NotNull(message = "来源账户不能为空")
    private String origin;

    @ApiModelProperty(value = "目标账户")
    @NotNull(message = "目标账户不能为空")
    private String target;

    @ApiModelProperty(value = "转账金额")
    @NotNull(message = "转账金额不能为空")
    private BigDecimal amount;

}

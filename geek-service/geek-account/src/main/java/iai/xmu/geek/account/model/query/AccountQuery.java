package iai.xmu.geek.account.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: iai.xmu.edu.cn

 */
@Getter
@Setter
@ToString
@ApiModel("账户列表查询参数")
public class AccountQuery {

    @ApiModelProperty(value = "账户类型")
    private String type;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "当前页码", required = true, example = "1")
    @NotNull(message = "当前页码不能为空")
    @Min(value = 1, message = "当前页码必须大于等于1")
    private Integer pageNum;

    @ApiModelProperty(value = "每页条数", required = true, example = "10")
    @Min(value = 1, message = "每页条数必须大于等于1")
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;
}

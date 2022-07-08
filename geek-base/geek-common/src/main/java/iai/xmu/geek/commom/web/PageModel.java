package iai.xmu.geek.commom.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

/**
 * 统一分页返回结构
 *
 * @Author: iai.xmu.edu.cn

 */
@Getter
@Setter
@ToString
@ApiModel("分页对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageModel<T> {

    public interface PageView extends Result.ResultView {
    }

    @ApiModelProperty("查询数据列表")
    @JsonView({PageView.class})
    public List<T> list = Collections.emptyList();

    @JsonView({PageView.class})
    @ApiModelProperty("当前页码")
    public Integer currentPage;

    @JsonView({PageView.class})
    @ApiModelProperty("每页条数")
    public Integer pageSize;

    @ApiModelProperty("总条数")
    @JsonView({PageView.class})
    public Integer total;

    @JsonView({PageView.class})
    @ApiModelProperty("总页数")
    public Integer totalPage;

}

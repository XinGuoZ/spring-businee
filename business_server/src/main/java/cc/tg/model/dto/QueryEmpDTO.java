package cc.tg.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 跟部门id查询
 *
 * @author xinguoz
 * @create 2020-11-08 13:43
 **/
@Data
public class QueryEmpDTO extends QueryPageDTO implements Serializable {
    @ApiModelProperty(name = "部门id")
    private Integer id;

    @ApiModelProperty(name = "是否分页")
    private Boolean pagination = true;
}

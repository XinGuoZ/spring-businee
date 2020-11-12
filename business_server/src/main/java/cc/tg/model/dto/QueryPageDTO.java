package cc.tg.model.dto;

import cn.hutool.core.util.PageUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 根据分组获取用户列表
 *
 * @author xinguoz
 * @create 2020-11-08 13:30
 **/
@Data
public class QueryPageDTO implements Serializable {

    @ApiModelProperty(name = "页数")
    private Integer currentPage = 1;

    @ApiModelProperty(name = "码数")
    private Integer pageSize = 25;

    @ApiModelProperty(hidden = true ,name ="开始页")
    private Integer startRow;

    @ApiModelProperty(hidden = true ,name ="结束页")
    private Integer endRow;

    public void cal(){
        int[] ints = PageUtil.transToStartEnd(currentPage-1, pageSize);
        this.startRow = ints[0];
        this.endRow = ints[1];
    }

}

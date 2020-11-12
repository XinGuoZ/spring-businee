package cc.tg.model.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("响应对象返回格式")
public class ResultVO {

    /**
     * 错误码
     */
    @ApiModelProperty("错误码")
    private String code;

    /**
     * 提示信息
     */
    @ApiModelProperty("提示信息")
    private String message;

    /**
     * 具体的内容
     */
    @ApiModelProperty("具体的内容")
    private Object data;
}

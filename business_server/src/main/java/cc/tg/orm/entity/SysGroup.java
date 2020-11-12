package cc.tg.orm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author xinguo
 * @since 2020-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名称
     */
    @ApiModelProperty( name = "部门名称",example = "数据研发")
    private String name;

    @ApiModelProperty( name = "父节点id",example = "2")
    private Integer parentId;

    @ApiModelProperty(hidden = true)
    private String depPath;

    @ApiModelProperty(hidden = true)
    private Boolean enabled;

    @ApiModelProperty(hidden = true)
    private Boolean isParent;

    @ApiModelProperty(hidden = true)
    @TableField(exist = false)
    private List<SysGroup> childGroup = new ArrayList<SysGroup>();

    /**
     * 备注
     */
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private String remark;

    /**
     * 删除状态 0-未删除 1-已删除
     */
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private String removeStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private String createAt;


}

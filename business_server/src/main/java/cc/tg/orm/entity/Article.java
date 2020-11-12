package cc.tg.orm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xinguo
 * @since 2020-09-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 文本内容
     */
    private String content;

    /**
     * 来源 1-原创，2-其他
     */
    private String source;

    /**
     * 归属组织
     */
    private String adscriptionName;

    /**
     * 归属组织_id
     */
    private Long adscriptionId;

    /**
     * 类型id
     */
    private Long contentTypeId;

    private String contentTypeName;

    /**
     * 权限: 0-全部可见 1-自己可见
     */
    private String auth;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除状态 0-未删除 1-已删除
     */
    private String removeStatus;

    /**
     * 创建时间
     */
    private String createAt;

    /**
     * 标题
     */
    private String title;


}

package cc.tg.orm.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜单状态封装
 *
 * @author xinguoz
 * @create 2020-11-03 23:04
 **/
@Data
public class MenuMeta implements Serializable {

    private boolean keepAlive;
    private boolean requireAuth;
}

package cc.tg.tools;

import cc.tg.orm.entity.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Security工具类
 *
 * @author xinguoz
 * @create 2020-11-03 22:49
 **/
public class SecurityUtils {
    public static UserInfo getCurrentHr() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserInfo) authentication.getPrincipal();
    }
}

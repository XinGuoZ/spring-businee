package cc.tg.tools;

import cn.hutool.extra.mail.MailUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * 发送邮件工具类
 *
 * @author xinguoz
 * @create 2020-10-29 22:08
 **/
@UtilityClass
@Slf4j
public class EmailUtils {


    /**
     * @Author     xinguoz
     * @Date        2020/10/29 22:09
     * @Param        
     * @return      boolean
     **/
    public String sendMail (String account, String subject ,String content){
        log.info("给{}发送->主题：{}内容{}",account, subject, content+MyConst.EMAIL_CLOSING_REMARKS);
        return MailUtil.send(account, subject, content+MyConst.EMAIL_CLOSING_REMARKS, false);
    }

    public  String sendMailAndAnnex(String account, String subject , String content, File annex) {
        log.info("给{}发送->主题：{}内容{}",account, subject, content+MyConst.EMAIL_CLOSING_REMARKS);
        return MailUtil.send(account, subject, content+MyConst.EMAIL_CLOSING_REMARKS, false, annex);
    }

    public static void main(String[] args) {
        sendMail("328196073@qq.com", "测试", "邮件来自Hutool测试");
    }
}

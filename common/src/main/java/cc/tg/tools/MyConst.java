package cc.tg.tools;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Create by zhangxinguo on 2020/10/28 23:52
 */

public class MyConst {

    /**
     * 注册验证码
    **/
    public static final String REGIST_CODE = "registCode" ;

    /**
     * 注册码邮件发送主题
     * */
    public static final String REGIST_SUBJECT = "酱校新用户注册验证码";

    /**
     * 注册邮件发送模板
     * */
    public static final String REGIST_MOUDLE = "您在正在注册酱校服务你的新注册验证码是:";
    /**
    * 邮件后缀
    * */
    public static final String EMAIL_CLOSING_REMARKS ="\n来自酱校消息";
    /**
     *
     * */

    public static final String REGIST_SUCCESS_SUBJECT = "酱校新用户注册成功";

    public static final String OPT_SUCCESS = "操作成功";

    public static enum ExamMap {
        A(0),
        B(1),
        C(2),
        D(3),
        E(4),
        F(5);
        private Integer name;
        ExamMap(Integer name) {
            this.name = name;
        }
        public Integer getName() {
            return name;
        }

    }
    public static ExamMap match(Integer name) {
        for (ExamMap e: ExamMap.values()) {
            if (Objects.equals(name, e.getName())) {
                return e;
            }
        }
        return null;
    }

    public static Timestamp increaseMinutes (int min){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE,min);
        Date time = instance.getTime();
        return new Timestamp(time.getTime()) ;
    }
    public static void main(String[] args) {
        System.out.println(match(0));
    }
}

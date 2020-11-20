package cc.tg.dto;

import lombok.Data;
import org.apache.kafka.common.serialization.Serializer;

/**
 * 邮件发送类
 *
 * @author xinguoz
 * @create 2020-11-20 13:56
 **/
@Data
public class MailDTO implements Serializer {
    private static final long serialVersionUID = 1L;
    /**
     * 邮箱地址
     * */
    private String email;
    /**
     * 主题
     * */
    private String subject;

    /**
     * 内容
     *
     * */
    private String conetet;

    @Override
    public byte[] serialize(String s, Object o) {
        return new byte[0];
    }
}

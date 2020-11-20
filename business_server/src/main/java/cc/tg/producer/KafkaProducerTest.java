package cc.tg.producer;

import cc.tg.dto.MailDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xinguoz
 * @create 2020-11-20 11:44
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class KafkaProducerTest {

    @Autowired KafkaProducer kafkaProducer;

    @Test
    public void send() {
        kafkaProducer.send("test1","328196073@qq.com");
        kafkaProducer.send("test2","328196073@qq.com");
    }

    @Test
    public void sendMail() {
        MailDTO mailDTO = new MailDTO();
        mailDTO.setEmail("961728361@qq.com");
        mailDTO.setSubject("kafka_丞风智能");
        mailDTO.setConetet("邓兴达——23333");
        kafkaProducer.sendMain(mailDTO);
    }
}
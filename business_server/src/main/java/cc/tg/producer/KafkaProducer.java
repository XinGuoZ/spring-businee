package cc.tg.producer;

import cc.tg.dto.MailDTO;
import cc.tg.tools.MyConst;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * kafka消息发送
 *
 * @author xinguoz
 * @create 2020-11-20 11:30
 **/
@Slf4j
@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public ResponseEntity<?> send( String topic,  String msg) {
        kafkaTemplate.send(topic, msg);
        log.info("[Producer] topic: {}; msg: {}", topic, msg);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<?> sendMain(MailDTO mailDTO) {
        ProducerRecord<String, Object> record = new ProducerRecord<>(MyConst.EMAILMQ, JSON.toJSONString(mailDTO));
        kafkaTemplate.send(record);
        log.info("[Producer] {}", record);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package cc.tg.client;


import cc.tg.model.vo.ResultVO;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xinguoz
 * @create 2020-11-19 18:19
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ExamClientTest {

    @Autowired
    ExamClient examClient;

    @Test
    public void sendEmailCode(){
        ResultVO resultVO = examClient.sendEmailCode("328196073@qq.com");
        log.info(resultVO.getCode());

    }
}
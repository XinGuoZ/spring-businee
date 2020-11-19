package cc.tg.client;

import cc.tg.model.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(url ="${server.examUrl}",name="exam")
public interface ExamClient {
    /**
     *
     * 发送验证码
     * @Author     xinguoz
     * @Date        2020/11/19 18:18
     * @Param       account
     * @return      cc.tg.model.vo.ResultVO
     **/
    @PostMapping("/checkCode/sendEmailCode")
    public ResultVO sendEmailCode(@RequestParam(name = "account") String account);
}

package cc.tg.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(url =" ${server.examUrl}",name="exam")
public class ExamClient {
}

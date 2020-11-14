package cc.tg.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * create by zhangxinguo on 2020/6/4 11:29
 */
@Slf4j
@Component
public class DingdingNotice {

    @Value("${spring.profiles.active}")
    private String profile;

    /**
     * @return void
     * @Author zhangxinguo
     * @Description 发送钉钉信息
     * @Date 2020/6/4 11:30
     * @Param []
     **/
    public boolean sendDdMsg(String msg, String title, String messageUrl) {
        try {
            msg = "数据转换服务：[" + profile + "]" + msg;
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            headers.add("User-Agent", "Mozilla/5.0(WindowsNT6.1;WOW64;rv:19.0)Gecko/20100101Firefox/19.0");
            JSONObject params = new JSONObject();
            Map<String, Object> link = new HashMap();
            params.put("msgtype", "link");
            link.put("text", msg);
            link.put("title", title);
            link.put("picUrl", "http://thirdwx.qlogo.cn/mmopen/icGs6iaDL9HHcibh7DLgu4b1CCD9Cnfa5VIqibgHC66tN6hrJSo4noK73GZlyruDbicaD4EK6UQuc8ibOdMyE1rKA1YNdO7pWkZBics/132");
            link.put("messageUrl", messageUrl);
            params.put("link", link);
            HttpEntity<JSONObject> requestEntity = new HttpEntity<JSONObject>(params, headers);
            String dingDingToken = "https://oapi.dingtalk.com/robot/send?access_token=56f2fe2c847a81aeec11e4597a7c8c9e367dd0c6027cab56790567653442d9c5";
            ResponseEntity<JSONObject> response = restTemplate.postForEntity(dingDingToken, requestEntity, JSONObject.class);
            log.info("发送钉钉信息响应: {}", JSON.toJSONString(response));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return true;
        }

    }

    /**
     * 字符串gzip压缩
     */
    @SuppressWarnings("restriction")
    public static String gzip(String primStr) {
        if (primStr == null || primStr.length() == 0) {
            return primStr;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(primStr.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new sun.misc.BASE64Encoder().encode(out.toByteArray());
    }

    /**
     * 字符串的解压
     * Aaron Wang
     * 2019-06-03
     */
    @SuppressWarnings("restriction")
    public static String jyZip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;
        try {
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);

            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return decompressed;
    }

    //存储配置属性的Map集合
    public static Map<String, Object> conf = new HashMap<>();


    //递归解析map对象
    public static void loadRecursion(Map<String, Object> map, String key) {
        map.forEach((k, v) -> {
            if (isParent(v)) {
                Map<String, Object> nextValue = (Map<String, Object>) v;
                System.out.println(nextValue);
                loadRecursion(nextValue, (("".equals(key) ? "" : key + ".") + k));
            } else {
                conf.put(key + "." + k, v);
            }
        });
    }

    //判断是否还有子节点
    public static boolean isParent(Object o) {
        if (!(o instanceof String || o instanceof Character || o instanceof Byte || o instanceof Boolean)) {
            try {
                Number n = (Number) o;
            } catch (Exception e) {
                return true;
            }
        }
        return false;
    }

}

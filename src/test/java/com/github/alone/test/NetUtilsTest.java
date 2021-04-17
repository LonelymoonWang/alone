package com.github.alone.test;

import cn.hutool.http.HttpRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/17 16:01
 * @Description:
 **********************************/
@RunWith(SpringRunner.class)
@SpringBootTest
public class NetUtilsTest {
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";
    @Test
    public void testNetworks(){
        //String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + "192.168.0.103" + "&json=true", Constants.GBK);
        StringBuilder s = new StringBuilder();
        s.append(IP_URL);
        s.append("?ip=" + "27.23.94.221" + "&json=true");//27.22.92.221
        String body = HttpRequest.get(s.toString()).execute().body();
        System.out.println(body);
    }


}

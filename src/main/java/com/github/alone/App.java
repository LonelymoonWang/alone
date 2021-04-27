package com.github.alone;

import com.github.alone.config.bean.SnowflakeBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/16 18:30
 * @Description: 启动类
 **********************************/
@SpringBootApplication
@Controller
public class App {

    public static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

    /**测试 Web**/
    @RequestMapping("/")
    @ResponseBody
    public String toIndex(){
        logger.info("这是主页");
        logger.debug("这是主页");
        return "这是主页";
    }
}

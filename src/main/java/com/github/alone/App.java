package com.github.alone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/16 18:30
 * @Description:
 **********************************/
@SpringBootApplication
@Controller
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

    /**
     *
     * 功能描述: 测试 Web
     *
     * @param:
     * @return: "这是主页"
     * @Author: WSIR
     * @date: 2021/4/16 18:44
     */
    @RequestMapping("/")
    @ResponseBody
    public String toIndex(){
        return "这是主页";
    }
}

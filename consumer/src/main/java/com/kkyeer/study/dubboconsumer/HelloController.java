package com.kkyeer.study.dubboconsumer;

import com.kkyeer.study.dubbo.dubbouserservice.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CompletableFuture;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in  2020-4-2 11:07
 * @Modified By:
 */
@RestController
public class HelloController {
    @Reference(version = "2.0", loadbalance = "demo", check = false, timeout = 1000000,async = true)
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    @GetMapping("/hello")
    public String hello(HttpServletResponse response) {
        String result = userService.hello("consumer007");
        CompletableFuture<String> future = RpcContext.getContext().getCompletableFuture();
        future.whenComplete(
                (r,e)->{
                    LOGGER.info("Result:{}", r);
//                    这里是错误的，因为你方法早已经返回，输出流已经关闭了，调用方收到空响应
                    PrintWriter writer = null;
                    try {
                        writer = response.getWriter();
                        writer.println(r);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } finally {
                        if (writer != null) {
                            writer.close();
                        }
                    }

                }
        );
        LOGGER.info("方法返回");
        return result;
    }
}
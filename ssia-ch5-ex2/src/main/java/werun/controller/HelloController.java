package werun.controller;

import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.*;

/**
 * @author werun
 * @version 1.0
 * @date 2022年01月24日 下午6:55
 * @description
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication a = context.getAuthentication();

        return "Hello, " + a.getName() + "!";
    }

    /**
     * 定义Callable对象,并将其作为任务在单独的线程上运行
     * 会报空指针异常
     * @return 用户姓名
     */
    @GetMapping("/ciao")
    public String ciao() throws Exception {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };

        ExecutorService e = newCachedThreadPool();
        try {
            return "Ciao, " + e.submit(task).get() + "!";
        } finally {
            e.shutdown();
        }
    }

    @GetMapping("/farvel")
    public String farvel() throws Exception {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };

        ExecutorService e = newCachedThreadPool();
        try {
            var contextTask = new DelegatingSecurityContextCallable<>(task);
            return "farvel, " + e.submit(contextTask).get() + "!";
        } finally {
            e.shutdown();
        }
    }
}

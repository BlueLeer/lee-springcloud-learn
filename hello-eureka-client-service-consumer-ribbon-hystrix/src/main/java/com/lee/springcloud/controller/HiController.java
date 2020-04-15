package com.lee.springcloud.controller;

import com.lee.springcloud.service.HiService;
import com.lee.springcloud.service.impl.command.HiCommand;
import com.lee.springcloud.service.impl.command.HiObservableCommand;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Subscriber;

import java.util.concurrent.ExecutionException;

/**
 * @author WangLe
 * @date 2019/7/17 18:19
 * @description
 */
@RestController
public class HiController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HiService hiService;

    @GetMapping(value = "/hi")
    public String hi() {
        return hiService.hi();
    }

    @GetMapping(value = "/hi2")
    public String hi2() {
        try {
            return hiService.hi2().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping(value = "/hi3")
    public String hi3() {
        // execute是同步的方式执行的,它的底层还是通过 .queue().get()的方式获得结果的
        return new HiCommand(restTemplate, 100000L).execute();
    }

    @GetMapping(value = "/hi4")
    public void hi4() {
        // execute是同步的方式执行的,它的底层还是通过 .queue().get()的方式获得结果的
        new HiObservableCommand().observe().subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("subscribe: " + s);
            }
        });

    }
}

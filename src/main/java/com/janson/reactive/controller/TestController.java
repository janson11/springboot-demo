package com.janson.reactive.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/26 5:41 下午
 */
@RestController
@RequestMapping("/test")
public class TestController {

    public static void main(String[] args) {
        Flux.just("Hello","World").subscribe(System.out::println);

        Flux.fromArray(new Integer[]{1,2,3}).subscribe(System.out::println);

        Flux.range(2020,5).subscribe(System.out::println);

        Flux.interval(Duration.ofSeconds(2),Duration.ofMillis(200)).subscribe(System.out::println);

        Flux.empty().subscribe(System.out::println);


        Flux.generate( synchronousSink -> {
            synchronousSink.next("janson");
            synchronousSink.complete();
        }).subscribe(System.out::println);

        Flux.generate(() ->1,(i,sink)->{
            sink.next(i);
            if (i==5){
                sink.complete();
            }
            return ++i;
        }).subscribe(System.out::println);


    }
}

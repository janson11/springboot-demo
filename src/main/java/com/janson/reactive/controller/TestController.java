package com.janson.reactive.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Optional;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/26 5:41 下午
 */
@RestController
@RequestMapping("/test")
public class TestController {

    public static void main(String[] args) {
        Flux.just("Hello", "World").subscribe(System.out::println);

        Flux.fromArray(new Integer[]{1, 2, 3}).subscribe(System.out::println);

        Flux.range(2020, 5).subscribe(System.out::println);

        Flux.interval(Duration.ofSeconds(2), Duration.ofMillis(200)).subscribe(System.out::println);

        Flux.empty().subscribe(System.out::println);


        Flux.generate(synchronousSink -> {
            synchronousSink.next("janson");
            synchronousSink.complete();
        }).subscribe(System.out::println);

        Flux.generate(() -> 1, (i, sink) -> {
            sink.next(i);
            if (i == 5) {
                sink.complete();
            }
            return ++i;
        }).subscribe(System.out::println);


        Mono.justOrEmpty(Optional.of("janson")).subscribe(System.out::println);

        Mono.create(monoSink ->
                monoSink.success("janson1")).subscribe(System.out::println);

        Mono.just("janson2")
                .concatWith(Mono.error(new IllegalStateException()))
                .subscribe(System.out::println,System.err::println);

        Mono.just("janson3")
                .concatWith(Mono.error(new IllegalStateException()))
                .onErrorReturn("default")
                .subscribe(System.out::println);

        Flux.just("xiaoyunzi1","xiaoyunzi2","xiaoyunzi3")
                .subscribe(data ->System.out.println("onNext:"+data),err ->{},()->System.out.println("onComplete"));
    }
}

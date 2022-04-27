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
                .subscribe(System.out::println, System.err::println);

        Mono.just("janson3")
                .concatWith(Mono.error(new IllegalStateException()))
                .onErrorReturn("default")
                .subscribe(System.out::println);

        Flux.just("xiaoyunzi1", "xiaoyunzi2", "xiaoyunzi3")
                .subscribe(data -> System.out.println("onNext:" + data), err -> {
                }, () -> System.out.println("onComplete"));

        Flux.range(1, 25).buffer(10).subscribe(System.out::println);

        Flux.range(1, 5).window(2).toIterable().forEach(w -> {
            w.subscribe(System.out::println);
            System.out.println("-------------");
        });

        Flux.just(1, 2).map(i -> "number-" + i).subscribe(System.out::println);

        Flux.just(1, 5).flatMap(x -> Mono.just(x * x)).subscribe(System.out::println);

        Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);

        System.out.println("++++++++++++");

        Flux.range(1, 100).take(10).subscribe(System.out::println);
        Flux.range(1, 100).takeLast(10).subscribe(System.out::println);

        Flux.just(1, 2, 3)
                .then()
                .subscribe(System.out::println);

        Flux.just(1, 2, 3)
                .thenMany(Flux.just(4, 5))
                .subscribe(System.out::println);


//        Flux.merge(Flux.interval(Duration.ofSeconds(0), Duration.ofSeconds(100)).take(2), Flux.interval(Duration.ofSeconds(50), Duration.ofSeconds(100)).take(2)).toStream()
//                .forEach(System.out::println);

        System.out.println("AAAAAAAA");

//        Flux.mergeSequential(Flux.interval(Duration.ofSeconds(0), Duration.ofSeconds(100)).take(2), Flux.interval(Duration.ofSeconds(50), Duration.ofSeconds(100)).take(2)).toStream()
//                .forEach(System.out::println);

        Flux flux1 =Flux.just(1,2);
        Flux flux2 =Flux.just(3,4);
        Flux.zip(flux1,flux2).subscribe(System.out::println);


        Flux.just(1,2).zipWith(Flux.just(3,4)).subscribe(System.out::println);

        Flux.just(1,2).zipWith(Flux.just(3,4),(s1,s2)->String.format("%s+%s=%s",s1,s2,s1+s2)).subscribe(System.out::println);


    }
}

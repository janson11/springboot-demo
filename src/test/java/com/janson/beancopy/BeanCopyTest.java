package com.janson.beancopy;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

/**
 * @Description: https://www.jianshu.com/p/20a1c04e9fde
 * @Author: Janson
 * @Date: 2023/3/9 8:36
 **/
@State(Scope.Benchmark)
@BenchmarkMode(value = Mode.Throughput)
@Warmup(iterations = 1)
@Measurement(iterations = 3,time = 5,timeUnit = TimeUnit.SECONDS)
@Threads(3)
@Fork(3)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BeanCopyTest {
}

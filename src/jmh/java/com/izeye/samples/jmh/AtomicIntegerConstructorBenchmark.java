package com.izeye.samples.jmh;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

/**
 * Benchmark for {@link AtomicInteger} constructors with or without an initial value.
 *
 * Copied from https://github.com/spring-projects/spring-framework/pull/25846.
 *
 * @author Johnny Lim
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class AtomicIntegerConstructorBenchmark {

	@Benchmark
	public Object withoutInitialValue() {
		return new AtomicInteger();
	}

	@Benchmark
	public Object withInitialValue() {
		return new AtomicInteger(0);
	}

}

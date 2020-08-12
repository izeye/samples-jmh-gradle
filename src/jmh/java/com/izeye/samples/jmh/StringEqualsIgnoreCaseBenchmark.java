package com.izeye.samples.jmh;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

/**
 * Benchmark for {@link String#equalsIgnoreCase(String)}.
 *
 * Copied from https://medium.com/javarevisited/micro-optimizations-in-java-string-equalsignorecase-ea25dfb03f95
 *
 * @author Johnny Lim
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class StringEqualsIgnoreCaseBenchmark {

	@Param({ "HELLO WORLD", "Hello World", "hello world", "hello there", "hello there!" })
	String string;

	@Benchmark
	public boolean equalsToLowerCase() {
		return "hello world".equals(string.toLowerCase());
	}

	@Benchmark
	public boolean equalsIgnoreCase() {
		return "hello world".equalsIgnoreCase(string);
	}

	@Benchmark
	public boolean equals() {
		return "hello world".equals(string);
	}

}

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
 * Benchmark for {@link String#isEmpty()}.
 *
 * Copied from https://medium.com/javarevisited/micro-optimizations-in-java-string-equals-22be19fd8416
 *
 * @author Johnny Lim
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class EmptyStringEqualsBenchmark {

	@Param({ "", "nonEmptyString" })
	private String string;

	@Benchmark
	public boolean nonNullAndIsEmpty() {
		return string != null && string.isEmpty();
	}

	@Benchmark
	public boolean emptyStringEquals() {
		return "".equals(string);
	}

	@Benchmark
	public boolean equalsEmptyString() {
		return string != null && string.equals("");
	}

}

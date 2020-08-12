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
 * Benchmark for {@link String#equals(Object)} with {@code String} with a single character.
 *
 * Copied from https://medium.com/javarevisited/micro-optimizations-in-java-string-equals-22be19fd8416
 *
 * @author Johnny Lim
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class SingleCharStringEqualsBenchmark {

	@Param({ "/", "/my/path" })
	private String path;

	@Benchmark
	public boolean equalsSingleCharString() {
		return path != null && path.equals("/");
	}

	@Benchmark
	public boolean singleCharStringEquals() {
		return "/".equals(path);
	}

	@Benchmark
	public boolean equalsOptimized() {
		return path != null && path.length() == 1 && path.charAt(0) == '/';
	}

}

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
 * Benchmark for {@link String#regionMatches(boolean, int, String, int, int)}.
 *
 * Copied from https://medium.com/javarevisited/micro-optimizations-in-java-string-equalsignorecase-ea25dfb03f95
 *
 * @author Johnny Lim
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class StringRegionMatchesBenchmark {

	@Param({ "http://facebook.com/loginMe", "HTTP://facebook.com/loginMe", "not url at all" })
	String url;

	@Benchmark
	public boolean regionMatches() {
		return url.regionMatches(true, 0, "http", 0, "http".length());
	}

	@Benchmark
	public boolean toLowerCaseStartsWith() {
		return url.toLowerCase().startsWith("http");
	}

	@Benchmark
	public boolean substringEqualsIgnoreCase() {
		return url.substring(0, 4).equalsIgnoreCase("http");
	}

}

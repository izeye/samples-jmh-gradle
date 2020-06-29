package com.izeye.samples.jmh;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

/**
 * Benchmark for {@code volatile} fields with implicit and explicit default values.
 *
 * Copied from https://github.com/spring-projects/spring-framework/pull/25261.
 *
 * @author Johnny Lim
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class VolatileFieldDefaultBenchmark {

	@Benchmark
	public Object explicitDefaultValue() {
		return new ExplicitDefaultValueClass();
	}

	@Benchmark
	public Object implicitDefaultValue() {
		return new ImplicitDefaultValueClass();
	}

	@Benchmark
	public Object volatileExplicitDefaultValue() {
		return new VolatileExplicitDefaultValueClass();
	}

	@Benchmark
	public Object volatileImplicitDefaultValue() {
		return new VolatileImplicitDefaultValueClass();
	}

	private static class ExplicitDefaultValueClass {

		private boolean field = false;

	}

	private static class ImplicitDefaultValueClass {

		private boolean field;

	}

	private static class VolatileExplicitDefaultValueClass {

		private volatile boolean field = false;

	}

	private static class VolatileImplicitDefaultValueClass {

		private volatile boolean field;

	}

}

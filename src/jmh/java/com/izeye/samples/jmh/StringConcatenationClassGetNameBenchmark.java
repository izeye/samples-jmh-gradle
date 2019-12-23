package com.izeye.samples.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

/**
 * Benchmark for {@code String} concatenation with {@link Class#getName()}.
 *
 * See https://stackoverflow.com/questions/59157085/java-8-class-getname-slows-down-string-concatenation-chain
 *
 * @author Johnny Lim
 */
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class StringConcatenationClassGetNameBenchmark {

	@Benchmark
	public String slow(Data data) {
		return "class " + data.clazz.getName();
	}

	@Benchmark
	public String fast(Data data) {
		String className = data.clazz.getName();
		return "class " + className;
	}

	@State(Scope.Thread)
	public static class Data {

		final Class<? extends Data> clazz = getClass();

		@Setup
		public void setUp() {
			this.clazz.getName();
		}

	}

}

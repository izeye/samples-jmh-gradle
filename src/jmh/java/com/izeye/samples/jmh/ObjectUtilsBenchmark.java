package com.izeye.samples.jmh;

import java.util.Arrays;
import java.util.List;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import org.springframework.util.ObjectUtils;

/**
 * Benchmark for {@link ObjectUtils}.
 *
 * Copied from https://github.com/spring-projects/spring-framework/pull/1076.
 *
 * @author Johnny Lim
 */
@BenchmarkMode(Mode.Throughput)
@State(Scope.Thread)
public class ObjectUtilsBenchmark {

	@State(Scope.Thread)
	public static class TestState {
		public String[] firstStringArray = new String[] { "a", "b", "c" };
		public String[] secondStringArray = new String[] { "a", "b", "c" };
		public List<String> firstStringCollection = Arrays.asList("a", "b", "c");
		public List<String> secondStringCollection = Arrays.asList("a", "b", "c");
	}

	@Benchmark
	public boolean testStringArray(TestState state) {
		return ObjectUtils.nullSafeEquals(
				state.firstStringArray, state.secondStringArray);
	}

	@Benchmark
	public boolean testStringCollection(TestState state) {
		return ObjectUtils.nullSafeEquals(
				state.firstStringCollection, state.secondStringCollection);
	}

}

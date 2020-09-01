package com.izeye.samples.jmh;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

/**
 * Benchmark for {@link ArrayList} and {@link LinkedList}.
 *
 * Copied from https://github.com/spring-projects/spring-framework/issues/25650
 *
 * @author Johnny Lim
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class ListBenchmark {

	@Param({ "0", "1", "2", "3" })
	int numberOfElements;

	@Benchmark
	public List<Integer> arrayList() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < this.numberOfElements; i++) {
			list.add(i);
		}
		return list;
	}

	@Benchmark
	public List<Integer> linkedList() {
		List<Integer> list = new LinkedList<>();
		for (int i = 0; i < this.numberOfElements; i++) {
			list.add(i);
		}
		return list;
	}

}

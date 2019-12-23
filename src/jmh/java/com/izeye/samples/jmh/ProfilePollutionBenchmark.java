package com.izeye.samples.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

/**
 * Benchmark for profile pollution.
 *
 * See https://stackoverflow.com/questions/59157085/java-8-class-getname-slows-down-string-concatenation-chain
 *
 * @author Johnny Lim
 */
@State(Scope.Benchmark)
public class ProfilePollutionBenchmark {

	private final MyClass clazz = new MyClass();

	static class MyClass {
		private String name;

		public String getName() {
			if (this.name == null) {
				this.name = "ZZZ";
			}
			return this.name;
		}
	}

	@Param({ "1", "100", "400", "1000" })
	private int pollutionCalls;

	@Setup
	public void setUp() {
		for (int i = 0; i < this.pollutionCalls; i++) {
			new MyClass().getName();
		}
	}

	@Benchmark
	public String slow() {
		return "str " + this.clazz.getName();
	}

	@Benchmark
	public String fast() {
		String className = this.clazz.getName();
		return "str " + className;
	}

}

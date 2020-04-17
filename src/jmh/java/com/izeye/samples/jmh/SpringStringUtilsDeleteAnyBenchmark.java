package com.izeye.samples.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

/**
 * Benchmark for Spring Framework {@code StringUtils.deleteAny()}.
 *
 * Copied from https://github.com/spring-projects/spring-framework/pull/24870.
 *
 * @author Johnny Lim
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = { "-Xms2g", "-Xmx2g", "-XX:+UseParallelGC" })
public class SpringStringUtilsDeleteAnyBenchmark {

	@Benchmark
	public String original() {
		return deleteAny("key1=value1 ", "\"");
	}

	@Benchmark
	public String patched() {
		return deleteAnyPatched("key1=value1 ", "\"");
	}

	private static String deleteAny(String inString, String charsToDelete) {
		StringBuilder sb = new StringBuilder(inString.length());
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (charsToDelete.indexOf(c) == -1) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	private static String deleteAnyPatched(String inString, String charsToDelete) {
		int lastCharIndex = 0;
		char[] result = new char[inString.length()];
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (charsToDelete.indexOf(c) == -1) {
				result[lastCharIndex++] = c;
			}
		}
		return new String(result, 0, lastCharIndex);
	}

}

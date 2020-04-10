package com.izeye.samples.jmh;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

/**
 * Benchmark for {@link ByteArrayOutputStream}.
 *
 * Copied from https://github.com/spring-projects/spring-framework/pull/24805.
 *
 * @author Johnny Lim
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = { "-Xms2g", "-Xmx2g", "-XX:+UseParallelGC" })
public class ByteArrayOutputStreamBenchmark {

	@Benchmark
	public String toString(Data data) throws UnsupportedEncodingException {
		return data.baos.toString(data.charset.name());
	}

	@Benchmark
	public String newString(Data data) {
		return new String(data.baos.toByteArray(), data.charset);
	}

	@Benchmark
	public String toStringWithoutCharset(Data data) {
		return data.baos.toString();
	}

	@Benchmark
	public String newStringWithoutCharset(Data data) {
		return new String(data.baos.toByteArray());
	}

	@State(Scope.Thread)
	public static class Data {

		@Param({ "0", "10", "100", "1000" })
		private int length;

		private final Charset charset = Charset.defaultCharset();

		private ByteArrayOutputStream baos;

		@Setup
		public void setUp() throws IOException {
			byte[] bytes = repeat('a', length).getBytes();
			baos = new ByteArrayOutputStream(length);
			baos.write(bytes);
		}

		private String repeat(char c, int length) {
			StringBuilder sb = new StringBuilder(length);
			for (int i = 0; i < length; i++) {
				sb.append(c);
			}
			return sb.toString();
		}

	}

}

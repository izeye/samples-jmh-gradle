package com.izeye.samples.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

/**
 * Benchmark for {@code AntPathRequestMatcher.getRequestPath()} from Spring Security.
 *
 * See https://github.com/spring-projects/spring-security/pull/5473
 *
 * @author Johnny Lim
 */
@BenchmarkMode(Mode.Throughput)
@State(Scope.Thread)
public class AntPathRequestMatcherBenchmark {

	@State(Scope.Thread)
	public static class HttpServletRequest {

		public String getServletPath() {
			return "";
		}

		public String getPathInfo() {
			return "test";
		}

	}

	@Benchmark
	public String testOld(HttpServletRequest request) {
		return getRequestPathOld(request);
	}

	@Benchmark
	public String testNew(HttpServletRequest request) {
		return getRequestPathNew(request);
	}

	private String getRequestPathOld(HttpServletRequest request) {
		String url = request.getServletPath();

		if (request.getPathInfo() != null) {
			url += request.getPathInfo();
		}

		return url;
	}

	private String getRequestPathNew(HttpServletRequest request) {
		String url = request.getServletPath();

		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			url = !url.isEmpty() ? url + pathInfo : pathInfo;
		}

		return url;
	}

}

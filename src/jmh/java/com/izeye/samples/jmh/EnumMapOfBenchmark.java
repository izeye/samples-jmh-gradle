package com.izeye.samples.jmh;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Benchmark for {@link EnumMap#get(Object)}.
 *
 * @author Johnny Lim
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class EnumMapOfBenchmark {

    @State(Scope.Thread)
    public static class TestState {

        private static final Map<ISO, String> ISO_PATTERNS_OLD;

        static {
            Map<ISO, String> formats = new EnumMap<>(ISO.class);
            formats.put(ISO.DATE, "yyyy-MM-dd");
            formats.put(ISO.TIME, "HH:mm:ss.SSSXXX");
            formats.put(ISO.DATE_TIME, "yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            ISO_PATTERNS_OLD = Collections.unmodifiableMap(formats);
        }

        private static final Map<ISO, String> ISO_PATTERNS_NEW = Map.of(
                ISO.DATE, "yyyy-MM-dd",
                ISO.TIME, "HH:mm:ss.SSSXXX",
                ISO.DATE_TIME, "yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    }

    @Param({ "DATE", "TIME", "DATE_TIME" })
    ISO iso;

    @Benchmark
    public String getOld() {
        return TestState.ISO_PATTERNS_OLD.get(this.iso);
    }

    @Benchmark
    public String getNew() {
        return TestState.ISO_PATTERNS_NEW.get(this.iso);
    }

    /**
     * Common ISO date time format patterns.
     */
    public enum ISO {

        /**
         * The most common ISO Date Format {@code yyyy-MM-dd} &mdash; for example,
         * "2000-10-31".
         */
        DATE,

        /**
         * The most common ISO Time Format {@code HH:mm:ss.SSSXXX} &mdash; for example,
         * "01:30:00.000-05:00".
         */
        TIME,

        /**
         * The most common ISO Date Time Format {@code yyyy-MM-dd'T'HH:mm:ss.SSSXXX}
         * &mdash; for example, "2000-10-31T01:30:00.000-05:00".
         */
        DATE_TIME,

        /**
         * Indicates that no ISO-based format pattern should be applied.
         */
        NONE
    }

}

package benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@Warmup(iterations = 1)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Measurement(iterations = 10, batchSize = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class MyBenchmark {
    private static final long INPUT_VALUE = 33761L;

    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    public void process() {
        StringBuilder key = new StringBuilder("Vladislav");
        long hash;
        long salt = 0;
        long start = System.currentTimeMillis();
        do {
            key.insert(0, salt++);
            hash = (key.toString()).hashCode(); // hash - 32 bits
        } while (Math.abs(hash) >= INPUT_VALUE);

        long finish = System.currentTimeMillis();
        long total = finish - start;
        System.out.println("Current time millis: " + total);
    }
}

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SleepyTimeThread {
  public static void main(String... args) {
    ExecutorService threads = Executors.newCachedThreadPool();
    List<Future<Long>> sleepers = IntStream.range(0, 1_000_000)
        .mapToObj(i -> threads.submit(() ->
            {
              long time = System.nanoTime();
              try {
                Thread.sleep(1000);
              } finally {
                return System.nanoTime() - time;
              }
            })).collect(Collectors.toList());
    System.out.println(sleepers.stream()
        .mapToLong(future -> {
          try {
            return future.get();
          } catch (InterruptedException | ExecutionException e) {
            throw new AssertionError();
          }
        }).summaryStatistics());
  }
}

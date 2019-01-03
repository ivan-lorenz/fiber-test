import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SleepyTimeFiberParkNano {
  public static void main(String... args) {
    List<Fiber<Long>> sleepers = IntStream.range(0, 10_000)
        .mapToObj(i -> Fiber.schedule(() ->
            {
              long time = System.nanoTime();
              try {
                LockSupport.parkNanos(1_000_000_000);
              } finally {
                return System.nanoTime() - time;
              }
            })).collect(Collectors.toList());
    System.out.println(sleepers.stream()
        .mapToLong(fiber -> {
          try {
            return fiber.join(Duration.ofSeconds(5));
          } catch (TimeoutException e) {
            throw new AssertionError(e);
          }
        })
        .summaryStatistics());
  }
}

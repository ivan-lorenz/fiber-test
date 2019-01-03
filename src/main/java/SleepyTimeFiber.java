import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SleepyTimeFiber {
  public static void main(String... args) {
    List<Fiber<Long>> sleepers = IntStream.range(0, 1_000_000)
        .mapToObj(i -> Fiber.schedule(() ->
            {
              long time = System.nanoTime();
              try {
                Thread.sleep(1000);
              } finally {
                return System.nanoTime() - time;
              }
            })).collect(Collectors.toList());
    System.out.println(sleepers.stream().mapToLong(Fiber::join).summaryStatistics());
  }
}

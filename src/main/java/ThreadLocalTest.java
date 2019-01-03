import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThreadLocalTest {
  private static ThreadLocal<DateFormat> tl = ThreadLocal.withInitial(() -> {
      return new SimpleDateFormat("yyyy-MM-dd");
  });

  public static void main(String... args) {
    List<Fiber<?>> fibers = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      fibers.add(Fiber.schedule(ThreadLocalTest::format));
    }
    fibers.forEach(Fiber::join);
  }

  private static void format() {
    for (int i = 0; i < 3; i++) {
      try {
        System.out.println((tl.get().parse("2018-12-01") + " " + System.identityHashCode(Thread.currentThread())));
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
  }

}

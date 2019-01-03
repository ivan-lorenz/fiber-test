public class ClassOfThread {
  public static void main(String[] args) {
    Fiber.schedule(() -> System.out.println(Thread.currentThread().getClass()));
  }
}

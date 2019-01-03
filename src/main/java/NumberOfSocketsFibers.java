import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NumberOfSocketsFibers extends TransmogrifierBase {
  public static void main(String[] args) throws IOException {
    ServerSocket ss = new ServerSocket(8080);
    while (true) {
      Socket s = ss.accept();
      System.out.println("Connected to " + s);
      Fiber.schedule(() -> readWrite(s));
    }
  }
}

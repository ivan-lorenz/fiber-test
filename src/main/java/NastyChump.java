import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NastyChump {
  public static void main(String... args) throws IOException, InterruptedException {
//    long time = System.nanoTime();
//    try {
      Socket[] sockets = new Socket[10_000];
      OutputStream[] outs = new OutputStream[sockets.length];
      InputStream[] ins = new InputStream[sockets.length];
      for (int i = 0; i < sockets.length; i++) {
        System.out.println("i = " + i);
        sockets[i] = new Socket("localhost",8080);
        outs[i] = sockets[i].getOutputStream();
        ins[i] = sockets[i].getInputStream();
      }
      Thread.sleep(100_000);
//      System.out.println("Created all sockets and opened streams.");
//      for (int i = 0; i < 100; i++) {
//        for (OutputStream out : outs) {
//          out.write('A');
//        }
//        for (InputStream in: ins) {
//          int b = in.read();
//          if (b!= 'a') throw new AssertionError("Input was '" + (char) b + "'");
//        }
//        System.out.println("i = " + i);
//      }
//
//      System.out.println("Finishing writing to " + sockets.length + " sockets");
//      for (Socket socket: sockets) {
//        socket.close();
//      }
//    } finally {
//      time = System.nanoTime() - time;
//      System.out.printf("time = %dms%n", (time / 1_000_000));
//    }
  }
}

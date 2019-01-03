import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TransmogrifierBase {
  protected static void readWrite(Socket s) {
    try {
      InputStream in = s.getInputStream();
      OutputStream out = s.getOutputStream();
      int i;
      while ((i = in.read()) != -1) {
        out.write(Character.isLetter(i) ? i ^ ' ' : 1);
      }
      System.out.println("Finishing reading: " + s);
      s.close();
    } catch (IOException e) {
      System.out.println("Disconnecting from " + s);
    }
  }
}

package project;

import java.io.FileWriter;
import java.io.IOException;

public class Save {

  public Save() {
    //null
  }

  public void saveGame() {
        try {
          FileWriter writer = new FileWriter("adventures/adv_1/saves/save.txt", true);
          writer.write("This is a test \r\nLine 2 test");
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
}

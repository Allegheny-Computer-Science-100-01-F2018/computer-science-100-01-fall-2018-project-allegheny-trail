package project;

import java.io.FileWriter;
import java.io.IOException;

public class Save {
  private String g = "";

  public Save(Scene s) {
  //  g = gIn;
  }

  public void saveGame() {
        try {
          FileWriter writer = new FileWriter("adventures/adv_1/saves/save.txt", true);
          //writer.write(g.sceneId);
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
}

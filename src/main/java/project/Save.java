package project;

import java.io.FileWriter;
import java.io.IOException;

public class Save {
  private Scene sceneId;

  /** Constructor for Save class.
  */
  public Save(Scene sceneIn) {
    //  g = gIn;
    sceneId = sceneIn;
  }

  /** Method to save the game.
  * It just writes text to a file.
  */
  public void saveGame() {
    try {
      FileWriter writer = new FileWriter("adventures/adv_1/saves/save.txt", true);
      //writer.write(g.sceneId);
      writer.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}

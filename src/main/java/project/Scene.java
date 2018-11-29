package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Scene {
  private String sceneId;
  private String[][][] commands = new String[20][20][3];
  private String sceneText;
  private int keyNum = 0;
  private int keyIndex = 0;

  public Scene() {
    //null
  }

  public void readScene(String inputSceneId) {
    sceneId = inputSceneId;

    System.out.println("DEBUG: Reading in Scene: " + sceneId + "scene.txt");

    Scanner scanner = null;
    try {
      File sceneFile = new File("adventures/adv_1/story_files/" + sceneId + "scene.txt");
      scanner = new Scanner(sceneFile);
    } catch (FileNotFoundException noFile) {
      System.out.println("ERROR: No scene file found with id " + sceneId);
    }

    while (scanner.hasNextLine()) {
      String input;
      input = scanner.nextLine();

      System.out.println("DEBUG: Line Read = " + input);

      switch (input.substring(input.indexOf("["), input.indexOf("]") + 1)) {
        case ("[Scene_Text]"):
            sceneText = input.substring(input.indexOf("<") + 1, input.indexOf(">"));
            //System.out.println("DEBUG: Scene Text = " + sceneText);
            break;

        case ("[Key]"):
            if (commands[keyNum][0][0] == null) {
              commands[keyNum][0][0] = input.substring(input.indexOf("<") + 1, input.indexOf(">"));
              //System.out.println("DEBUG: Array Empty, inserting:" + input.substring(input.indexOf("<") + 1, input.indexOf(">")));
            } else {
              keyNum++;
              commands[keyNum][0][0] = input.substring(input.indexOf("<") + 1, input.indexOf(">"));
              keyIndex = 0;

              //System.out.println("DEBUG: Array not empty");
              //System.out.println("DEBUG: Inserting: " + input.substring(input.indexOf("<") + 1, input.indexOf(">")));
              //System.out.println("DEBUG: Keynum = " + keyNum);
              //System.out.println("DEBUG: KeyIndex Reset");
            }
            break;

        case ("[Obj]"):
            commands[keyNum][1 + keyIndex][0] = input.substring(input.indexOf("(") + 1, input.indexOf(")"));
            commands[keyNum][1 + keyIndex][1] = input.substring(input.indexOf("<") + 1, input.indexOf(">"));
            commands[keyNum][1 + keyIndex][2] = input.substring(input.indexOf("{") + 1, input.indexOf("}"));
            keyIndex++;
            break;

        default:
          //None
          break;
      }
    }
  }

  public void printScene() {
    System.out.println("\n" + sceneText);
  }

  public boolean checkObject(String commandIn, String objectIn) {
    boolean commandAvailable = false;
    boolean objectAvailable = false;
    int checkCounter;
    int checkCounter2;
    while (commandAvailable == false && commands[checkCounter][0][0] != null) {
      if (commandIn == commands[checkCounter][0][0]) {
        commandAvailable = true;
      } else {
        checkCounter++;
      }
    }
    if (commandAvailable == false) {
      System.out.println("Command not recognized.");
    } else {
      while (objectAvailable == false && commands[checkCounter][checkCounter2][0] != null) {
        if (objectIn == commands[checkCounter][checkCounter2][0]) {
          objectAvailable = true;
        } else {
          checkCounter2++;
        }
        if (objectAvailable == false) {
          System.out.println("Object not recognized.");
        } else {
          return true;
        }
      }
    }
  }
}

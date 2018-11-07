package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Scene {
  private int sceneId;
  private String[][][] commands = commands[20][20][1];
  private String sceneText;
  private int keyNum = 0;
  private int keyIndex = 0;
  public void readScene(int inputSceneId) {
    sceneId = inputSceneId;

    System.out.println("DEBUG: Reading in Scene: " + sceneId

    Scanner scanner = null;
    try {
      File sceneFile = new File("Adventures/Adv_1/Story_Files/" + sceneId + "scene.txt");
      scanner = new Scanner(sceneFile);
    } catch (FileNotFoundException noFile) {
      System.out.println("ERROR: No scene file found with id " + sceneId);
    }

    while (scanner.nextLine() != "") {

      String input = scanner.nextLine();
      switch (input.substring(input.indexOf("["), input.indexOf("]") + 1)) {
        case ("[Scene_Text]"):
            sceneText = input.substring(input.indexOf("\"") + 1, input.indexOf("\""));
            break;
        case ("[Key]"):
            if (commands[keyNum][0][0] == "") {
              commands[keyNum][0][0] = input.substring(input.indexOf("\""), input.indexOf("\""));
            } else {
              keyNum++;
              commands[keyNum][0][0] = input.substring(input.indexOf("\""), input.indexOf("\""));
              keyIndex = 0;
            }
            break;
        case ("[Obj]"):
            commands[keyNum][1 + keyIndex][0] = input.substring(input.indexOf("("), input.indexOf(")"));
            commands[keyNum][1 + keyIndex][1] = input.substring(input.indexOf("\""), input.indexOf("\""));
            keyIndex++;
            break;

        }
    }

    for (int i = 0; i <= 1 ; i++) {
      for (int j = 0; j <= 2 ; j++) {
        for (int k = 0; j <= 1 ; j++) {
            System.out.print(commands[i][j][k] + " ");
          }
        }

      System.out.println(); 
    }

    }

  }
}

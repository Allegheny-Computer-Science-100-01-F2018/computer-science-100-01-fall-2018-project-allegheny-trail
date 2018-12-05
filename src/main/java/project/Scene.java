package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;


public class Scene {
  private String sceneId;
  private String[][][] commands = new String[20][20][3];
  private String sceneText;
  private int keyNum = 0;
  private int keyIndex = 0;
  private Input inputMain;
  private boolean debug = true;

  public Scene(Input inputObj) {
    inputMain = inputObj;
  }

  public void readScene(String inputSceneId) {
    sceneId = inputSceneId;
    //String[][][] commands = new String[20][20][3];

    if (debug) {
      System.out.println("DEBUG: Reading in Scene: " + sceneId + "scene.txt");
    }

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

      if (debug) {
        System.out.println("DEBUG: Line Read = " + input);
      }

      switch (input.substring(input.indexOf("["), input.indexOf("]") + 1)) {
        case ("[Scene_Text]"):
        sceneText = input.substring(input.indexOf("<") + 1, input.indexOf(">"));

        if (debug) {
          System.out.println("DEBUG: Scene Text = " + sceneText);
        }
        break;

        case ("[Key]"):
        if (commands[keyNum][0][0] == null) {
          commands[keyNum][0][0] = input.substring(input.indexOf("<") + 1, input.indexOf(">"));
          if (debug) {
            System.out.println("DEBUG: Array Empty, inserting:" + input.substring(input.indexOf("<") + 1, input.indexOf(">")));
          }
        } else {
          keyNum++;
          commands[keyNum][0][0] = input.substring(input.indexOf("<") + 1, input.indexOf(">"));
          keyIndex = 0;

          if (debug) {
            System.out.println("DEBUG: Array not empty");
            System.out.println("DEBUG: Inserting: " + input.substring(input.indexOf("<") + 1, input.indexOf(">")));
            System.out.println("DEBUG: Keynum = " + keyNum);
            System.out.println("DEBUG: KeyIndex Reset");
          }
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


    for(int i = 0; i < commands.length; i++) {
      System.out.println("commands[" + i + "][0][0] = " + commands[i][0][0]);
    }
  }

  public void printScene() {
    clearScreen();
    System.out.println("[   Current Scene: " + sceneId + "   |   Allegheny Trail   |   Type \"EXIT\" at any time to end the game.   ]");
    System.out.println("\n" + sceneText);

    String commandsList = "";
    for(int i = 0; i < commands.length; i++) {
      //System.out.println("ARRAY " + commands[i][0][0]);
      if (commands[i][0][0] != null && !commands[i][0][0].equals("")) {
        commandsList = commandsList + "[" + commands[i][0][0] + "]   ";
      } else {
        break;
      }
    }

    System.out.println("\n[   Available Commands:   " + commandsList + "]");
  }

  public boolean checkCommand(String commandIn, String objectIn) {
    boolean commandAvailable = false;
    boolean objectAvailable = false;
    boolean status = false;
    int checkCounter = 0;
    int checkCounter2 = 0;

    System.out.println("checkCommand(" + commandIn + ", " + objectIn + ");");

    while (!commandAvailable && commands[checkCounter][0][0] != null) {
      if (commandIn.contains(commands[checkCounter][0][0])) {
        commandAvailable = true;
      } else {
        checkCounter++;
      }
    }

    if (!commandAvailable) {
      if (debug) {
        System.out.println("Command not recognized.");
      }
    } else {
      while (!objectAvailable && commands[checkCounter][checkCounter2][0] != null) {
        if (objectIn.contains(commands[checkCounter][checkCounter2][0])) {
          objectAvailable = true;
        } else {
          checkCounter2++;
        }
      }

      if (!objectAvailable) {
        if (debug) {
          System.out.println("Object not recognized.");
        }
      } else {
        status = true;
      }
    }
    return status;
  }

  public void runLogic(String commandIn) {
    int checkCounter = 0;
    int checkCounter2 = 0;
    String key = commandIn.substring(commandIn.indexOf("[") + 1, commandIn.indexOf("]"));
    String obj = commandIn.substring(commandIn.indexOf("<") + 1, commandIn.indexOf(">"));

    while (commands[checkCounter][0][0] != null && checkCommand(key, obj) != false) {
      if (key.contains(commands[checkCounter][0][0])) {
        while(commands[checkCounter][checkCounter2][0] != null && !obj.contains(commands[checkCounter][checkCounter2][0])) {
          checkCounter2++;
        }
        //Print Text
        System.out.println(commands[checkCounter][checkCounter2][1]);
        askContinue();

        String c = commands[checkCounter][checkCounter2][2];

        if (c != null && !c.equals("")) {
          switch(c.substring(0, c.indexOf("["))) {
            case "goto":
                sceneId = c.substring(c.indexOf("[") + 1, c.indexOf("]"));
                clearAll();
                readScene(sceneId);
                clearScreen();
                //printScene();
                break;
            case "rand":
                int optionNum = c.length() - c.replace("[", "").length();
                String[] data = new String[optionNum];
                Random rand = new Random();
                int randomNum = rand.nextInt(optionNum);

                //rand[03][04][05]
                for(int i = 0; i < optionNum; i++) {
                  data[i] = c.substring(c.indexOf("[") + 1, c.indexOf("]"));
                  c = c.substring(c.indexOf("]") + 1, c.length());
                }

                sceneId = data[randomNum];
                clearAll();
                readScene(sceneId);
                clearScreen();
                //printScene();
                break;
          }

          break;
        }
      } else {
        checkCounter++;
      }

      if (checkCommand(key, obj) == false) {
        System.out.println("I didn't catch that, could you rephrase that?");
      }
    }
  }

  public void askContinue() {
    System.out.println("Press ENTER to continue.");
    inputMain.getInput();
  }

  public void clearAll() {
    commands = new String[20][20][3];
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}

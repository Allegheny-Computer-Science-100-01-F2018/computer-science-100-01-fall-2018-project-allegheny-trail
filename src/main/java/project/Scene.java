package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


public class Scene {
  private String sceneId;
  private String[][][] commands = new String[20][20][3];
  private String sceneText;
  private int keyNum = 0;
  private int keyIndex = 0;
  private Input inputMain;
  private boolean debug = false;

  /** Constructor Method for Scene.
  */
  public Scene(Input inputObj) {
    inputMain = inputObj;
  }

  /** Method to read the scene file into the program.
  */
  public void readScene(String inputSceneId) {
    sceneId = inputSceneId;
    //String[][][] commands = new String[20][20][3];
    keyNum = 0;
    keyIndex = 0;

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

      //if (debug) {
      System.out.println("DEBUG: Line Read = " + input);
      //}
      if (!input.equals("")) {
        switch (input.substring(input.indexOf("["), input.indexOf("]") + 1)) {
          case ("[Scene_Text]"):
            sceneText = input.substring(input.indexOf("<") + 1, input.indexOf(">"));

            if (debug) {
              System.out.println("DEBUG: Scene Text = " + sceneText);
            }
            break;
          case ("[Key]"):
            if (commands[keyNum][0][0] == null) {
              commands[keyNum][0][0] = input.substring(input.indexOf("<")
                  + 1, input.indexOf(">"));
              if (debug) {
                System.out.println("DEBUG: Array Empty, inserting:"
                    + input.substring(input.indexOf("<") + 1, input.indexOf(">")));
              }
            } else {
              keyNum++;
              commands[keyNum][0][0] = input.substring(input.indexOf("<")
                  + 1, input.indexOf(">"));
              keyIndex = 0;

              if (debug) {
                System.out.println("DEBUG: Array not empty");
                System.out.println("DEBUG: Inserting: "
                    + input.substring(input.indexOf("<") + 1, input.indexOf(">")));
                System.out.println("DEBUG: Keynum = " + keyNum);
                System.out.println("DEBUG: KeyIndex Reset");
              }
            }
            break;
          case ("[Obj]"):
            commands[keyNum][1 + keyIndex][0] = input.substring(input.indexOf("(")
                + 1, input.indexOf(")"));
            commands[keyNum][1 + keyIndex][1] = input.substring(input.indexOf("<")
                + 1, input.indexOf(">"));
            commands[keyNum][1 + keyIndex][2] = input.substring(input.indexOf("{")
                + 1, input.indexOf("}"));

            if (debug) {
              System.out.println("keyIndex = 1 + " + keyIndex);
              System.out.println("obj 0: "
                  + input.substring(input.indexOf("(") + 1, input.indexOf(")")));
              System.out.println("obj 1: "
                  + input.substring(input.indexOf("<") + 1, input.indexOf(">")));
              System.out.println("obj 2: "
                  + input.substring(input.indexOf("{") + 1, input.indexOf("}")));
            }

            keyIndex++;
            break;

          default:
            //None
            break;
        }
      }
    }

    if (debug) {
      for (int i = 0; i < commands.length; i++) {
        //System.out.println("commands[" + i + "][0][0] = " + commands[i][0][0]);

        for (int z = 0; z < commands[1].length; z++) {
          System.out.println("commands[" + i + "][" + z + "][0] = " + commands[i][z][0]);
        }
      }
    }
  }

  /** Method to display the scene to the user.
  */
  public void printScene() {
    clearScreen();
    System.out.println("[   Current Scene: " + sceneId
        + "   |   Allegheny Trail   |   Type \"EXIT\" at any time to end the game.   ]");

    //Printer
    System.out.println();

    Scanner scan = null;
    scan = new Scanner(sceneText);

    String printed = "";
    while (scan.hasNext()) {
      String txt = scan.next() + " ";

      if (txt == "\n  ") {
        System.out.println("\n");
        printed = printed;
      } else {
        printed = printed + txt;
      }

      System.out.print(txt);
      if (printed.length() > 100) {
        printed = "";
        System.out.println();
      }
    }

    System.out.println();

    String commandsList = "";
    for (int i = 0; i < commands.length; i++) {
      //System.out.println("ARRAY " + commands[i][0][0]);
      if (commands[i][0][0] != null && !commands[i][0][0].equals("")) {
        commandsList = commandsList + "[" + commands[i][0][0] + "]   ";
      }

      for (int z = 1; z < commands[1].length; z++) {
        if (commands[i][z][0] != null && !commands[i][z][0].equals("")) {
          commandsList = commandsList + "<" + commands[i][z][0] + ">   ";
        } else {
          break;
        }
      }
    }

    System.out.println("\n[   Available Commands:   " + commandsList + "]");
  }

  /** Method to check if the input command is a valid command.
  */
  public boolean checkCommand(String commandIn, String objectIn) {
    boolean commandAvailable = false;
    boolean objectAvailable = false;
    boolean status = false;
    int checkCounter = 0;
    int checkCounter2 = 0;
    commandIn = commandIn.toUpperCase();
    objectIn = objectIn.toUpperCase();

    //System.out.println("checkCommand(" + commandIn + ", " + objectIn + ");");

    while (!commandAvailable && commands[checkCounter][0][0] != null) {
      if (commandIn.contains(commands[checkCounter][0][0])) {
        commandAvailable = true;
        if (debug) {
          System.out.println("Command Valid");
        }
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
        if (debug) {
          System.out.println("Checking Object: " + commands[checkCounter][checkCounter2][0]);
          System.out.println("Checking Object2: " + commands[checkCounter][checkCounter2 + 1][0]);
        }

        if (objectIn.contains(commands[checkCounter][checkCounter2][0])) {
          objectAvailable = true;
        } else {
          //System.out.println("Object FAILED");
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

  /** Method to run commands and do what the scene file what it to do.
  * This is the main method that makes the whole system work. It's really
  * confusing to look at but it's still pretty cool. This is Danny's baby.
  * She is beautiful is she not? She's a mess but a beauty none the less.
  */
  public void runLogic(String commandIn) {
    int checkCounter = 0;
    int checkCounter2 = 0;
    String key = commandIn.substring(commandIn.indexOf("[") + 1, commandIn.indexOf("]"));
    String obj = commandIn.substring(commandIn.indexOf("<") + 1, commandIn.indexOf(">"));

    while (commands[checkCounter][0][0] != null && checkCommand(key, obj) != false) {
      if (key.contains(commands[checkCounter][0][0])) {
        while (commands[checkCounter][checkCounter2][0] != null
            && !obj.contains(commands[checkCounter][checkCounter2][0])) {
          checkCounter2++;
        }
        //Print Text
        System.out.println(commands[checkCounter][checkCounter2][1]);
        askContinue();

        String com = commands[checkCounter][checkCounter2][2];

        if (com != null && !com.equals("")) {
          switch (com.substring(0, com.indexOf("["))) {
            case "goto":
              sceneId = com.substring(com.indexOf("[") + 1, com.indexOf("]"));
              clearAll();
              readScene(sceneId);
              clearScreen();
              printScene();
              break;
            case "rand":
              int optionNum = com.length() - com.replace("[", "").length();
              String[] data = new String[optionNum];
              Random rand = new Random();
              int randomNum = rand.nextInt(optionNum);

              //rand[03][04][05]
              for (int i = 0; i < optionNum; i++) {
                data[i] = com.substring(com.indexOf("[") + 1, com.indexOf("]"));
                com = com.substring(com.indexOf("]") + 1, com.length());
              }

              sceneId = data[randomNum];
              clearAll();
              readScene(sceneId);
              clearScreen();
              printScene();
              break;

            case "end":
              System.out.println("Exiting...");
              try {
                Thread.sleep(1000);
              } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
              }

              System.exit(0);
              break;
            default:
              //null
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

    if (commandIn.equals("EXIT")) {
      System.out.println("Are you sure you want to quit? [Y]/[N]");
      String in = inputMain.getInput();

      switch (in) {
        case "Y":
        case "YES":
          clearScreen();
          System.out.println("Exiting...");
          try {
            Thread.sleep(1000);
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
          }
          break;
        case "N":
        case "NO":
          //null
          break;
        default:
          //null
          break;
      }
    }
  }

  /** Method asks for user to press enter to continue.
  */
  public void askContinue() {
    System.out.println("\nPress ENTER to continue.");
    inputMain.getInput();
  }

  /** Method to clear the commands array.
  */
  public void clearAll() {
    commands = new String[20][20][3];
  }

  /** Method to clear the screen.
  */
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}

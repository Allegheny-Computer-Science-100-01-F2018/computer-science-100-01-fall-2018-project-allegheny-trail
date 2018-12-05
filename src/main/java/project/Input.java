package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {
  private String[][] dict = new String[20][20];
  private int keyNum = 0;
  private int keyIndex = 0;
  private Scanner inputScan;

  //Dicionary
  private String[][] vars = new String[2][20];
  private int startNum = 0;



  public Input() {
    inputScan = new Scanner(System.in);
  }

  /** Method for getting user input.
  */
  public String getInput() {
    return inputScan.nextLine();
  }

  public void initDictionary() {

    System.out.println("DEBUG: Reading in Dictionary");

    Scanner scanner = null;
    try {
      File dictionaryFile = new File("adventures/adv_1/story_files/dictionary.txt");
      scanner = new Scanner(dictionaryFile);
    } catch (FileNotFoundException noFile) {
      System.out.println("ERROR: No dictionary file!");
    }

    while (scanner.hasNextLine()) {
      String input;
      input = scanner.nextLine();

      System.out.println("DEBUG: Line Read = " + input);

      switch (input.substring(input.indexOf("["), input.indexOf("]") + 1)) {
        case ("[Main]"):
        if (dict[keyNum][0] == null) {
          dict[keyNum][0] = input.substring(input.indexOf("<") + 1, input.indexOf(">"));
        } else {
          keyNum++;
          dict[keyNum][0] = input.substring(input.indexOf("<") + 1, input.indexOf(">"));
          keyIndex = 0;
        }
        break;

        case ("[Syn]"):
        dict[keyNum][1 + keyIndex] = input.substring(input.indexOf("(") + 1, input.indexOf(")"));
        keyIndex++;
        break;

        default:
        //None
        break;
      }
    }
  }

  public String parse(String input, Scene sceneIn) {
    Scanner scan = new Scanner(input.toUpperCase());
    Scene sc = sceneIn;
    String inputCommand = "";
    String inputObject = "";
    Boolean proceed = true;

    while (scan.hasNext() && proceed == true) {
      inputCommand = scan.next();
      inputObject = inputCommand;
      System.out.println("DEBUG: Testing for " + inputCommand);
      if (sc.checkCommand(inputCommand, inputObject) == true) {
        proceed = false;
      } else {
        inputCommand = checkDictionary(inputCommand);
        inputObject = inputCommand;
        if (sc.checkCommand(inputCommand, inputObject) == true) {
          proceed = false;
        }
      }
    }

    if (sc.checkCommand(inputCommand, inputObject) == false || inputCommand.equals("INVALID")) {
      System.out.println("I didn't catch that, can you rephrase that?");
    } else {
      inputObject = "";
      proceed = true;
      while (scan.hasNext() && proceed == true) {
        inputObject = scan.next();
        System.out.println("DEBUG: Testing for " + inputCommand + " and " + inputObject);
        if (sc.checkCommand(inputCommand, inputObject) == true && inputCommand.contains(inputObject) == false) {
          proceed = false;
        }
      }

      if (sc.checkCommand(inputCommand, inputObject) == true && inputCommand.contains(inputObject) == false) {
        System.out.println("\"" + input + "\"" + " Is a valid command");
      } else {
        System.out.println("\"" + input + "\"" + " Is not a valid command");
      }
    }

    if (inputCommand != "" && inputObject != "") {
      return ("[" + inputCommand + "]<" + inputObject + ">");
    } else {
      return null;
    }
  }

  public String checkDictionary(String inputCommand) {
    boolean proceed = false;
    boolean synFound = true;
    int checkCounter = 0;
    int checkCounter2 = 0;
    String checkResult = inputCommand;

    while (checkResult.contains(dict[checkCounter][checkCounter2]) == false && proceed == false) {
      if (dict[checkCounter][checkCounter2 + 1] != null) {
        checkCounter2++;
      } else {
        checkCounter2 = 0;
        if (dict[checkCounter + 1][checkCounter2 + 1] != null) {
          checkCounter++;
        } else {
          System.out.println("No synonyms found.");
          synFound = false;
          proceed = true;
        }
      }
    }
    if (synFound == true) {
      checkResult = dict[checkCounter][0];
    }
    return checkResult;
  }

  //Config Stuff
  public void readConfig() {
    System.out.println("DEBUG: Reading in Config");

    Scanner scanner = null;
    try {
      File configFile = new File("adventures/adv_1/config.txt");
      scanner = new Scanner(configFile);
    } catch (FileNotFoundException noFile) {
      System.out.println("ERROR: No config file found");
    }

    while (scanner.hasNextLine()) {
      String input;
      input = scanner.nextLine();

      System.out.println("DEBUG: Line Read = " + input);

      vars[0][startNum] = input.substring(input.indexOf("(") + 1, input.indexOf(")"));
      vars[1][startNum] = input.substring(input.indexOf("<") + 1, input.indexOf(">"));

      System.out.println(vars[0][startNum] + " | " + vars[1][startNum]);
      startNum++;
    }
  }

  public int getVar(String varInput) {
    int rVar = -1;
    for (int i = 0; i < vars[0].length; i++) {
      if (vars[0][i].equals(varInput)) {
        rVar = Integer.valueOf(vars[1][i]);
        break;
      }
    }
    return rVar;
  }
}

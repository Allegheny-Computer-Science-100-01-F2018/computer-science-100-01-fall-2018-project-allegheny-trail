package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dictionary {
  private String dictionaryId;
  private String[][] commands = new String[20][20];
  private int keyNum = 0;
  private int keyIndex = 0;

  public Dictionary() {
    //null
  }

  public void readDictionary(String inputDictionaryId) {
    dictionaryId = inputDictionaryId;

    System.out.println("DEBUG: Reading in Dictionary: " + dictionaryId + "scene.txt");

    Scanner scanner = null;
    try {
      File dictionaryFile = new File("adventures/adv_1/story_files/" + dictionaryId + "dictionary.txt");
      scanner = new Scanner(dictionaryFile);
    } catch (FileNotFoundException noFile) {
      System.out.println("ERROR: No scene file found with id " + dictionaryId);
    }

    while (scanner.hasNextLine()) {
      String input;
      input = scanner.nextLine();

      System.out.println("DEBUG: Line Read = " + input);

      switch (input.substring(input.indexOf("["), input.indexOf("]") + 1)) {
        case ("[Main]"):
            if (commands[keyNum][0] == null) {
              commands[keyNum][0] = input.substring(input.indexOf("<") + 1, input.indexOf(">"));
            } else {
              keyNum++;
              commands[keyNum][0] = input.substring(input.indexOf("<") + 1, input.indexOf(">"));
              keyIndex = 0;
            }
            break;

        case ("[Syn]"):
            commands[keyNum][1 + keyIndex] = input.substring(input.indexOf("(") + 1, input.indexOf(")"));
            keyIndex++;
            break;

        default:
          //None
          break;
      }
    }
  }
}

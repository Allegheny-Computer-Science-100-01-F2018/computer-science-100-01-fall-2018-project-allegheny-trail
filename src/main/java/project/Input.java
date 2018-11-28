package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {
  private String[][] dict = new String[20][20];
  private int keyNum = 0;
  private int keyIndex = 0;
  private Scanner inputScan;

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

  public String parse(String input) {
      Scanner scan = new Scanner(input);//.userDelimiter("\\s*");

      while (scan.hasNext()) {
        String in = scan.next();
        int key = 0;
        int count = 0;
        String command = "";

        while (dict[key][count] != null) {
          if (dict[key][count] == in) {
            command = command + dict[key][0];
          }

          if (dict[key][count+1] != null) {
            count++;
          } else if (dict[key+1][count] != null){
            count = 0;
            key++;
          } else {
            break;
          }

        }

      }
      return null;
  }
}

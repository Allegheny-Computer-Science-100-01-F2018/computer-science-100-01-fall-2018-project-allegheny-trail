package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Config {
  private String[][] vars = new String[1][20];
  private int startNum = 0;

  public Config() {
    //null
  }

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

      System.out.println(vars[startNum][startNum] + " | " + vars[startNum][startNum+1]);
      startNum++;
    }

    public int getVar(String varInput) {
      for (i = 0; i < var.length; i++) {
        if (var[0][i] == varInput) {
          return var[1][i].toInt();
          break;
        }
      }
    }
  }
}

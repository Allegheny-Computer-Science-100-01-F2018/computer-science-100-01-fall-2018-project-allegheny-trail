package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Config {
  private String[][] vars = new String[2][20];
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

      System.out.println(vars[0][startNum] + " | " + vars[1][startNum]);
      startNum++;
    }
  }

  public int getVar(String varInput) {
    int rVar = -1;
    //System.out.println("vars.length: " + vars[0].length);
    for (int i = 0; i < vars[0].length; i++) {
      //System.out.println("Loop #" + i);
      //System.out.println("Vars: " + vars[0][i]);
      if (vars[0][i].equals(varInput)) {
        rVar = Integer.valueOf(vars[1][i]);
        break;
      }
    }
    return rVar;
  }
}

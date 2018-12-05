package project;

import java.lang.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
  public static void main(String [] args) {
    boolean inputOk = false;
    String userInput = null;
    Input input = new Input();
    Scene sc = new Scene(input);
    boolean debug = false;
    boolean game = false;
    //Scanner scan = new Scanner(System.in);

    clearScreen(); //Flush Terminal

    while (inputOk == false) {
      System.out.println("\n");
      System.out.println("      _       _____     _____     ________    ______  ____  ____  ________  ____  _____  ____  ____ "
          + "\n     / \\     |_   _|   |_   _|   |_   __  | .' ___  ||_   ||   _||_   __  ||_   \\|_   _||_  _||_  _|"
          + "\n    / _ \\      | |       | |       | |_ \\_|/ .'   \\_|  | |__| |    | |_ \\_|  |   \\ | |    \\ \\  / / "
          + "\n   / ___ \\     | |   _   | |   _   |  _| _ | |   ____  |  __  |    |  _| _   | |\\ \\| |     \\ \\/ /   "
          + "\n _/ /   \\ \\_  _| |__/ | _| |__/ | _| |__/ |\\ `.___]  |_| |  | |_  _| |__/ | _| |_\\   |_    _|  |_   "
          + "\n|____| |____||________||________||________| `._____.'|____||____||________||_____|\\____|  |______|     "
          + "\n"
          + "\n _________  _______          _       _____  _____     "
          + "\n|  _   _  ||_   __ \\        / \\     |_   _||_   _|    "
          + "\n|_/ | | \\_|  | |__) |      / _ \\      | |    | |      "
          + "\n    | |      |  __ /      / ___ \\     | |    | |   _  "
          + "\n   _| |_    _| |  \\ \\_  _/ /   \\ \\_  _| |_  _| |__/ | "
          + "\n  |_____|  |____| |___||____| |____||_____||________| ");
      System.out.println("+-----------------------------------------------------+");
      System.out.println("|       Allegheny Trail - Choose Your Adventure       |");
      System.out.println("|                                                     |");
      System.out.println("|       Please type a selection:                      |");
      System.out.println("|                                                     |");
      System.out.println("|       [ 1. | Play ]                                 |");
      System.out.println("|       [ 2. | Exit ]                                 |");
      System.out.println("|                                                     |");
      System.out.println("+-----------------------------------------------------+");

      userInput = input.getInput();
      userInput = userInput.toLowerCase();
      userInput = userInput.replace(" ", "");
      userInput = userInput.replace(".", "");

      System.out.println(userInput);

      if (userInput != null) {
        switch (userInput) {
          case "1":
          case "play":
          case "newgame":
              input.initDictionary();
              sc.readScene("00");
              sc.printScene();
              game = true;
              inputOk = true;
              break;
          case "2":
          case "e":
          case "q":
          case "quit":
          case "exit":
              clearScreen();
              inputOk = true;
              System.out.println("Exiting...");
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException ex) {
                  Thread.currentThread().interrupt();
              }

              System.exit(0);
              break;
          default:
              clearScreen();
              System.out.println("Input Not Recognized!");
              break;
        }
      }
    }

    while (game) {

      //sc.printScene();
      //Debug == System.out.println(input.parse(input.getInput(), sc));
      String initInput = input.getInput();
      initInput = initInput.toUpperCase();

      if (initInput.equals("EXIT")) {

        clearScreen();
        System.out.println("Exiting...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        System.exit(0);
      }

      String preInput = input.parse(initInput, sc);

      if (debug == true) {
        System.out.println("DEBUG: 1" + initInput);
        System.out.println("DEBUG: 2" + preInput);
      }

      if (preInput != null && !preInput.equals("") && !preInput.equals("INVALID")) {
        sc.runLogic(preInput);
      } else if (debug) {
        System.out.println("Input Check Failed");
      }

      Boolean $Debug = false;
      String debugString = "";

      if ($Debug && debugString.contains("")) {
        System.out.println("Please type something...");
        debugString = input.getInput();
      }
    }
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }


}

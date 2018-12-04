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
    Scene sc = new Scene();
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
      System.out.println("|       [ 1. | New Game  ]                            |");
      System.out.println("|       [ 2. | Load Game ]                            |");
      System.out.println("|       [ 3. | Options   ]                            |");
      System.out.println("|       [ 4. | Exit      ]                            |");
      System.out.println("+-----------------------------------------------------+");

      userInput = input.getInput();
      userInput = userInput.toLowerCase();
      userInput = userInput.replace(" ", "");
      userInput = userInput.replace(".", "");

      System.out.println(userInput);

      if (userInput != null) {
        switch (userInput) {
          case "1":
          case "new":
          case "newgame":
              //Read in and display the scene.

              //Read in config file and vars
              //sc.readConfig();
              //System.out.println("hp: " + sc.getVar("hp"));

              //sc.readScene(sc.getVar("scene"));
              sc.readScene("00");

              inputOk = true;
              break;

          case "2":
          case "load":
          case "loadgame":
              //Load save
              //run game
              inputOk = true;
              break;

          case "3":
          case "option":
          case "options":

              break;

          case "4":
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
              break;
          default:
              clearScreen();
              System.out.println("Input Not Recognized!");
              break;
        }
      }
    }

      input.initDictionary();
      sc.printScene();
      // TODO Debug == System.out.println(input.parse(input.getInput(), sc));
      String initInput = input.getInput();
      initInput = initInput.toUpperCase();
      System.out.println("DEBUG: " + initInput);

      String preInput = input.parse(initInput, sc);
      System.out.println("DEBUG: " + preInput);
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
   }


}

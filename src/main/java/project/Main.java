package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
  public static void main(String [] args) {
    String userInput = null;
    boolean inputOk = false;
    Scanner scan = new Scanner(System.in);
    clearScreen(); //Flush Terminal

    while (inputOk == false) {
      System.out.println("\n");
      System.out.println("+-----------------------------------------------------+");
      System.out.println("|       Allegheny Trail - Choose Your Adventure       |");
      System.out.println("|               Please type a selection:              |");
      System.out.println("|       [New Game] [Load Game] [Options] [Exit]       |");
      System.out.println("+-----------------------------------------------------+");

      userInput = scan.nextLine();
      userInput = userInput.toLowerCase();
      userInput = userInput.replace(" ", "");

      System.out.println(userInput);

      if (userInput != null) {
        switch (userInput) {
          case "newgame":
              //New Game Stuff
              inputOk = true;
              break;

          case "loadgame":
              //Load Game Stuff
              inputOk = true;
              break;

          case "options":
              //Options Stuff
              inputOk = true;
              break;

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

    Scene sc = new Scene();

    sc.readScene("00");
    /*

    try {
      File saveFile = new File("saves/save.txt");

      System.out.println("  -Continue");
    } catch (FileNotFoundException noFile) {
      System.out.println("---No Save File Found---");
    }

    System.out.println("  -Exit");
    */


    /**
    Scan all folders to see if there are more than one adventure? Maybe?

    Scanner scanner = null;
    try {
      File gameOptions[] = new File("adventures/_/config.txt");
      scanner = new Scanner(gameOptions[0]);
    } catch (FileNotFoundException noFile) {
      System.out.println("Unable to locate file");
    }
    */

  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
   }


}

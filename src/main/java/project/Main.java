package project;

import java.lang.*;
import java.io.File;
import java.io.FileNotFoundException;


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
              //Warn it will delete saves
              //Load configs
              //run game
              inputOk = true;
              break;

          case "loadgame":
              //Load save
              //run game
              inputOk = true;
              break;

          case "options":
              //Open options menu
              Config c = new Config();
              c.readConfig();
              System.out.println("hp: " + c.getVar("hp"));
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

    //Run Game
    Scene sc = new Scene();
    sc.readScene("00");
    Dictionary dc = new Dictionary();
    dc.readDictionary("00");
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
   }


}

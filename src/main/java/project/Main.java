package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
  public static void main(String [] args) {
    System.out.println("Allegheny Trail - Choose Your Adventure");
    System.out.println("\nPlease choose an option:");

    Scene sc = new Scene();

    sc.readScene("00");
    /*
    Scanner scanner = null;
    try {
      File saveFile = new File("saves/save.txt");
      scanner = new Scanner(saveFile);
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
}

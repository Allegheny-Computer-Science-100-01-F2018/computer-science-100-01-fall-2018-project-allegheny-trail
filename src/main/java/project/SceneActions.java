package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** Here the actions for the first few scenes will be tested and implemented. Most code
is being taken from Main.java file.
* No bugs ATM.
* @author Zach
*/

public class SceneActions {
  public static void main(String [] args) {
    String userInput = null;
    boolean inputOk = false;
    Scanner scan = new Scanner(System.in);

    userInput = scan.nextLine();
    userInput = userInput.toLowerCase();
    userInput = userInput.replace(" ", "");

    System.out.println(userInput);

    Scene sc = new Scene();
    sc.readScene("00");

    if (userInput != null) {
      switch (userInput) {
        case "sky":
            //Examines the sky, this decision leads nowhere.
            System.out.println("You look at the sky. It's twilight, and the stars
            are starting to come out. It's beautiful. You acheive nothing by doing this.");
            inputOk = true;
            break;

        case "ground":
            //Examines the ground, which uncovers a path. This is the only option that works.
            System.out.println("Successful path. You WIN. This is a placeholder.");
            inputOk = true;
            break;

        case "trees":
            //Examines the trees. Does nothing, but provide a bad pun.
            System.out.println("Tree-ditionally the trees would not interest you.
            However, there seem to be a Tree-lion, tree-angular, trees, all standing
            like sen-trees. Its a beautiful coun-tree. \n\n\n I'm not sorry. \n You
            were the one who chose to look at trees.");
            inputOk = true;
            break;

        case "exit":
            System.out.println("There is no escape.");
            inputOk = true;
            System.out.println("Exiting...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            break;
        default:
            System.out.println("Input Not Recognized!");
            break;
  }
}

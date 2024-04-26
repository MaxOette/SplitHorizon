package main;

import javafx.application.Application;
import view.UI;

import java.util.Scanner;

/**
 * Main class of the application.
 * The application prototype "SplitHorizon" allows users to input a name as a single string which is then split into its
 * individual parts. Also, the user can define and edit the fields manually. This parsed name can then be used to
 * generate a formal salutation. Please also refer to the attached documentation files.
 */
public class Main {

    /**
     * main method - responsible for starting the application by launching UI.class
     * @param args - console arguments
     */
    public static void main(String[] args)  {
        Application.launch(UI.class, args);
    }
}

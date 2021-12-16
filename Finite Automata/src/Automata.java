import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//This class acts as an object for the FiniteAutomataProject
//Its main purpose, buildAutomata, is to read a text file
//and build an automata with proper assignments to account for
//accepting states and state transitions to aid in the computation
//in FiniteAutomataProject
public class Automata {
    String fileName = "DFA.txt";
    ArrayList<Character> alphabet = new ArrayList<Character>();
    ArrayList<Integer> acceptingStates = new ArrayList<Integer>();
    int numOfStates = 0;
    ArrayList<ArrayList<Integer>> stateTransitions = new ArrayList<ArrayList<Integer>>();

    //Runs through the DFA.txt file and assigns the information
    //inside to variables for use later.
    void buildAutomata() {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("DFA.txt"));
        } catch (Exception e) {
            System.out.println("Invalid File");
            return;
        }

        int stateCounter = 0;
        //Assigns the number of states in the Automata
        numOfStates = scanner.nextInt();
        scanner.nextLine();

        //Assigns which states are the accepting states
        String nextLine = scanner.nextLine();
        for (int x = 0; x < nextLine.length(); x++) {
            if (nextLine.charAt(x) != ' ') {
                acceptingStates.add(Character.getNumericValue(nextLine.charAt(x)));
            }
        }

        //Defines the alphabet of the entire automata
        nextLine = scanner.nextLine();
        for (int x = 0; x < nextLine.length(); x++) {
            if (nextLine.charAt(x) != ' ') {
                alphabet.add(nextLine.charAt(x));
            }
        }

        //Assigns the state transitions. Each row of stateTransitions
        //is a different state while each column is the state it will
        //go to if it receives that letter with the corresponding index.
        while ((scanner.hasNextLine())) {
            nextLine = scanner.nextLine();
            stateTransitions.add(new ArrayList<Integer>());
            for (int x = 0; x < nextLine.length(); x++) {
                if (nextLine.charAt(x) != ' ') {
                    stateTransitions.get(stateCounter).add(
                            Character.getNumericValue(nextLine.charAt(x)));
                }
            }
            stateCounter++;
        }
    }

    //Returns the alphabet
    ArrayList<Character> getAlphabet() {
        return alphabet;
    }

    //Returns the accepting states
    ArrayList<Integer> getAcceptingStates() {
        return acceptingStates;
    }

    //Takes in the current letter of the input and returns
    //the state that the computation should go next
    int getTransition(int currentState, char letter) {
        int letterIndex = alphabet.indexOf(letter);
        return stateTransitions.get(currentState).get(letterIndex);
    }
}

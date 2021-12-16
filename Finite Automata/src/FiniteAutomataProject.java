import java.util.*;


/*****************************************
        *
        * Theoretical Foundations Project 1: Finite Automata *
        * Brennan Mulligan *
        * *
        * Program Description: This project simulates a Deterministic
        * Finite Automata. Based on a predetermined DFA and alphabet
        * in a text file, the user can enter a string to be evaluated
        * by the program. The program will accurately determine if the
        * string would be accepted by the DFA or rejected due to not
        * ending in an accepting state or having a letter that is not
        * part of the alphabet.
        * *
        ************************************/
public class FiniteAutomataProject
{
    public static void main(String[] args)
    {
        //Declare Variables
        Scanner scanner = new Scanner(System.in);
        Automata DFA = new Automata();
        String input;

        //Reads the text file and assigns the states and alphabet
        System.out.println(">>>Loading DFA.txt…");
        DFA.buildAutomata();

        //Constantly loops different strings to see if they are accepted
        //by the alphabet and only quits if user types "Quit"
        System.out.println(">>>Please enter a string to evaluate:");
        input = scanner.next();
        while(!input.equalsIgnoreCase("Quit"))
        {
            System.out.println(">>>Computation...");
            //Will either print ACCEPTED or REJECTED after printing out
            //the steps it took through the DFA.
            System.out.println(computation(input, DFA));
            System.out.println(">>>Please enter a string to evaluate:");
            input = scanner.next();
        }
        System.out.println(">>>Goodbye!");
    }

    //Evaluates the input string and prints the steps it takes through
    //the DFA to see if it will be accepted.
    //Returns "ACCEPTED" or "REJECTED"
    public static String computation(String input, Automata DFA)
    {
        //Declare Variables
        ArrayList<Character> alphabet = DFA.getAlphabet();
        int currentState = 0;
        String shrunkInput;

        //For every letter in the input, it will print out the current state
        //and its transition into the next as well as what letters it still
        //needs to go through. Will not stop unless a letter is not in the alphabet.
        for(int x = 0; x < input.length(); x++)
        {
            System.out.print(currentState + "," + input.substring(x) + " -> ");
            if(alphabet.contains(input.charAt(x)))
            {
                currentState = DFA.getTransition(currentState, input.charAt(x));
                shrunkInput = "" == input.substring(x + 1) ? "{e}" : input.substring(x + 1);
                System.out.print(currentState + "," + shrunkInput + "\n");
            }
            else
            {
                System.out.print("INVALID INPUT\n");
                return "REJECTED";
            }
        }

        //If the final state that the DFA computation ends on is an accepting state,
        //it will return "ACCEPTED", but if it ends on an intermediate state, it will
        //return "REJECTED"
        if((DFA.getAcceptingStates()).contains(currentState))
        {
            return "ACCEPTED";
        }
        return "REJECTED";
    }
}
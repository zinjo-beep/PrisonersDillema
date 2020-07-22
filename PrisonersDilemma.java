import java.util.*;
import java.lang.*;
/**
 * Write a description of class Prober here.
 *
 * @author (Felix)
 * @version (Alpha v2.5)
 * DISCLAIMER: This program and the decisions it make's will be labeled to as "the program/computer" & "the program/computer's moves"
 * The individual opposing the computer whether they be human or another computer is labeled as "the opponent/player"
 *
 */
public class PrisonersDilemma
{
    String clarifyInput(String tempS){ //clarifyInput is designed to make the code more user-friendly, so
                                       //^ they don't have to specifically type a lowercase c to cooperate.
        tempS=tempS.toLowerCase(); // This line is turning the player's input into lowercase.
        char ch = tempS.charAt(0); // This line is turning the string into a char so I can use charAt.
        String outputS = String.valueOf(ch); //This line is turning the char back into a String.
        return outputS; // This line is sending the clarified input back to the code.
    }
    String checklistInput(String outputS){ //checklistInput is to make the code more user-friendly, by
                  //^ clarifying their input against a checklist to predict what they're trying to say.
        switch(outputS){      
            case "c": case "cooperate": case "C": case "Cooperate": case "coop": case "Coop":
            //^ This list of words is all the forms of "cooperate" I think someone would realistcally do.
            //^ Through user feedback and observing playtesters inputs, this list may increase
                   outputS = "c";
                   return outputS;
                           
            case "d": case "defect": case "D": case "Defect": case "defection": case "Defection":
            //^ This list of words is all the forms of "defect" I think someone would realistcally do.
            //^ Through user feedback and observing playtesters inputs, this list may increase
                   outputS = "d";
                   return outputS;
                   
            default :
                   outputS = "?";
                   return outputS;
                }
    }
    public PrisonersDilemma()
    {
        // initialise instance variables
        Scanner inputStream = new Scanner (System.in); //Scanner for players input
        /** Player and computer input strings **/
        String oppoInput = "";; //String holding the players' input, the = ""; is just to say it's empty
        String compResponse = ""; //String holding the computers' input, the = ""; is just to say it's empty
        /** various variables **/
        int round = 0;
        String oppoInputs[] = new String[20000]; // array for holding the opponents's inputs, the "String[20000]" is to say the array holds a max 20000 strings
        /** integers for scores **/
        int oppoScore = 0; // int recording the score of the player
        int compScore = 0; // int recording the score of the computer
        /** floats for ratio **/
        float oppoCoop = 0; // float recording the number of times the computer cooperated
        float oppoDefect = 0; // float recording the number of times the computer defected
        float oppoRatio = 0; // float for finding the ratio of computer cooperates : computer defects
        /** bool for game end **/
        boolean finish = false; // boolean dictating if the game is still going
        while (!finish){
            System.out.println("--------------------");
            System.out.println("Cooperate or Defect?");
            oppoInput=clarifyInput(inputStream.nextLine()); // This takes the input of the scanner, then sends it to the "clarifyInput" function
            switch (oppoInput) {
                /** Case "c" is if the player cooperates, case "d" is if the player defects **/
                case "c" : System.out.println("You cooperated");
                           oppoCoop += 1; //This is saying "if opponent cooperates, add 1 to oppoCoop"
                           oppoInputs[round]="c"; // This sets the move the player made to an array with the placement being the current round
                           break;
                           
                case "d" : System.out.println("You defected");
                           oppoDefect += 1; //This is saying "if opponent defects, add 1 to oppoDefect"
                           oppoInputs[round]="d"; // This sets the move the player made to an array with the placement being the current round
                           break;
                           
                case "f" : System.out.println("Game Over");
                           finish = true; //turns finish on to turn off the "while (!finish)" loop
                           break;
                           
                default : System.out.println("Invalid input");
                           break;
                 }
            //This chunk of code below is deciding the computer's move based on the ratio of the player's moves
                 if(oppoRatio < 35) 
                {compResponse = "d";}
            else if(oppoRatio < 55)
                {compResponse = "c";}
            else if(oppoRatio < 80)
                {compResponse = "d";}
            else if(oppoRatio < 90)
                {compResponse = "c";}
            else if(oppoRatio < 101)
                {compResponse = "d";}     
            //This chunk of code below changes the score based on all possible combinations of the opponent & computer's cooperations & defects
            switch (compResponse) {
                /** Case "c" is if the computer cooperates, case "d" is if the computer defects **/
                case "c" : if(oppoInput.equals("c")){oppoScore += 1; compScore += 1;} // This line is for if both the player and computer cooperate
                           else if(oppoInput.equals("d")){oppoScore += 2; compScore -= 5;} // This line is for if the comp cooperates and the player defects
                           System.out.println("The computer cooped");
                           break;
                           
                case "d" : if(oppoInput.equals("d")){oppoScore -= 3; compScore -= 3;} // This line is for if both the player and computer defect
                           else if(oppoInput.equals("c")){oppoScore -= 5; compScore += 2;} // This line of is for if the player cooperates and the comp defects
                           System.out.println("The computer defected");
                           break;
                           
                default : System.out.println("Something went wrong, the computer entered an invalid input");
                // This^ line is an error message
                           break;
            }
            if(round>9){ // The if(round>10) is so the array doesn't detect something out of bounds like -9 on the first round
                oppoCoop = 0;
                oppoDefect = 0;
                for(int loop = round-10; loop < round; loop++) {
                    if(oppoInputs[loop].equals("c")) { //This line is checking the last 10 opponent inputs and counting the number of cooperations
                        oppoCoop += 1;
                    }
                    if(oppoInputs[loop].equals("d")) { //Same as above but counting defections instead
                        oppoDefect += 1;
                    }
                }
            }
            round += 1; //just adds a point to 'round' every round the code goes by
            /** Finding the "trust percentage" **/
            oppoRatio = oppoCoop / (oppoCoop + oppoDefect); //finds oppoRatio through the power of math
            oppoRatio *= 100;
            /** Score displays **/
            //System.out.println("DEBUG: round: "+ round); - commented out incase need to use later
            System.out.println("DEBUG: oppoRatio is "+ oppoRatio);// - commented out incase need to use later
            //System.out.println("DEBUG: oppoCoop is "+ oppoCoop); - commented out incase need to use later
            //System.out.println("DEBUG: oppoDefect is "+ oppoDefect); - commented out incase need to use later
            System.out.println("The player score is "+ oppoScore);
            System.out.println("The comp score is "+ compScore);
            }
    }
}
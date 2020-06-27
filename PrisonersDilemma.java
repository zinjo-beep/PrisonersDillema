import java.util.*;
import java.lang.*;
/**
 * Write a description of class Prober here.
 *
 * @author (Felix)
 * @version (Alpha v2.2)
 * DISCLAIMER: This program and the decisions it make's will be labeled to as "the program/computer" & "the program/computer's moves"
 * The individual opposing the computer whether they be human or another computer is labeled as "the opponent/player"
 * 
 */
public class PrisonersDilemma
{
    public PrisonersDilemma()
    {
        // initialise instance variables
        Scanner inputStream = new Scanner (System.in); //Scanner for players input
        /** Player and computer input strings **/
        String oppoInput = "";; //String holding the players' input, the = ""; is just to say it's empty
        String compResponse = ""; //String holding the computers' input, the = ""; is just to say it's empty
        /** various variables **/
        int round = 0;
        String oppoInputs[] = new String[20000]; // array for holding the opponents's inputs, the "= {}" is to say the array is empty but initialized
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
            System.out.println("Cooperate (enter 'c') or defect? (enter 'd')");
            oppoInput=inputStream.nextLine();
            switch (oppoInput) {
                /** Case "c" is if the player cooperates, case "d" is if the player defects **/
                case "c" : System.out.println("You cooperated");
                           oppoCoop += 1; //This is saying "if opponent cooperates, add 1 to oppoCoop"
                           oppoInputs[round]="c";
                           break;
                           
                case "d" : System.out.println("You defected");
                           oppoDefect += 1; //This is saying "if opponent defects, add 1 to oppoDefect"
                           oppoInputs[round]="d";
                           break;
                           
                case "f" : System.out.println("Game Over");
                           finish = true; //turns finish on to turn off the "while (!finish)" loop
                           break;
                           
                default : System.out.println("Invalid input");
                           break;
                 }
            /** DEBUG: THIS ROUND TO FIND COMPUTER INPUT IS JUST FOR TESTING **/
            
            if(round % 2 == 0)
                {compResponse = "d";}
            else
                {compResponse = "c";
            }
            
            switch (compResponse) {
                /** Case "c" is if the computer cooperates, case "d" is if the computer defects **/
                case "c" : if(oppoInput.equals("c")){oppoScore += 1; compScore += 1;} // This line is for if both the player and computer cooperate
                           else if(oppoInput.equals("d")){oppoScore += 2; compScore -= 5;} // This line is for if the comp cooperates and the player defects
                           System.out.println("DEBUG: The computer cooped");
                           break;
                           
                case "d" : if(oppoInput.equals("d")){oppoScore -= 3; compScore -= 3;} // This line is for if both the player and computer defect
                           else if(oppoInput.equals("c")){oppoScore -= 5; compScore += 2;} // This line of is for if the player cooperates and the comp defects
                           System.out.println("DEBUG: The computer defected");
                           break;
                           
                default : System.out.println("Something went wrong, the computer entered an invalid input");
                // This^ line is an error message
                           break;
            }
            if(round>9){ // The if(round>10) is so the array doesn't detect something out of bounds like -9 on the first round
                oppoCoop = 0;
                oppoDefect = 0;
                for(int loop = round-10; loop < round; loop++) {
                    if(oppoInputs[loop].equals("c")) {
                        oppoCoop += 1;
                    }
                    if(oppoInputs[loop].equals("d")) {
                        oppoDefect += 1;
                    }
                }
            }
            //The compInputs array will hold all of the computer's cooperations and defects in order so I can just use the most recent inputs to find the ratio
            round += 1;
            /** Finding the "trust percentage" **/
            oppoRatio = oppoCoop / (oppoCoop + oppoDefect);
            oppoRatio *= 100;
            /** Score displays **/
            System.out.println("DEBUG: round: "+ round);
            System.out.println("DEBUG: oppoRatio is "+ oppoRatio);
            System.out.println("DEBUG: oppoCoop is "+ oppoCoop);
            System.out.println("DEBUG: oppoDefect is "+ oppoDefect);
            System.out.println("The player score is "+ oppoScore);
            System.out.println("The comp score is "+ compScore);
            }
    }
}
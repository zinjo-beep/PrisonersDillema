import java.util.*;
import java.lang.*;
/**
 * Write a description of class Prober here.
 *
 * @author (Felix)
 * @version (Alpha v2.0)
 */
public class PrisonersDilemma
{
    public PrisonersDilemma()
    {
        // initialise instance variables
        Scanner inputStream = new Scanner (System.in); //Scanner for players input
        /** Player and computer input strings **/
        String playerInput = "";; //String holding the players' input, the = ""; is just to say it's empty
        String compResponse = ""; //String holding the computers' input, the = ""; is just to say it's empty
        /** various variables **/
        int round = 0;
        String compInputs[] = new String[20000]; // array for holding the computer's inputs, the "= {}" is to say the array is empty but initialized
        /** integers for scores **/
        int playerScore = 0; // int recording the score of the player
        int compScore = 0; // int recording the score of the computer
        /** floats for ratio **/
        float compCoop = 0; // float recording the number of times the computer cooperated
        float compDefect = 0; // float recording the number of times the computer defected
        float compRatio = 0; // float for finding the ratio of computer cooperates : computer defects
        /** bool for game end **/
        boolean finish = false; // boolean dictating if the game is still going
        while (!finish){
            System.out.println("--------------------");
            System.out.println("Cooperate (enter 'c') or defect? (enter 'd')");
            playerInput=inputStream.nextLine();
            switch (playerInput) {
                /** Case "c" is if the player cooperates, case "d" is if the player defects **/
                case "c" : System.out.println("You cooperated");
                            if(compResponse.equals("c")){playerScore += 1; compScore += 1;}
                            // This^ line is for if both the player and computer cooperate
                            else if(compResponse.equals("d")){playerScore -= 5; compScore += 2;}
                            // This^ line of is for if the player cooperates and the comp defects
                            break;
                case "d" : System.out.println("You defected");
                            if(compResponse.equals("c")){playerScore += 2; compScore -= 5;}
                            // This^ line is for if the comp cooperates and the player defects
                            else if(compResponse.equals("d")){playerScore -= 3; compScore -= 3;}
                            // This^ line is for if both the player and computer defect
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
                case "c" : compCoop += 1; //This is saying "if computer cooperates, add 1 to compCoop"
                           System.out.println("DEBUG: The computer cooped");
                           compInputs[round]="c";
                           break;
                case "d" : compDefect += 1; //This is saying "if computer defects, add 1 to compDefect"
                           System.out.println("DEBUG: The computer defected");
                           compInputs[round]="d";
                           break;
                default : System.out.println("Something went wrong, the computer entered an invalid input");
                // This^ line is an error message
                           break;
            }
            if(round>10){ // The if(round>10) is so the array doesn't detect something out of bounds like -9 on the first round
                compCoop = 0;
                compDefect = 0;
                for(int loop = round-10; loop < round; loop++) {
                    if(compInputs[loop].equals("c")) {
                        compCoop += 1;
                    }
                    if(compInputs[loop].equals("d")) {
                        compDefect += 1;
                    }
                }
            }
            //The compInputs array will hold all of the computer's cooperations and defects in order so I can just use the most recent inputs to find the ratio
            round += 1;
            /** Finding the "trust percentage" **/
            compRatio = compCoop / (compCoop + compDefect);
            compRatio *= 100;
            /** Score displays **/
            System.out.println("DEBUG: round: "+ round);
            System.out.println("DEBUG: compRatio is "+ compRatio);
            System.out.println("DEBUG: compCoop is "+ compCoop);
            System.out.println("DEBUG: compDefect is "+ compDefect);
            //System.out.println("The player score is "+ playerScore);
            //System.out.println("The comp score is "+ compScore);
            }
    }
}
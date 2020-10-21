package com.sklr.smsPrank;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class smsPrank {

    Scanner scanner = new Scanner( System.in );
    String to_number = "";
    String from_number = "";
    String MessageToSend = "";
    String MessageFileLocation = "";

    /**
     * This is a program that takes a plaintext file and sends it one word at a time
     * through text (using Twilio) to a phone number.
     */
    public static void main(String[] args) {
        smsPrank start = new smsPrank();
        start.startup();
    }

    /**
     * This is the working main. It handles the user interface, and calls the smsSend
     * methods, which are the ones that interface with Twilio.
     */
    public void startup() {

        boolean correctInput;
        smsSend send = new smsSend();
        String defaultSettingInput;

        do {
            System.out.println("Would you like to use the default settings? (Y/N)");
            defaultSettingInput = scanner.nextLine();

            correctInput = authYesNo(defaultSettingInput);

        } while (!correctInput);



        if (YesOrNo(defaultSettingInput)) /* If the user wants default settings */ {

            send.ACCOUNT_SID = "AC0fe6c4167eac6a04dc3fd63a5ed7818e";
            send.AUTH_TOKEN = "d301405c048610e1059629bebf1cb1b4";
            from_number = "8452445669";

            boolean phoneNumberCorrectInput = false;
            do {
                System.out.println("Please enter the 10 digit phone number to prank: ");
                to_number = scanner.nextLine();

                if (to_number.length() != 10) {
                    System.out.println("The phone number must be 10 digits long.");
                }
                else {
                    phoneNumberCorrectInput = true;

                    to_number = "+1" + to_number; // Adds a "+" to the beginning of the number, which Twilio needs.


                    System.out.println("Please enter the location of the file to read: ");
                    System.out.println("Here are some Options: ");
                    System.out.println("===================================================================================================================================================");
                    System.out.println("Fitness gram Pacer Test Short:   C:\\Users\\David\\Desktop\\Folders\\Programming\\Java Projects\\SMS Prank\\Messages\\PacergramFitnessTestShort.txt");
                    System.out.println("Fitness gram Pacer Test Regular: C:\\Users\\David\\Desktop\\Folders\\Programming\\ ! Danger [Remove to Proceed] ! Java Projects\\SMS Prank\\Messages\\PacergramFitnessTest.txt");
                    System.out.println("Full Bee Movie Script:           C:\\Users\\David\\Desktop\\Folders\\Programming\\ ! Danger [Remove to Proceed] ! Java Projects\\SMS Prank\\Messages\\The Bee Movie.txt");
                    System.out.println("Test:                            C:\\Users\\David\\Desktop\\Folders\\Programming\\Java Projects\\SMS Prank\\Messages\\test.txt");
                    System.out.println("Messages File Location:          C:\\Users\\David\\Desktop\\Folders\\Programming\\Java Projects\\SMS Prank\\Messages\\");
                    System.out.println("===================================================================================================================================================");
                    System.out.println("Please enter the location of the file to read: ");
                    MessageFileLocation = scanner.nextLine();
                }
            } while (!phoneNumberCorrectInput);

            String sendAsSpam;

            do {
                System.out.println("Would you like to spam this message? (Y/N)");
                sendAsSpam = scanner.nextLine();

                correctInput = authYesNo(sendAsSpam);

            } while (!correctInput);

            if (YesOrNo(sendAsSpam)){
                spam(send);
            }
            else{
                dontSpam(send);
            }

        }


        else if (!YesOrNo(defaultSettingInput)) /* If the user wants to enter everything from scratch */{
            //correctInput = true;
            System.out.println("Non default settings have not yet been coded. Please try again later.");

/*                send.ACCOUNT_SID = "";
            send.AUTH_TOKEN = "";

            send.send();*/


        }
    }

    /**
     * Sends the Message as Spam
     */
    void spam(smsSend send){
        try{
            File prankFile = new File (MessageFileLocation);
            Scanner fileScanner = new Scanner (prankFile); // FileScanner reads the entire file

            System.out.println("The text file at '" + MessageFileLocation + "' will be sent to " + to_number + ".");

            while (fileScanner.hasNextLine()){
                Scanner FileScannerWord = new Scanner (fileScanner.nextLine()); // FileScannerWord just grabs the next word in the file
                while (FileScannerWord.hasNext()){
                    MessageToSend = FileScannerWord.next();

                    send.send(to_number, from_number, MessageToSend);
                }
            }
        } catch(FileNotFoundException e){
            System.out.println("An Error has occurred reading the file.");
            e.printStackTrace();
        }
    }

    /**
     * Sends the message as one text
     */
    void dontSpam(smsSend send){
        try{
            File prankFile = new File (MessageFileLocation);
            Scanner fileScanner = new Scanner (prankFile); // FileScanner reads the entire file

            System.out.println("The text file at '" + MessageFileLocation + "' will be sent to " + to_number + ".");

            while (fileScanner.hasNextLine()){
                    MessageToSend = fileScanner.nextLine();

                    send.send(to_number, from_number, MessageToSend);
            }
        } catch(FileNotFoundException e){
            System.out.println("An Error has occurred reading the file.");
            e.printStackTrace();
        }
    }

    /**
     * Authenticates whether or not the user inputed a "yes or no".
     * @param input The user's input. It is supposed to be "yes" or "no" but
     *              if not, it will return false
     * @return Returns true if the input is "yes" or "no", and false if it
     *         something else.
     */
    boolean authYesNo(String input){


        if (input.equals("Y") || input.equals("y") ||
                input.equals("Yes") || input.equals("yes")) {
            return true;
        }

        else if (input.equals("N") || input.equals("n") ||
                input.equals("No") || input.equals("no")) {
            return true;
        }

        else {
            System.out.println("Please enter a valid command.");
            return false;
        }
    }

    /**
     * Returns a true if the input was "Yes" or a false if the input was "No"
     * @param input The user's input. It is supposed to be "yes" or "no" but
     *              if not, it will output a warning and exit the program;
     * @return returns a Boolean expression. True is a "yes" and false is a
     *         "no".
     */
    boolean YesOrNo(String input){

        if (input.equals("Y") || input.equals("y") ||
                input.equals("Yes") || input.equals("yes")) {

            return true;
        }

        else if (input.equals("N") || input.equals("n") ||
                input.equals("No") || input.equals("no")) {
            return false;
        }

        System.out.println("Warning: This input is not 'yes' or 'no'.");
        System.out.println("Exiting");

        System.exit(0);

        return false; // Just so it doesn't get mad, even though it'll never get here.
    }
}

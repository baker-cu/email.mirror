/**
*  TCP Client Program
*  Connects to a TCP Server
*  Waits for a Welcome message from the server
*  Receives a line of input from the keyboard and sends it to the server
*  Receives a response from the server and displays it.
*  Receives a second line of input from the keyboard and sends it to the server
*  Receives a second response from the server and displays it.
*  Closes the socket and exits
*
*  @author: Michael Fahy
*  Email:  fahy@chapman.edu
*  Date:  2/4/2018
*  @version: 3.0
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

import java.net.Socket;

class Email {
  public static void main(String[] argv) throws Exception {

    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

    System.out.println("Sender Email: ");
    String fromEmail=inFromUser.readLine();

    System.out.println("Recipient Email: ");
    String toEmail=inFromUser.readLine();

    System.out.println("Sender Name: ");
    String toEmail=inFromUser.readLine();

    System.out.println("Sender Name:");
    String sender = inFromUser.readLine();

    //get the body part of the email
    String line;
    while((line = inFromUser.readLine()) != "."){
        String[] body = line;
    }



    Socket clientSocket = null;

    try {
      clientSocket = new Socket("smtp.chapman.edu", 25);
    } catch (Exception e) {
      System.out.println("Failed to open socket connection");
      System.exit(0);
    }

    PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(),true);
    BufferedReader inFromServer =  new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    String welcomeMessage = inFromServer.readLine();
    System.out.println("FROM SERVER:" + welcomeMessage);

    System.out.println("FROM CLIENT: HELO icd.chapman.edu");
    outToServer.println("HELO icd.chapman.edu");

    String message = inFromServer.readLine();
    System.out.println("FROM SERVER: " + message);

    System.out.println("MAIL FROM: " + fromEmail);
    outToServer.println("MAIL FROM: " + fromEmail);

    String message = inFromServer.readLine();
    System.out.println("FROM SERVER: " + message);

    System.out.println("RCPT TO: " + fromEmail);
    outToServer.println("MAIL FROM: " + fromEmail);

    String message = inFromServer.readLine();
    System.out.println("FROM SERVER: " + message);

    //I HAVE NO CLUE WHAT TO PUT HERE YET





    clientSocket.close();
  }
}
/**
*  Connects to socket
*  Asks user for email information
*  Sends email
*
*  @author: Jacob Baker
*  Email:  baker210@mail.chapman.edu
*  Date:  9/17/2019
*  @version: 1.0
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

class Email {
  public static void main(String[] argv) throws Exception {

    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

    System.out.println("Sender Email: ");
    String fromEmail = inFromUser.readLine();

    System.out.println("Recipient Email: ");
    String toEmail = inFromUser.readLine();

    System.out.println("Sender Name: ");
    String sender = inFromUser.readLine();

    System.out.println("Recipient Name:");
    String recipient = inFromUser.readLine();

    //get the body part of the email
    String line;
    String[] body = new String[100];
    int i = 0;
    while (body.length < 100) {
      line = inFromUser.readLine();
      if (line != ".") {
        body[i] = line;
        i++;
      }
      else {
        body[i] = line;
        break;
      }
    }

    Socket clientSocket = null;

    try {
      clientSocket = new Socket("smtp.chapman.edu", 25);
    } catch (Exception e) {
      System.out.println("Failed to open socket connection");
      System.exit(0);
    }

    PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(),true);
    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    String welcomeMessage = inFromServer.readLine();
    System.out.println("FROM SERVER:" + welcomeMessage);

    System.out.println("FROM CLIENT: HELO icd.chapman.edu");
    outToServer.println("HELO icd.chapman.edu");

    String message = inFromServer.readLine();
    System.out.println("FROM SERVER: " + message);

    System.out.println("MAIL FROM: " + fromEmail);
    outToServer.println("MAIL FROM: " + fromEmail);

    message = inFromServer.readLine();
    System.out.println("FROM SERVER: " + message);

    System.out.println("RCPT TO: " + fromEmail);
    outToServer.println("MAIL FROM: " + fromEmail);

    message = inFromServer.readLine();
    System.out.println("FROM SERVER: " + message);

    //I HAVE NO CLUE WHAT TO PUT HERE YET

    for (int x = 0; x < body.length; x++) {
      if (body[x] == ".") {
        outToServer.println(".");
        break;
      }
      outToServer.println(body[x]);
    }
    outToServer.println("QUIT");

    clientSocket.close();
  }
}

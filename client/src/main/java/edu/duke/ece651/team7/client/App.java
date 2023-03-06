/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.ece651.team7.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
  public static void main(String[] args) {
    if (args.length == 3) {
      try {
        Client client = new Client(args[0], Integer.parseInt(args[1]), args[2],
            new BufferedReader(new InputStreamReader(System.in)), System.out);
        client.start();
      } catch (Exception e) {
        System.err.println("Exception: " + e);
      }
    } else {
      System.err.println("Usage: client <host> <port> <playername>");
    }
  }
}

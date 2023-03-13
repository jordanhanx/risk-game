/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.ece651.team7.server;

import edu.duke.ece651.team7.shared.MapFactory;
import edu.duke.ece651.team7.shared.TextMapFactory;

public class App {
  public static void main(String[] args) {
    if (args.length == 2) {
      try {
        MapFactory factory  = new TextMapFactory();
        Server server = new Server(Integer.parseInt(args[1]), System.out, 10, factory.createMapTest());
        server.start(Integer.parseInt(args[0]));
      } catch (Exception e) {
        System.err.println("Exception: " + e);
      }
    } else {
      System.err.println("Usage: server <port> <clientCapacity>");
    }
  }
}

package edu.duke.ece651.team7.server;

import java.io.PrintStream;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import edu.duke.ece651.team7.shared.*;

public class Server extends UnicastRemoteObject implements RemoteController{
  private final PrintStream out;
  private final int numPlayers;
  private HashMap<RemoteClient, Player> clients;
  // private MapFactory factory;
  private GameMap map;
  private ArrayList<ArrayList<Territory> > territoryGroups;
  private int ID;

  public Server(int n, PrintStream out, GameMap m) throws RemoteException {
    this.numPlayers = n;
    this.clients = new HashMap<RemoteClient, Player>();
    this.out = out;
    map = m;
    territoryGroups = new ArrayList<ArrayList<Territory> >();
    ID = 0; 
    groupTerritories();
  }

  /**
   * Split territorries in map into numPlayers groups
   * @return the groups of territories
   * @throws RemoteException
   */
  private void groupTerritories() throws RemoteException{
    // ArrayList<ArrayList<Territory> > ans = new ArrayList<ArrayList<Territory> >();
    List<Territory> tList = new ArrayList<Territory>(map.getTerritories());
    int numGroup = tList.size() / numPlayers;
    Collections.shuffle(tList);
    // out.println("numGroups is: "+ numGroup);
    for(int i = 0; i < numPlayers; i++){
      ArrayList<Territory> elem = new ArrayList<Territory>();
      for(int j = i*numGroup; j < (i+1)*numGroup; j++){
        elem.add(tList.get(j));
      }
      territoryGroups.add(elem);
    }
  }

  /**
   * testing the above function
   * @return a arraylist of grouped territories
   */
  public ArrayList<ArrayList<Territory> > getTerritoryGroups(){
    return territoryGroups;
  }

  /**
   * Start the server
   * @param port port number
   * @throws RemoteException
   */
  public void start(int port) throws RemoteException {
    LocateRegistry.createRegistry(port).rebind("GameServer", this);
    out.println("Server ready");
  }

  @Override
  public synchronized String tryRegisterClient(RemoteClient client, String name) throws InterruptedException, RemoteException {
    out.println("Received rejister request, players current/max = " + clients.size() + "/" + numPlayers);
    if (clients.size() < numPlayers && !clients.containsKey(client)) {
      Player p = new Player(name);
      for(Territory t: territoryGroups.get(ID)){
        p.addTerritory(t);
        t.setOwner(p);
      }
      clients.put(client, p);
      if (clients.size() != numPlayers) {
        wait();
      }else{
        out.println("All player joined!");
        notifyAll();
      }
      // out.println("Remote Player:" + client. + " has joined game");
      return null;
    } else {
      return "Cannot register Client";
    }
  }

  @Override
  public synchronized GameMap getGameMap() throws RemoteException, InterruptedException {
    return map;
  }

  @Override
  public String tryUnRegisterClient(RemoteClient client) throws RemoteException {
    if(clients.containsKey(client)){
      clients.remove(client);
      return null;
    }
    return "The client is not registered";
  }

  @Override
  public String tryMoveOrder(RemoteClient client, String from, String to, int units) throws RemoteException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'tryMoveOrder'");
  }

  @Override
  public String tryAttackOrder(RemoteClient client, String from, String to, int units) throws RemoteException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'tryAttackOrder'");
  }

  @Override
  public void doCommitOrder(RemoteClient client) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'doCommitOrder'");
  }

  @Override
  public boolean isGameOver() throws RemoteException {
    int numLose = 0;
    for(RemoteClient c: clients.keySet()){
      if(clients.get(c).isLose()){
        numLose++;
      }
    }
    if(numLose == numPlayers-1){
      return true;
    }
    return false;
  }


}

package edu.duke.ece651.team7.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteTerritory extends Remote {
    public String getName() throws RemoteException;
}

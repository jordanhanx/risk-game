
// copied from emma's latest commit 

package edu.duke.ece651.team7.shared;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.HashSet;

public class Territory {
  private final String name;
  //private Player owner;
  private int units;
  private HashSet<String> neighbors;
public class Territory implements RemoteTerritory{
  private final String name;
  //private Player owner;
  private int units;
  private HashSet<String> neighbors;

  /**
   * Constructs a territory with default values
   */
  public Territory() {
    this.name = "Default";
  /**
   * Constructs a territory with default values
   */
  public Territory() {
    this.name = "Default";
  }

  /**
     Constucts a territory with just inputted name
  */
     Constucts a territory with just inputted name
  */
  public Territory(String name) {
    this.name = name;
    //this.owner = new Player();
    this.units = 0;
    this.neighbors = new HashSet<String>();

  }
  
  /**
     Constructs a territory with inputted values
     @param name name of territory
     @param owner player that owns the territory
     @param units number of player's units present in territory
     @param neighbors the nearby (adjacent) territories
  */
  public Territory(String name, //Player owner,
                   int units, HashSet<String> neighbors) {
    this.name = name;
    //this.owner = owner;
    this.units = units;
    this.neighbors = neighbors;
  }
  

  public String getName() {
    return name;
  }

  public int getUnits() {
    return units;
  }

  // public Player getOwner() {
  //   return owner;
  // }

  // public void setOwner(Player p) {
  //   owner = p;
  // }

  public void increaseUnits() {
    units++;
  }

  public void decreaseUnits() {
    if (units > 0) {
      units--;
    }
  }

  public Boolean isAdjacent(String name) {
    return neighbors.contains(name);
  }




  //add the override methods for equal and hashcode

  public List<Territory> getNeighbourTerritories(){
    List neighbourtTerritoriesList = new ArrayList<>();
    for(String name: neighbors){
      neighbourtTerritoriesList.add(new Territory(name));
    }
    return neighbourtTerritoriesList;
  }

  @Override
  public boolean equals (Object other){
    if(other != null && other.getClass().equals(getClass())){
        Territory otherTerritory = (Territory) other;
        return this.getName().equals(otherTerritory.getName()) ;
    }
        return false;
  }

  @Override
  public String toString() {
      return getName();
    //this.owner = new Player();
    this.units = 0;
    this.neighbors = new HashSet<String>();

  }
  
  @Override
  public int hashCode() {
      return toString().hashCode();
  }
  


}


  
  /**
     Constructs a territory with inputted values
     @param name name of territory
     @param owner player that owns the territory
     @param units number of player's units present in territory
     @param neighbors the nearby (adjacent) territories
  */
  public Territory(String name, //Player owner,
                   int units, HashSet<String> neighbors) {
    this.name = name;
    //this.owner = owner;
    this.units = units;
    this.neighbors = neighbors;
  }
  
  @Override
  public String getName() {
    return name;
  }

  public int getUnits() {
    return units;
  }

  // public Player getOwner() {
  //   return owner;
  // }

  // public void setOwner(Player p) {
  //   owner = p;
  // }

  public void increaseUnits() {
    units++;
  }

  public void decreaseUnits() {
    if (units > 0) {
      units--;
    }
  }

  public Boolean isAdjacent(String name) {
    return neighbors.contains(name);
  }

}

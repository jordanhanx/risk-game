package edu.duke.ece651.team7.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This type represents Territory in the game.
 */
public class Territory implements Serializable {
  private static final long serialVersionUID = 1L; // Java recommends to declare this explicitly.
  private final String name;
  private Player owner;
  // private int units;
  private ArrayList<Unit> units;
  private final int foodProductionRate;
  private final int techProductionRate;


  /**
   * Constucts a Territory with just inputted name
   * 
   * @param name name of territory
   */
  public Territory(String name) {
    this.name = name;
    this.owner = null;
    this.units = new ArrayList<Unit>();
    this.foodProductionRate = 1;
    this.techProductionRate = 1;

  }

  public Territory(String name, int numUnits){
    this.name = name;
    this.owner = null;
    if (numUnits < 0) {
      throw new IllegalArgumentException("units cannot be less than 0");
    }
    this.units = new ArrayList<Unit>();
    for(int i = 0; i < numUnits; i++){
      this.units.add(new Unit());
    }
    this.foodProductionRate = 1;
    this.techProductionRate = 1;
  }

  public Territory(String name, int numUnits, int foodProductionRate, int techProductionRate){
    this.name = name;
    this.owner = null;
    if (numUnits < 0) {
      throw new IllegalArgumentException("units cannot be less than 0");
    }
    this.units = new ArrayList<Unit>();
    for(int i = 0; i < numUnits; i++){
      this.units.add(new Unit());
    }
    this.foodProductionRate = foodProductionRate;
    this.techProductionRate = techProductionRate;
  }
  /**
   * Constructs a territory with inputted values
   * 
   * @param name  name of territory
   * @param owner player that owns the territory
   * @param units number of player's units present in territory
   */
  public Territory(String name, Player owner, int numUnits) {
    this.name = name;
    this.owner = owner;
    if (numUnits < 0) {
      throw new IllegalArgumentException("units cannot be less than 0");
    }
    this.units = new ArrayList<Unit>();
    for(int i = 0; i < numUnits; i++){
      this.units.add(new Unit());
    }
    this.foodProductionRate = 1;
    this.techProductionRate = 1;
  }

  public String getName() {
    return name;
  }

  public int getUnitsNumber() {
    return units.size();
  }
  
  public Collection<Unit> getUnits(){
    return units;
  }

  public Player getOwner() {
    return owner;
  }

  public void setOwner(Player p) {
    // p.addTerritory(this);
    owner = p;
  }

  public void setUnits(ArrayList<Unit> newUnits) {
    units = newUnits;
  }
  // public void setUnits(int num) {
  //   if (num < 0) {
  //     throw new IllegalArgumentException("Set Territory Units: input num must be greater than or equal to 0");
  //   } else {
  //     units = num;
  //   }
  // }

  /**
   * 
   * @param us one unit to be added
   */
  public void addUnits(Unit u){
    if(units.contains(u)){
      throw new IllegalArgumentException("Cannot repeatedly add the same unit");
    }
      units.add(u);
  }

  /**
   * 
   * @param us collection of units to be added
   */
  public void addUnits(Collection<Unit> us){
    for(Unit u: us){
      if(units.contains(u)){
        throw new IllegalArgumentException("Cannot repeatedly add the same unit");
      }
      units.add(u);
    }
  }

  /**
   * 
   * @param l the level of unit that want to be removed
   * @param num the number of units want to remove
   * @return null if no enough unit with level l, else return the collection of units to be moved
   */
  public Collection<Unit> removeUnits(Level l, int num){
    ArrayList<Unit> tomove = new ArrayList<>();
    for(int i = 0; i < units.size(); i++){
      if(units.get(i).getLevel() == l){
        tomove.add(units.get(i));
        // units.remove(units.get(i));
        num--;
        if(num == 0){
          break;
        }
      }
    }
    if(num!=0){
      return null;
    }else{
      for(Unit u: tomove){
        units.remove(u);
      }
      return tomove;
    }
  }

  public int produceFood(){
    return foodProductionRate;
  }

  public int produceTech(){
    return techProductionRate;
  }
  
  // public void increaseUnits(int num) {
  //   if (num <= 0) {
  //     throw new IllegalArgumentException("Increase Territory Units: input num must be greater than 0");
  //   } else {
  //     units += num;
  //   }
  // }


  // public void decreaseUnits(int num) {
  //   if (num <= 0) {
  //     throw new IllegalArgumentException("Decrease Territory Units: input num must be greater than 0");
  //   } else if (units < num) {
  //     throw new ArithmeticException("Decrease Territory Units: units cannot be less than 0");
  //   } else {
  //     units -= num;
  //   }
  // }

  @Override
  public boolean equals(Object other) {
    if (other != null && other.getClass().equals(getClass())) {
      Territory otherTerritory = (Territory) other;
      return name.equals(otherTerritory.name);
    }
    return false;
  }

  @Override
  public String toString() {
    return getName();
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }
}

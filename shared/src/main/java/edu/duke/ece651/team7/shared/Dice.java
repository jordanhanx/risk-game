package edu.duke.ece651.team7.shared;

import java.util.Random;

public class Dice {
    /**
     * @param faces number of faces of the dice
     */
    private final int faces;

    public Dice(int n){
        if(n>0){
            faces = n;
        }else{
            throw new IllegalArgumentException("Invalid Dice");
        }
    }
    
    /**
     * Throw the dice and return the value of the dice
     * @return
     */
    public int throwDice(){
        Random rand = new Random();
        return rand.nextInt(faces)+1;
    }    
}

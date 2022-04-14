package com.monstersaku.srcMove;
import com.monstersaku.elementMonster.ElementType;


public class Move {
    private final int id;
    private final MoveType moveType;
    private final String name;
    private final ElementType elementType;
    private final int accuracy;
    private final int priority;
    private int ammunition;
    private final MoveTarget target;
    // private final int effect;
    // private final Stats statsEffect;

    public Move(int id, MoveType moveType, String name, ElementType elementType, int accuracy, int priority, int ammunition, MoveTarget target){ 
    // int effect, Stats statsEffect) {
        this.id = id;
        this.moveType = moveType;
        this.name = name;
        this.elementType=elementType;
        this.accuracy = accuracy;
        this.priority = priority;
        this.ammunition=ammunition;
        this.target = target;
        // this.effect = effect;
        // this.statsEffect = statsEffect;
    }

    // Setters
    
    public void setAmmunition(int ammunition) {
        this.ammunition= ammunition;
    }

    // Getters
    public int getId() {
        return id;
    }
    public MoveType getMoveType() {
        return moveType;
    }
    public String getName() {
        return name;
    }
    public ElementType getElementType() {
        return elementType;
    }
    public int getAccuracy() {
        return accuracy;
    }
    public int getPriority() {
        return priority;
    }
    public int getAmmunition() {
        return ammunition;
    }
    public MoveTarget getTarget() {
        return target;
    }
    // public int getEffect() {
    //     return effect;
    // }
    // public Stats getStatsEffect() {
    //     return statsEffect;
    // }


    public void printMove(){
        System.out.println("Detail Move : ");
        System.out.println("Id : "+ getId());
        System.out.println("Move Type : "+ getMoveType());
        System.out.println("Name : "+ getName());
        System.out.println("Element Type : "+ getElementType());
        System.out.println("Accuracy : "+ getAccuracy());
        System.out.println("Priority : "+ getPriority()); 
        System.out.println("Ammunition : "+ getAmmunition());
        System.out.println("Target : "+ getTarget());
        // System.out.println("Effect : "+ getEffect());
        // System.out.println("Stats Effect : "+ getStatsEffect());
    }
    
}



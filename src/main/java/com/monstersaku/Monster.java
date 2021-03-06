package com.monstersaku;
import com.monstersaku.elementMonster.*;
import com.monstersaku.srcMove.*;

import java.util.List;
import java.util.Random;

public class Monster {
    //attribute
    private int id;
    private String nama;
    private List<ElementType> elementTypes; //Ini generics
    private Stats baseStats;
    private List<Move> moves;
    private EffectType condition = EffectType.NONE;
    private MonsterState state = MonsterState.ALIVE;
    private int sleepingTime = 0;
    private double maxHP;

    //constructor
    public Monster(int id, String nama, Stats baseStats, List<ElementType> elementTypes, List<Move> moves) {
        this.id = id;
        this.nama = nama;
        this.baseStats = baseStats;
        this.elementTypes = elementTypes;
        this.moves = moves;
        this.maxHP = baseStats.getHP();
    }   

    //Setter
    public void setID(int id){
        this.id = id;
    }
    
    public double getMaxHP(){
        return this.maxHP;
    }
    //get name of monster
    public void setName(String nama) {
        this.nama = nama;
    }

    /**Stats and Buff of Monster **/
    //get stats of monster
    public void setStats(Stats baseStats) {
        this.baseStats = baseStats;
    }

    /**Element Types of Monster**/
    //get list of element type of monster
    public void setElementTypes(List<ElementType> elementTypes) {
        this.elementTypes = elementTypes;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Move getMoveByID(int id){
        return moves.get(id);
    }

    //get id of monster
    public int getID(){
        return this.id;
    }
    
    //get name of monster
    public String getName() {
        return this.nama;
    }

    /**Stats and Buff of Monster **/
    //get stats of monster
    public Stats getStats() {
        return this.baseStats;
    }
    
    public Stats getStatsCur(Monster monster) {
        return monster.baseStats;
    }

    public int getSleepingTime(){
        return this.sleepingTime;
    }

    /**Element Types of Monster**/
    //get list of element type of monster
    public List<ElementType> getElementTypes() {
        return this.elementTypes;
    }
    public ElementType getElementType() {
        return this.elementTypes.get(0);
    }
    //add element type of monster
    public void addElementType(ElementType type) {
        this.elementTypes.add(type);
    }

    /**Moves of Monster **/
    //get list of move of monster
    public List<Move> getMoves() {
        return this.moves;
    }
    //add move of monster
    public void addMove(Move move) {
        this.moves.add(move);
    }
    //print info move
    public void printMonsterMoves() {
        System.out.println("List of Move:");
        for(int i = 0; i < this.getMoves().size(); i++){
            this.moves.get(i).printMove();
        }
    }
    public void printMonsterNamaMoves() {
        System.out.println("List of Move:");
        for(int i = 0; i < this.getMoves().size(); i++){
            String namaMove = this.moves.get(i).getName();
            System.out.println(i+1 +". "+ namaMove);
        }
    }

    /** Condition of Monster **/
    public void setAffectedBy(EffectType effect) {
        this.condition = effect;
        if (effect == EffectType.SLEEP) {
            Random r = new Random();
            this.sleepingTime = 1 + r.nextInt(7);
        }

    }
    public EffectType getAffectedBy() {
        return this.condition;
    }
    public void minusSleepingTime() {
        if (this.sleepingTime > 0) {
            this.sleepingTime--;
        } else {
            this.condition = EffectType.NONE;
        }
    }

    /**State of Monster**/
    public MonsterState getMonsterState() {
        return this.state;
    }
    public void knockOut(Monster monster) {
        monster.state = MonsterState.DEAD;
    }
    public boolean isAlive() {
        return this.state == MonsterState.ALIVE;
    }

    public void printNamaMonster() {
        System.out.println("Monster " + this.getName());
    }
    public void printHPMonster(Monster monster){
        System.out.println("HP " + monster.getName() + ": " + this.baseStats.getHP());
    }

    public void printInfoMonster() {
        System.out.println("Monster " + this.getName());
        System.out.println(this.getElementTypes());
        this.getStats().printStats();
        this.printMonsterNamaMoves();
    }
    
    public void printInfoMonsterNoMoves() {
        System.out.println("Monster " + this.getName());
        System.out.println(this.getElementTypes());
        this.getStats().printStats();
    }

    public void BurnEffect(Monster target){
        target.getStats().setHP(target.getStats().getHP()-Math.floor(target.getStats().getHP()*0.125));
    }
    public void PoisonEffect(Monster target){
        target.getStats().setHP(target.getStats().getHP()-Math.floor(target.getStats().getHP()*0.0625));
    }
    
}


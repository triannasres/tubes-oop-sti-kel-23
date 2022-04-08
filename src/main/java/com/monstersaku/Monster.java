//import ElementType
//import Stats
//import Move
//import EffectType
//import MonsterState

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Monster {
    //attribute
    private final String nama;
    private List<ElementType> elementTypes;
    private Stats baseStats;
    private List<Move> moves;
    private EffectType condition = EffectType.NONE;
    private MonsterState state = MonsterState.ALIVE;
    private int sleepingTime = 0;

    //constructor
    public Monster(String nama, Stats baseStats, List<ElementType> elementTypes, List<Move> moves) {
        this.nama = nama;
        this.baseStats = baseStats;
        this.elementTypes = elementTypes;
        this.moves = moves;
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

    /**Element Types of Monster**/
    //get list of element type of monster
    public List<ElementType> getElementTypes() {
        return this.elementTypes;
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

    /** Condition of Monster **/
    public EffectType setAffectedBy(EffectType effect) {
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
    public boolean isAlive() {
        return this.state == MonsterState.ALIVE;
    }

    public void printInfoMonster() {
        System.out.println("Monster " + this.getName());
        this.getElementTypes();
        this.getStats().printStats();
        this.printMonsterMoves();
    }
}


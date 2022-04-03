//import com.monstersaku.elementMonster.*;
//import com.monstersaku.util.*;
//import java.util.ArrayList;
//import java.util.List;

public class Monster {
    //attribute
    private String nama;
    private List<ElementType> elementTypes;
    private Stats baseStats;
    private List<Move> moves;

    //constructor
    public Monster(String nama, Stats baseStats) {
        this.nama = nama;
        this.baseStats = baseStats;
        this.elementTypes = new ArrayList<ElementType>();
        this.moves = new ArrayList<Move>();
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
    //print element type of monster
    public void printElementTypesMonster() {
        List<ElementType> element = this.getElementTypes();
        System.out.println("Element Type of " + this.nama + " :");
        for (int i = 0; i < element.size(); i++) {
            switch(element) {
                case NORMAL:
                    System.out.println((i+1) + ". " + "NORMAL");
                case FIRE:
                    System.out.println((i+1) + ". " + "FIRE");
                case WATER:
                    System.out.println((i+1) + ". " + "WATER");
                case GRASS:
                    System.out.println((i+1) + ". " + "GRASS");
            }
        }
    }

    /**Moves of Monster **/
    //get list of move of monster
    /*public List<Move> getMoves() {
        return this.moves;
    }
    //add move of monster
    public void addMove(Move move) {
        this.moves.add(move);
    }
    //print move of monster
    public void printMovesMonster() {
        System.out.println("Move List of " + this.nama + " :");
        for (int i = 0; i < this.moves.size(); i++) {
        }
    }*/

}
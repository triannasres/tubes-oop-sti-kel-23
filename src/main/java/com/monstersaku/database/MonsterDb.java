package com.monstersaku.database;

import java.util.*;

import com.monstersaku.Monster;
import com.monstersaku.Stats;
import com.monstersaku.elementMonster.*;
import com.monstersaku.srcMove.*;
import com.monstersaku.util.*;

public class MonsterDb {
    public static List<Monster> monsters = new ArrayList<Monster>();
    // private int activeMonster;
    static CSVReader reader;

    public static void setReader(CSVReader reader){
        MonsterDb.reader= reader;
    }

    public MonsterDb(MoveDb moveDatabase){
        try{
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();
            // System.out.println("=========== CONTENT START ===========");
            for (String[] line : lines) {
                List<Move> moves = new ArrayList<Move>();
                List<ElementType> elements = new ArrayList<ElementType>();
                int id = Integer.parseInt(line[0]);
                String name = line[1];
                String[] lineElementTypes = line[2].split(",");
                elements.add(ElementType.valueOf(lineElementTypes[0]));
                int elementCount = lineElementTypes.length;
                if(elementCount == 2){
                    elements.add(ElementType.valueOf(lineElementTypes[1]));
                }
                else{
                    //do nothing
                }
                String[] lineStats = line[3].split(",");
                Stats stats = new Stats(
                    Double.parseDouble(lineStats[0]),
                    Double.parseDouble(lineStats[1]),
                    Double.parseDouble(lineStats[2]),
                    Double.parseDouble(lineStats[3]),
                    Double.parseDouble(lineStats[4]),
                    Double.parseDouble(lineStats[5])
                    );
                String[] lineMove = line[4].split(",");
                // Stats statsEffect = new Stats(0, 0, 0, 0, 0, 0);
                // int moveId = Integer.parseInt(lineMove[0]);
                for(int i = 0;i < lineMove.length;i++){
                    int lineMoveInt = Integer.parseInt(lineMove[i]); 
                    Move move  = moveDatabase.getMoveByID(lineMoveInt-1);
                    moves.add(move);
                }

                // for (String word : line) {

                //     System.out.printf("%s ", word);
                // }
                monsters.add(new Monster(id, name, stats, elements, moves));
                // System.out.println();
            }
            // System.out.println("=========== CONTENT END =============");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
    }

    
}
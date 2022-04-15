package com.monstersaku.database;
import java.util.ArrayList;
import java.util.List;

import com.monstersaku.elementMonster.ElementType;
import com.monstersaku.srcMove.*;
import com.monstersaku.Stats;
import com.monstersaku.util.CSVReader;

public class MoveDb {
    public static List<Move> moves;
    static CSVReader reader;
    
    public static void setReader(CSVReader csvReader){
        MoveDb.reader= csvReader;
    }

    public MoveDb(){
        try {
            reader.setSkipHeader(true);
            moves = new ArrayList<Move>();
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();
            // System.out.println("=========== CONTENT START ===========");
            for (String[] line : lines) {
                Move move;
                int id = Integer.parseInt(line[0]);
                MoveType moveType = MoveType.valueOf(line[1]);
                String name = line[2];
                ElementType elementType = ElementType.valueOf(line[3]);
                int accuracy = Integer.parseInt(line[4]);
                int priority = Integer.parseInt(line[5]);
                int ammunition = Integer.parseInt(line[6]);
                MoveTarget target = MoveTarget.valueOf(line[7]);
                String[] lineStats = line[9].split(",");
                    Stats stats = new Stats(
                    Double.parseDouble(lineStats[0]),
                    Double.parseDouble(lineStats[1]),
                    Double.parseDouble(lineStats[2]),
                    Double.parseDouble(lineStats[3]),
                    Double.parseDouble(lineStats[4]),
                    Double.parseDouble(lineStats[5])
                    );
                if (moveType == MoveType.NORMAL) {
                    if(line[8].equals("-")) {
                        EffectType statusCondition = EffectType.NONE;
                        move = new StatsMove(id, moveType, name, elementType, accuracy, priority, ammunition, target, statusCondition, stats);
                    }
                    else{
                        int basePower = Integer.parseInt(line[8]);
                        move = new NormalMove(id, moveType, name, elementType, accuracy, priority, ammunition, target, basePower);
                    }
                } else if (moveType == MoveType.DEFAULT) {
                        move = new DefaultMove(elementType);
                } else if (moveType == MoveType.SPECIAL) {
                        int basePower = Integer.parseInt(line[8]);
                        move = new SpecialMove(id, moveType, name, elementType, accuracy, priority, ammunition, target, basePower);
                } else {
                    EffectType statusCondition;
                    if(line[8].equals("-")) {
                        statusCondition = EffectType.NONE;
                        stats.setStatsBuff((int)stats.getHP(), (int)stats.getAtk(), (int)stats.getDef(), (int)stats.getSpAtk(), (int)stats.getSpDef(), (int)stats.getSpd());
                    } else {
                        statusCondition = EffectType.valueOf(line[8]);
                    }
                    move = new StatsMove(id, moveType, name, elementType, accuracy, priority, ammunition, target, statusCondition, stats);
                }
                // for (String word : line) {
                //     System.out.printf("%s ", word);
                // }
                // System.out.println();

                MoveDb.moves.add(move);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());   
        }
    }

    public void add(Move move){
        moves.add(move);
    }

    public int amountMove(){
        return moves.size();
    }

    public Move getMoveByID(int id){
        return moves.get(id);
    }

    public static MoveType determineMoveType(Move moveChosen){
        MoveType mType = MoveType.DEFAULT;
        try{
            if(MoveType.valueOf("NORMAL") == moveChosen.getMoveType()){
                mType = MoveType.NORMAL;
            }
            else if(MoveType.valueOf("SPECIAL") == moveChosen.getMoveType()){
                mType = MoveType.SPECIAL;
                
            }
            else if(MoveType.valueOf("STATUS") == moveChosen.getMoveType()){
                // StatsMove.StatsEffect(defenseMonster);
                mType = MoveType.STATUS;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return mType;

    }

}

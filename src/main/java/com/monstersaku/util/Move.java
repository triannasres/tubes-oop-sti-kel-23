package com.monstersaku.util;

public class Move {
    private String name;
    private ElementType elementType;
    private int accuracy;
    private int priority;
    private int ammunition;
    private int jenisMove; 
    /* jenisMove menyatakan angka berdasar move yang dipilih 
    1 = normal move, 
    2 = special move, 
    3 = default move, 
    4 = status move.  */
    private int basePower;
    private double elementEffectivity;
    private double burn;
    private Stats effect;
    private EffectType effectType;

    public Move(String name, ElementType elementType, int accuracy, int priority, int ammunition, int jenisMove, int basePower, Stats effect, EffectType effectType) {
        this.name = name;
        this.elementType = elementType;
        this.accuracy = accuracy;
        this.priority = priority;
        this.ammunition;
        this.jenisMove = jenisMove;
        this.basePower = basePower; 
        this.effect=effect;
        this.effectType = effectType;
    }
    public void actionMove(Mons movedMons, Mons target){
        if(jenisMove == 1){
            // normal move
            if(ammunition!=0){
                double damage = (double)Math.floor((basePower*(movedMons.getStats().getAttack()/(target.getStats().getDefense())+2))*elementEffectivity*burn);
                target.getStats().setHP(target.getStats().getHP()-damage);
            }else{
                // ammunition = 0
                System.out.println("Amunisi telah habis");
            }
        }else if(jenisMove == 2){
            // special moved
            if(ammunition!=0){
                double damage = (double)Math.floor((basePower*(movedMons.getStats().getSpecialAttack()/(target.getStats().getSpecialDefense())+2))*(0.85+Math.random()*0.15)*elementEffectivity*burn);
                target.getStats().setHP(target.getStats().getHP()-damage);
            }else{
                // ammunition = 0
                System.out.println("Amunisi telah habis");
            }
        }else if (jenisMove == 3){
            // default move
            double damage = (double)Math.floor((50*(movedMons.getStats().getAttack()/(target.getStats().getDefense())+2))*(0.85+Math.random()*0.15)*elementEffectivity*burn);
            target.getStats().setHP(target.getStats().getHP()-damage);
            movedMons.getStats().setHP(movedMons.getStats().getHP()-Math.floor(movedMons.getStats().getMaxHP()*0.25));
        }else if(jenisMove == 4){

        }
    }
}

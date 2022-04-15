package com.monstersaku.srcMove;
import com.monstersaku.elementMonster.*;
import com.monstersaku.Monster;
import com.monstersaku.database.ElementDb;

public class SpecialMove extends Move {
    private final int basePower;
    public SpecialMove(int id, MoveType moveType, String name, ElementType elementType, int accuracy, int priority, int ammunition, MoveTarget target, int basePower){
        super(id, moveType, name, elementType, accuracy, priority, ammunition, target);
        this.basePower = basePower;
    }
    ElementDb elementDb = new ElementDb();
    public void SpecialAttack(Monster movedMons, Move moveChosen, Monster target){
        double damage = 0;
        if(getAmmunition()!=0){
            double effectivity = elementDb.getEffectivity(moveChosen.getElementType(),target.getElementType());
            damage = (double)Math.floor((basePower*effectivity*(movedMons.getStats().getSpAtk()/(target.getStats().getSpDef())+2))*(0.85+Math.random()*0.15));
            target.getStats().setHP(target.getStats().getHP()-damage);
            setAmmunition(getAmmunition()-1);
        }else{
            // ammunition = 0
            System.out.println("Amunisi telah habis");
        }
    }
}

package com.monstersaku.srcMove;
import com.monstersaku.elementMonster.*;
import com.monstersaku.Monster;

public class SpecialMove extends Move {
    private final int basePower;
    public SpecialMove(int id, MoveType moveType, String name, ElementType elementType, int accuracy, int priority, int ammunition, MoveTarget target, int basePower){
        super(id, moveType, name, elementType, accuracy, priority, ammunition, target);
        this.basePower = basePower;
    }
    public void SpecialAttack(Monster movedMons, Monster target){
        if(getAmmunition()!=0){
            double damage = (double)Math.floor((basePower*(movedMons.getStats().getSpAtk()/(target.getStats().getSpDef())+2))*(0.85+Math.random()*0.15));
            target.getStats().setHP(target.getStats().getHP()-damage);
        }else{
            // ammunition = 0
            System.out.println("Amunisi telah habis");
        }
    }
}

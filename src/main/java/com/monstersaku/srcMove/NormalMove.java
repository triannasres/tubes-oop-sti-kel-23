package com.monstersaku.srcMove;
import com.monstersaku.elementMonster.ElementType;
import com.monstersaku.Monster;

public class NormalMove extends Move {
    private final int basePower;
    public NormalMove(int id, MoveType moveType, String name, ElementType elementType, int accuracy, int priority, int ammunition, MoveTarget target, int basePower){
        super(id, moveType, name, elementType, accuracy, priority, ammunition, target);
        this.basePower = basePower;
    }
    public void NormalAttacl(Monster movedMons, Monster target){
        if(getAmmunition()!=0){
            double damage = (double)Math.floor((basePower*(movedMons.getStats().getAtk()/(target.getStats().getDef())+2)));
            target.getStats().setHP(target.getStats().getHP()-damage);
        }else{
            // ammunition = 0
            System.out.println("Amunisi telah habis");
        }

    }
}

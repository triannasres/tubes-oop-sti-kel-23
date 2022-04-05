package com.monstersaku.srcMove;
import com.monstersaku.elementMonster.ElementType;
import com.monstersaku.Stats;


public class DefaultMove extends Move{
    public DefaultMove(ElementType elementType){
        super(0, MoveType.NORMAL, "Default Move", elementType, 100, 0, 999, MoveTarget.ENEMY);
    }
    public void DefaultAttack(Monster movedMons, Monster target){
        double damage = (double)Math.floor((50*(movedMons.getStats().getAttack()/(target.getStats().getDefense())+2))*(0.85+Math.random()*0.15));
        target.getStats().setHP(target.getStats().getHP()-damage);
        movedMons.getStats().setHP(movedMons.getStats().getHP()-Math.floor(movedMons.getStats().getMaxHP()*0.25));
    }
}
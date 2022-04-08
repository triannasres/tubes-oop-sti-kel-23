package com.monstersaku.srcMove;
import com.monstersaku.elementMonster.*;
import com.monstersaku.Stats;
import com.monstersaku.Monster;

public class StatsMove extends Move{
    private final EffectType effectType;
    private final Stats stats;

    public StatsMove(int id, MoveType moveType, String name, ElementType elementType, int accuracy, int priority, int ammunition, MoveTarget target,EffectType effectType, Stats stats){
        super(id, moveType, name, elementType, accuracy, priority, ammunition, target);
        this.effectType = effectType;
        this.stats = stats;
    }
    public EffectType getEffectype() {
        return effectType;
    }
    public Stats getStats() {
        return stats;
    }
    public void BurnEffect(Monster target){
        target.getStats().setHP(target.getStats().getHP()-Math.floor(target.getStats().getHP()*0.125));
    }
    public void PoisonEffect(Monster target){
        target.getStats().setHP(target.getStats().getHP()-Math.floor(target.getStats().getHP()*0.0625));
    }
    public int SleepEffect(){
        int sleepTurn = (int)(Math.random()*6)+1; 
        return sleepTurn;
    }
    public void ParalyzeEffect(Monster target){
        target.getStats().setSpd(target.getStats().getSpd()-(target.getStats().getSpd()*0.5));
        // belum chance kena sleep
    }
    public void StatsEffect(Monster target){
        target.setAffectedBy(effectType);
        target.getStats().setHP(target.getStats().getHP()+((stats.getHP()/100)*target.getStats().getHP()));
        target.getStats().setAtk(target.getStats().getAtk()+((stats.getAtk())));
        target.getStats().setDef(target.getStats().getDef()+((stats.getDef())));
        target.getStats().setSpAtk(target.getStats().getSpAtk()+((stats.getSpAtk())));
        target.getStats().setSpDef(target.getStats().getSpDef()+((stats.getSpDef())));
        target.getStats().setSpd(target.getStats().getSpd()+((stats.getSpd())));
    }
}
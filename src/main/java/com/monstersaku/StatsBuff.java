package com.monstersaku;

public interface StatsBuff {
    public void atkBuff(Monster monster, int attackBuff);
    public void defBuff(Monster monster, int defenseBuff);
    public void spAtkBuff(Monster monster, int specialAttackBuff);
    public void spDefBuff(Monster monster, int specialDefenseBuff);
    public void speedBuff(Monster monster, int speedBuff);
}

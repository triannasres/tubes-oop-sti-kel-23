package com.monstersaku;

public interface StatsBuff {
    public void atkBuff(double attack, int attackBuff);
    public void defBuff(double defense, int defenseBuff);
    public void spAtkBuff(double specialAttack, int specialAttackBuff);
    public void spDefBuff(double specialDefense, int specialDefenseBuff);
    public void speedBuff(double speed, int speedBuff);
}

package com.monstersaku;


public class Stats implements StatsBuff{
    private double healthPoint;
    private double attack;
    private double defense;
    private double specialAttack;
    private double specialDefense;
    private double speed;

    private int HPBuff;
    private int attackBuff;
    private int defenseBuff;
    private int specialAttackBuff;
    private int specialDefenseBuff;
    private int speedBuff;

    public Stats(double hp, double atk, double def, double spAtk, double spDef, double spe){
        this.healthPoint = hp;
        this.attack = atk;
        this.defense = def;
        this.specialAttack = spAtk;
        this.specialDefense = spDef;
        this.speed = spe;
        this.HPBuff = 0;
        this.attackBuff = 0;
        this.defenseBuff = 0;
        this.specialAttackBuff = 0;
        this.specialDefenseBuff = 0;
        this.speedBuff = 0;
    }

    // public Stats(List<Double> statsList) {
    //     this.statsList = statsList;
    // }

    public void setHP(double healthPoint){
        this.healthPoint = healthPoint;
    }
    
    public void setAtk(double attack){
        this.attack = attack;
    }
    public void setDef(double defense){
        this.defense = defense;
    }
    public void setSpAtk(double specialAttack){
        this.specialAttack = specialAttack;
    }
    public void setSpDef(double specialDefense){
        this.specialDefense = specialDefense;
    }
    public void setSpd(double speed){
        this.speed = speed;
    }

    public double getHP(){
        return this.healthPoint;
    }
    
    public double getAtk(){
        return this.attack;
    }
    public double getDef(){
        return this.defense;
    }
    public double getSpAtk(){
        return this.specialAttack;
    }
    public double getSpDef(){
        return this.specialDefense;
    }
    public double getSpd(){
        return this.speed;
    }
    public int getHPBuff(){
        return this.HPBuff;
    }
    public int getAtkBuff(){
        return this.attackBuff;
    }
    public int getDefBuff(){
        return this.defenseBuff;
    }
    public int getSpAtkBuff(){
        return this.specialAttackBuff;
    }
    public int getSpDefBuff(){
        return this.specialDefenseBuff;
    }
    public int getSpdBuff(){
        return this.speedBuff;
    }

    public void heal(Monster monster, double healthPoint){
        this.healthPoint += healthPoint;
    }
    
    public void setStatsBuff(int HPBuff, int attackBuff, int defenseBuff, int spAtkBuff, int spDefBuff, int speedBuff){
        this.HPBuff = HPBuff;
        this.attackBuff += attackBuff;
        this.defenseBuff += defenseBuff;
        this.specialAttackBuff += spAtkBuff;
        this.specialDefenseBuff += spDefBuff;
        this.speedBuff += speedBuff;
    }

    public void doStatsBuff(Monster monster){
        heal(monster, HPBuff);
        atkBuff(monster, attackBuff);
        defBuff(monster, defenseBuff);
        spAtkBuff(monster, specialAttackBuff);
        spDefBuff(monster, specialDefenseBuff);
        speedBuff(monster, speedBuff);
    }

    //ntar input base statsnya biar abis dibuff/debuff hasilnya tetap
    //nanti kalo udah pelajarin csv reader gua masukin stats 
    public void atkBuff(Monster monster, int attackBuff){ 
        if(attackBuff == -4){
            setAtk(monster.getStats().getAtk()*2/6);
        }
        else if (attackBuff == -3){
            setAtk(monster.getStats().getAtk()*2/5);
        }
        else if (attackBuff == -2){
            setAtk(monster.getStats().getAtk()*2/4);
        }
        else if (attackBuff == -1){
            setAtk(monster.getStats().getAtk()*2/3);
        }
        else if (attackBuff == 0){;
            setAtk(monster.getStats().getAtk()*1);
        }
        else if (attackBuff == 1){
            setAtk(monster.getStats().getAtk()*3/2);
        }
        else if (attackBuff == 2){
            setAtk(monster.getStats().getAtk()*4/2);
        }
        else if (attackBuff == 3){
            setAtk(monster.getStats().getAtk()*5/2);
        }
        else if (attackBuff == 4){
            setAtk(monster.getStats().getAtk()*6/2);
        }
    }
    public void defBuff(Monster monster, int defenseBuff){ 
        if(defenseBuff == -4){
            setDef(monster.getStats().getDef()*2/6);
        }
        else if (defenseBuff == -3){
            setDef(monster.getStats().getDef()*2/5);
        }
        else if (defenseBuff == -2){
            setDef(monster.getStats().getDef()*2/4);
        }
        else if (defenseBuff == -1){
            setDef(monster.getStats().getDef()*2/3);
        }
        else if (defenseBuff == 0){;
            setDef(monster.getStats().getDef()*2/2);
        }
        else if (defenseBuff == 1){
            setDef(monster.getStats().getDef()*3/2);
        }
        else if (defenseBuff == 2){
            setDef(monster.getStats().getDef()*4/2);
        }
        else if (defenseBuff == 3){
            setDef(monster.getStats().getDef()*5/2);
        }
        else if (defenseBuff == 4){
            setDef(monster.getStats().getDef()*6/2);
        }
    }
    public void spAtkBuff(Monster monster, int specialAttackBuff){ 
        if(specialAttackBuff == -4){
            setSpAtk(monster.getStats().getSpAtk()*2/6);
        }
        else if (specialAttackBuff == -3){
            setSpAtk(monster.getStats().getSpAtk()*2/5);
        }
        else if (specialAttackBuff == -2){
            setSpAtk(monster.getStats().getSpAtk()*2/4);
        }
        else if (specialAttackBuff == -1){
            setSpAtk(monster.getStats().getSpAtk()*2/3);
        }
        else if (specialAttackBuff == 0){;
            setSpAtk(monster.getStats().getSpAtk()*2/2);
        }
        else if (specialAttackBuff == 1){
            setSpAtk(monster.getStats().getSpAtk()*3/2);
        }
        else if (specialAttackBuff == 2){
            setSpAtk(monster.getStats().getSpAtk()*4/2);
        }
        else if (specialAttackBuff == 3){
            setSpAtk(monster.getStats().getSpAtk()*5/2);
        }
        else if (specialAttackBuff == 4){
            setSpAtk(monster.getStats().getSpAtk()*6/2);
        }
    }
    public void spDefBuff(Monster monster, int specialDefenseBuff){ 
        if(specialDefenseBuff == -4){
            setSpDef(monster.getStats().getSpd()*2/6);
        }
        else if (specialDefenseBuff == -3){
            setSpDef(monster.getStats().getSpd()*2/5);
        }
        else if (specialDefenseBuff == -2){
            setSpDef(monster.getStats().getSpd()*2/4);
        }
        else if (specialDefenseBuff == -1){
            setSpDef(monster.getStats().getSpd()*2/3);
        }
        else if (specialDefenseBuff == 0){;
            setSpDef(monster.getStats().getSpd()*2/2);
        }
        else if (specialDefenseBuff == 1){
            setSpDef(monster.getStats().getSpd()*3/2);
        }
        else if (specialDefenseBuff == 2){
            setSpDef(monster.getStats().getSpd()*4/2);
        }
        else if (specialDefenseBuff == 3){
            setSpDef(monster.getStats().getSpd()*5/2);
        }
        else if (specialDefenseBuff == 4){
            setSpDef(monster.getStats().getSpd()*6/2);
        }
    }
    public void speedBuff(Monster monster, int speedBuff){ 
        if(speedBuff == -4){
           setSpd(monster.getStats().getSpd()*2/6);
        }
        else if (speedBuff == -3){
            setSpd(monster.getStats().getSpd()*2/5);
        }
        else if (speedBuff == -2){
            setSpd(monster.getStats().getSpd()*2/4);
        }
        else if (speedBuff == -1){
            setSpd(monster.getStats().getSpd()*2/3);
        }
        else if (speedBuff == 0){;
            setSpd(monster.getStats().getSpd()*2/2);
        }
        else if (speedBuff == 1){
            setSpd(monster.getStats().getSpd()*3/2);
        }
        else if (speedBuff == 2){
            setSpd(monster.getStats().getSpd()*4/2);
        }
        else if (speedBuff == 3){
            setSpd(monster.getStats().getSpd()*5/2);
        }
        else if (speedBuff == 4){
            setSpd(monster.getStats().getSpd()*6/2);
        }
    }

    public void decreaseHP(double healthPoint, double damage){
        this.healthPoint -= damage;
    }

    public void printStats(){
        System.out.println("Stats : ");
        System.out.println("HP : " + this.healthPoint);
        System.out.println("Attack : " + this.attack);
        System.out.println("Defense : " + this.defense);
        System.out.println("Special Attack : " + this.specialAttack);
        System.out.println("Special Defense : " + this.specialDefense);
        System.out.println("Speed : " + this.speed);
    }
    public void BurnEffect(Monster target){
        target.getStats().setHP(target.getStats().getHP()-Math.floor(target.getStats().getHP()*0.125));
    }
    public void PoisonEffect(Monster target){
        target.getStats().setHP(target.getStats().getHP()-Math.floor(target.getStats().getHP()*0.0625));
    }
    public void ParalyzeEffect(Monster target){
        target.getStats().setSpd(target.getStats().getSpd()-(target.getStats().getSpd()*0.5));
        // belum chance kena sleep
    }
}

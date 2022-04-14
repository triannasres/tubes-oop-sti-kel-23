package com.monstersaku;


public class Stats implements StatsBuff{
    private double healthPoint;
    private double attack;
    private double defense;
    private double specialAttack;
    private double specialDefense;
    private double speed;

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
    
    public void setStatsBuff(int attackBuff, int defenseBuff, int spAtkBuff, int spDefBuff, int speedBuff){
        this.attackBuff = attackBuff;
        this.defenseBuff = defenseBuff;
        this.specialAttackBuff = spAtkBuff;
        this.specialDefenseBuff = spDefBuff;
        this.speedBuff = speedBuff;
    }

    //ntar input base statsnya biar abis dibuff/debuff hasilnya tetap
    //nanti kalo udah pelajarin csv reader gua masukin stats 
    public void atkBuff(double attack, int attackBuff){ 
        if(attackBuff == -4){
            this.attack = attack * 2/6;
        }
        else if (attackBuff == -3){
            this.attack = attack * 2/5;
        }
        else if (attackBuff == -2){
            this.attack = attack * 2/4;
        }
        else if (attackBuff == -1){
            this.attack = attack * 2/3;
        }
        else if (attackBuff == 0){;
            this.attack = attack * 1;
        }
        else if (attackBuff == 1){
            this.attack = attack * 3/2;
        }
        else if (attackBuff == 2){
            this.attack = attack * 4/2;
        }
        else if (attackBuff == 3){
            this.attack = attack * 5/2;
        }
        else if (attackBuff == 4){
            this.attack = attack * 6/2;
        }
    }
    public void defBuff(double defense, int defenseBuff){ 
        if(defenseBuff == -4){
            this.defense = defense * 2/6;
        }
        else if (defenseBuff == -3){
            this.defense = defense * 2/5;
        }
        else if (defenseBuff == -2){
            this.defense = defense * 2/4;
        }
        else if (defenseBuff == -1){
            this.defense = defense * 2/3;
        }
        else if (defenseBuff == 0){;
            this.defense = defense * 1;
        }
        else if (defenseBuff == 1){
            this.defense = defense * 3/2;
        }
        else if (defenseBuff == 2){
            this.defense = defense * 4/2;
        }
        else if (defenseBuff == 3){
            this.defense = defense * 5/2;
        }
        else if (defenseBuff == 4){
            this.defense = defense * 6/2;
        }
    }
    public void spAtkBuff(double specialAttack, int specialAttackBuff){ 
        if(specialAttackBuff == -4){
            this.specialAttack = specialAttack * 2/6;
        }
        else if (specialAttackBuff == -3){
            this.specialAttack = specialAttack * 2/5;
        }
        else if (specialAttackBuff == -2){
            this.specialAttack = specialAttack * 2/4;
        }
        else if (specialAttackBuff == -1){
            this.specialAttack = specialAttack * 2/3;
        }
        else if (specialAttackBuff == 0){;
            this.specialAttack = specialAttack * 1;
        }
        else if (specialAttackBuff == 1){
            this.specialAttack = specialAttack * 3/2;
        }
        else if (specialAttackBuff == 2){
            this.specialAttack = specialAttack * 4/2;
        }
        else if (specialAttackBuff == 3){
            this.specialAttack = specialAttack * 5/2;
        }
        else if (specialAttackBuff == 4){
            this.specialAttack = specialAttack * 6/2;
        }
    }
    public void spDefBuff(double specialDefense, int specialDefenseBuff){ 
        if(specialDefenseBuff == -4){
            this.specialDefense = specialDefense * 2/6;
        }
        else if (specialDefenseBuff == -3){
            this.specialDefense = specialDefense * 2/5;
        }
        else if (specialDefenseBuff == -2){
            this.specialDefense = specialDefense * 2/4;
        }
        else if (specialDefenseBuff == -1){
            this.specialDefense = specialDefense * 2/3;
        }
        else if (specialDefenseBuff == 0){;
            this.specialDefense = specialDefense * 1;
        }
        else if (specialDefenseBuff == 1){
            this.specialDefense = specialDefense * 3/2;
        }
        else if (specialDefenseBuff == 2){
            this.specialDefense = specialDefense * 4/2;
        }
        else if (specialDefenseBuff == 3){
            this.specialDefense = specialDefense * 5/2;
        }
        else if (specialDefenseBuff == 4){
            this.specialDefense = specialDefense * 6/2;
        }
    }
    public void speedBuff(double speed, int speedBuff){ 
        if(speedBuff == -4){
            this.speed = speed * 2/6;
        }
        else if (speedBuff == -3){
            this.speed = speed * 2/5;
        }
        else if (speedBuff == -2){
            this.speed = speed * 2/4;
        }
        else if (speedBuff == -1){
            this.speed = speed * 2/3;
        }
        else if (speedBuff == 0){;
            this.speed = speed * 1;
        }
        else if (speedBuff == 1){
            this.speed = speed * 3/2;
        }
        else if (speedBuff == 2){
            this.speed = speed * 4/2;
        }
        else if (speedBuff == 3){
            this.speed = speed * 5/2;
        }
        else if (speedBuff == 4){
            this.speed = speed * 6/2;
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
}

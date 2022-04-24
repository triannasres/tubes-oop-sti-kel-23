package com.monstersaku;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.monstersaku.database.*;
// import com.monstersaku.elementMonster.*;
import com.monstersaku.srcMove.*;

public class Pokeman {
    public Monster activeMonster1;
    public Monster activeMonster2;
    public String offensePlayer;
    public String defensePlayer;
    public boolean gameEnd = false;

    public Pokeman(){
        Monster dum = MonsterDb.monsters.get(20);
        Monster[] monsters1Arr = getMonsterPlayer(1);
        Monster[] monsters2Arr = getMonsterPlayer(2, monsters1Arr);
        ArrayList<Monster> monsters1 = new ArrayList<Monster>();
        ArrayList<Monster> monsters2 = new ArrayList<Monster>();
        for(Monster monster : monsters1Arr){
            monsters1.add(monster);
        }
        for(Monster monster : monsters2Arr){
            monsters2.add(monster);
        }
        Scanner pok = new Scanner(System.in);
        System.out.print("Pemain 1, pilih pokeman yang dikeluarkan ke battlefield : ");
        int pokeman1 = pok.nextInt();
        activeMonster1 = monsters1.get(pokeman1-1);
        System.out.println("Pemain 1 mengeluarkan pokeman : "+ activeMonster1.getName()+"\n");
        System.out.print("Pemain 2, pilih pokeman yang dikeluarkan ke battlefield : ");
        int pokeman2 = pok.nextInt();
        activeMonster2 = monsters2.get(pokeman2-1);
        System.out.println("Pemain 2 mengeluarkan pokeman : "+ activeMonster2.getName()+"\n");
        while(gameEnd != true){
            // offensePlayer = "Pemain 2";
            // defensePlayer = "Pemain 1";
            turn(monsters1, monsters2);
        }
    }
    
    private void turn(ArrayList<Monster> monsters1, ArrayList<Monster> monsters2) {
        offensePlayer = "Pemain 1";
        defensePlayer = "Pemain 2";
        Move moveChosen1 = askCommand(monsters1, monsters2, activeMonster1, activeMonster2);
        offensePlayer = "Pemain 2";
        defensePlayer = "Pemain 1";
        Move moveChosen2 = askCommand(monsters2, monsters1, activeMonster2, activeMonster1);
        try{
            if(moveChosen1 == null && moveChosen2 == null){
                //do nothing
            }
            else if(moveChosen1 == null){
                offensePlayer = "Pemain 2";
                defensePlayer = "Pemain 1";
                doMove(monsters2, monsters1, activeMonster2, activeMonster1, moveChosen2);
            }
            else if(moveChosen2 == null){
                offensePlayer = "Pemain 1";
                defensePlayer = "Pemain 2";
                doMove(monsters1, monsters2, activeMonster1, activeMonster2, moveChosen1);
            }
            else if(moveChosen1.getPriority() > moveChosen2.getPriority()){
                offensePlayer = "Pemain 1";
                defensePlayer = "Pemain 2";
                doMove(monsters1, monsters2, activeMonster1, activeMonster2, moveChosen1);
                if(isMonsterDeadNoPrint(monsters2, activeMonster2).equals(MonsterState.ALIVE)){
                    offensePlayer = "Pemain 2";
                    defensePlayer = "Pemain 1";
                    doMove(monsters2, monsters1, activeMonster2, activeMonster1, moveChosen2);
                }
            }
            else if(moveChosen1.getPriority() < moveChosen2.getPriority()){
                offensePlayer = "Pemain 2";
                defensePlayer = "Pemain 1";
                doMove(monsters2, monsters1, activeMonster2, activeMonster1, moveChosen2);
                if(isMonsterDeadNoPrint(monsters1, activeMonster1).equals(MonsterState.ALIVE)){
                    offensePlayer = "Pemain 1";
                    defensePlayer = "Pemain 2";
                    doMove(monsters1, monsters2, activeMonster1, activeMonster2, moveChosen1);
                }
            }
            else if(moveChosen2.getPriority() == moveChosen1.getPriority() && activeMonster1.getStats().getSpd() > activeMonster2.getStats().getSpd()){
                offensePlayer = "Pemain 1";
                defensePlayer = "Pemain 2";
                doMove(monsters1, monsters2, activeMonster1, activeMonster2, moveChosen1);
                if(isMonsterDeadNoPrint(monsters2, activeMonster2).equals(MonsterState.ALIVE)){
                    offensePlayer = "Pemain 2";
                    defensePlayer = "Pemain 1";
                    doMove(monsters2, monsters1, activeMonster2, activeMonster1, moveChosen2);
                }
            }
            else if(moveChosen2.getPriority() == moveChosen1.getPriority() && activeMonster1.getStats().getSpd() < activeMonster2.getStats().getSpd()){
                offensePlayer = "Pemain 2";
                defensePlayer = "Pemain 1";
                doMove(monsters2, monsters1, activeMonster2, activeMonster1, moveChosen2);
                if(isMonsterDeadNoPrint(monsters1, activeMonster1).equals(MonsterState.ALIVE)){
                    offensePlayer = "Pemain 1";
                    defensePlayer = "Pemain 2";
                    doMove(monsters1, monsters2, activeMonster1, activeMonster2, moveChosen1);
                }
            }
            else if(moveChosen2.getPriority() == moveChosen1.getPriority() && activeMonster1.getStats().getSpd() == activeMonster2.getStats().getSpd()){
                offensePlayer = "Pemain 2";
                defensePlayer = "Pemain 1";
                doMove(monsters2, monsters1, activeMonster2, activeMonster1, moveChosen2);
                if(isMonsterDeadNoPrint(monsters1, activeMonster1).equals(MonsterState.ALIVE)){
                    offensePlayer = "Pemain 1";
                    defensePlayer = "Pemain 2";
                    doMove(monsters1, monsters2, activeMonster1, activeMonster2, moveChosen1);
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        afterDamageCalc();
        offensePlayer = "Pemain 2";
        defensePlayer = "Pemain 1";
        if(isMonsterDead(monsters1, activeMonster1).equals(MonsterState.DEAD)){
            switchPokemanIfKnockout(monsters1);
        }
        // MonsterState monsState2 = activeMonster2.getMonsterState();
        offensePlayer = "Pemain 1";
        defensePlayer = "Pemain 2";
        if(isMonsterDead(monsters2, activeMonster2).equals(MonsterState.DEAD)){
            switchPokemanIfKnockout(monsters2);
        }
    }

    public void afterDamageCalc(){
        if(activeMonster1.getAffectedBy().equals(EffectType.BURN)){
            activeMonster1.BurnEffect(activeMonster1);
            System.out.println("After Burn Effect :");
            activeMonster1.printHPMonster(activeMonster1);
        };
        if(activeMonster2.getAffectedBy().equals(EffectType.BURN)){
            activeMonster2.BurnEffect(activeMonster1);
            System.out.println("After Burn Effect :");
            activeMonster2.printHPMonster(activeMonster2);
        };
        if(activeMonster1.getAffectedBy().equals(EffectType.POISON)){
            activeMonster1.PoisonEffect(activeMonster1);
            System.out.println("After Poison Effect :");
            activeMonster1.printHPMonster(activeMonster1);
        };
        if(activeMonster2.getAffectedBy().equals(EffectType.POISON)){
            System.out.println("After Poison Effect :");
            activeMonster2.PoisonEffect(activeMonster1);
            activeMonster2.printHPMonster(activeMonster2);
        };
    }

    public Monster[] getMonsterPlayer(int pemain){
        Monster[] monsters = new Monster[6]; 
        for(int i = 0; i < 6; i++){
            // Asumsi nanti array isi monsters namanya monsters terus array isi 6 monsters punya pemain 1 monsters, 2 monsters2
            int rnd = new Random().nextInt(MonsterDb.monsters.size());
            while (checkin(monsters, MonsterDb.monsters.get(rnd)) || rnd == 20){
                rnd = new Random().nextInt(MonsterDb.monsters.size());
            }
            monsters[i] = MonsterDb.monsters.get(rnd);
        }
        System.out.println("\nPemain " + pemain + " mendapatkan pokeman :");
        for(int i = 0; i < 6; i++){
            monsters[i].printNamaMonster();
        }
        System.out.println();
        // Monster offenseMonster = monsters[0];
        // System.out.println("Pemain " + pemain + " mengeluarkan pokeman : "+ offenseMonster.getName()+"\n");
        return monsters;
    }

    public Monster[] getMonsterPlayer(int pemain, Monster[] monsterArr){
        Monster[] monsters = new Monster[6]; 
        for(int i = 0; i < 6; i++){
            // Asumsi nanti array isi monsters namanya monsters terus array isi 6 monsters punya pemain 1 monsters, 2 monsters2
            int rnd = new Random().nextInt(MonsterDb.monsters.size());
            while (checkin(monsters, MonsterDb.monsters.get(rnd)) || checkin(monsterArr, MonsterDb.monsters.get(rnd)) || rnd == 20){
                rnd = new Random().nextInt(MonsterDb.monsters.size());
            }
            monsters[i] = MonsterDb.monsters.get(rnd);
        }
        System.out.println("\nPemain " + pemain + " mendapatkan pokeman :");
        for(int i = 0; i < 6; i++){
            monsters[i].printNamaMonster();
        }
        System.out.println();
        // Monster offenseMonster = monsters[0];
        // System.out.println("Pemain " + pemain + " mengeluarkan pokeman : "+ offenseMonster.getName()+"\n");
        return monsters;
    }

    public Move askCommand(ArrayList<Monster> monstersOffense, ArrayList<Monster> monstersDefense, Monster offenseMonster, Monster defenseMonster) {
        Scanner com = new Scanner(System.in);
        System.out.println("\n==================== TURN BARU =============================");
        System.out.println("Giliran "+ offensePlayer + " untuk memilih command. Pilih command :");
        System.out.println("1. FIGHT");
        System.out.println("2. POKEMAN");
        System.out.println("3. VIEW MONSTERS INFO");
        System.out.println("4. VIEW GAME INFO");
        String command = com.nextLine();
        int randParalyze = 0;
        if(offenseMonster.getAffectedBy().equals(EffectType.PARALYZE)){
            Random rand = new Random();
            randParalyze = rand.nextInt(100);
        }
        try{
            if(command.equals("1")){
                if(offenseMonster.getSleepingTime() <= 0 && randParalyze <= 75){
                    System.out.println("Move yang dimiliki "+ offenseMonster.getName());
                    offenseMonster.printMonsterNamaMoves();
                    Move moveDipilih = getMoveChosen();
                    int idMoveChosen = moveDipilih.getId();
                    Move moveChosen = MoveDb.moves.get(idMoveChosen-1);
                    return moveChosen;
                    // moveChosen.printMove();
                    //Nanti pilih move terus damage dll gitu lah ya
                }
                else if(offenseMonster.getSleepingTime() > 0){
                    System.out.println(offenseMonster.getName() + "lagi tidur nyenyak");
                    offenseMonster.minusSleepingTime();
                    return null;
                }
                else if(randParalyze > 75){
                    System.out.println(offenseMonster.getName() + "tidak bisa gerak karena PARALYZE");
                    offenseMonster.minusSleepingTime();
                    return null;
                }
            }
            else if(command.equals("2")){
                printPokemanPlayer(offensePlayer, monstersOffense);
                switchPokemanFromChoice(monstersOffense);
                return null;
                // Monster offenseMonsterTemp = offenseMonster;
                // offenseMonster = defenseMonster;
                // defenseMonster = offenseMonsterTemp;
            }
            else if(command.equals("3")){
                System.out.println("Pokeman milik " + defensePlayer);
                for(Monster monster : monstersDefense){
                    monster.printInfoMonster();
                    System.out.println();
                }
                System.out.println("Pokeman milik " + offensePlayer);
                for(Monster monster : monstersOffense){
                    monster.printInfoMonster();
                    System.out.println();
                }
                Move moveChosen = askCommand(monstersOffense, monstersDefense, offenseMonster, defenseMonster);
                return moveChosen;
            }
            else if(command.equals("4")){
                System.out.println(offensePlayer + " memiliki " + offenseMonster.getName() + " sebagai activeMonster dia");
                offenseMonster.printInfoMonsterNoMoves();
                System.out.println();
                System.out.println(defensePlayer + " memiliki " + defenseMonster.getName() + " sebagai activeMonster dia");
                defenseMonster.printInfoMonsterNoMoves();
                Move moveChosen = askCommand(monstersOffense, monstersDefense, offenseMonster, defenseMonster);
                return moveChosen;
            }
            else{
                System.out.println("Masukkan salah, masukkan command lagi");
                Move moveChosen = askCommand(monstersOffense, monstersDefense, offenseMonster, defenseMonster);
                return moveChosen;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    public void doMove(ArrayList<Monster> monstersOffense, ArrayList<Monster> monstersDefense, Monster offenseMonster, Monster defenseMonster, Move moveChosen){
        
        MoveType moveTypeChosen = MoveDb.determineMoveType(moveChosen);
        int rand = new Random().nextInt(100);
                    if(moveChosen.getAccuracy() < rand){
                        System.out.println("Move miss lol");
                        System.out.println();
                        return;
                    }
                    else if(moveTypeChosen.equals(MoveType.NORMAL) && moveChosen.getAccuracy() >= rand){
                        if(moveChosen instanceof NormalMove){
                            ((NormalMove)moveChosen).NormalAttack(offenseMonster, moveChosen, defenseMonster);
                        }
                    }
                    else if(moveTypeChosen.equals(MoveType.SPECIAL) && moveChosen.getAccuracy() >= rand){
                        if(moveChosen instanceof SpecialMove){
                            ((SpecialMove)moveChosen).SpecialAttack(offenseMonster, moveChosen, defenseMonster);
                        }
                    }
                    else if(moveTypeChosen.equals(MoveType.STATUS) && moveChosen.getAccuracy() >= rand){
                        if(moveChosen instanceof StatsMove){
                            StatsMove statsMoveChosen = ((StatsMove)moveChosen);
                            if(statsMoveChosen.getEffectype().equals(EffectType.valueOf("PARALYZE"))){
                                defenseMonster.setAffectedBy(EffectType.PARALYZE);
                                defenseMonster.getStats().ParalyzeEffect(defenseMonster);
                                // statsMoveChosen.ParalyzeEffect(defenseMonster);
                            }
                            if(statsMoveChosen.getEffectype().equals(EffectType.valueOf("BURN"))){
                                defenseMonster.setAffectedBy(EffectType.BURN);
                                // statsMoveChosen.BurnEffect(defenseMonster);
                            }
                            if(statsMoveChosen.getEffectype().equals(EffectType.valueOf("POISON"))){
                                defenseMonster.setAffectedBy(EffectType.POISON);
                                // statsMoveChosen.PoisonEffect(defenseMonster);
                            }
                            if(statsMoveChosen.getEffectype().equals(EffectType.valueOf("SLEEP"))){
                                defenseMonster.setAffectedBy(EffectType.SLEEP);
                            }
                            if(statsMoveChosen.getEffectype().equals(EffectType.valueOf("NONE"))){
                                if(statsMoveChosen.getTarget().equals(MoveTarget.valueOf("OWN"))){
                                    Stats statBuffs = statsMoveChosen.getStats();
                                    int HPBuff = statBuffs.getHPBuff();
                                    int attackBuff = statBuffs.getAtkBuff();
                                    int defenseBuff = statBuffs.getDefBuff();
                                    int spAtkBuff = statBuffs.getSpAtkBuff();
                                    int spDefBuff = statBuffs.getSpDefBuff();
                                    int speedBuff = statBuffs.getSpdBuff();
                                    // offenseMonster.getStats().atkBuff(offenseMonster.getStats().getAtk(), (int)statBuffs.getAtk());
                                    offenseMonster.getStats().setStatsBuff(HPBuff, attackBuff, defenseBuff, spAtkBuff, spDefBuff, speedBuff);
                                    if(offenseMonster.getMaxHP() >= offenseMonster.getStats().getHP()+HPBuff){
                                        offenseMonster.getStats().doStatsBuff(offenseMonster);
                                    }
                                    else{
                                        System.out.println("Already at max health");
                                    }
                                    // offenseMonster.getStats().printStats();
                                    // System.out.println("Nunjukkin stats keubah karena statsBuff");
                                }
                                else if(statsMoveChosen.getTarget().equals(MoveTarget.valueOf("ENEMY"))){
                                    Stats statBuffs = statsMoveChosen.getStats();
                                    int HPBuff = statBuffs.getHPBuff();
                                    int attackBuff = statBuffs.getAtkBuff();
                                    int defenseBuff = statBuffs.getDefBuff();
                                    int spAtkBuff = statBuffs.getSpAtkBuff();
                                    int spDefBuff = statBuffs.getSpDefBuff();
                                    int speedBuff = statBuffs.getSpdBuff();
                                    // offenseMonster.getStats().atkBuff(offenseMonster.getStats().getAtk(), (int)statBuffs.getAtk());
                                    defenseMonster.getStats().setStatsBuff(HPBuff, attackBuff, defenseBuff, spAtkBuff, spDefBuff, speedBuff);
                                    defenseMonster.getStats().doStatsBuff(defenseMonster);
                                    // defenseMonster.getStats().printStats();;
                                    // System.out.println("Nunjukkin stats keubah karena statsBuff");
                                }
                            }
                            // ((StatsMove)moveChosen).StatsEffect(defenseMonster);
                        }
                    }
                    // else if(moveTypeChosen.equals(MoveType.)){

                    // }

                    switch(offenseMonster.getAffectedBy()){
                        case POISON:
                        offenseMonster.getStats().PoisonEffect(offenseMonster);
                        ;
                        case BURN:
                        offenseMonster.getStats().BurnEffect(offenseMonster);
                        ;
                        case NONE:
                        //do nothing
                        ;
                        case PARALYZE:
                        //Udah dieffect di atas
                        ;
                        case SLEEP:
                        //Sama kayak paralyze
                        ;
                    }
                    System.out.println(offenseMonster.getName() + " menggunakan move " + moveChosen.getName());
                    // Menunjukkan HP targetMonster setelah dilakukan move terhadapnya
                    defenseMonster.printHPMonster(defenseMonster);
                    System.out.println();             
    }

    public MonsterState isMonsterDeadNoPrint(ArrayList<Monster> monstersDefense, Monster defenseMonster){
        if(defenseMonster.getStats().getHP() <= 0){
            // System.out.println("Pokeman anda ke-knockout");
            defenseMonster.knockOut(defenseMonster);
            MonsterState monsState = defenseMonster.getMonsterState();
            // monstersDefense.remove(defenseMonster);
            // System.out.println("Ganti Pokeman anda : ");
            // printPokemanPlayerIfKnockout(defensePlayer, monstersDefense);
            return monsState;
        }
        else{
            MonsterState monsState = defenseMonster.getMonsterState();
            return monsState;
        }
    }

    public MonsterState isMonsterDead(ArrayList<Monster> monstersDefense, Monster defenseMonster){
        if(defenseMonster.getStats().getHP() <= 0){
            System.out.println("Pokeman anda ke-knockout\n");
            defenseMonster.knockOut(defenseMonster);
            MonsterState monsState = defenseMonster.getMonsterState();
            monstersDefense.remove(defenseMonster);
            System.out.println("Ganti Pokeman anda : ");
            printPokemanPlayerIfKnockout(defensePlayer, monstersDefense);
            return monsState;
        }
        else{
            MonsterState monsState = defenseMonster.getMonsterState();
            return monsState;
        }
    }

    private void switchPokemanFromChoice(ArrayList<Monster> monsters) {
        Scanner swPFC = new Scanner(System.in);
        System.out.println("Masukkan urutan pokeman yang anda ingin keluarkan ke battlefield : ");
        int pokeman = swPFC.nextInt();
        if(offensePlayer.equals("Pemain 1")){
            activeMonster1 = monsters.get(pokeman-1);
        }
        else if(offensePlayer.equals("Pemain 2")){
            activeMonster2 = monsters.get(pokeman-1);
        }
        // swPFC.close();
    }

    private void switchPokemanIfKnockout(ArrayList<Monster> monsters) {
        Scanner swPIF = new Scanner(System.in);
        System.out.println("Masukkan urutan pokeman yang anda ingin keluarkan ke battlefield : ");
        int pokeman = swPIF.nextInt();
        if(defensePlayer.equals("Pemain 1")){
            activeMonster1 = monsters.get(pokeman-1);
        }
        else if(defensePlayer.equals("Pemain 2")){
            activeMonster2 = monsters.get(pokeman-1);
        }
        // swPIF.close();
    }

    public void printPokemanPlayerIfKnockout(String defensePlayer, ArrayList<Monster> monsters){
        if(defensePlayer.equals("Pemain 1")){
            System.out.println("Pokeman yang dimiliki Pemain 1");
            int i = 1;
            for(Monster monster : monsters){
                if(monster.isAlive()){
                    System.out.print(i+". ");
                    monster.printNamaMonster();
                    i++;
                    }
                }
            System.out.println();
            }
        else if(defensePlayer.equals("Pemain 2")){
            System.out.println("Pokeman yang dimiliki Pemain 2");
            int i = 1;
            for(Monster monster : monsters){
                if(monster.isAlive()){
                    System.out.print(i+". ");
                    monster.printNamaMonster();
                    i++;
                }    
            }
            System.out.println();
        }
        if(monsters.isEmpty()){
            gameEnd = true;
            if(defensePlayer.equals("Pemain 2")){
            System.out.println("Selamat Pemain 1, kamu menang!\nHadiahnya adalah : tidak ada\n");
            printCredits();
            }
            if(defensePlayer.equals("Pemain 1")){
            System.out.println("Selamat Pemain 2, kamu menang!\nHadiahnya adalah : tidak ada\n");
            printCredits();
            }
        }
    }

    public void printPokemanPlayer(String offensePlayer, ArrayList<Monster> monsters){
        if(offensePlayer.equals("Pemain 1")){
            System.out.println("Pokeman yang dimiliki Pemain 1");
            int i = 1;
            for(Monster monster : monsters){
                if(monster.isAlive()){
                    System.out.print(i+". ");
                    monster.printNamaMonster();
                    i++;
                    }
                }
            System.out.println();
            }
        else if(offensePlayer.equals("Pemain 2")){
            System.out.println("Pokeman yang dimiliki Pemain 2");
            int i = 1;
            for(Monster monster : monsters){
                if(monster.isAlive()){
                    System.out.print(i+". ");
                    monster.printNamaMonster();
                    i++;
                }    
            }
            System.out.println();
            }
    }


    private Move getMoveChosen(){
        Scanner mov = new Scanner(System.in);
        System.out.println("Pilih move yang diinginkan : ");
        int idMoveMonster = mov.nextInt();
        System.out.println();
        Monster offenseMonster = null;
        if(offensePlayer.equals("Pemain 1")){
            offenseMonster = activeMonster1;
        }
        else if(offensePlayer.equals("Pemain 2")){
            offenseMonster = activeMonster2;
        }
        List<Move> offenseMoves = offenseMonster.getMoves();
        Move MoveChosen = offenseMoves.get(idMoveMonster-1);
        return MoveChosen;
    }

    private boolean checkin(Monster[] arr, Monster toCheckValue)
    {
        // check if the specified element
        // is present in the array or not
        // using Linear Search method
        boolean test = false;
        for (Monster element : arr) {
            if (element == toCheckValue) {
                test = true;
                break;
            }
        }
        return test;
    }

    public void printCredits(){
        System.out.println("\nTerima kasih sudah bermain game pokeman dari kami");
            System.out.println("Credits : Kelompok 23 IF2212 - Object Oriented Programming");
            System.out.println("18220004 David Hugo Triannas");
            System.out.println("18220024 Aulia Fajriaturrakhmah");
            System.out.println("18220050 Alya Apriliyanti");
            System.out.println("18220072 Muhammad Ammar Murtaqib");
            System.exit(0);
    }
}

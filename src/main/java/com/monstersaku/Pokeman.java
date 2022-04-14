package com.monstersaku;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.monstersaku.database.*;
import com.monstersaku.srcMove.*;

public class Pokeman {
    public Monster offenseMonster;
    public Monster defenseMonster;
    public String offensePlayer;
    public String defensePlayer;
    public boolean gameEnd = false;

    public Pokeman(){
        Monster[] monsters1Arr = getMonsterPlayer(1);
        Monster[] monsters2Arr = getMonsterPlayer(2);
        ArrayList<Monster> monsters1 = new ArrayList<Monster>();
        ArrayList<Monster> monsters2 = new ArrayList<Monster>();
        for(Monster monster : monsters1Arr){
            monsters1.add(monster);
        }
        for(Monster monster : monsters2Arr){
            monsters2.add(monster);
        }
        offenseMonster = monsters1.get(0);
        defenseMonster = monsters2.get(0);
        while(gameEnd != true){
            offensePlayer = "Pemain 1";
            defensePlayer = "Pemain 2";
            turn(monsters1, monsters2);
            offensePlayer = "Pemain 2";
            defensePlayer = "Pemain 1";
            turn(monsters2, monsters1);
        }
    }

    public Monster getOffenseMonster(Monster[] monsters){
        return offenseMonster;
    }
    public Monster getDefenseMonster(Monster[] monsters){
        return defenseMonster;
    }

    public Monster[] getMonsterPlayer(int pemain){
        Monster[] monsters = new Monster[6]; 
        for(int i = 0; i < 6; i++){
            // Asumsi nanti array isi monsters namanya monsters terus array isi 6 monsters punya pemain 1 monsters, 2 monsters2
            int rnd = new Random().nextInt(MonsterDb.monsters.size());
            if (checkin(monsters, MonsterDb.monsters.get(rnd))){
                monsters[i] = MonsterDb.monsters.get(new Random().nextInt(MonsterDb.monsters.size()));
            }
            else{
                monsters[i] = MonsterDb.monsters.get(rnd);
            }
        }
        System.out.println("\nPemain " + pemain + " mendapatkan pokeman :");
        for(int i = 0; i < 6; i++){
            monsters[i].printNamaMonster();
        }
        System.out.println();
        Monster offenseMonster = monsters[0];
        System.out.println("Pemain " + pemain + " mengeluarkan pokeman : "+ offenseMonster.getName()+"\n");
        return monsters;
    }


    public void turn(ArrayList<Monster> monstersOffense, ArrayList<Monster> monstersDefense) {
        Scanner com = new Scanner(System.in);
        System.out.println("\n==================== TURN BARU =============================");
        System.out.println("Giliran "+ offensePlayer + " untuk berjalan. Pilih command :");
        System.out.println("1. FIGHT");
        System.out.println("2. POKEMAN");
        String command = com.nextLine();
        try{
            if(command.equals("1")){
                System.out.println("Move yang dimiliki "+ offenseMonster.getName());
                offenseMonster.printMonsterNamaMoves();
                Move moveChosen = getMoveChosen();
                // int idMoveChosen = moveChosen.getId();

                MoveType moveTypeChosen = MoveDb.determineMoveType(moveChosen); 
                if(moveTypeChosen.equals(MoveType.NORMAL)){
                    if(moveChosen instanceof NormalMove){
                        ((NormalMove)moveChosen).NormalAttack(offenseMonster, defenseMonster);
                    }
                }
                else if(moveTypeChosen.equals(MoveType.SPECIAL)){
                    if(moveChosen instanceof SpecialMove){
                        ((SpecialMove)moveChosen).SpecialAttack(offenseMonster, defenseMonster);
                    }
                }
                else if(moveTypeChosen.equals(MoveType.STATUS)){
                    if(moveChosen instanceof StatsMove){
                        ((StatsMove)moveChosen).StatsEffect(defenseMonster);
                    }
                }
                defenseMonster.printHPMonster(defenseMonster);
                if(defenseMonster.getStats().getHP() <= 0){
                    System.out.println("Pokeman anda ke-knockout");
                    defenseMonster.knockOut(defenseMonster);
                    monstersDefense.remove(defenseMonster);
                    System.out.println("Ganti Pokeman anda : ");
                    printPokemanPlayerIfKnockout(defensePlayer, monstersDefense);
                }
                Monster offenseMonsterTemp = offenseMonster;
                offenseMonster = defenseMonster;
                defenseMonster = offenseMonsterTemp;
                // moveChosen.printMove();
                //Nanti pilih move terus damage dll gitu lah ya
            }
            else if(command.equals("2")){
                printPokemanPlayer(defensePlayer, monstersOffense);
                switchPokemanFromChoice(monstersOffense);
                Monster offenseMonsterTemp = offenseMonster;
                offenseMonster = defenseMonster;
                defenseMonster = offenseMonsterTemp;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void switchPokemanFromChoice(ArrayList<Monster> monsters) {
        Scanner swPFC = new Scanner(System.in);
        System.out.println("Masukkan urutan pokeman yang anda ingin keluarkan ke battlefield : ");
        int pokeman = swPFC.nextInt();
        offenseMonster = monsters.get(pokeman-1);
    }

    private void switchPokemanIfKnockout(ArrayList<Monster> monsters) {
        Scanner swPIF = new Scanner(System.in);
        System.out.println("Masukkan urutan pokeman yang anda ingin keluarkan ke battlefield : ");
        int pokeman = swPIF.nextInt();
        defenseMonster = monsters.get(pokeman-1);
    }

    public void printPokemanPlayerIfKnockout(String defensePlayer, ArrayList<Monster> monsters){
        if(defensePlayer.equals("Pemain 1")){
            System.out.println("Pokeman yang dimiliki "+ defensePlayer);
            int i = 1;
            for(Monster monster : monsters){
                if(monster.isAlive()){
                    System.out.print(i+". ");
                    monster.printNamaMonster();
                    i++;
                    }
                }
            System.out.println();
            switchPokemanIfKnockout(monsters);
            }
        else if(defensePlayer.equals("Pemain 2")){
            System.out.println("Pokeman yang dimiliki "+ defensePlayer);
            int i = 1;
            for(Monster monster : monsters){
                if(monster.isAlive()){
                    System.out.print(i+". ");
                    monster.printNamaMonster();
                    i++;
                }    
            }
            System.out.println();
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
            switchPokemanIfKnockout(monsters);
        }

    }
    public void printPokemanPlayer(String offensePlayer, ArrayList<Monster> monsters){
        if(offensePlayer.equals("Pemain 1")){
            System.out.println("Pokeman yang dimiliki "+ defensePlayer);
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
            System.out.println("Pokeman yang dimiliki "+ offensePlayer);
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
    // private Move doMove(int idMoveChosen){
    //     Move moveChosen;
    //     if()
    //     return moveChosen;
    //     }

    private Move getMoveChosen(){
        Scanner mov = new Scanner(System.in);
        int idMoveMonster = mov.nextInt();
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

package com.monstersaku;

import java.util.Random;
import java.util.Scanner;

import com.monstersaku.database.MonsterDb;

public class Pokeman {
    public Monster activeMonster;
    public String activePlayer;
    public boolean gameEnd = false;

    public Pokeman(){
        Monster[] monsters1 = getMonsterPlayer(1);
        Monster[] monsters2 = getMonsterPlayer(2);
        while(gameEnd != true){
            activePlayer = "Pemain 1";
            activeMonster = monsters1[0];
            turn(monsters1);
            activePlayer = "Pemain 2";
            activeMonster = monsters2[0];
            turn(monsters2);
        }
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
        Monster activeMonster = monsters[0];
        System.out.println("Pemain " + pemain + " mengeluarkan pokeman : "+ activeMonster.getName()+"\n");
        return monsters;
    }

    public Monster[] startGame1(){
        Monster[] monsters1 = getMonsterPlayer(1);
        return monsters1;
    }

    public Monster[] startGame2(){
        Monster[] monsters2 = getMonsterPlayer(2);
        return monsters2;
        // monsters2[0].printInfoMonster();
    }

    public void turn(Monster[] monsters) {
        Scanner com = new Scanner(System.in);
        System.out.println("Giliran Pemain 1 untuk berjalan. Pilih command :");
        System.out.println("1. FIGHT");
        System.out.println("2. POKEMAN");
        String command = com.nextLine();
        try{
            if(command.equals("1")){
                System.out.println("Move yang dimiliki "+ activeMonster.getName());
                activeMonster.printMonsterNamaMoves();
                int moveChosen = com.nextInt();
                // if(moveChosen == 1){
                    
                // }
                // else if(moveChosen == 2){
                    
                // }
                // else if(moveChosen == 3){
                    
                // }
                // else if(moveChosen == 4){
                    
                // } Nanti pilih move terus damage dll gitu lah ya
            }
            else if(command.equals("2")){
                if(activePlayer.equals("Pemain 1")){
                System.out.println("Pokemon yang dimiliki "+ activePlayer);
                int i = 1;
                for(Monster monster : monsters){
                    System.out.print(i+". ");
                    monster.printNamaMonster();
                    i++;
                    }
                System.out.println();
                }
                else if(activePlayer.equals("Pemain 2")){
                System.out.println("Pokemon yang dimiliki "+ activePlayer);
                int i = 1;
                for(Monster monster : monsters){
                    System.out.print(i+". ");
                    monster.printNamaMonster();
                    i++;
                }
                System.out.println();
                }
                com.close();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
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
}

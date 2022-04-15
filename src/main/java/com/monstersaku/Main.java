package com.monstersaku;

import com.monstersaku.database.*;
import com.monstersaku.util.*;
import com.monstersaku.database.MoveDb;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final List<String> CSV_FILE_PATHS = Collections.unmodifiableList(Arrays.asList(
            "configs/movepool.csv",
            "configs/element-type-effectivity-chart.csv",
            "configs/monsterpool.csv"));
    public static void main(String[] args) {
        List<CSVReader> reader = new ArrayList<CSVReader>();
        for (String fileName : CSV_FILE_PATHS) {
            try {
                reader.add(new CSVReader(new File(Main.class.getResource(fileName).toURI()), ";"));
            } 
            catch (Exception e) {
                // do nothing
            }
        }
        MoveDb.setReader(reader.get(0));
        ElementDb.setReader(reader.get(1));
        MonsterDb.setReader(reader.get(2));
        
        MoveDb listMoves = new MoveDb();
        new ElementDb();
        new MonsterDb(listMoves);
        boolean menu = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("=================================================");
        System.out.println("========= SELAMAT DATANG DI GAME PIKMIN =========");
        System.out.println("================== EH POKEMAN ===================");
        System.out.println("=================================================");
        System.out.println("");

        while (menu){
        System.out.println("Keterangan :");
        System.out.println("1. Mulai bermain");
        System.out.println("2. Tutorial");
        System.out.println("3. Exit\n");
        System.out.println("Silahkan masukkan command : ");
        int command = sc.nextInt();

        if(command == 1){
            //masukkin fungsi buat start game
            new Pokeman();
        }

        else if(command == 2){
            printHelp();
        }

        else if(command == 3){
            System.out.println("\nTerima kasih sudah bermain game pokeman dari kami");
            System.out.println("Credits : Kelompok 23 IF2212 - Object Oriented Programming");
            System.out.println("18220004 David Hugo Triannas");
            System.out.println("18220024 Aulia Fajriaturrakhmah");
            System.out.println("18220050 Alya Apriliyanti");
            System.out.println("18220072 Muhammad Ammar Murtaqib");
            System.exit(0);
            
            sc.close();
        }
        }
    }

    // public Move getMoveByID(int id){
    //     return this..get(id-1);
    // }

    public static void printHelp(){
        System.out.println("===========================================================================================");
        System.out.println("Game pokeman ini dibuat untuk pemenuhan tugas besar IF2212 - Object Oriented Programming");
        System.out.println("Game ini adalah turn-based fighting game yang wajib dimainkan 2 orang ");
        System.out.println("Cara bermain game ini adalah dengan mengisi 1 saat permintaan command");
        System.out.println("Kedua pemain akan mendapatkan 6 pokeman yang dapat digunakan untuk fighting");
        System.out.println("Masing-masing pemain akan memilih pokemon yang akan dikeluarkan ke battlefield pertama kali");
        System.out.println("Dalam game, pemain dapat memilih antara FIGHT dan POKEMAN");
        System.out.println("FIGHT akan memungkinkan pemain untuk memilih move yang akan dipakai untuk menyerang");
        System.out.println("POKEMAN akan memungkinkan pemain untuk mengganti pokeman yang sedang aktif di lapangan");
        System.out.println("Jika memilih FIGHT maka move akan dijalankan setelah kedua input pemain diterima");
        System.out.println("dan ditentukan siapa yang jalan dahulu berdasarkan movePriority dan pokemanSpeed");
        System.out.println("Jika setelah melakukan move dan targetPokeman knockout, maka pemain akan harus segera mengganti activePokeman miliknya");
        System.out.println("dan move yang sebelumnya dipilih tidak akan dieksekusi");
        System.out.println("Setiap move dan pokeman memiliki Type yang menentukan effectivity dari suat move yang dilakukan");
        System.out.println("EffectivityType dapat dilihat disini : https://bulbapedia.bulbagarden.net/wiki/Type/Type_chart");
        System.out.println("Ada beberapa EFFECT yang dapat merugikan pokeman yaitu :");
        System.out.println("POISON yang akan mengurangi HP pokeman sebanyak 0.0625*HP tiap turn");
        System.out.println("BURN yang akan mengurangi HP pokeman sebanyak 0.125*HP tiap turn dan \nmengurangi damage sebanyak 1/2*damage");
        System.out.println("PARALYZE yang akan mengurangi speed pokeman sebanyak 0.5*speed dan \nmemiliki kemungkinan 25% untuk pokeman tidak bisa bergerak ");
        System.out.println("SLEEP yang akan membuat pokeman tidak bisa bergerak sebanyak SleepTurn");
        System.out.println("Jika memilih POKEMAN maka akan langsung dieksekusi pertama pada turn sebelum move dieksekusi");
        System.out.println("Game akan selesai jika salah satu pemain sudah tidak memiliki pokeman");
        System.out.println("Maka pemain yang masih memiliki pokeman akan dijadikan pemenang dari pertarungan");
        System.out.println("===========================================================================================");
    }


    // public Monster activeMonster(){
    //     return activemonster;
    // }

    
}

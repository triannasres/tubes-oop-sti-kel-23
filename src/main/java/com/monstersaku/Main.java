package com.monstersaku;

import com.monstersaku.util.*;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {

    private static final List<String> CSV_FILE_PATHS = Collections.unmodifiableList(Arrays.asList(
            "configs/monsterpool.csv",
            "configs/movepool.csv",
            "configs/element-type-effectivity-chart.csv"));

    public static void main(String[] args) {
        for (String fileName : CSV_FILE_PATHS) {
            try {
                System.out.printf("Filename: %s\n", fileName);
                CSVReader reader = new CSVReader(new File(Main.class.getResource(fileName).toURI()), ";");
                reader.setSkipHeader(true);
                List<String[]> lines = reader.read();
                System.out.println("=========== CONTENT START ===========");
                for (String[] line : lines) {
                    for (String word : line) {
                        System.out.printf("%s ", word);
                    }
                    System.out.println();
                }
                System.out.println("=========== CONTENT END =============\n");

            } 
            catch (Exception e) {
                // do nothing
            }
        
        }
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
            startGame();
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

    public static void printHelp(){
        System.out.println("========================================================================================");
        System.out.println("Game pokeman ini dibuat untuk pemenuhan tugas besar IF2212 - Object Oriented Programming");
        System.out.println("Game ini adalah turn-based fighting game yang wajib dimainkan 2 orang ");
        System.out.println("Cara bermain game ini adalah dengan mengisi 1 saat permintaan command");
        System.out.println("Kedua pemain akan mendapatkan 6 pokeman yang dapat digunakan untuk fighting");
        System.out.println("Dalam game, pemain dapat memilih antara FIGHT dan POKEMAN");
        System.out.println("FIGHT akan memungkinkan pemain untuk memilih move yang akan dipakai untuk menyerang");
        System.out.println("POKEMAN akan memungkinkan pemain untuk mengganti pokeman yang sedang aktif di lapangan");
        System.out.println("Game akan selesai jika salah satu pemain sudah tidak memiliki pokeman");
        System.out.println("Maka pemain yang masih memiliki pokeman akan dijadikan pemenang dari pertarungan");
        System.out.println("========================================================================================");
    }

    public static void startGame(){
        Monster[] monsters = new Monster[20]; 
        Monster[] monsters1 = new Monster[6]; 
        Monster[] monsters2= new Monster[6]; 
        System.out.println("Pemain 1 mendapatkan pokeman :");
        for(int i = 0; i < 6; i++){
            // Asumsi nanti array isi monsters namanya monsters terus array isi 6 monsters punya pemain 1 monsters1, 2 monsters2
            int rnd1 = new Random().nextInt(monsters.length);
            int rnd2 = new Random().nextInt(monsters.length);
            if (checkin(monsters1, monsters[rnd1])){
                monsters1[i] = monsters[new Random().nextInt(monsters.length)];
            }
            else{
                monsters1[i] = monsters[rnd1];
            }
            if (checkin(monsters2, monsters[rnd2])){
                monsters2[i] = monsters[new Random().nextInt(monsters.length)];
            }
            else{
                monsters2[i] = monsters[rnd1];
            }
        }
        System.out.println("Pemain 1 mendapatkan pokeman :\n");
        System.out.println("Nama, elementtypes, stats, moves");
        for(int i = 0; i < 6; i++){
            monsters1[i].printInfoMonster();
        }
        System.out.println("Pemain 2 mendapatkan pokeman :\n");
        System.out.println("Nama, elementtypes, stats, moves");
        for(int i = 0; i < 6; i++){
            monsters2[i].printInfoMonster();
        }
    }

    private static boolean checkin(Monster[] arr, Monster toCheckValue)
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

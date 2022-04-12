package com.monstersaku.database;

import java.util.HashMap;
import java.util.List;

import com.monstersaku.elementMonster.ElementType;
import com.monstersaku.util.CSVReader;

public class ElementDb {
    public HashMap<String, Double> elementEffectivityList = new HashMap<String, Double>();
    static CSVReader reader;
    
    public static void setReader(CSVReader csvReader){
        ElementDb.reader = csvReader;
    }
    public ElementDb(){
        try{
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();
            // System.out.println("=========== CONTENT START ===========");
            for (String[] line : lines) {
                try{
                Double effectivity;
                String offense = line[0];
                String defense = line[1];
                effectivity = Double.parseDouble(line[2]);
                elementEffectivityList.put(offense +","+ defense, effectivity);
                // for (String word : line) {
                //     System.out.printf("%s ", word);
                // }
                // System.out.println();
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            // System.out.println("=========== CONTENT END ===========");
            // System.out.println();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public double getEffectivity(ElementType source, ElementType target) {
        String string = source.toString() + "," + target.toString();
        double effectivity = elementEffectivityList.get(string);
        return effectivity;
    }
}

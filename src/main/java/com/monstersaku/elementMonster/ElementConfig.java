package com.monstersaku.elementMonster;

public class ElementConfig {
    
    public ElementType toElement(String str){
        switch (str){
            case "GRASS" : return ElementType.GRASS;
            case "FIRE" : return ElementType.FIRE;
            case "WATER" : return ElementType.WATER;
            case "POISON" : return ElementType.POISON;
            case "DARK" : return ElementType.DARK;
            case "GHOST" : return ElementType.GHOST;
            case "FAIRY" : return ElementType.FAIRY;
            case "BUG" : return ElementType.BUG;
            case "ROCK" : return ElementType.ROCK;
            case "GROUND" : return ElementType.GROUND;
            case "FIGHTING" : return ElementType.FIGHTING;
            case "FLYING" : return ElementType.FLYING;
            case "STEEL" : return ElementType.STEEL;
            case "ICE" : return ElementType.ICE;
            case "ELECTRIC" : return ElementType.ELECTRIC;
            case "PSYCHIC" : return ElementType.PSYCHIC;
            case "DRAGON" : return ElementType.DRAGON;
            case "NORMAL" : return ElementType.NORMAL;
            default : return ElementType.NORMAL;
        }
    }
}

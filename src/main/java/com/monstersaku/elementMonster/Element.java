package com.monstersaku.elementMonster;

public class Element {
    private final ElementType source;
    private final ElementType target;
    private final double effectivity;

    public Element(ElementType source, ElementType target, double effectivity) {
        this.source = source;
        this.target = target;
        this.effectivity = effectivity;
    }

    public ElementType getSource() {
        return this.source;
    }
    public ElementType getTarget() {
        return this.target;
    }
    public double getEffectivity() {
        return  this.effectivity;
    }
}

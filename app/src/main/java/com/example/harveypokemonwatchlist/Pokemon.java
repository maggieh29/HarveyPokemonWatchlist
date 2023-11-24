package com.example.harveypokemonwatchlist;

public class Pokemon {
    private String name, move, ability, id, weight, height, xp;


    public Pokemon(String name, String id, String weight, String height, String xp, String move, String ability){
        this.name = name;
        this.id = id;
        this.weight = weight;
        this.height = height;
        this.xp = xp;
        this.move = move;
        this.ability = ability;
    }

    public String getName() {
        return name;
    }

    public String getMove() {
        return move;
    }

    public String getAbility() {
        return ability;
    }

    public String getId() {
        return id;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public String getXp() {
        return xp;
    }

    @Override
    public String toString(){
        String v = id + "\t" + name;
        return v;
    }
}

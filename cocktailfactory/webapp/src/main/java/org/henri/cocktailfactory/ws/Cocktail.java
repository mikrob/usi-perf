package org.henri.cocktailfactory.ws;

public class Cocktail {
    private String name;
    private String description;

    public Cocktail() {
    }

    public Cocktail(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

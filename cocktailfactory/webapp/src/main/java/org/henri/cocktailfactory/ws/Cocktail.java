package org.henri.cocktailfactory.ws;

public class Cocktail implements Comparable<Cocktail> {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cocktail cocktail = (Cocktail) o;

        if (!name.equals(cocktail.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Cocktail o) {
        return name.compareTo(o.name);
    }
}

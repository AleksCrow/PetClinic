package com.voronkov.model;

/**
 * @author Sanek
 * @since 06.07.2020
 */
public class Pet {

    private String name;
    private final PetType petType;

    public Pet(String name, PetType petType) {
        this.name = name;
        this.petType = petType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", petType=" + petType +
                '}';
    }
}

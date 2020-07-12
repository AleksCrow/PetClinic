package com.voronkov.model;

/**
 * Client
 * @author Sanek
 * @since 05.07.20
 */
public class Client {

    private String name;
    private final Pet pet;

    public Client(String name, Pet pet) {
        this.name = name;
        this.pet = pet;
    }

    public String getName() {
        return this.name;
    }

    public Pet getPet() {
        return this.pet;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", pet=" + pet +
                '}';
    }
}

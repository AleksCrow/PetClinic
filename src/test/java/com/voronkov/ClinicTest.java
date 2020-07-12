package com.voronkov;

import com.voronkov.model.Client;
import com.voronkov.model.Pet;
import com.voronkov.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClinicTest {

    public Clinic clinicTest = new Clinic();

    public final Client CLIENT1 = new Client("John", new Pet("Sparky", PetType.DOG));
    public final Client CLIENT2 = new Client("Ann", new Pet("Jane", PetType.CAT));
    public final String CLIENT_NAME = "John";
    public final String PET_NAME = "Sparky";

    public final List<Client> clients = new ArrayList<>();

    @BeforeEach
    public void init() {
        if (!clients.isEmpty())
            clients.removeAll(clients);
        clients.add(CLIENT1);
        clients.add(CLIENT2);
    }

    @Test
    public void findClientByName() {
        InputStream in = new ByteArrayInputStream(CLIENT_NAME.getBytes());
        Client client = this.clinicTest.findClientByName(in, clients);
        assertEquals(CLIENT1, client);
    }

    @Test
    public void findClientByPetName() {
        InputStream in = new ByteArrayInputStream(PET_NAME.getBytes());
        Client client = this.clinicTest.findClientByPetName(in, clients);
        assertEquals(CLIENT1, client);
    }

    @Test
    void renameClient() {
        InputStream inOld = new ByteArrayInputStream(CLIENT_NAME.getBytes());
        InputStream inNew = new ByteArrayInputStream("Bob".getBytes());
        this.clinicTest.renameClient(inOld, inNew, clients);
        Client client = this.clinicTest.findClient("Bob", clients);
        assertEquals("Bob", client.getName());
    }

    @Test
    void renamePet() {
        InputStream inOld = new ByteArrayInputStream(CLIENT_NAME.getBytes());
        InputStream inNew = new ByteArrayInputStream("Rex".getBytes());
        this.clinicTest.renamePet(inOld, inNew, clients);
        Client client = this.clinicTest.findClient(CLIENT_NAME, clients);
        assertEquals("Rex", client.getPet().getName());
    }

    @Test
    void removeClient() {
        InputStream in = new ByteArrayInputStream(CLIENT_NAME.getBytes());
        this.clinicTest.removeClient(in, clients);
        assertEquals(clients.get(0), CLIENT2);
    }

    @Test
    public void findClient() {
        Client client = this.clinicTest.findClient(CLIENT_NAME, clients);
        System.out.println(clients);
        assertEquals(CLIENT1, client);
    }

    @Test
    public void findPetOfClient() {
        Client client = this.clinicTest.findPetOfClient(PET_NAME, clients);
        assertEquals(CLIENT1, client);
    }
}
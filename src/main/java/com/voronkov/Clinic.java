package com.voronkov;

import com.voronkov.exceptions.NotFoundException;
import com.voronkov.model.Client;
import com.voronkov.model.Pet;
import com.voronkov.model.PetType;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clinic
 * @author Sanek
 * @since 05.07.2020
 */
public class Clinic {

    private final List<Client> clients = new ArrayList<>();

    /**
     * get type of operation
     */
    public void getOperation() {
        String programExit = "no";
        while (!programExit.equals("exit")) {
            int operationType = Integer.parseInt(getStringFromKeybr(System.in, "Select operation:" +
                    " 1 - add client, " +
                    " 2 - find client by pet name," +
                    " 3 - find client," +
                    " 4 - rename client," +
                    " 5 - rename pet," +
                    " 6 - remove client," +
                    " 7 - get all clients."));
            switch (operationType) {
                case 1:
                    addClient();
                    break;
                case 2:
                    findClientByPetName(System.in, clients);
                    break;
                case 3:
                    findClientByName(System.in, clients);
                    break;
                case 4:
                    renameClient(System.in, System.in, clients);
                    break;
                case 5:
                    renamePet(System.in, System.in, clients);
                    break;
                case 6:
                    removeClient(System.in, clients);
                    break;
                case 7:
                    getAllClients();
                    break;
            }
            programExit = getStringFromKeybr(System.in, "Continue or exit? exit/no");
        }
    }


    /**
     * add client
     */
    private void addClient(){
        String clientName = getStringFromKeybr(System.in, "Enter client name");
        if (clients.size() > 0) {
            for (Client addedClient : clients) {
                if (clientName.equals(addedClient.getName())) {
                    System.out.println("Client with this name already exist");
                    addClient();
                    return;
                }
            }
        }
        String petName = getStringFromKeybr(System.in ,"Enter pet name");
        int petTypeByNumber = Integer.parseInt(getStringFromKeybr(System.in, "Enter pet type:" +
                " 1 - Cat," +
                " 2 - Dog," +
                " 3 - Bird," +
                " 4 - Cow."));
        PetType petType;
        switch (petTypeByNumber) {
            case 2:
                petType = PetType.DOG;
                break;
            case 3:
                petType = PetType.BIRD;
                break;
            case 4:
                petType = PetType.COW;
                break;
            case 1:
            default:
                petType = PetType.CAT;
        }
        Client client = new Client(clientName, new Pet(petName, petType));
        clients.add(client);
    }

    /**
     * find client by name
     * @param in client's name
     * @param clients list of clients
     */
    public Client findClientByName(InputStream in, List<Client> clients) {
        String clientName = getStringFromKeybr(in, "Enter client name");
        return findClient(clientName, clients);
    }

    /**
     * find client by pet name
     * @param in pet's name
     * @param clients list of clients
     */
    public Client findClientByPetName(InputStream in, List<Client> clients) {
        String petName = getStringFromKeybr(in, "Enter pet name");
        return findPetOfClient(petName, clients);
    }

    /**
     * rename client
     * @param inOld old name
     * @param inNew new name
     * @param clients client's name, which must renamed
     */
    public void renameClient(InputStream inOld, InputStream inNew, List<Client> clients) {
        String clientName = getStringFromKeybr(inOld, "Enter client name");
        Client client = findClient(clientName, clients);
        client.setName(getStringFromKeybr(inNew, "Enter new name"));
        System.out.println("Client " + client.getName() + " renamed");
    }

    /**
     * rename clients pet
     * @param inOld old name
     * @param inNew new name
     * @param clients client's name, which pet must renamed
     */
    public void renamePet(InputStream inOld, InputStream inNew, List<Client> clients) {
        String clientName = getStringFromKeybr(inOld, "Enter client name");
        Client client = findClient(clientName, clients);
        client.getPet().setName(getStringFromKeybr(inNew, "Enter new name"));
        System.out.println("Client's pet " + client.getPet().getName() + " renamed");
    }

    /**
     * remove client
     * @param in parameter, which get from keyboard
     * @param clients client's name, which must deleted
     */
    public void removeClient(InputStream in, List<Client> clients) {
        String clientName = getStringFromKeybr(in, "Enter client name");
        Client client = findClient(clientName, clients);
        clients.remove(client);
        System.out.println("Client " + clientName + " removed");
    }

    /**
     * get all clients
     */
    public List<Client> getAllClients() {
        System.out.println(clients);
        return clients;
    }

    /**
     * find optional client
     * @param clientName client's name
     * @param clients List of clients
     */
    public Client findClient(String clientName, List<Client> clients) {
        Client client;
        client = clients.stream()
                .filter(c -> c.getName().equals(clientName))
                .findFirst()
                .orElse(null);
        if (client == null) {
            try {
                throw new NotFoundException("This client name not found in base");
            } catch (NotFoundException e) {
                System.out.println(e);
            }
        }
        return client;
    }

    /**
     * find optional client by pet name
     * @param petName pet's name
     * @param clients list of clients
     */
    public Client findPetOfClient(String petName, List<Client> clients) {
        Client client;
        client = clients.stream()
                .filter(c -> c.getPet().getName().equals(petName))
                .findFirst()
                .orElse(null);
        if (client == null) {
            try {
                throw new NotFoundException("This pet name not found in base");
            } catch (NotFoundException e) {
                System.out.println(e);
            }
        }
        return client;
    }

    /**
     * get String from keyboard
     * @param in parameter, which get from keyboard
     * @param request request what we must do
     */
    private String getStringFromKeybr(InputStream in, String request) {
        Scanner scanner = new Scanner(in);
        System.out.println(request);
        return scanner.next();
    }
}

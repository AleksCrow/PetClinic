package com.voronkov;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
            int operationType = Integer.parseInt(getStringFromKeybr("Select operation:" +
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
                    findClientByPetName();
                    break;
                case 3:
                    findClientByName();
                    break;
                case 4:
                    renameClient();
                    break;
                case 5:
                    renamePet();
                    break;
                case 6:
                    removeClient();
                    break;
                case 7:
                    getAllClients();
                    break;
            }
            programExit = getStringFromKeybr("Continue or exit? exit/no");
        }
    }


    /**
     * add client
     */
    private void addClient() {
        String clientName = getStringFromKeybr("Enter client name");
        if (clients.size() > 0) {
            for (Client addedClient : clients) {
                if (clientName.equals(addedClient.getName())) {
                    System.out.println("Client with this name already exist");
                    addClient();
                    return;
                }
            }
        }
        String petName = getStringFromKeybr("Enter pet name");
        int petTypeByNumber = Integer.parseInt(getStringFromKeybr("Enter pet type:" +
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
     */
    private void findClientByName() {
        String clientName = getStringFromKeybr("Enter client name");
        String findedClient = "Client with name " + clientName + " not found in base";
        Optional<Client> client = findClient(clientName);
        if (client.isPresent()) {
            findedClient = client.get().toString();
        }
        System.out.println(findedClient);
    }

    /**
     * find client by pet name
     */
    private void findClientByPetName() {
        String petName = getStringFromKeybr("Enter pet name");
        String findedClient = "Client with pet name " + petName + " not found in base";
        Optional<Client> client = findPetOfClient(petName);
        if (client.isPresent()) {
            findedClient = client.get().toString();
        }
        System.out.println(findedClient);
    }

    /**
     * rename client
     */
    private void renameClient() {
        String clientName = getStringFromKeybr("Enter client name");
        String findedClient = "Client with name " + clientName + " not found in base";
        Optional<Client> client = findClient(clientName);
        if (client.isPresent()) {
            client.get().setName(getStringFromKeybr("Enter new name"));
            System.out.println("Client " + client.get().getName() + " renamed");
        } else {
            System.out.println(findedClient);
        }
    }

    /**
     * rename clients pet
     */
    private void renamePet() {
        String petName = getStringFromKeybr("Enter pet name");
        String findedClient = "Client with pet name " + petName + " not found in base";
        Optional<Client> client = findPetOfClient(petName);
        if (client.isPresent()) {
            client.get().getPet().setName(getStringFromKeybr("Enter new pet name"));
            System.out.println("Pet of client " + client.get().getName() + " renamed");
        } else {
            System.out.println(findedClient);
        }
    }

    /**
     * remove client
     */
    private void removeClient() {
        String clientName = getStringFromKeybr("Enter client name");
        String findedClient = "Client with name " + clientName + " not found in base";
        Optional<Client> client = findClient(clientName);
        if (client.isPresent()) {
            clients.remove(client.get());
            System.out.println("Client " + clientName + " removed");
        } else {
            System.out.println(findedClient);
        }
    }

    /**
     * get all clients
     */
    private void getAllClients() {
        System.out.println(clients);
    }

    /**
     * find optional client
     * @param clientName
     */
    private Optional<Client> findClient(String clientName) {
        return clients.stream()
                .filter(c -> c.getName().equals(clientName))
                .findFirst();
    }

    /**
     * find optional client by pet name
     * @param petName
     */
    private Optional<Client> findPetOfClient(String petName) {
        return clients.stream()
                .filter(c -> c.getPet().getName().equals(petName))
                .findFirst();
    }

    /**
     * get String from keyboard
     * @param request
     */
    private String getStringFromKeybr(String request) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(request);
        return scanner.next();
    }
}

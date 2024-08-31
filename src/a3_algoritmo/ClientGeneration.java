/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package a3_algoritmo;

import java.util.Random;


public class ClientGeneration {

    DeskService desk;
    Queue queue;

    public ClientGeneration(DeskService desk, Queue queue) {
        this.desk = desk;
        this.queue = queue;
    }

    Random randomNumber = new Random();
    int min = 0;
    int max = 29;

    // Limites de criação cliente
    int clientsNumbers = 0;

    // Valores do Cliente Anterior
    int timeQueuePrev = 0;

    // Resultados
    int totalQueueTime = 0;
    int totalTime = 0;

    // Informaçoes do banco:
    int deskFree = 0;
    int[] deskEnabled = new int[3];

    boolean started = false;
    boolean startedQueue = false;
    int isFullStart = 0;
    int deskOpen = 0;

    public int getDeskFree() {
        return deskFree;
    }

    public int getClientsNumbers() {
        return clientsNumbers;
    }

    public int getTotalQueueTime() {
        return totalQueueTime;
    }

    public int gettotalTime() {
        return totalTime;
    }

    public int newLowerValue(int[] newDeskEnabled) {
        int newlowerValue = 0;
        for (int i = 0; i < 3; i++) {
            if (newDeskEnabled[i] > 0) {
                newlowerValue = newDeskEnabled[i];
                break;
            }
        }
        for (int j = 0; j < 3; j++) {

            if (newDeskEnabled[j] < newlowerValue && newDeskEnabled[j] > 0) {
                newlowerValue = newDeskEnabled[j];
            }

        }

        if (newlowerValue == 0) {
            for (int k = 0; k < 3; k++) {
                if (newDeskEnabled[k] > newlowerValue && newDeskEnabled[k] > 0) {
                    newlowerValue = newDeskEnabled[k];
                }
            }
        }

        return newlowerValue;
    }

    public int newBiggestValue(int[] newDeskEnabled) {
        int newBiggestValue = newDeskEnabled[0];
        for (int j = 0; j < 3; j++) {

            if (newDeskEnabled[j] > newBiggestValue && newDeskEnabled[j] != 0) {
                newBiggestValue = newDeskEnabled[j];
            }
        }
        return newBiggestValue;
    }

    public Client clientGeneration(int i) {
        int clients = randomNumber.nextInt((max - min));
        if (clients == 0) {
            Client client = new Client(i);
            return client;
        }

        return null;
    }

    public int[] getDeskEnabled() {
        return deskEnabled;
    }

    public void setDeskEnabled(int[] deskEnabled) {
        this.deskEnabled = deskEnabled;
    }

    public int findDeskOpen(int service, int arrived, int timeArrivedPrev) {

        int lowerValue = deskEnabled[0];

        if (isFullStart < 3) {
            deskOpen = desk.deskFree(service, arrived);
            isFullStart++;
        } else {
            deskOpen = desk.deskFree(service, timeArrivedPrev);
        }

        for (int j = 0; j < 3; j++) {

            if (deskEnabled[j] < lowerValue && deskEnabled[j] != 0) {
                lowerValue = deskEnabled[j];
            }
        }

        return lowerValue;
    }

    int checkStart = 0;

    public int BankGoals(Client client, int timeArrivedPrev, int timeServicePrev, ServiceGeneration services) {
        

        if (checkStart < 3) {
            
            deskFree = client.getArrivedTime() + client.getService();

            int noQueue = client.timeQueue(0, 0, 0, 0);
            totalTime = totalTime + client.timeTotal(noQueue, client.getService());
            services.countServices(client.getService());
            System.out.println("Teste pra ver se rodou 1");
            checkStart++;
        } else {

            deskFree = timeArrivedPrev + timeServicePrev + client.getService();

            if (0 > deskFree) {
                client.timeQueue(0, 0, 0, 0);
                totalTime = totalTime + client.timeTotal(client.timeQueue(0, 0, 0, 0), client.getService());
                services.countServices(client.getService());
                System.out.println("Teste pra ver se rodou 2");
            } else {
                totalQueueTime = totalQueueTime
                        + client.timeQueue(timeArrivedPrev, timeQueuePrev, client.getArrivedTime(), timeServicePrev);
                totalTime = totalTime + client.timeTotal(client.timeQueue(0, 0, 0, 0), client.getService());
                services.countServices(client.getService());
                System.out.println("Teste pra ver se rodou 3");
            }
            System.out.println("Tempo na fila:"
                    + client.timeQueue(timeArrivedPrev, timeQueuePrev, client.getArrivedTime(), timeServicePrev));
        }

        clientsNumbers++;

        System.out.println("Numero de Clientes no Expediente:" + clientsNumbers);

        return 0;
    }

    public void insertQueue(Client client) {

        if (desk.deskFull() == false) {
            desk.insertClientDesk(client);
        } else {
            queue.insertQueue(client);
            startedQueue = true;

        }
    }

    public void insertOnDesk(int index) {
        if (startedQueue == true) {
            desk.removeDesk(index);
            queue.insertOnDesk();

        }
    }

}

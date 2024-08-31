/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package a3_algoritmo;

import java.util.Arrays;

public class Bank {

    private DeskService desk = new DeskService();
    private Queue queue = new Queue(desk);
    private ClientGeneration client = new ClientGeneration(desk, queue);
    private ServiceGeneration service = new ServiceGeneration();

    // BankWork
    private int timeToWork = 21600;
    private int i = 0;
    private int lowerValue = 0;
    public int isEqual = 0;

    // Valores do Cliente Anterior
    private int timeArrivedPrev = 0;
    public int timeQueuePrev = 0;
    private int timeServicePrev = 0;

    public int clientTimer = 0;

    private int[] copyDeskEnabled = new int[3];

    public int otherLower = 0;

    public int returnI() {
        return i;
    }

    public void BankWorking() {

        while (i < timeToWork) {

            Client clientArrived = client.clientGeneration(i);

            int[] deskEnabled = client.getDeskEnabled();

            int holdFirst = deskEnabled[0];
            boolean repeated = false;
            int[] repeatedIndex = new int[2];
            for (int item = 1; item < deskEnabled.length; item++) {
                if (holdFirst == deskEnabled[item] && deskEnabled[item] > 0) {
                    repeatedIndex[item - 1] = item;
                    repeated = true;
                }
            }

            if (lowerValue == i && i > 0) {
                isEqual = i;
                copyDeskEnabled = deskEnabled;

                for (int j = 0; j < deskEnabled.length; j++) {
                    if (deskEnabled[j] == lowerValue) {
                        if (queue.getQueue().isEmpty()) {
                            if (repeated == true) {

                                for (int i = 0; i < repeatedIndex.length; i++) {
                                    copyDeskEnabled[repeatedIndex[i]] = 0;
                                    desk.removeDesk(i);
                                }
                                lowerValue = client.newLowerValue(copyDeskEnabled);
                                if (client.newBiggestValue(copyDeskEnabled) >= 21600) {
                                    timeToWork = client.newBiggestValue(copyDeskEnabled) + 1;
                                }
                                break;
                            } else {
                                copyDeskEnabled[j] = 0;

                                client.setDeskEnabled(copyDeskEnabled);
                                desk.removeDesk(j);

                                lowerValue = client.newLowerValue(copyDeskEnabled);

                                if (client.newBiggestValue(copyDeskEnabled) >= 21600) {
                                    timeToWork = client.newBiggestValue(copyDeskEnabled) + 1;
                                }
                                break;
                            }

                        } else {
                            if (repeated == true) {

                                for (int i = 0; i < repeatedIndex.length; i++) {
                                    if (queue.getQueue().size() > 1) {

                                        copyDeskEnabled[repeatedIndex[i]] = copyDeskEnabled[repeatedIndex[i]]
                                                + queue.firstInQueue().getService();
                                        client.setDeskEnabled(copyDeskEnabled);
                                        client.newLowerValue(client.getDeskEnabled());
                                        client.insertOnDesk(repeatedIndex[i]);
                                        lowerValue = client.newLowerValue(copyDeskEnabled);

                                        if (client.newBiggestValue(copyDeskEnabled) >= 21600) {
                                            timeToWork = client.newBiggestValue(copyDeskEnabled) + 1;
                                        }
                                    } else if (queue.getQueue().size() == 1) {

                                        copyDeskEnabled[repeatedIndex[0]] = copyDeskEnabled[repeatedIndex[0]]
                                                + queue.firstInQueue().getService();
                                        client.insertOnDesk(repeatedIndex[0]);
                                        copyDeskEnabled[repeatedIndex[1]] = 0;
                                        desk.removeDesk(repeatedIndex[1]);

                                        client.setDeskEnabled(copyDeskEnabled);

                                        client.newLowerValue(client.getDeskEnabled());
                                        lowerValue = client.newLowerValue(copyDeskEnabled);

                                        if (client.newBiggestValue(copyDeskEnabled) >= 21600) {
                                            timeToWork = client.newBiggestValue(copyDeskEnabled) + 1;
                                        }
                                        break;
                                    }

                                }
                                break;
                            } else {
                                copyDeskEnabled[j] = copyDeskEnabled[j] + queue.firstInQueue().getService();
                                client.setDeskEnabled(copyDeskEnabled);
                                client.newLowerValue(client.getDeskEnabled());
                                client.insertOnDesk(j);
                                lowerValue = client.newLowerValue(copyDeskEnabled);

                                if (client.newBiggestValue(copyDeskEnabled) >= 21600) {
                                    timeToWork = client.newBiggestValue(copyDeskEnabled) + 1;
                                }

                                break;
                            }

                        }

                    }

                }

            }

            if (timeToWork <= 21600) {
                if (clientArrived != null) {

                    for (int j = 0; j < copyDeskEnabled.length; j++) {
                        if (desk.deskFull() == true) {
                            client.insertQueue(clientArrived);
                            break;
                        }
                        if (copyDeskEnabled[j] == 0) {
                            copyDeskEnabled[j] = clientArrived.getArrivedTime() + clientArrived.getService();

                            client.setDeskEnabled(copyDeskEnabled);
                            desk.insertClientDesk(clientArrived);
                            System.out.println("Valores de desk após cliente inserido: "
                                    + Arrays.toString(client.getDeskEnabled()));

                            if (client.newBiggestValue(copyDeskEnabled) >= 21600) {
                                timeToWork = client.newBiggestValue(copyDeskEnabled) + 1;
                            }
                            break;
                        } else {

                        }
                    }

                    lowerValue = client.findDeskOpen(clientArrived.getService(), clientArrived.getArrivedTime(),
                            timeArrivedPrev);

                    client.BankGoals(clientArrived, timeArrivedPrev, timeServicePrev, service);

                    timeArrivedPrev = clientArrived.getArrivedTime();
                    timeServicePrev = clientArrived.getService();
                }
            }

            i++;

        }
        System.out.println(
                "Total de clientes atendidos: " + client.getClientsNumbers() + " clientes.");
        System.out.println(
                "Tempo de fila médio: " + (client.getTotalQueueTime() / client.getClientsNumbers()) + " segundos.");
        System.out.println(
                "Tempo extra de expediente: " + ((timeToWork - 2) - 21600) + " segundos.");

        System.out.println("Quantidade total de saques: " + service.getSaque());
        System.out.println("Quantidade total de depositos: " + service.getDeposito());
        System.out.println("Quantidade total de pagamentos " + service.getPagamento());

    }
}

package a3_algoritmo;

import java.util.ArrayList;


public class Queue {
    DeskService desk;
    public Queue(DeskService desk) {
        this.desk = desk;
    }

    ArrayList<Client> queue = new ArrayList<>();

    public void insertQueue(Client client) {
        queue.add(client);
        System.out.println("Lista: " + queue);
    }

    public Client firstInQueue() {
        return queue.get(0);
    }

    public void insertOnDesk() {

        System.out.println("Proximo a entrar: " + queue.get(0));
        desk.insertClientDesk(queue.get(0));
        queue.remove(0);

    }

    public ArrayList<Client> getQueue() {
        return queue;
    }

}

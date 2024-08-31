package a3_algoritmo;

import java.util.Arrays;

public class DeskService {

    public Client[] desk = new Client[3];

    public Client[] getDesk() {
        return desk;
    }

    public void setDesk(Client[] desk) {
        this.desk = desk;
    }

    public int deskFree(int service, int deskPrevPosition) {
        int isFree = service + deskPrevPosition;
        return isFree;
    }

    public void removeDesk(int index) {
        System.out.println("Desk a ser Retirado: " + desk[index]);
        desk[index] = null;
        System.out.println("desk removida: " + Arrays.toString(desk));
    }

    public void insertClientDesk(Client client) {
        for (int i = 0; i < desk.length; i++) {
            if (desk[i] == null) {
                desk[i] = client;
                break;
                
            } 
        }
        System.out.println("desks após inserção: " + Arrays.toString(desk));
        
    }

    public boolean deskFull() {
        for (Client c : desk) {
            if (c == null) {
                return false;
            }
        }
        return true;
    }

}

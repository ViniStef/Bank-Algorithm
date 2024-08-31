/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package a3_algoritmo;

import java.util.Random;


public class ServiceGeneration {

    int saque = 0;
    int deposito = 0;
    int pagamento = 0;

    public int createService() {
        Random randomNumber = new Random();
        int min = 0;
        int max = 3;
        int service = randomNumber.nextInt((max - min));

        if (service == 0) {
            return 60;
        }
        if (service == 1) {
            return 90;
        }
        if (service == 2) {
            return 120;
        }
        return 0;
    }

    public void countServices(int service) {
        if (service == 60) {
            saque++;

        }
        if (service == 90) {
            deposito++;

        }
        if (service == 120) {
            pagamento++;

        }

    }

    public int getSaque() {
        return saque;
    }

    public int getDeposito() {
        return deposito;
    }

    public int getPagamento() {
        return pagamento;
    }

}

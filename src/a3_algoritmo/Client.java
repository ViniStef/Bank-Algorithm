package a3_algoritmo;


public class Client {

    public Client(int arrivedTime, int leaveTime, int service) {
        this.arrivedTime = arrivedTime;
        this.leaveTime = leaveTime;
        this.clientService = service;

    }

    private int lowerValue;
    private int arrivedTime;
    private int leaveTime;

    ServiceGeneration service = new ServiceGeneration();
    private int clientService = service.createService();

    public int getLowerValue() {
        return lowerValue;
    }

    public void setLowerValue(int lowerValue) {
        this.lowerValue = lowerValue;
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Client(int seconds) {
        this.arrivedTime = seconds;

    }

    public int getArrivedTime() {
        return arrivedTime;
    }

    public int getService() {
        return clientService;
    }

    public int timeQueue(int arrivedTimePrev, int queueTimePrev, int timeArrived, int serviceTimePrev) {
        int timeQueue = (((arrivedTimePrev + queueTimePrev) - timeArrived) + serviceTimePrev);
        return Math.abs(timeQueue);
    }

    public int timeTotal(int timeQueue, int serviceTime) {
        int timeTotal = (timeQueue + serviceTime);
        return timeTotal;
    }

}

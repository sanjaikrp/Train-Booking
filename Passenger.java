import java.util.*;

public class Passenger {
    static int id = 1;
    int pnr;
    int start;
    int destin;
    ArrayList <Integer> seats = new ArrayList<>();
    String stat;
    Passenger(int start , int destin){
        pnr = id;
        this.start = start;
        this.destin = destin; 
        stat = "";
        id++;
    }
}

/**
 * TicketBooker
 */
import java.util.* ;

public class TicketBooker {
    static int stations = 5;
    static int main_capacity = 8;
    static int wait_list_capacity = 2;
    //Arrays of 1 & 0 where 1 is booked and 0 is available
    static int[][] manager = new int[stations][main_capacity + wait_list_capacity];
    static HashMap <Integer , Passenger> passengers = new HashMap<>();
    static Queue <Integer> wait_queue = new LinkedList<>();
    
    //int manager[5][10]
    //stations denotions A -> 0 | B -> 1 | C -> 2 | D -> 3 | E -> 4
    boolean avail_checker(int start, int destin, int asked_seat, ArrayList <Integer> avail_seat_no){
    
        int count = 0;
        for(int i=0;i<main_capacity+wait_list_capacity;i++){
            boolean individual_seat_avail = true;
            for(int j=start;j<=destin;j++){
                if(manager[j][i] == 1){
                    individual_seat_avail = false;
                    break;
                }
            }
            if(individual_seat_avail == true){
                count++;
                avail_seat_no.add(i+1);
            }
            if(count == asked_seat){
                return true;
            }           
        }
        
        return false;
        
        
    }

    void booker(int start, int destin, int asked_seat){
        ArrayList <Integer> avail_seat_no = new ArrayList<>();
        //Checking if seats are available
        if(avail_checker(start, destin, asked_seat, avail_seat_no)){
            //Creating passenger obj
            Passenger p = new Passenger(start , destin);
            boolean flag = true;
            for(int i=0;i<avail_seat_no.size();i++){
                int col = avail_seat_no.get(i) - 1;
                for(int j=start;j<=destin;j++){
                    manager[j][col] = 1;
                }
                p.seats.add(col+1);
                //If 9th and 10th are booked falg = false
                if(col+1 > 8){
                    flag = false;
                }
            }
            //If 9th and 10th are booked mark ticket as W.L and add to queue
            if(flag == false){
                wait_queue.add(p.pnr);
                p.stat = "W.L";
            }else{
                p.stat = "Confirmed";
            }
            System.out.println("booked");
            passengers.put(p.pnr,p);
            print_passenger();
        }else{
            System.out.println("No seats available");
        }
    }

    void print_passenger(){
        for(int key : passengers.keySet()){
            System.out.println("PNR -> " + key);
            Passenger temp = passengers.get(key);
            char start = (char) (65 + temp.start);
            char destin = (char) (65 + temp.destin);
            System.out.println("From -> "+ start + " To -> "+ destin);
            
            System.out.print("seats -> ");
            for(int i=0;i<temp.seats.size();i++){
                int val = temp.seats.get(i);
                if(val <= 8){
                    System.out.print(val + " ");
                }else{
                    System.out.print("W.L ");
                }
            }
            System.out.println();
            System.out.println("Status -> "+temp.stat);
            System.out.println();
        }
    }
    
    void print_manager(){
        
        for(int i=0;i<stations;i++){
            System.out.print((char) (65 + i) + " ");
            for(int j=0;j<main_capacity+wait_list_capacity;j++){
                System.out.print(manager[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println(wait_queue);
        return;
    }

    void cancel(int asked_pnr , int no_of_seats_cancel){
        int start = 0;
        int destin = 0;

        //Get the asked ticket obj address from passengers list
        ArrayList <Integer> seats = new ArrayList<>();
        if(passengers.containsKey(asked_pnr)){
            Passenger temp = passengers.get(asked_pnr);
            start = temp.start;
            destin = temp.destin;
            for(int i : temp.seats){
                seats.add(i);
            }

            int seats_count_before_deletion = seats.size();
            int asked_cancel_no = no_of_seats_cancel;
            //Reduce the ticket from seats array and make manager seats to available (0)
            while(no_of_seats_cancel > 0){
                int curr_seat = seats.get(0);
                seats.remove(0);
                for(int i=start;i<=destin;i++){
                    manager[i][curr_seat-1] = 0;
                }
                no_of_seats_cancel--;
            }

            //Requested seats cancelled and adding back remaining tickets
            temp.seats.clear();
            for(int i : seats){
                temp.seats.add(i);
            }

            //If no of seats to be cancelled == total no of seats delete passenger
            if(seats_count_before_deletion == asked_cancel_no){
                passengers.remove(asked_pnr);
            }
            System.out.println("cancelled");
            print_passenger();

            //Checking if W.L can be moved up

            Iterator<Integer> it = wait_queue.iterator();
            //Iterating through the queue
            while(it.hasNext()){
                int pnr = it.next();
                //Checking if the ticket is not cancelled
                if(passengers.containsKey(pnr)){
                    Passenger temp_wait_checker = passengers.get(pnr); 
                    //Checking if the tickets has Waitinglist seats
                    if(temp_wait_checker.stat.equals("W.L")){
                        
                        seats.clear();
                        //if there are waitlist seats add the idx of them to array
                        for(int i=0;i<temp_wait_checker.seats.size();i++){
                            if(temp_wait_checker.seats.get(i) > 8){
                                seats.add(i);
                            }
                        }

                        //Check if 1 seat is available for ticket and book it and remove it
                        while(seats.size() > 0){
                            
                            ArrayList <Integer> avail_seat_no = new ArrayList<>();
                            if(avail_checker(temp_wait_checker.start, temp_wait_checker.destin, 1, avail_seat_no)){
                                //Booking 1 seat if available
                                for(int j=temp_wait_checker.start;j<=temp_wait_checker.destin;j++){
                                    manager[j][avail_seat_no.get(0)-1] = 1;
                                }
                                for(int j=temp_wait_checker.start;j<=temp_wait_checker.destin;j++){
                                    manager[j][temp_wait_checker.seats.get(seats.get(0))-1] = 0;
                                }
                                //Using the W.L index. change it to newly given seat
                                temp_wait_checker.seats.set(seats.get(0), avail_seat_no.get(0));
                                //Remove the 0th idx and check if another w.l is there
                                seats.remove(0);
                            }else{
                                break;
                            }
                        }
                        //If seats array (that stores the total W.L in that ticket) becomes 0 then we can change status to confirmed
                        temp_wait_checker.stat = "Confirmed";
                    }
                }
            }
            
        }else{
            System.out.println("Invalid pnr");
        }
        
    }
}
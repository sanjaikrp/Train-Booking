import java.util.* ;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        
        //Cases for getting input, booking and printing
        TicketBooker ticket = new TicketBooker();
        boolean run = true;
        try (Scanner scn = new Scanner(System.in)) {
           
            while(run){
                System.out.println("\n 1 . Book ticket \n 2 . Print all tickets \n 3 . Print manager \n 4 . exit \n 5. cancel");
                System.out.println("Enter a number - ");
                int operation = scn.nextInt();
                switch(operation){
                    case 1: {
                        System.out.println("Enter From");
                        char from = scn.next().charAt(0);
                        System.out.println("Enter To");
                        char To = scn.next().charAt(0);
                        System.out.println("Enter no of seats");
                        int no_of_seats = scn.nextInt();
                        int f =(int)from - 65;
                        int t =(int)To - 65;
                        ticket.booker(f, t, no_of_seats);
                        ticket.print_manager();
                        break;
                    }
                    case 2: {
                        ticket.print_passenger();
                        break;
                    }
                    case 3: {
                        ticket.print_manager();
                        break;
                    }
                    case 4 : {
                        run = false;
                        break;
                    }
                    case 5: {
                        System.out.println("Enter pnr of the ticket to cancel");
                        int pnr = scn.nextInt();
                        System.out.println("Number of tickets to be cancelled");
                        int no_of_seats_cancel = scn.nextInt();
                        ticket.cancel(pnr, no_of_seats_cancel);
                        break;
                    }
                    default: {
                        run = false;
                    }
                }
                
            }
        }
        System.out.println("Exited");
    }
}

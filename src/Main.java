import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    //intializing standard array for seats
    public static int[][] seatmanag = new int[4][];
    static int count = 0;

    public static Ticket[] ticketarr = new Ticket[52];
    //creating a static int variable to count the number of times buy_seat is called and numbering the tickets


    public static void main(String[] args) {
        System.out.println("\nWelcome to the Plane Management application\n");

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 4; i++) {
            if (i == 1 || i == 2) {
                seatmanag[i] = new int[12];
            } else if (i == 0 || i == 3) {
                seatmanag[i] = new int[14];
            }
        }

        //MENU OPTION using pattern printing
        while (true) {
            String[] menu = {"MENU OPTIONS"};
            patternprint('*', 48);
            System.out.println();
            System.out.print("* ");
            patternprint(' ', 16);
            for (String s : menu) {
                System.out.print(s);
            }
            patternprint(' ', 16);
            System.out.print(" *");
            System.out.println();
            patternprint('*', 48);
            System.out.print("\n1) Buy a seat\n2) Cancel a seat" +
                    "\n3) Find first available seat\n4) Show seating plan" +
                    "\n5) Print tickets information and total sales" +
                    "\n6) Search ticket\n0) Quit\n");
            patternprint('*', 48);
            System.out.println("\nPlease select an option: ");

            //asking for choice from user
            try{
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        buy_seat();
                        break;
                    case 2:
                        cancel_seat();
                        break;
                    case 3:
                        find_first_available();
                        break;
                    case 4:
                        show_seating_plan();
                        break;
                    case 5:
                        print_tickets_info();
                        break;
                    case 6:
                        search_ticket();
                        break;

                }
                if (choice == 0) {
                    break;
                }else if(choice>6){
                    System.out.println("Please choose a valid option from 0-6");
                }

            }catch(InputMismatchException e){
                System.out.println("Please enter a valid integer");
                //This prevents the program from getting stuck inside an infinite loop
                sc.nextLine();

            }


        }


    }

    //intializing a method for pattern printing METHOD OPTION
    public static void patternprint(char charac, int time) {
        for (int i = 0; i < time; i++) {
            System.out.print(charac);
        }
    }

    //creating function buy_seat
    public static void buy_seat() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter the row letter: ");
            String upper = sc.next().toUpperCase();
            char row = upper.charAt(0);
            //converting char into int so that the array understands it
            int row_let = row - 65;
            System.out.println("Enter seat number: ");
            //deducting 1 from seat number since array starts from 0
            int seat_num = sc.nextInt() - 1;

            // validating seat booking
            if (seatmanag[row_let][seat_num] == 0) {
                seatmanag[row_let][seat_num] = 1;
                System.out.println("Seat booked successfully");

                //defining the ticket prices for each seat
                int price;
                if (seat_num >= 0 && seat_num <= 4) {
                    price = 200;
                } else if (seat_num >= 5 && seat_num <= 8) {
                    price = 150;
                } else {
                    price = 180;
                }

                //asking for person information from user
                System.out.print("Enter name: ");
                String name = sc.next();
                System.out.print("Enter surname: ");
                String surname = sc.next();
                String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                System.out.print("Enter email: ");
                String email=sc.next();
                while (true) {
                    Matcher matcher = pattern.matcher(email);
                    if(matcher.matches()==true){
                        break;
                    }
                    System.out.print("Enter a valid email: ");
                    email = sc.next();
                }

                //creating new person object
                System.out.println();
                Person person1 = new Person(name, surname, email);
                //adding ticket to array
                System.out.println("\n");
                ticketarr[count] = new Ticket(row, seat_num + 1, price, person1);
                //incrementing the ticket array index
                if (count <= 52) {
                    count += 1;
                }
            }else if (seatmanag[row_let][seat_num] == 1) {
                System.out.println("Seat already booked");
            }
            //searching if the row letter and seat number exists
        } catch (IndexOutOfBoundsException | InputMismatchException e) {
            System.out.println("Enter valid row letter or seat number");
        }
    }
    //Creating method called cancel_seat
    public static void cancel_seat() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter the row letter: ");
            String upper=sc.next().toUpperCase();
            char roww = upper.charAt(0);
            int row_let = roww - 65;
            System.out.println("Enter seat number: ");
            int seat_n = sc.nextInt();
            int seat_num = seat_n - 1;
            if (seatmanag[row_let][seat_num] == 1) {
                seatmanag[row_let][seat_num] = 0;
                //Cheking if the seat is booked and cancelling the seat
                for (int i = 0; i < ticketarr.length; i++) {
                    if (ticketarr[i].display_row() == roww && ticketarr[i].display_seat() == seat_n) {
                        System.out.println("Seat cancelled successfully");
                        //making the ticket null
                        ticketarr[i] = null;
                        break;
                    }
                }
                //checking if seat is already booked to cancel
            } else if (seatmanag[row_let][seat_num] == 0) {
                System.out.println("Seat not yet booked");

            }
            //checking if the row or seat entered is valid
        } catch (IndexOutOfBoundsException | InputMismatchException e) {
            System.out.println("Enter valid row letter or seat number");
        }
    }

    public static void find_first_available() {
        Scanner sc=new Scanner(System.in);
        char c=0;
        int seat=0;
        //intiating boolean value for finding the first seat availble
        boolean find;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 14; j++) {
                if (seatmanag[i][j] == 0) {
                    seatmanag[i][j] = 1;
                    c = (char) (i + 65);
                    seat=j+1;
                    System.out.println(c + Integer.toString(seat) + " seat is available");
                    System.out.println("Seat successfully booked");
                    find = true;
                    break;

                }
            }
            //if search is finished search becomes and true and the loop stops
            if (find = true) {
                break;
            }

        }
        int price;
        if (seat >= 0 && seat <= 4) {
            price = 200;
        } else if (seat>= 5 && seat <= 8) {
            price = 150;
        } else {
            price = 180;
        }
        if(find=true){
            System.out.print("Enter name: ");
            String name = sc.next();
            System.out.print("Enter surname: ");
            String surname = sc.next();
            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            System.out.print("Enter email: ");
            String email=sc.next();
            while (true) {
                Matcher matcher = pattern.matcher(email);
                if(matcher.matches()==true){
                    break;
                }
                System.out.print("Enter a valid email: ");
                email = sc.next();
            }
            //creating new person object
            Person person1 = new Person(name, surname, email);
            //adding ticket to array
            ticketarr[count] = new Ticket(c, seat, price, person1);
            //incrementing the ticket array index
            if(count<=52){
                count+=1;
            }
        }

    }
    public static void show_seating_plan() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 14; j++) {
                //If seat is booked it displays 'X'
                if ((!((i == 1 || i == 2) && (j == 12 || j == 13))) && seatmanag[i][j] == 1) {
                    System.out.print("X");
                    //If seat is availble it displays 'O'
                } else if (!((i == 1 || i == 2) && (j == 12 || j == 13))) {
                    System.out.print("O");
                }

            }
            System.out.println();
        }
    }
    public static void print_tickets_info() {
        //creating variable for calculating total sales
        double tot = 0;
        for (int i = 0; i < ticketarr.length; i++) {
            //if the ticket is not null it displays the seating information from the ticket array
            if(ticketarr[i]!=null){
                ticketarr[i].display_info();
                System.out.println();
                //returns the price of each seat
                double pr = ticketarr[i].display_price();
                //adds each seat's price to the total sales
                tot += pr;
            }
        }
        System.out.println("The total sales of tickets is: " + tot);
    }
        public static void search_ticket () {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the row letter: ");
            String upper=sc.next().toUpperCase();
            char roww = upper.charAt(0);
            int row_let = roww - 65;
            System.out.println("Enter seat number: ");
            int seat_n = sc.nextInt();
            int seat_num = seat_n - 1;
            //if seat is booked it displays person and seat information
            if (seatmanag[row_let][seat_num] == 1) {
                System.out.println("Seat already booked");
            }else if(seatmanag[row_let][seat_num] == 0){
                System.out.println("Seat not yet booked");
            }
            for (int i = 0; i < ticketarr.length; i++) {
                //searching thorugh the ticket array and displaying peron and seat information if the seat is booked
                if(seatmanag[row_let][seat_num]==1){
                    if (ticketarr[i].display_row() == roww && ticketarr[i].display_seat() == seat_n) {
                        ticketarr[i].per_call();
                        System.out.println();
                        ticketarr[i].display_info();
                        break;
                    }
                }
            }
        }
    }
//References
/*
Lokesh Gupta.(2022). Java Email Validation using Regex. HowtodoinJava. https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
Jagged Array in Java. geeksforgeeks. https://www.geeksforgeeks.org/jagged-array-in-java/
How to create array of objects in java. Javatpoint. https://www.javatpoint.com/how-to-create-array-of-objects-in-java
Java.io.BufferedReader class in Java. geeksforgeeks. https://www.geeksforgeeks.org/java-io-bufferedreader-class-java/


 */

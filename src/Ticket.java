import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Ticket {

    static Person person;
    public char row;
    public int seat;
    public double price;
    public Ticket(char row, int seat, int price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        display_info();
        this.person = person;
        save();



    }
    public String strseat=Integer.toString(seat);

    public void save () {
        //converting row and seat avriables to string and naming the file under the seat's name
        String filename = String.valueOf(row) + String.valueOf(seat) + ".text";
        try (BufferedWriter detail = new BufferedWriter(new FileWriter(filename))) {
            detail.write(reteve());
            detail.newLine();
            //writing the details returned by the retper() method
            detail.write(person.retper());
            detail.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //creating method to print seating details
        public void display_info () {
            System.out.println("row letter: " + row);
            System.out.println("seat number: " + seat);
            System.out.println("ticket price: " + price);

        }

        //creating functions to return attributes from the ticket object so they can be assigned to variables.
        public double display_price () {
            return price;
        }
        public char display_row () {
            return row;
        }
        public int display_seat () {
            return seat;
        }
        public void per_call () {
            person.print_info();
        }

        //creating a similar function to retper() so that it can return seating details to be saved in a file 
        public String reteve() {
            return "row letter: " + String.valueOf(row) + "\nseat number: " + String.valueOf(seat)
                    + "\nticket price: " + String.valueOf(price);
        }


}

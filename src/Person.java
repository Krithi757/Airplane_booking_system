public class Person {
    //declaring variables to be static so that it remains unchanged
    // and public making it accessible by all classes
    public static String name;
    public static String surname;
    public static String email;
    public Person(String name, String surname, String email){
        this.name =name;
        this.surname =surname;
        this.email =email;
        print_info();
    }

    //creating method print_info() so that it can print the person information whenever called
    public void print_info(){
        System.out.println("name: "+name);
        System.out.println("surname: "+surname);
        System.out.println("email: "+email);

    }

    //creating method retper() so that it returns evrything from the person object to be written in a file
    public static String retper(){
        return "\nname: "+ name+"\nsurname: "+surname+"\nemail: "+email;
    }
}

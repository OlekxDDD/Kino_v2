import org.jetbrains.annotations.NotNull;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Action {
    static List<Movie> movies = new ArrayList<>();  //declare List of objects Movie
    static Scanner scanner = new Scanner(System.in);    //create object Scanner
    static String moviesSrc = "src/movies.txt"; // create src to movies
    static String ticketsSrc = "src/tickets.txt"; // create src to  tickets
    public static void WhatDo() throws IOException {
        System.out.println();
        System.out.println("What do you want to do");   //write instructors to user
        System.out.println("1 - Add Ticket");
        System.out.println("2 - Add Movie");
        System.out.println("3 - Add Room");
        System.out.println("4 - Delete Ticket");
        System.out.println("5 - Delete Movie");
        System.out.println("6 - Delete Room");
        System.out.println("7 - List of Movie");
        System.out.println("8 - list of ticket in Movie");
        System.out.println("9 - list of Rooms");
        System.out.println("10 - exit");
        System.out.print(">>");
        String choiceString = scanner.nextLine();     //get choice of what user wat to do
        int choice = 0; // declare variable choice
        if(isInt(choiceString)){    // if choiceString have only number in
            choice = Integer.parseInt(choiceString);    // Parse it to Int
        }else{
            System.out.println("write Number"); // write instruction
            Action.WhatDo();    //come back to What Do
        }


        if(choice == 1){
            Action.addTicket();     //go to function addTicket
        } else if (choice == 2) {
            Action.addMovie();      //go to function addMovie
        }else if (choice == 3) {
            Action.addRoom();      //go to function deleteRoom
        }else if (choice == 4) {
            Action.deleteTicket();      //go to function deleteTicket
        }else if (choice == 5) {
            Action.deleteMovie();       //go to function deleteMovie
        }else if (choice == 6) {
            Action.deleteRoom();      //go to function deleteRoom
        }else if (choice == 7) {
            Action.listOfMovie(true);       //go to function listOfMovie with come back to WhatDo
        }else if (choice == 8) {
            Action.listOfTicket();      //go to function listOfTicket
        }else if(choice == 9){
            Action.listOfRooms();   //go to function listOfRooms
        }else if(choice == 10){
            Action.exit();             //go to Object ExitStart to function exit()
        }else{
            System.out.println("you write wrong number"); // write instruction
            Action.WhatDo();    //come back to What Do
        }
    }

    //function of add
    private static void addTicket() throws IOException {
        System.out.println("set row beetween A-L");  //view instruction to user
        System.out.print(">>");
        String row = scanner.next();    //get row from scanner
        String[] letters = {"A","B","C","D","E","F","G","H","I","J","K","L"}; // declare table of allowed letters
        boolean isInArrayRow = true; //declare flag
        for (int i = 0; i < letters.length; i++) {
            if (row.equals(letters[i])){ //if set row equals something in table letters
                isInArrayRow = false; //change flag to true
                break; //break loop
            }
        }
        if (isInArrayRow){ // if invalid row
            System.out.println("invalid row");  //view instruction for user
            System.out.println();
            Action.WhatDo(); // come back to WhatDo
        }

        System.out.println("set seat beetwen 1-20" );  //view instruction to user
        System.out.print(">>");
        String seatString = scanner.next();    //get seat from scanner
        String[] seatAllowed = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"}; // declare array of allowed numbers
        int seat = 0;   //declare seat
        boolean isInArraySeat = false;  //set flag is In Array Seat false
        for (String j : seatAllowed) {
            if (seatString == j) {    // if flag equal seat Allowed in position 'i'
                isInArraySeat = true;   //set flag is In Array Seat true
                break;
            }
        }
        if (!isInArraySeat){    // if is In Array Seat flag is false
            System.out.println("invalid seat number");  //write information to user
            Action.WhatDo();    // come back to What Do
        }else{
            seat = Integer.parseInt(seatString);    //parse seat String to seat int
        }
        System.out.println("set data in pattern dd-MM-yyyy");  //view instruction to user
        System.out.print(">>");
        String data = scanner.next();    //get data from scanner
        if(checkDate(data)) {

            System.out.println("write title of movie");  //view instruction to user
            System.out.print(">>");
            String title = scanner.next();    //get title of movie from scanner

            for (Movie movie : movies) {    //search movie where title from scanner equals title from scanner
                if (movie.title.equals(title)) {    //if title from movie equal title from user
                    Ticket ticket = new Ticket(row, seat, data);    //create ticket
                    movie.addTicket(ticket);    //add to movie
                    System.out.println("ticket added");     //view information to user
                    break;  //break loop
                }
            }
        }
        Action.WhatDo();    //come back to WhatDo
    }
    private static void addMovie() throws IOException {
        System.out.println("set title ( '-' beetween words ) ");    //view instruction for user
        System.out.print(">>");
        String title = scanner.next();  //get title from scanner

        System.out.println("set data");    //view instruction for user
        System.out.print(">>");
        String data = scanner.next();  //get title from scanner

        System.out.println("write room");    //view instruction for user
        System.out.print(">>");
        String roomString = scanner.next();  //get title from scanner
        int room = 0;   // declare variable
        if(isInt(roomString)){  // check is it Int
            room = Integer.parseInt(roomString);    // parse it to Int
        }else{
            System.out.println("Wrong room");   // write instruction to user
            Action.WhatDo();    //come back to What DO
        }
        // TODO : przeskakuje do WhatDo i wpisuje 'writeNumber' wypisuje 'Not a number' i wypisuje jeszcze raz WhatDo xDD

        boolean roomExist = false;  // declare variable

        for (int i = 0; i < Rooms.rooms.size(); i++) {   // as long as
            if(Rooms.rooms.get(i) == room){
                roomExist = true;
            }
        }
        if(checkDate(data) && roomExist){
            Movie movie = new Movie(data,title,room);    //create object movie
            movies.add(movie);      //add created movie to list Of movie
            System.out.println("movie added");      //view information to user
            Action.WhatDo();        //come back to WhatDo
        }else{
            if (!roomExist){
                System.out.println("room doesn't exist");
            }
            Action.WhatDo();
        }
    }
    private static void addRoom() throws IOException{
        System.out.println("write room number");
        System.out.print(">>");
        String numberRoomString = scanner.next();
        int numberRoom = 0;
        if(isInt(numberRoomString)){
            numberRoom = Integer.parseInt(numberRoomString);
        }else{
            System.out.println("write number!!!");
            Action.WhatDo();
        }
        if (numberRoom < 0){
            System.out.println("Room can't be minus");
        }

        boolean isInRooms = true;
        for (int i = 0; i < Rooms.rooms.size(); i++) {
            if(Rooms.rooms.get(i) == numberRoom){
                isInRooms = false;

            }
        }
        if (isInRooms){
            System.out.println("room added");
            Rooms.addRoom(numberRoom);
        }else{
            System.out.println("room doesn't exist");
        }
        Action.WhatDo();
    }

    //function of delete
    private static void deleteTicket() throws IOException {
        Action.listOfMovie(false);      //view information about movie
        System.out.println("write index of movie you want to delete ticket");       //view instruction to user
        System.out.print(">>");
        int choice = scanner.nextInt();     //get choice from scanner

        if(choice > movies.size() || choice < 0){
            System.out.println("this index of tickets doesn't exist");
            Action.WhatDo();
        }

        Movie movie = movies.get(choice);       //get movie from listOfMovie
        if(movies.isEmpty()){
            System.out.println("list of ticket is empty");
            Action.WhatDo();
        }
        for (int i = 0; i < movie.tickets.size(); i++) {    //print information about tickets
            Ticket ticket = movie.tickets.get(i);
            System.out.println("index "+i);
            System.out.println("row "+ticket.row);
            System.out.println("seat "+ticket.seat);
            System.out.println("data "+ticket.data);
        }
        System.out.println("write index of ticket you want to delete");     //view instruction for user
        System.out.print(">>");
        choice = scanner.nextInt();     //get choice

        if(choice > movie.tickets.size() || choice < 0){
            System.out.println("this index of ticket doesn't exist");
            Action.WhatDo();
        }
        movie.tickets.remove(choice);       //remove ticket from movie
        System.out.println("ticket deleted");       //view information
        Action.WhatDo();        //come back to whatDo
    }
    private static void deleteMovie() throws IOException {
        Action.listOfMovie(false);      // view all movie in ListOfMovie
        System.out.println("write index of movie you want to delete");      //view instruction for user
        System.out.print(">>");
        int choice = scanner.nextInt();     //get choice from scanner
        if(choice > movies.size() || choice < 0){
            System.out.println("this movie don't exist");
            Action.WhatDo();
        }
        movies.remove(choice);      //remove movie from movies
        System.out.println("movie deleted");    //view information to user
        Action.WhatDo();    //come back to WhatDo
    }
    private static void deleteRoom() throws IOException {
        System.out.println("write number of room you want delete ");
        System.out.print(">>");
        String numberRoomString = scanner.next();
        int numberRoom = 0;
        if(isInt(numberRoomString)){
            numberRoom = Integer.parseInt(numberRoomString);
        }else{
            System.out.println("you must write number");
            Action.WhatDo();
        }

        boolean isInRooms = false;
        for (int i = 0; i < Rooms.rooms.size(); i++) {
            if(Rooms.rooms.get(i) == numberRoom){
                Rooms.removeRoom(numberRoom);
                isInRooms = true;
            }
        }
        if (isInRooms){
            System.out.println("room deleted");
        }else{
            System.out.println("room doesn't exist");
        }
        Action.WhatDo();
    }

    // function of list
    private static void listOfTicket() throws IOException {
        Action.listOfMovie(false); //use function listOfMovie
        System.out.println("write index of movie you want to see tickets");     //write instructors to user
        System.out.print(">>");
        int choice = scanner.nextInt();     //get choice from scanner

        if( choice >= movies.size() || choice < 0){
            System.out.println("this index of movie doesn't exist");
            Action.WhatDo();
        }

        Movie movie = movies.get(choice);       //get movie from listOfMovie
        for (int i = 0; i < movie.tickets.size(); i++) {  //print informatoin about ticket in movie
            Ticket ticket = movie.tickets.get(i);
            System.out.println();
            System.out.println("index "+i);
            System.out.println("row "+ticket.row);
            System.out.println("seat "+ticket.seat);
            System.out.println("data "+ticket.data);
        }
        if(movie.tickets.isEmpty()){
            System.out.println("zero tickets");
        }
        Action.WhatDo(); // come back to WhatDo
    }
    private static void listOfMovie(boolean returnToWhat) throws IOException {
        for (int i = 0; i < movies.size(); i++) { // view all information about movie
            Movie movie = movies.get(i);
            System.out.println("index "+i);
            System.out.println("title "+movie.title);
            System.out.println("data "+movie.data);
            System.out.println("room "+movie.room);
            System.out.println();
        }
        if (movies.isEmpty()){  // if is empty
            System.out.println("zero movies "); //write instruction for user
            Action.WhatDo();
        }
        if(returnToWhat){     //if true
            Action.WhatDo();    //come back to WhatDo
        }

    }
    private static void listOfRooms() throws IOException {
        if(Rooms.rooms.isEmpty()){
            System.out.println("zero Rooms");
            Action.WhatDo();
        }
        System.out.println("rooms:");
        for (int i = 0; i < Rooms.rooms.size(); i++) {
            System.out.println(Rooms.rooms.get(i));
        }
        Action.WhatDo();
    }

    // other function
    public static void start() throws IOException {
        File ticketFile = new File(ticketsSrc); // create object File
        File movieFile = new File(moviesSrc); // create object File
        if(movieFile.exists() && ticketFile.exists()) {  // if both files exist

            Scanner scannerMovie = new Scanner(movieFile);  //get object scanner from file
            Scanner scannerTicket = new Scanner(ticketFile);  //get object scanner from file

            while (scannerMovie.hasNext()) {    // if scanner movie has next line in file
                String title = scannerMovie.nextLine(); // get title from scanner
                String data = scannerMovie.nextLine(); // get data from scanner

                String roomString = scannerMovie.nextLine();
                int room = Integer.parseInt(roomString);

                int year = Integer.parseInt(data.substring(6,10)); // get from date only year
                Calendar calendar = Calendar.getInstance(); // create instance calendar
                int currentYear = calendar.get(Calendar.YEAR);  // get current date like a int

                if(year >= currentYear){ // if year is bigger than current year
                    Movie movie = new Movie(data, title, room);   // create object movie
                    movies.add(movie);  //add movie to movies
                }
            }
            while (scannerTicket.hasNext()) {     // if scanner movie has next line in file
                String titleTicket = scannerTicket.nextLine();  // get title of movie from ticket

                String rowTicket = scannerTicket.nextLine();    // get row from scanner

                String seatString = scannerTicket.nextLine();   // get seat from scanner
                int seatTicket = Integer.parseInt(seatString);  //parse seat into int

                String dataTicket = scannerTicket.nextLine();   // get date from scanner

                for (int j = 0; j < movies.size(); j++) {   // repeat this loop as long as there is a record in movies
                    if(movies.get(j).title.equals(titleTicket)){    // if movie title equals title from ticket
                        Ticket ticket = new Ticket(rowTicket,seatTicket,dataTicket);    // create object ticket
                        movies.get(j).addTicket(ticket);    //add ticket to list of ticket in movie
                        break;
                    }
                }
            }
            //TODO : dodaj import Room

            scannerTicket.close();  // end action Ticket
            scannerMovie.close();  // end action Movie
        }else { // if files doesn't exist
            System.out.println("files doesn't exist");  // view instruction for user
        }
        Action.WhatDo();    //come back to what do
    }
    static void exit() throws IOException {

        FileWriter writerTicket = new FileWriter(ticketsSrc);
        FileWriter writerMovie = new FileWriter(moviesSrc);

        writerTicket.write("");
        writerMovie.write("");

        PrintWriter writerMovies = new PrintWriter(new FileWriter(moviesSrc));
        PrintWriter writerTickets = new PrintWriter(new FileWriter(ticketsSrc));

        writerMovies.flush();   //open write to file

        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);

            writerMovies.println(movie.title);
            writerMovies.println(movie.data);
            writerMovies.println(movie.room);
        }
        writerMovies.close();   //close writing to file

        writerTickets.flush();

        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            for (int j = 0; j < movie.tickets.size();j++) {

                Ticket ticket = movie.tickets.get(j);

                writerTickets.println(movie.title);
                writerTickets.println(ticket.row);
                writerTickets.println(ticket.seat);
                writerTickets.println(ticket.data);

            }
        }
        writerTickets.close();

        //TODO : dodaj export Room
    }
    private static boolean checkDate(@NotNull String data) throws IOException {
        boolean dayOkey = false;    // set flag as false
        boolean monthOkey = false;    // set flag as false
        boolean yearOkey = false;    // set flag as false

        if(data.length() == 10){ // if date is correct
            int day = Integer.parseInt(data.substring(0,2)); // get from date only day
            int month = Integer.parseInt(data.substring(3,5)); // get from date only month
            int year = Integer.parseInt(data.substring(6,10)); // get from date only year

            Calendar calendar = Calendar.getInstance(); // create instance calendar
            int currentYear = calendar.get(Calendar.YEAR);  // get current date like a int

            if(day < 31 && day >0 ){ // if day is correct
                dayOkey = true; //set flag as true
            }
            if (month > 0 && month < 13){ // if month is correct
                monthOkey = true; //set flag as true
            }
            if(year >= currentYear){ // if year is bigger than current year
                yearOkey = true; //set flag as true
            }
        }else if(data.length() > 10){   // if date is too short

            System.out.println("too long data");    //view instruction to user
            Action.WhatDo();    //come back to What Do
        }else{   // if date is too long
            System.out.println("too short data");    //view instruction to user
            Action.WhatDo();    //come back to what do
        }
        if (dayOkey == false || monthOkey == false || yearOkey == false){   //if something is wrong in date
            System.out.println("invalid date"); //view instruction to user
            if(dayOkey == false){
                System.out.println("invalid day");
            }
            if(monthOkey == false){
                System.out.println("invalid month");
            }
            if(yearOkey == false){
                System.out.println("invalid year");
            }
            return false;
        }else{
            return true;
        }
    }
    public static boolean isInt(@NotNull String str) {
        return str.matches("\\d+");
    }
}

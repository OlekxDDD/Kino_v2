import java.util.ArrayList;
import java.util.List;

public class Movie {
    String data;
    String title;
    int room;
    List<Ticket> tickets = new ArrayList<>();
    Movie(String data, String title, int room){
        this.data = data;
        this.title = title;
        this.room = room;
    }
    void addTicket(Ticket ticket){
        tickets.add(ticket);
    }
}

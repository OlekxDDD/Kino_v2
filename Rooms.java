import java.util.ArrayList;
import java.util.List;
public class Rooms {
    public static List<Integer> rooms = new ArrayList<>();
    public static void addRoom(int numberRoom){
        rooms.add(numberRoom);
    }
    public static void removeRoom(int numberRoom){
        rooms.remove(numberRoom);
    }
}

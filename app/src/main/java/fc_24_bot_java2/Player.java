package fc_24_bot_java2;
import java.util.List;

public class Player {
    private String name;
    private String version;
    private String position;
    private String club;
    private double price;
    private String nation;

    public Player(String name, String version, String position, String nation,  String club, double price) {
        this.name = name;
        this.version = version;
        this.position = position;
        this.club = club;
        this.price = price;
        this.nation= nation;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getPosition() {
        return position;
    }


    public String getClub() {
        return club;
    }

    public double getPrice() {
        return price;
    }

    public String getNation() {
        return nation;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", position='" + position + '\'' +
                ", club='" + club + '\'' +
                ", price=" + price +
                '}';
    }




}
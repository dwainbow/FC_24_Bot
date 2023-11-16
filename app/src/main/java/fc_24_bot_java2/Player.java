package fc_24_bot_java2;

public class Player {
    private String name;
    private String version;
    private String position;
    private String club;
    private double price;
    private String nation;
    private int rating;

    public Player(String name, String version, String position, String nation,  String club, double price,int rating) {
        this.name = name;
        this.version = version;
        this.position = position;
        this.club = club;
        this.price = price;
        this.nation= nation;
        this.rating = rating;
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

    public int getRating()
    {
        return rating;
    }

    @Override
    public String toString() {
        return "{" +
                "Name: '" + name +  
                ", rating: " + rating + 
                ", version: '" + version + 
                ", position: '" + position + 
                ", club: '" + club + 
                ", price: " + price +
                '}';
    }

}

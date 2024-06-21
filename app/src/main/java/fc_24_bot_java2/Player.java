package fc_24_bot_java2;

/**
 * The Player class represents a football player.
 */
public class Player {
    private String name;
    private String version;
    private String position;
    private String club;
    private double price;
    private String nation;
    private int rating;

    /**
     * Constructs a new Player object with the specified attributes.
     *
     * @param name     the name of the player
     * @param version  the version of the player
     * @param position the position of the player
     * @param nation   the nation of the player
     * @param club     the club of the player
     * @param price    the price of the player
     * @param rating   the rating of the player
     */
    public Player(String name, String version, String position, String nation, String club, double price, int rating) {
        this.name = name;
        this.version = version;
        this.position = position;
        this.club = club;
        this.price = price;
        this.nation = nation;
        this.rating = rating;
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the version of the player.
     *
     * @return the version of the player
     */
    public String getVersion() {
        return version;
    }

    /**
     * Returns the position of the player.
     *
     * @return the position of the player
     */
    public String getPosition() {
        return position;
    }

    /**
     * Returns the club of the player.
     *
     * @return the club of the player
     */
    public String getClub() {
        return club;
    }

    /**
     * Returns the price of the player.
     *
     * @return the price of the player
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the nation of the player.
     *
     * @return the nation of the player
     */
    public String getNation() {
        return nation;
    }

    /**
     * Returns the rating of the player.
     *
     * @return the rating of the player
     */
    public int getRating() {
        return rating;
    }

    /**
     * Returns a string representation of the player.
     *
     * @return a string representation of the player
     */
    @Override
    public String toString() {
        return "{" +
                "Name: '" + name +
                ", rating: " + rating +
                ", version: " + version +
                ", position: " + position +
                ", club: '" + club +
                ", price: " + price +
                '}';
    }
}

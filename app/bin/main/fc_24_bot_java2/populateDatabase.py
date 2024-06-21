import sqlite3

class PopulateDatabase:
    """
    A class for populating a SQLite database with player data.

    Attributes:
        sqlite_filename (str): The filename of the SQLite database.

    Methods:
        connect(): Connects to the SQLite database.
        commit(): Commits the changes made to the database.
        rollback(): Rolls back the changes made to the database.
        disconnect(): Disconnects from the SQLite database.
        create_tables(): Creates the necessary tables in the database.
        insert_player_data(player_data): Inserts player data into the database.
        clear_table(): Clears the data in the "PLAYERS" table.
    """

    def __init__(self, sqlite_filename):
        self.sqlite_filename = sqlite_filename
        self.connection = None

    def connect(self):
        """
        Connects to the SQLite database.

        Raises:
            RuntimeError: If the connection is already opened.
        """
        if self.connection is not None and not self.connection.closed:
            raise RuntimeError("The connection is already opened")
        self.connection = sqlite3.connect(self.sqlite_filename)
        self.connection.execute("PRAGMA foreign_keys = ON")
        self.connection.isolation_level = None  

    def commit(self):
        """
        Commits the changes made to the database.
        """
        self.connection.commit()

    def rollback(self):
        """
        Rolls back the changes made to the database.
        """
        self.connection.rollback()

    def disconnect(self):
        """
        Disconnects from the SQLite database.
        """
        self.connection.close()

    def create_tables(self):
        """
        Creates the necessary tables in the database.
        """
        cursor = self.connection.cursor()

        players_sql = """
            CREATE TABLE IF NOT EXISTS PLAYERS (
                ID INTEGER PRIMARY KEY AUTOINCREMENT,
                Name TEXT NOT NULL,
                Rating INTEGER NOT NULL,
                Version TEXT NOT NULL,
                Club TEXT NOT NULL,
                League TEXT NOT NULL,
                Nation TEXT NOT NULL,
                Position TEXT NOT NULL,
                OtherPositions TEXT NOT NULL,
                Price REAL NOT NULL
            )
        """

        cursor.execute(players_sql)
        self.connection.commit()

    def insert_player_data(self, player_data):
        """
        Inserts player data into the database.

        Args:
            player_data (tuple): A tuple containing the player data.

        Example:
            player_data = ("John Doe", 80, "1.0", "Club A", "League A", "Nation A", "Forward", "Midfielder", 1000000.0)
            insert_player_data(player_data)
        """
        cursor = self.connection.cursor()
        player_values = (None, player_data[0], player_data[1], player_data[2], player_data[3], player_data[4], player_data[5], player_data[6], player_data[7], player_data[8])

        insert_query = """
            INSERT INTO PLAYERS (ID, Name, Rating, Version, Club, League, Nation, Position, OtherPositions, Price)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """
        cursor.execute(insert_query, player_values)
        self.connection.commit()

    def clear_table(self):
        """
        Clears the data in the "PLAYERS" table.
        """
        cursor = self.connection.cursor()
        cursor.execute("DELETE FROM PLAYERS")
        self.connection.commit()
import sqlite3

class PopulateDatabase:
    def __init__(self, sqlite_filename):
        self.sqlite_filename = sqlite_filename
        self.connection = None

    def connect(self):
        if self.connection is not None and not self.connection.closed:
            raise RuntimeError("The connection is already opened")
        self.connection = sqlite3.connect(self.sqlite_filename)
        self.connection.execute("PRAGMA foreign_keys = ON")
        self.connection.isolation_level = None  

    def commit(self):
        self.connection.commit()

    def rollback(self):
        self.connection.rollback()

    def disconnect(self):
        self.connection.close()

    def create_tables(self):
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
    def insert_player_data(self,player_data):
        
        cursor = self.connection.cursor()
        player_values = (None,player_data[0], player_data[1], player_data[2], player_data[3], player_data[4], player_data[5], player_data[6],player_data[7],player_data[8])
        
        insert_query = """
            INSERT INTO PLAYERS (ID, Name,Rating,Version, Club, League, Nation, Position, OtherPositions,Price)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)
        """
        cursor.execute(insert_query, player_values)
        self.connection.commit()
    
    def clear_table(self):
        cursor = self.connection.cursor()
        cursor.execute("DELETE FROM PLAYERS")
        self.connection.commit()
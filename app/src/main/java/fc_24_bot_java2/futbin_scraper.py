import requests
import csv
from bs4 import BeautifulSoup
import sqlite3


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
        player_values = (None,player_data[0], player_data[1], player_data[2], player_data[3], player_data[4], player_data[5], player_data[6],player_data[7])
        
        insert_query = """
            INSERT INTO PLAYERS (ID, Name, Version, Club, League, Nation, Position, OtherPositions,Price)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """
        cursor.execute(insert_query, player_values)
        self.connection.commit()
    
    def clear_table(self):
        cursor = self.connection.cursor()
        cursor.execute("DELETE FROM PLAYERS")
        self.connection.commit()
    


def initSoup(url):
    headers = {
        'User-Agent': 'Mozilla/5.0'}
    req = requests.Session()
    response = req.get(url, headers=headers)
    soup = BeautifulSoup(response.text, 'html.parser')
    return soup

def getVersions(url):
    soup = initSoup(url)
    print(soup)
    versions = soup.findAll('a', class_= "dropdown-item dropdown-item-rt static-filter")

    version_list = []
    for version in versions:
        version_list.append(version.text)
    return version_list

def convertPrice(price):
    if 'K' in price:
        price = price.replace('K','')
        price = float(price)*1000
        return price
    elif 'M' in price:
        price = price.replace('M','')
        price = float(price)*1000000
        return price
    else:
        return price

def noResults(url):
    soup = initSoup(url)
    players_tr_1= soup.findAll('tr','player_tr_1')
    for tag in players_tr_1:
        tds = tag.findAll('td')
        no_results = tds[0]
        if no_results.text == 'No Results':
            return True
        else:
            return False
        
def saveToCSV(player_data):
    with open('futbin_data.csv','w', newline='') as file:
        writer = csv.writer(file)
        headers = ['Player Name', 'Version', 'Club', 'League', 'Nation', 'Position', 'Other Positions', 'Price']
        writer.writerow(headers)
        for data in player_data:
            writer.writerow(data)




def getPlayerName(tds):
    player_name_tag = tds[1].find("a", class_="player_name_players_table get-tp")
    return player_name_tag.text

def getPlayerPrice(tds):
    player_price_tag = tds[5].find("span", class_="font-weight-bold")
    return convertPrice(player_price_tag.text)

def getPlayerPostion(tds):
    player_positions_divs = tds[3].findAll("div")
    return player_positions_divs[0].text

def getPlayerOtherPostions(tds):
    player_positions_divs = tds[3].findAll("div")
    return player_positions_divs[1].text

def getPlayerClub(tds):
    player_clubs_nations  = tds[1].find("span", class_="players_club_nation").findAll("a")
    return player_clubs_nations[0]["data-original-title"]

def getPlayerNation(tds):
    player_clubs_nations  = tds[1].find("span", class_="players_club_nation").findAll("a")
    return player_clubs_nations[1]["data-original-title"]

def getPlayerLeague(tds):
    player_clubs_nations  = tds[1].find("span", class_="players_club_nation").findAll("a")
    return player_clubs_nations[2]["data-original-title"]

def scrapeData():

    populate_database = PopulateDatabase("fc24.db")
    populate_database.connect()
    populate_database.clear_table()
    populate_database.create_tables()

    player_data=[]
    versions = ["icons", "centurions_icon", "centurions", " klpundit_pick","triple_threat_hero","triple_threat","trailblazers","all_rttk","ucl_w","uefa_heroes_men","uefa_heroes_women", "nike", 
                "fut_heroes", "gold_rare", "gold_nr",  "silver_rare", "silver_nr",
                "bronze_rare", "bronze_nr", "if_gold", "if_silver", "if_bronze", "icons", "libertadores_b", "sudamericana"] 
    
    for version in versions:
        page_number= 0
        while True:
            page_number+=1
            print(version, page_number)
            url = f'https://www.futbin.com/players?page={page_number}&version={version}'
            if(noResults(url)):
                break
            soup = initSoup(url)
            numbers = [1,2]
            for num in numbers:
                data = []
                players = soup.findAll('tr','player_tr_'+str(num))
                for player in players:
                    tds = player.findAll('td')

                    player_name = getPlayerName(tds)
                    player_price=  getPlayerPrice(tds)
                    player_positon = getPlayerPostion(tds)
                    player_other_positions = getPlayerOtherPostions(tds) 
                    club = getPlayerClub(tds)
                    league = getPlayerLeague(tds)
                    nation=getPlayerNation(tds)
                    
                    
                    data = [player_name,version,club,league,nation,player_positon, player_other_positions, player_price]
                    
                    populate_database.insert_player_data(data)
                    

                    # player_data.append([player_name,version,club,league,nation,player_positon, player_other_positions, player_price])
        # saveToCSV(player_data)
scrapeData()


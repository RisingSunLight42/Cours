import requests
from bs4 import BeautifulSoup

url = 'https://www.pokepedia.fr/Liste_des_Pok%C3%A9mon_dans_l%27ordre_du_Pok%C3%A9dex_National'

response = requests.get(url)

if response.ok:
    soup = BeautifulSoup(response.text, features="html.parser")
    table = soup.find("table", class_="tableaustandard") # Récupère la table des noms pokemons
    body = table.find("tbody") # Récupère le body de la table
    tr = body.findAll("tr") # Récupère mes row de la table
    print(tr[1]) # Affiche ma première row qui nous intéresse (indice 0 ne nous intéresse pas)
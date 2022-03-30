import requests
from bs4 import BeautifulSoup

url = 'https://www.pokepedia.fr/Liste_des_Pok%C3%A9mon_dans_l%27ordre_du_Pok%C3%A9dex_National'

response = requests.get(url)

if response.ok:
    soup = BeautifulSoup(response.text, features="html.parser")
    table = soup.find("table", class_="tableaustandard") # Récupère la table des noms pokemons
    body = table.find("tbody") # Récupère le body de la table
    tr = body.findAll("tr") # Récupère mes row de la table
    dico_noms_langues = {} # Initialise le dictionnaire
    # Parcours la liste des rangées par élément pour obtenir chaque case françaises et anglaise et obtenir le nom du pokemon
    for i in range(1, len(tr) - 1):
        td = tr[i].findAll("td")
        # Récupère les noms sans les retours à la ligne
        nom_fr = td[2].getText().replace("\n", "")
        nom_en = td[3].getText().replace("\n", "")
        if nom_fr not in dico_noms_langues.keys(): # Si le nom_fr n'est pas présent dans les clés du dico, l'ajoute avec en valeur nmom_en
            dico_noms_langues[nom_fr] = nom_en
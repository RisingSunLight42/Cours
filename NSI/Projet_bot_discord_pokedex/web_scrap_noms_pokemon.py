import requests
import json
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
    for i in range(1, len(tr)):
        td = tr[i].findAll("td")
        decalage = 0
        numero = td[0].get_text().replace("\n", "")
        # Si jamais la case 1 n'est pas un numéro, alors c'est une forme alternative, tous les éléments sont décalés d'un
        if (not numero.isdigit() and numero != "—"): # Les cases avec le signe "-" sont celles qui n'ont pas de numéros, mais qui ne sont pas des formes alternatives
            decalage = 1 # On attribue donc un décalage de 1
        # Récupère les noms sans les retours à la ligne
        nom_fr = td[2 - decalage].get_text(separator=" ").replace("\n", "").strip().lower()
        nom_en = td[3 - decalage].get_text(separator=" ").replace("\n", "").strip().lower()
        if nom_fr not in dico_noms_langues.keys(): # Si le nom_fr n'est pas présent dans les clés du dico, l'ajoute avec en valeur nmom_en
            dico_noms_langues[nom_fr] = nom_en
    # Push le dictionnaire dans le fichier JSON
    with open("noms_pokemon_fr_en.json", "w") as fichierJSON:
        json.dump(dico_noms_langues, fichierJSON)

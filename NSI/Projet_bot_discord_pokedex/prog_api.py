import requests
import json

def traduction_nom(nom):
    '''Fonction qui prend en argument le nom d'un pokemon en français ou en anglais, et
       qui renvoie les noms anglais et français du pokemon.
       Exemple : >>> traduction_nom('dracaufeu')
       renvoie :
       [Dracaufeu, Charizard] '''
    nom = nom.lower()   # On met le nom en minuscule
    with open('noms_pokemon_fr_en.json', encoding = 'utf-8') as fichierJSON :
        contenu = json.load(fichierJSON)
        for cle, valeur in contenu.items(): # On cherche si le nom est présent dans le ficher json
            if nom in [cle, valeur]:
                return [cle, valeur]        # Si oui on renvoie la valeur et la clé
        return False                        # Sinon on renvoie False


def info_pokemon(nom):
    ''' Fonction qui prend en argument le nom d'un pokemon (en français ou en anglais), et
        qui renvoie un dictionnaire contenant le numéro, la taille, le poid, les types et l'url d'une image.
        Exemple : >>> info_pokemon('dracaufeu')
        renvoie :
        {'numero': 6, 'nom': 'Dracaufeu', 'taille': '5.2', 'poids': '410.5',
        'image': 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png',
        'types': ['Fire', 'Flying']} '''
    nom = traduction_nom(nom)
    if not nom:                      # Si nom vaut False, alors la clé est incorrecte 
        return None                  # on renvoie None
    url = f"https://pokeapi.co/api/v2/pokemon/{nom[1]}/" # Sinon on met le nom du pokemon dans l'url
    
    reponse = requests.get(url)      # On recupère les informations dans l'API     
    contenu = reponse.json()         
    
    infos  = {
        'numero' : contenu["id"],    # On crée le dictionnaire avec les informations
        'nom' : nom[0].capitalize(),
        'taille' : str(round(int(contenu["height"]) * 0.1, 2)),
        'poids' : str(round(int(contenu["weight"]) * 0.1, 2)),
        'image' : contenu["sprites"]["other"]["official-artwork"]["front_default"]
        }
    liste_types = []
    for i in range(len(contenu["types"])):      # On y ajoute les types
        letype = contenu["types"][i]['type']['name']
        liste_types.append(letype.capitalize())
    infos['types'] = liste_types
    return infos                     # Et on le renvoie

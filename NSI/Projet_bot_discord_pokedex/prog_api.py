import requests

def info_pokemon(nom):
    ''' Fonction qui prend en argument le nom d'un pokemon (en anglais), et
        qui renvoie un dictionnaire contenant le numéro, la taille, le poid, les types et l'url d'une image.
        Exemple : >>> info_pokemon(charizard)
        renvoie :
        {'numero' : 6, 'nom' : 'charizard','taille' : '17','poids' : '905',
        'image' : 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png',
        'types': ['Flying','Fire']}'''
    nom = nom.lower()
    url = f"https://pokeapi.co/api/v2/pokemon/{nom}/" # On met le nom du pokemon dans l'url
    
    reponse = requests.get(url)      # On recupère les informations dans l'API
    if reponse.status_code == 404:   # Si le nom est mauvais, on a l'erreur 404
        return None                  # donc on renvoie None
    contenu_txt = reponse.text       # Si c'est bon on met
    contenu = reponse.json()         # tout sous forme de dictionnaire
    infos  = {
        'numero' : contenu["id"],    # On crée le dictionnaire
        'nom' : contenu["name"],
        'taille' : contenu["height"],
        'poids' : contenu["weight"],
        'image' : contenu["sprites"]["front_default"]
        }
    liste_types = []
    for i in range(len(contenu["types"])):      # On y ajoute les types
        letype = contenu["types"][i-1]['type']['name']
        liste_types.append(letype.capitalize())
    infos['types'] = liste_types
    return infos                     # Et on le renvoie
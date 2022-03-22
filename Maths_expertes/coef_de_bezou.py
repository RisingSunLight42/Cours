def bezou(a,b):
    """Fonction retournant les coefs de Bézou.

    Args:
        a (integer): Premier nombre, il doit être divisible par b
        b (integer): Second nombre, il est le diviseur de a
    """
    # Créa la liste des divisions euclidiennes
    liste_div_eucl = []
    while a%b != 0:
        reste = a%b
        liste_div_eucl.append([reste, [1, a], [-(a//b), b]]) # Ajoute une liste où on calcule le reste
        a = b
        b = reste
        
    # Calcul des coefs de Bézou
    indice = len(liste_div_eucl) - 1
    # Crée une liste dernier reste qui contient le point de départ des calculs
    dernier_reste = [liste_div_eucl[indice][0], liste_div_eucl[indice][1], liste_div_eucl[indice][2]]
    indice -= 1
    while indice >= 0:
        # Fait une liste des éléments pour le calcul de reste
        calcul_reste = [liste_div_eucl[indice][1], liste_div_eucl[indice][2]]
        
        #* Partie remplacement
        # Cherche où remplacer par les calculs de reste
        indice_remplacement = 2
        if dernier_reste[2][1] == liste_div_eucl[indice][2][1]:
            indice_remplacement = 1
        # Définit le multiplicateur pour multiplier les quotients des nombres du calcul du reste
        multiplicateur = dernier_reste[indice_remplacement][0]
        # Modifie le calcul de reste en conséquence, il est composé des a et b utilisés pour calculer le reste à l'étape donnée
        calcul_reste = [[multiplicateur*calcul_reste[0][0],calcul_reste[0][1]], [multiplicateur*calcul_reste[1][0],calcul_reste[1][1]]]
        # Met à jour le calcul du dernier reste
        dernier_reste[indice_remplacement] = calcul_reste # On remplace la zone de dernier reste par calcul reste, pour remonter
        
        #* Partie combinaison des différents éléments
        # On cherche à quel endroit dans la liste on doit combiner des éléments
        indice_combinaison = 1
        if indice_remplacement == 1: # Pour cela on vérifie que l'indice n'est pas le même que celui où on a remplacé par une liste
            indice_combinaison = 2
        
        # On combine après les termes entre eux (par exemple, s'il y a deux fois 137 d'un côté, et 3 fois de l'autre, il va combiner pour faire 5)
        if dernier_reste[indice_combinaison][1] == dernier_reste[indice_remplacement][0][1]:
            # Si l'élément à remplacer correspond à la première liste que l'on a remplacé, on combine en conséquence
            dernier_reste[indice_combinaison] = [dernier_reste[indice_combinaison][0] + dernier_reste[indice_remplacement][0][0]]
            dernier_reste[indice_remplacement] = dernier_reste[indice_remplacement][1] # On détruit la liste de liste pour conserver que la liste non combinée
        else:
            # Si l'élément à remplacer correspond à la seconde liste que l'on a remplacé, on combine en conséquence
            dernier_reste[indice_combinaison][0] = dernier_reste[indice_combinaison][0] + dernier_reste[indice_remplacement][1][0]
            dernier_reste[indice_remplacement] = dernier_reste[indice_remplacement][0] # On détruit la liste de liste pour conserver que la liste non combinée
        indice -= 1
    return dernier_reste

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
        liste_div_eucl.append([[1, a], [a//b, b], reste]) # Ajoute une liste avec le nombre de fois que l'on multiplie a, b et le reste de a/b
        a = b
        b = reste
        
    # Calcul des coefs de Bézou
    indice = len(liste_div_eucl) - 1
    # Crée une liste dernier reste qui contient le point de départ des calculs
    dernier_reste = [liste_div_eucl[indice][2], liste_div_eucl[indice][0], liste_div_eucl[indice][1]]
    indice -= 1
    print(dernier_reste)
    while indice > 0:
        # Fait une liste des éléments pour le calcul de reste
        calcul_reste = [liste_div_eucl[indice][0], liste_div_eucl[indice][1]]
        # Cherche où remplacer par les calculs de reste
        if dernier_reste[1][1] == liste_div_eucl[indice][2]:
            dernier_reste[1][1] = calcul_reste
        else:
            dernier_reste[2][1] = calcul_reste
        indice -= 1
    return liste_div_eucl

bezou(559, 325)
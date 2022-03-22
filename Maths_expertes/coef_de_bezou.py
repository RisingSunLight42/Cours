def bezou(a,b):
    """Fonction retournant les coefs de Bézou.

    Args:
        a (integer): Premier nombre, il doit être divisible par b
        b (integer): Second nombre, il est le diviseur de a
    """
    liste_div_eucl = []
    while a%b != 0:
        reste = a%b
        liste_div_eucl.append([a, a//b, b, reste])
        a = b
        b = reste
    return liste_div_eucl

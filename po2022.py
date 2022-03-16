from tkinter import *

# On définit les couleurs de fond et de textepour les boutons lorsqu'ils sont actifs ou pas
couleur_fond_passif = "#345ac2"
couleur_texte_passif = "#f0f8ff"
couleur_fond_actif = "#283c74"
couleur_texte_actif = "gold"
couleurs = [(couleur_fond_passif,couleur_texte_passif) for i in range(8)] # Crée la liste de 8 tuples de couleurs, un tuple par bouton

# Création de la fenêtre avec son titre et mise en place du compteur pour avoir la valeur entrée par le visiteur
maFenetre = Tk()
maFenetre.title("Rallye de NSI")
compteur = 0

def zero ():
    """Fonction remettant à 0 tous les boutons et retirant l'afficheur de félicitation
    """
    global compteur
    compteur = 0
    for i in range (8):
        couleurs[i] = (couleur_fond_passif,couleur_texte_passif)
        bouton[i].configure (text='0',bg=couleurs[i][0],fg=couleurs[i][1])
    afficheur.configure(text=f"Valeur en cours : {compteur}")
    felicite.grid_remove()

def b(i):
    """Modifie le bouton d'une couleur et d'un texte suivant s'il est pressé ou pas

    Args:
        i (integer): Position du bouton pour modifier la bonne couleur et le bon bouton
    """
    global compteur
    if couleurs [i][0] == couleur_fond_passif : # Si la couleur est celle classique, passe le bouton en pressé
        couleurs[i] = (couleur_fond_actif, couleur_texte_actif)
        compteur = compteur + 2**i
        valeur = 1
    else: # Sinon passe le bouton en relâché
        couleurs[i] = (couleur_fond_passif,couleur_texte_passif)
        compteur = compteur - 2**i
        valeur = 0
    bouton[i].configure (text=valeur,bg=couleurs[i][0],fg=couleurs[i][1])
    # Modifie l'afficheur, si le compteur vaut 107, c'est gagné et on affiche le message
    afficheur.configure(text=f"Valeur en cours : {compteur}")
    if compteur == 107:
        felicite.grid()
    else :
        felicite.grid_remove()

# Définit le label de titre, des deux textes explications, crée la frame contenant les boutons et crée la liste des boutons
titre = Label (maFenetre,text='Le rallye des portes ouvertes : la question NSI',font=('Comic',36),height=3,width=60)
texte1 = Label (maFenetre, text = "Activez les cases ci-dessous pour obtenir 107 !",font=('Arial',20),height=2)
texte2 = Label (maFenetre,text = "La réponse attendue est le nombre qui va apparaître dans les cases",font=('Arial',20),height=2)
zoneBoutons = Frame (maFenetre,bg=couleur_fond_actif)
bouton = [Button (zoneBoutons,text= 0,relief='ridge',bd=4,width=10 ,height=3 ,bg=couleur_fond_passif,fg=couleur_texte_passif,font=('Arial',18,'bold'),command= lambda x=i: b(x)) for i in range(8)]

# Crée l'afficheur de la valeur crée, le message de félicitation et le bouton de mise à zéro
afficheur = Label (maFenetre,text=f"Valeur en cours : {compteur}",height=2,font=('Arial',20,'bold'))
felicite = Label(maFenetre,fg='purple',font=('Arial',16,'bold'),text='Bravo, notez bien la réponse, vous venez d\'obtenir le codage binaire de 107 !',height=2)
boutonZero = Button (maFenetre,text= "Remettre le compteur à 0",font=('Comic',24),bg=couleur_fond_passif,fg=couleur_texte_passif, command= zero)

# Place les différents éléments sur la page
titre.grid(row=0, column=0, columnspan=8)
texte1.grid(row=1, column=0, columnspan=8)
texte2.grid(row=2, column=0, columnspan=8)
for i in range(8):
    # La droite étant le plus grand nombre de colonne, on utilise i pour réduire l'indice des colonnes et placer dans le bon ordre les boutons
    bouton[i].grid(row=3, column=7-i)
zoneBoutons.grid(row=3, column=0, columnspan=8)
afficheur.grid(row=4, column=0, columnspan=8)
felicite.grid(row=5, column=0, columnspan=8)
felicite.grid_remove() # Masque le label de félicitation
boutonZero.grid(row=6, column=0, columnspan=8, pady=(0, 10))

# Lancement de la fenêtre
maFenetre.mainloop() 
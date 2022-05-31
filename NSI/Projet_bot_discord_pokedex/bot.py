# Programme connection au bot Discord
import discord
from discord.ext import commands
from discord_slash import SlashCommand
from discord_slash.utils.manage_commands import create_option
from prog_api import info_pokemon
import csv

# Création de l'objet bot avec pour prefix "poke!" et ajout d'un objet slash
bot = commands.Bot(command_prefix='poke!')
slash = SlashCommand(bot, sync_commands=True)

# Initialisation dictionnaire des couleurs de type
dico_type_couleur = {}
with open("trad_types_et_code_couleur.csv", "r") as fichierDicoTypes:
    lecteur = csv.reader(fichierDicoTypes, delimiter=";")
    for ligne in lecteur:
        dico_type_couleur[ligne[0]] = (ligne[1], ligne[2])


# Récupération du token de connection à partir du fichier texte
token = ""
with open("token.txt", "r") as fichierToken:
    token = fichierToken.readline()


# Définition d'un event qui dit que le bot est connecté lorsqu'il l'event "on_ready" est lancé
@bot.event
async def on_ready():
        print(f'Le bot est bien connecté.')


# Fonction créant l'embed avec les infos du pokemon
async def embed_pokemon_pokedex(ctx, nom):
    dico = info_pokemon(nom)
    if dico is None:
        return await ctx.send("Le nom que tu as donné est incorrect !")
    # Récupère la liste des types et définit le pluriel s'il y a plusieurs types
    types = dico['types']
    str_type = "Type"
    if len(types) > 1: str_type += "s"
    couleur_embed = int(dico_type_couleur[types[0]][1], 16) # Récupère la couleur correspond au type d'indice 0 renvoyé par les infos
    for i in range(len(types)): # Fait une boucle pour remplacer chaque type par leur version française
        types[i] = dico_type_couleur[types[i]][0]
    
    # Création de l'embed
    embed = discord.Embed(title=f"{dico['nom'].capitalize()} (#{dico['numero']})", # .capitalize() met la première lettre en maj
                          color=couleur_embed) 
    embed.add_field(name="Taille", value=f"{dico['taille']} m")
    embed.add_field(name="Poids", value=f"{dico['poids']} kg")
    embed.add_field(name=str_type, value=f"{', '.join(types)}", inline=False)
    embed.set_image(url=dico["image"])
    await ctx.send(embed=embed)


# Définition de la commande info, qui renverra les infos d'un Pokemon obtenues grâce au programme de Léni
@bot.command()
async def info(ctx, nom):
    await ctx.message.delete() # Supprime le message de commande
    await embed_pokemon_pokedex(ctx, nom)
    

# Définition de la commande info en commande slash
@slash.slash(name="info",
             guild_ids=[727116492147130439],
             description="Permet d'avoir des informations sur le pokemon donné !",
             options=[create_option(name="nom", description="Nom du pokemon voulu !", option_type=3, required=True)])
async def infoPokemon(ctx, nom):
    await embed_pokemon_pokedex(ctx, nom)


# Gestion des erreurs de la commande infoPokemon
@info.error
async def info_error(ctx, error):
    if isinstance(error, commands.MissingRequiredArgument):
        await ctx.message.delete() # Supprime le message de commande
        await ctx.send("Tu ne m'as pas donné de nom ! Je ne peux pas faire de recherches dans le pokedex sans !")


# Lancement du bot
bot.run(token)

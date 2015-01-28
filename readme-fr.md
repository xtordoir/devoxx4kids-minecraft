## Instructions Plugins Minecraft

## Etape 1: Premier plugin - commande checkpoint et goto

```git checkout step01```

Commande ```checkout <label>``` permet d'enregistrer la localisation courante du joureur dans le monde avec un nom (```<label>```).
Exemple:

```checkpoint home```

Commande ```got <label>``` permet de retourner par teleportation au point ```<label>```.
Exemple:

```goto home```

## Etape 2: goto peut résulter un un saut vers le haut (la magie ne fonctionne pas toujours...)

changer le parametre de chance et le vecteur de saut pour voir...

## Etape 3: Event Fleche touche

Au toucher de fleche, un message s'affiche pour celui qui a lancé la flèche

## Etape 4: Event fleche touche, coordonnées de l'évenement

Pour afficher la distance en blocs, ajouter:

```
    val dist = arrow.getLocation.distance(player.getLocation)
    player.sendMessage("Distance :" + dist)
```


## Etape 5 hmmm petite explosion à l'atterissage?

Modifier la puissance de l'explosion,...
Ajouter le feu...



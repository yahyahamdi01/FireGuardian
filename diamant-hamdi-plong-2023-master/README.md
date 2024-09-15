# Projet : Simulateur de Caserne de Pompiers

## Description

Ce projet implique le développement d'un simulateur des différentes opérations d'une caserne de pompiers dans un monde autonome.L'idée est de gérer un ensemble de robots pompiers avec des caractéristiques variées afin d'éteindre des incendies de diverses intensités sur des terrains distincts.

## COMPILATION ET EXECUTION

Pour compiler le projet:
```mvn verify```
et pour lancer le projet:
```java -jar target/FirefighterSimulation-1.0-SNAPSHOT-jar-with-depencies.jar --gui```

## Ressources

Elles sont accessibles à partir de ce dépôt : 
[ressources_scientifiques_prjlong_dianmanthamdi](https://gaufre.informatique.univ-paris-diderot.fr/hamdiy/ressources_scientifiques_prjlong_dianmanthamdi)


## Objectifs

### Principal

- Conception de l'environnement du projet, incluant les casernes, les terrains, les points de remplissage d'eau et les incendies.
- Création d'un algorithme capable d'éteindre les incendies de la manière la plus ideale possible en prenant en compte les différentes variables(Chemin,robots disponibles)
- Définition des différentes stratégies utilisées pour le choix du robot.

### Intermédiaire

- Définition des différentes stratégies utilisées pour le choix du robot.
- Développement et définition des caractéristiques des robots.
-  Conception de l'algorithme A* pour calculer la distance entre le robot et l'incendie, ainsi que les points de remplissage d'eau.
- Création d'un système de priorité prenant en compte les caractéristiques des robots en plus de la distance qui les sépare de l'incendie.

### Optionnel

Le programme devrait être capable de prendre la décision idéale en fonction du temps de trajet, de la capacité du réservoir, du type de terrain et du temps nécessaire pour éteindre l'incendie et remplir le réservoir.

Par exemple : si un incendie d'une intensité de 3 se trouve en montagne, le choix se fera entre le robot de type 4X4 et le drone. Certes, le drone est plus rapide, mais son réservoir lui permet d'éteindre un incendie d'intensité 1 d'un seul coup, ce qui signifie qu'il devra effectuer 3 allers-retours pour l'éteindre complètement. Le 4x4, quant à lui, est moins rapide mais peut l'éteindre d'un seul coup.

### Environnement

Notre environnement se compose d'une caserne de pompiers et de terrains différents, tels que des routes goudronnées et non goudronnées, ainsi que des rivières. On y trouve également des incendies, des points de remplissage d'eau et des robots.

### Incendies

Les incendies se caractérisent par un emplacement et une intensité. Pour l'instant, nous utilisons une unité de mesure d'intensité croissante allant de 1 à 9.

### Vehicules

Les vehicules sont caracterisés avec des capacités différentes et un réservoir d'eau. Il convient de noter que la vitesse des vehiculent est représentée en fonction du nombre de cases traversées par seconde. La capacité des réservoirs est également représentée par un numéro allant de 1 à 5. Un réservoir de capacité 2  peut éteindre un incendie d'intensité 1 d'un seul coup et en deux coups un incendie d'intensité 4.

Nous trouvons 4 types de robots :

- Le camion : un véhicule qui ne peut circuler que sur des routes goudronnées. D'une part, sa vitesse est de 1 case/s. D'autre part, sa capacité de réservoir est la plus grande avec une valeur de 5.
- Le 4X4 peut circuler sur n'importe quelle route avec une vitesse de 3 cases/s. Son réservoir a une capacité de 3.
- L'avion' est le plus rapide avec une vitesse égale à 5 cases/s. Il peut aussi se déplacer directement vers l'incendie sans suivre les chemins existants. Son réservoir est petit avec une capacité égale à 1.
- Le bateau ne peut emprunter que les voies maritimes avec une vitesse égale à 2 cases/s. Son réservoir a une capacité égale à 4.

### Remplissage du réservoir

Une fois que le réservoir du robot est vide, il faut se diriger vers l'un des nombreux points de remplissage qui se trouvent sur la carte. Le robot connaît tous les emplacements à l'avance. Il faut ainsi calculer le chemin le plus rapide pour effectuer un aller-retour depuis l'incendie vers le point de remplissage.

### Remarque

Il existe un temps de remplissage qui est une unité de réservoir par seconde. Plus le réservoir est grand, plus le temps de remplissage est long. L'algorithme doit donc prendre en considération le temps de remplissage du réservoir avant d'affecter le robot.

## Déroulement
L'environnement de notre sujet est un monde autonome. Au cours du temps, des incendies de differentes intensités apparaissent d'une manière aleatoire. Le programme devrait etre capable de les etreindre de la manière la plus efficace en fonction des ressources disponibles.

## Algorithmes

- Nous utiliserons l'algorithme A* pour trouver le chemin le plus court depuis l'emplacement du robot jusqu'à sa destination qui peur etre soit l'endroit de l'incendie ou un point de remplissage d'eau. On ajoutera un calcul par rapport au differents types de terrains et les vehicules .En se basant sur ce dernier, on choisira le vehicule le mieux adapté.
- Nous utiserons l'algorithme des lucioles pour trouver une solution qui tend vers l'optimale



## Originalité 
## Pourquoi ce n'est pas un collage d'API ?
Aucun API n'a été utilisé  dans ce projet.

## Testabilites

### Utilisation des Données pour Tester Notre Programme de Prise de Décision en Lutte contre les Incendies

Les données présentées dans le document kowait_firefighting_responsetime.pdf nous fourniront une référence essentielle pour évaluer l'efficacité de notre programme de prise de décision en lutte contre les incendies. En comparant les performances de notre programme avec les statistiques réelles, nous serons en mesure de déterminer son adéquation et son potentiel d'amélioration.

### Tableau V : Résultats du Modèle de Simulation

Le Tableau V dans le document  présente diverses statistiques sur l'utilisation des stations de lutte contre les incendies, y compris le temps de réponse moyen, le nombre moyen d'incidents supérieurs à 10 minutes, et l'utilisation moyenne. Ces données nous permettront de :
1. **Comparer les temps d'intervention'** : En observant les temps d'intervention moyens des différentes stations, nous pourrons évaluer si notre programme propose des réponses plus rapides ou équivalentes.
2. **Évaluer l'utilisation des ressources** : L'utilisation moyenne (%) nous aidera à comprendre si notre programme utilise les ressources de manière plus efficace.
3. **Analyser les incidents** : Le nombre moyen d'incidents supérieurs à 10 minutes nous aidera à voir si notre programme peut réduire ces occurrences, améliorant ainsi la rapidité et l'efficacité de la réponse.

### Section III du document : Opérations de Lutte contre les Incendies au Koweït

Cette section offre un aperçu des opérations de lutte contre les incendies au Koweït, incluant le nombre total d'incidents et leur classification. Les points clés incluent :
1. **Types d'incidents** : Les incidents sont classés en incendies résidentiels, commerciaux, de transport aérien et maritime, etc. Cette classification nous permet de tester notre programme dans différents scénarios pour voir comment il gère chaque type d'incident.
2. **Distribution des incidents** : Avec 30,4% des incidents étant des incendies et 69,6% étant des non-incendies, notre programme doit être capable de gérer efficacement les deux types. Nous utiliserons ces proportions pour tester la polyvalence de notre programme.



# Optimisation de la Stratégie de Lutte contre les Incendies : force brute

 Ce document explore un algorithme d'optimisation discrète conçu pour optimiser la séquence dans laquelle une seule équipe de pompiers devrait aborder plusieurs incendies afin de minimiser le temps total passé en déplacement et en extinction des feux.

## Introduction au Problème d'Optimisation

On considère un ensemble d'incendies survenant à différents emplacements, chacun avec un niveau d'intensité unique qui correspond au temps nécessaire pour l'éteindre. Étant donné ces paramètres, le défi réside dans la détermination de l'ordre le plus efficace pour qu'une seule équipe de pompiers s'attaque à ces incendies, en partant de et en revenant finalement à la caserne. L'objectif est de minimiser le temps opérationnel total, englobant à la fois le temps de déplacement entre les lieux et le temps de lutte contre l'incendie à chaque site.

## Représentation du Problème

- **Incendies** : Définis comme une liste de dictionnaires, où chaque dictionnaire représente un incendie avec son emplacement (en coordonnées `x, y`) et son intensité (indiquant le temps nécessaire pour l'éteindre).
- **Emplacement de la Caserne** : Le point de départ et d'arrivée pour l'équipe de pompiers, représenté en coordonnées `x, y`.
- **Objectif** : Minimiser le temps total passé en déplacement et en lutte contre les incendies.

## L'Algorithme

### Fonction : `calculate_total_time(sequence)`

- **But** : Calcule le temps total requis pour une séquence donnée de lutte contre les incendies, incluant à la fois les temps de déplacement et d'extinction.
- **Entrée** : `sequence` - Un tuple représentant l'ordre dans lequel les incendies sont abordés, chaque élément étant un indice faisant référence à un incendie dans la liste `fires`.
- **Deroulement** :
  - Initialiser le temps total à zéro et définir l'emplacement actuel à la caserne.
  - Pour chaque incendie dans la séquence, calculer le temps de déplacement de l'emplacement actuel à l'emplacement de l'incendie en utilisant la formule de distance euclidienne. Ajouter ce temps de déplacement et l'intensité de l'incendie au temps total. Mettre à jour l'emplacement actuel à l'emplacement de l'incendie.
  - Retourner le temps total une fois que tous les incendies de la séquence ont été pris en compte.
- **Sortie** : Le temps total requis pour que l'équipe de pompiers complète la séquence.

### Optimisation

- Générer toutes les permutations possibles de la séquence dans laquelle les incendies pourraient être abordés en utilisant la fonction `permutations` du module `itertools`. Cette approche considère chaque ordre unique des incendies comme une solution potentielle.
- Pour chaque séquence, utiliser la fonction `calculate_total_time` pour calculer le temps opérationnel total.
- Suivre la séquence qui résulte en un temps total minimum.



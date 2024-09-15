# Présentation de l'Algorithme de Luciole Discret (ALD)

## Definition

L'Algorithme de Luciole Discret (ALD) est inspiré par le comportement naturel des lucioles, attirées les unes vers les autres par leur luminosité. En optimisation, chaque luciole représente une solution potentielle à un problème donné, et la luminosité de chaque luciole reflète la qualité ou l'aptitude de cette solution. L'objectif de l'ALD est de simuler ce comportement pour explorer l'espace de solution et trouver la solution optimale.

## Comment Fonctionne l'ALD ?

1. **Initialisation** : Générer une population initiale de lucioles (solutions potentielles).
2. **Évaluation de l'Aptitude** : Calculer l'aptitude (ou luminosité) de chaque luciole basée sur une fonction objectif définie.
3. **Attraction entre Lucioles** : Les lucioles moins lumineuses sont attirées par les plus lumineuses, ce qui les conduit à explorer l'espace de solution en se déplaçant vers de meilleures solutions.
4. **Mise à Jour des Positions** : Mettre à jour la position (solution) des lucioles en fonction de l'attraction exercée par les lucioles plus lumineuses.
5. **Répétition** : Répéter les étapes 2 à 4 jusqu'à convergence vers une solution optimale ou après un nombre prédéfini d'itérations.

## Pseudocode de l'ALD

```plaintext
Initialiser une population de lucioles avec des positions (solutions) aléatoires
Pour chaque luciole dans la population :
    Évaluer l'aptitude (luminosité) de la luciole

Répéter jusqu'à convergence :
    Pour chaque luciole i dans la population :
        Pour chaque luciole j plus lumineuse que i :
            Calculer l'attraction et ajuster la position de i vers j
            Mettre à jour l'aptitude de i si nécessaire
    Mettre à jour les luminosités et les positions de toutes les lucioles
```

## Adaptation de l'ALD à l'Optimisation des Interventions des Pompiers

### Étape d'Adaptation

Pour adapter l'ALD à notre problème d'optimisation des séquences d'interventions des pompiers, nous considérons chaque séquence d'interventions comme une luciole. L'aptitude d'une luciole est déterminée par le temps total nécessaire pour compléter la séquence d'interventions, y compris les temps de déplacement et d'extinction.

### Processus 

1. **Représentation des Lucioles** : Chaque luciole représente une permutation des incendies, indiquant l'ordre des interventions.
2. **Évaluation de l'Aptitude** : L'aptitude est calculée comme étant inversement proportionnelle au temps total nécessaire pour la séquence d'interventions.
3. **Attraction et Mise à Jour** : Les séquences moins efficaces sont ajustées en fonction des séquences plus efficaces, par des méthodes telles que l'échange d'ordre des incendies dans la séquence, pour explorer de nouvelles séquences potentiellement plus efficaces.
4. **Optimisation** : Ce processus est répété, permettant aux séquences moins efficaces d'être influencées par les séquences plus efficaces, conduisant à une exploration progressive de l'espace de solution vers la séquence optimale d'interventions.

### Pseudocode pour l'optimisation des interventions

```plaintext
Initialiser une population de lucioles, chaque luciole représentant une séquence d'interventions sur les incendies
Pour chaque luciole dans la population :
    Évaluer l'aptitude de la luciole en calculant le temps total nécessaire pour la séquence d'interventions, incluant les temps de déplacement et d'extinction

Répéter jusqu'à convergence ou jusqu'à atteindre le nombre maximal d'itérations :
    Pour chaque luciole i dans la population :
        Pour chaque luciole j plus lumineuse (meilleure aptitude) que i :
            Calculer l'attraction entre i et j et ajuster la position de i vers j
                - Ajustement : Modifier la séquence i en s'inspirant de la séquence j, par exemple, en échangeant deux incendies dans la séquence i pour explorer une nouvelle séquence potentiellement plus efficace
            Mettre à jour l'aptitude de i en recalculant le temps total pour la nouvelle séquence
    Si aucune amélioration significative n'est observée pour un nombre prédéfini d'itérations, considérer que l'algorithme a convergé

Sélectionner la luciole avec la meilleure aptitude (le temps total le plus bas) comme la séquence optimale d'interventions pour les pompiers
```
### Pseudocode pour l'ajustement des lucioles
```plaintext
Pour chaque luciole i moins efficace :
    Trouver une luciole j plus efficace     
    Tenter d'ajuster la séquence de i pour qu'elle soit plus proche de celle de j
        en echangeant l'emplacement de certains incedies
    Évaluer la nouvelle séquence de i
    Si la nouvelle séquence est plus efficace :
        Mettre à jour la séquence et l'aptitude de i
Répéter jusqu'à convergence
```
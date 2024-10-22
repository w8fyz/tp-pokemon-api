# Bonjour !

Voici les quelques modifications que j'ai apporté :

- Rajout d'un système d'inventaire basique avec les "GameObject" basé sur un système d'enum et une interface permettant l'implémentation de leur effet directement via l'API.
- Rajout d'un système d'Arene basique permettant d'identifier les arènes visités par les joueurs.

En bref, rajout de 2 routes principales, arènes et gameObjects et ajout de la partie base de donnée lié.

A la récupération d'un Trainer, on récupère donc aussi les arènes qu'il a visité et ses objets.

Voici un exemple de résultat à la requête **/trainers** GET


```json
[
{
"id": "545615bb-3542-438a-822a-554df58a3ae5",
"name": "TestTrainer",
"team": [],
"gameObjects": [],
"visitedArena": []
},
{
"id": "72257a44-c621-48bd-953d-072a2ca866e2",
"name": "TestTrainer",
"team": [],
"gameObjects": [
{
"id": "a78e194f-6473-49d3-9472-d603252315ab",
"gameObject": "HEALTH_POTION_25"
}
],
"visitedArena": []
},
{
"id": "a30f9eb0-d4d6-4995-891b-0475c9412f94",
"name": "TestTrainerArenas",
"team": [],
"gameObjects": [
{
"id": "45549c66-99b1-4cae-a3e3-b7053d9adb45",
"gameObject": "MANA_POTION_25"
}
],
"visitedArena": [
{
"id": "1c837f33-e969-4fe5-a4ef-4b62754ab513",
"name": "Bonjour"
}
]
}
]
```
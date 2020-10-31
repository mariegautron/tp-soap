# Projet SOAP

## Architecture
* client # Partie client soap (en cli)
* server # Partie server soap
  * controller # Liste des contrôleurs. Convertie les données du WS pour une utilisation dans avec le DAO
  * dao # Gestion du DAO. Utilisation de RepositoryManager.
  * entity # Models utilisés par le DAO
  * model # Models utilisés par le WS
  * ws # Point d'entrée du web service soap

## Lancer le projet pré-compilé 

1. Via une console, dans le dossier out/artifacts/WebServiceTP1_Server_jar
2. Exécuter la commande `java -jar WebServiceTP1.jar`
3. Via une console, dans le dossier out/artifacts/WebServiceTP1_Client_jar
4. Exécuter la commande `java -jar WebServiceTP1.jar`
  
## Exécuter le projet

1. Créer un nouveau projet intellij ou eclipse
2. Installer les dépendances maven `mvn install`
3. Installer les dépendances des fichiers ZIP du dossier `jars`
4. Lancer le serveur `server.ServerMain`. Noter qu'il recrée la base de données à chaque lancement.
5. Lancer le client `client.ClientMain`

## Lancer le projet en mode automatique

1. Répéter les étapes 1, 2, 3 et 4 de la partie `Exécuter le projet`
2. Exécuter le client avec en entrée le jeu de données `client_dataset` `java -jar WebServiceTP1.jar < client_dataset` 

## Fonctionnalités implémentées

* Server
  * CRUD des livres
  * Récupération des livres grâce à un nom d'auteur
  * Récupération des livres sans auteur
  * Récupération des livres publiés après une date
  * CRUD des auteurs
  * Récupération des auteurs grâce à un nom
* Client
  * CRUD des livres
  * Afficher les livres à partir d'un nom d'auteur
  * Afficher les livres sans auteur
  * Afficher les livres publiés après une date
  * CRUD des auteurs
  * Afficher les auteurs à partir du nom
  * Ajouter des livres lors de l'ajout ou la modification d'un auteur
  
## Fonctionnalités à implémenter

* Gestion des exceptions serveur
* Possibilité d'ajouter des auteurs lors de la création d'un livre
* Possibilité d'ajouter des auteurs lors de la mise à jour d'un livre
* Possibilité d'enlever des auteurs lors de la mise à jour d'un livre
* Vérification de doublon lors de l'ajout d'auteurs à un livre
* Possibilité d'enlever des livres lors de la mise à jour d'un auteur
* Vérification de doublon lors de l'ajout de livres à un auteur 
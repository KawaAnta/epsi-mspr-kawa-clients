# epsi-mspr-kawa-clients

## Lancement de l'application sous Docker

Sans PostgreSQL en local, à la racine du projet :

```sh
mvn package -Dmaven.test.skip
docker-compose up
```

URL de l'API : https://localhost:8081
URL de la base: https://localhost:5433

## Lancement de l'application en local

Nécessite un PostgreSQL qui tourne en local sur le port 5432

https://www.postgresql.org/download/

Installez bien PgAdmin

Une fois installez, ouvrez PgAdmin et suivez les étapes suivantes:

-   Créer un utilisateur nommé 'admin' (Dans login/grouprole)
-   Utiliser le mot de passe trouvable dans la conf
-   Donnez lui les privilèges de pouvoir se connecter et les droits superadmin
-   Créer une base de données nommé 'client' et assignez lui l'utilisateur admin

Ensuite, vous pouvez lancer l'application

```sh
mvn clean install
mvn spring-boot:run
```

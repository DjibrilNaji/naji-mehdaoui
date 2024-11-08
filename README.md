# Pimber

# Setup du projet (à la racine du projet)

- BDD

```sh
docker-compose -f docker/docker-compose.yml up
```

ou aller a la racine du projet et lancer :

```sh
cd docker
docker compose up
```

## Lancer les migrations Liquibase :

Installer Liquibase sur macos

```sh
brew install liquibase
```

Dans le répertoire Back (une fois le back lancer et les tables créées avec l'ORM).

```sh
liquibase update --changelog-file src/main/resources/db/changelog/seed-data.yml
```

Si la seed n'a pas fonctionné :

```sh
liquibase update --changelog-file src/main/resources/db/changelog/delete-seed.yml
```

- Back

Lancer le back Spring dans le répertoire `back`.
A noter qu'il faut prendre le fichier d'exemple pour application.properties pour s'adapter à la configuration de la BDD en local.

- Front

Lancer le front Next.js :

```sh
npm i
npm run dev
```

On a la possibilité de voir toutes les soirées, de voir une seule soirée et d'ajouter une soirée.

# Optimisation des requêtes pour les Query N+1

- Utilisation de FetchType -> Lazy pour éviter les problèmes de performances avec les requêtes à gros volume
- Utilisation des Join Fetch en Override des méthodes du CRUD de JPA Repository

# Optimisation de la BDD avec une vue matérialisée : note d'un user calculée automatiquement

## Requête SQL

```sql
CREATE MATERIALIZED VIEW user_average_ratings A`
SELECT
    u.id AS user_id,
    AVG(c.rating) AS average_rating
FROM
    app_user u
        JOIN
    comment c ON u.id = c.posting_user_id
GROUP BY
    u.id;
```

A terme, on ajoutera un refresh sur la vue lorsque des commentaires sont ajoutés ou modifiés pour le user.

## Entité

```java
@Entity
@Data
@Table(name = "user_average_ratings")
public class UserAverageRating {
    @Id
    private UUID userId;

    private Double averageRating;
}
```

# Partition

## Recréation de la table party depuis SQL pour la rendre partionnable

```sql
ALTER TABLE party RENAME TO party_old;
```

```sql
CREATE TABLE party (
                       id UUID,
                       count_people INT,
                       max_people INT,
                       start_timestamp TIMESTAMP NOT NULL,
                       end_timestamp TIMESTAMP,
                       publication TIMESTAMP,
                       price FLOAT,
                       bring_something BOOLEAN,
                       type_id UUID,
                       address_id UUID,
                       creator_id UUID,
                       PRIMARY KEY (id, start_timestamp)  -- Ajoutez start_timestamp à la clé primaire
) PARTITION BY RANGE (start_timestamp);
```

- Partitions

```sql
-- Partition pour les événements passés (avant 2024)
CREATE TABLE party_past PARTITION OF party
    FOR VALUES FROM (MINVALUE) TO ('2024-01-01 00:00:00');

-- Partition pour les événements actuels (2024)
CREATE TABLE party_current PARTITION OF party
    FOR VALUES FROM ('2024-01-01 00:00:00') TO ('2025-01-01 00:00:00');

-- Partition pour les événements futurs (à partir de 2025)
CREATE TABLE party_future PARTITION OF party
    FOR VALUES FROM ('2025-01-01 00:00:00') TO (MAXVALUE);
```

# Swagger

URL : http://localhost:8080/swagger-ui/index.html

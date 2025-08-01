# Pimber

# Project Setup (at the root of the project)

- Database

```sh
docker-compose -f docker/docker-compose.yml up
```

or go to the root of the project and run:

```sh
cd docker
docker compose up
```

## Run Liquibase Migrations:

Install Liquibase on macOS:

```sh
brew install liquibase
```

In the Back directory (once the backend is running and the tables are created with the ORM):

```sh
liquibase update --changelog-file src/main/resources/db/changelog/seed-data.yml
```

If the seed didn't work:

```sh
liquibase update --changelog-file src/main/resources/db/changelog/delete-seed.yml
```

- Backend

Run the Spring backend in the `back` directory.
Note: Use the example `application.properties` file and adapt it to your local database configuration.

- Frontend

Run the Next.js frontend:

```sh
npm i
npm run dev
```

You can view all parties, view a single party, and add a party.

# Query Optimization for N+1 Problem

- Use `FetchType.LAZY` to avoid performance issues with large-volume queries
- Use Join Fetch overrides in the CRUD methods of JPA Repository

# Database Optimization with a Materialized View: User's Average Rating Calculated Automatically

## SQL Query

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

Eventually, a refresh will be added to the view when comments are added or updated for a user.

## Entity

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

# Partitioning

## Recreate the `party` table in SQL to make it partitionable

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

# Cache

- Cache is used for some queries

# Chapter 2 - Getting Started with Serverless Java
This directory contains example projects used in Chapter 2 - Getting Started with Serverless Java. 

All of the examples showcase the exact same code snippets as discussed in Chapter 2 of the book. 

## Run a PostgreSQL locally with a native executable

Run a PostgreSQL using the following Docker command.

```
podman run --name lightspeedbooster -p 5432:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=superSecret -d postgres:14
```

Connect to the database.

```
psql -h localhost -p 5432 -U user
```

Enter `superSecret` for the password.

Create a database schema, `lightspeedbooster` using the following PSQL command:

```
user=# CREATE DATABASE lightspeedbooster;
```

The output should be something like this.

```
CREATE DATABASE
```

You can verify if the database is created properly using the following PSQL command.

```
user=# \l
```

The output should be something like this.

```
                                                 List of databases
       Name        | Owner | Encoding |  Collate   |   Ctype    | ICU Locale | Locale Provider | Access privileges 
-------------------+-------+----------+------------+------------+------------+-----------------+-------------------
 lightspeedbooster | user  | UTF8     | en_US.utf8 | en_US.utf8 |            | libc            | 
 postgres          | user  | UTF8     | en_US.utf8 | en_US.utf8 |            | libc            | 
 template0         | user  | UTF8     | en_US.utf8 | en_US.utf8 |            | libc            | =c/user          +
                   |       |          |            |            |            |                 | user=CTc/user
 template1         | user  | UTF8     | en_US.utf8 | en_US.utf8 |            | libc            | =c/user          +
                   |       |          |            |            |            |                 | user=CTc/user
 user              | user  | UTF8     | en_US.utf8 | en_US.utf8 |            | libc            | 
(5 rows)
```

After you run Quarkus Dev Mode or native executable, you can find that the table, `lightspeedbooster` is created by Quarkus. 

Run the following PSQL command.

```
user=# \c lightspeedbooster
```

The output should be something like this.

```
psql (15.4, server 14.6 (Debian 14.6-1.pgdg110+1))
You are now connected to database "lightspeedbooster" as user "user".
```

Quarkus already loaded the data using the `import.sql` as well. Run the following PSQL command.

```
lightspeedbooster=# select * from lightspeedbooster;
```

The output should be something like this.

```
 credit | iswarpdrive | quantity | id |     name      
--------+-------------+----------+----+---------------
    300 | f           |      100 |  1 | Tachyon Burst
    250 | f           |      200 |  2 | Thrusters
   5000 | t           |       10 |  3 | Transwarp
    700 | f           |      300 |  4 | Turbolift
(4 rows)
```

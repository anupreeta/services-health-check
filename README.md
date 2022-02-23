## Solution Description
This is an application to check health of services and periodically keeps a track of status of these added services. The
polling gets triggered every 30 seconds.
Each service is saved with details: id, name, url, status, created and modified.

Database runs in a docker container and is brought up using

``docker-compose up --build --force-recreate``

init.sql script is run to create table ``services`` and populate it with first service entry.
Database credentials are saved in application.properties under resources
Whenever a new service is added the first time, its status is set to default i.e. UNKNOWN.
Once the app is up, the poller (HealthCheck) gets executed every 30 seconds which send GET request to the url of the service. If it succeeds (HTTP CODE = 200) then status is updated to "OK", for failures it gets set to "FAIL"

## Frameworks used
* Frontend : React (under my-app)
* Backend: Springboot (under src) 
* Database: MySQL (under db)

## Build system used
Build: gradle

## Steps to run the application
* Start database by bringing up docker container by running docker-compose
  * ``docker-compose up --build --force-recreate```
* Build and start backend
```./gradlew clean build```
* Run the springboot application
```./gradlew bootRun```
  Here you will see that health check poll kicks off in 30 seconds and keeps polling added services.
* Bring up react frontend
  * cd my-app
  * npm start 
  * Check out the UI at `http://localhost:3000/`
  
* Optional: check for CRUD actions in the database inside docker

```
 docker ps 
 docker exec -it <container-name> /bin/bash
 mysql -u dev -p
 use dev;
mysql> show tables;
+---------------+
| Tables_in_dev |
+---------------+
| services      |
+---------------+

mysql> select * from services;
| id | name              | url                                   | created             | modified            | status |
+----+-------------------+---------------------------------------+---------------------+---------------------+--------+
|  1 | test_service1     | http://test_service1:8081/            | 2022-02-08 18:30:36 | 2022-02-23 21:44:30 | FAIL   |
|  5 | incorrect-service | http://localhost:8080/services/wrong  | 2022-02-09 22:56:43 | 2022-02-23 21:44:31 | FAIL   |
|  6 | incorrect-service | http://localhost:8080/services/error  | 2022-02-09 23:02:32 | 2022-02-23 21:44:31 | FAIL   |
|  7 | youtube           | https://www.youtube.com/              | 2022-02-09 23:31:49 | 2022-02-23 21:44:32 | OK     |
| 10 | docker            | https://hub.docker.com/               | 2022-02-09 23:51:46 | 2022-02-23 21:44:32 | OK     |
| 11 | cancel-service    | http://localhost:8080/services/cancel | 2022-02-10 00:01:52 | 2022-02-23 21:44:32 | FAIL   |
| 12 | space-service     | http://www.space.com                  | 2022-02-10 00:09:55 | 2022-02-10 00:09:55 | FAIL   |
| 14 | trial-service     | http://www.google.com                 | 2022-02-10 00:18:10 | 2022-02-23 21:44:33 | OK     |
| 15 | week-service      | https://www.vecka.nu                  | 2022-02-10 00:23:46 | 2022-02-23 21:44:33 | OK     |
| 16 | music             | https://www.spotify.com               | 2022-02-11 18:30:23 | 2022-02-23 21:44:34 | OK     |
+----+-------------------+---------------------------------------+---------------------+---------------------+--------+
 ```
* You can manually check REST API calls using POSTMAN or curl
  * Save service: POST http://localhost:8080/services/save and provide request body in json with name and url
  * Get list of services: GET http://localhost:8080/services/list
  * Retrieve details of one service: GET http://localhost:8080/services/<id> 
  * Update name and/url of a service: POST http://localhost:8080/services/<id>, provide id of the service to be updated 
    and request body with new name and/or url of the existing service
  * Delete a service: DELETE http://localhost:8080/services/<id> where id is the id of the service to be deleted


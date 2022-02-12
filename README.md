## Solution Description
This is an application to check health of services and periodically keeps a track of status of these added services. The
polling gets triggered every 30 seconds.
Each service is saved with details: id, name, url, status, created and modified.

Database runs in a docker container and is brought up using
```docker-compose up --build --force-recreate```

init.sql script is run to create table ``services`` and populate it with first service entry.
Database credentials are saved in application.properties under resources
Whenever a new service is added the first time, its status is set to default i.e. UNKNOWN.
Once the app is up, the poller (HealthCheck) gets executed every 30 seconds which send GET request to the url of the service. If it succeeds (HTTP CODE = 200) then status is updated to "OK", for failures it gets set to "FAIL"

## Frameworks used
Frontend : React (under my-app)
Backend: Springboot (under src) 
Database: MySQL (under db)

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
+----+-------------------+---------------------------------------+---------------------+---------------------+---------+
| id | name              | url                                   | created             | modified            | status  |
+----+-------------------+---------------------------------------+---------------------+---------------------+---------+
|  1 | test_service1     | http://test_service1:8081/            | 2022-02-08 18:30:36 | 2022-02-08 18:30:36 | FAIL    |
|  5 | incorrect-service | http://localhost:8080/services/wrong  | 2022-02-09 22:56:43 | 2022-02-09 22:56:43 | FAIL    |
|  6 | incorrect-service | http://localhost:8080/services/error  | 2022-02-09 23:02:32 | 2022-02-09 23:02:32 | FAIL    |
|  7 | fail-service      | http://localhost:8080/services/fail   | 2022-02-09 23:31:49 | 2022-02-09 23:31:49 | FAIL    |
|  8 | search-service    | http://www.google.com                 | 2022-02-09 23:41:21 | 2022-02-09 23:41:21 | OK      |
| 10 | bogus-service     | http://localhost:8080/services/bogus  | 2022-02-09 23:51:46 | 2022-02-09 23:51:46 | FAIL    |
| 11 | cancel-service    | http://localhost:8080/services/cancel | 2022-02-10 00:01:52 | 2022-02-10 00:01:52 | FAIL    |
| 12 | space-service     | http://www.space.com                  | 2022-02-10 00:09:55 | 2022-02-10 00:09:55 | UNKNOWN |


references:
https://howtodoinjava.com/spring-core/spring-scheduled-annotation/
https://www.baeldung.com/spring-scheduled-tasks (fixed rate versus fixed delay)
 ```
* You can manually check REST API calls using POSTMAN or curl
  * Give the path urls here
* Start the react app 
  * npm start

.

docker commands:

docker exec -it <container-name> /bin/bash
mysql -u dev -p
use dev;

Problems faced:
1) Learnt more about diff options of Polling for @Scheduled -fixedRate and fixedDelay
2) Working on UI was a bit challenging but was fun and learnt new stuff. Learned about MUI components and use of create-react-app
3) SQL problem - wrong column name used lastModified, changed to modified
4) Difference between getById (lazy loading of entity and only reference to proxy of entity) and findById (eager loading
of entity and immediately work on actual entity)
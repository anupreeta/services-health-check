docker commands:

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
+----+-----------------+------------------------------+---------------------+---------------------+---------+
| id | name            | url                          | created             | lastModified        | status  |
+----+-----------------+------------------------------+---------------------+---------------------+---------+
|  1 | test_service1   | http://test_service1:8081/   | 2022-02-07 20:23:50 | 2022-02-07 20:23:50 | UNKNOWN |
|  2 | fruit-service   | https://www.fruits.com:9000  | 2022-02-07 23:05:45 | 2022-02-07 22:05:45 | UNKNOWN |
|  3 | clothes-service | https://www.clothes.com:9010 | 2022-02-07 23:07:54 | 2022-02-07 22:07:54 | UNKNOWN |
+----+-----------------+------------------------------+---------------------+---------------------+---------+



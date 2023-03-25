# docker mariadb replication

## create mariadb-master volume
```
# mkdir -p ~/{님이 원하는 디렉토리}/docker-volume/mariadb/master/var/lib/mysql
```
```
# mkdir -p ~/{님이 원하는 디렉토리}/docker-volume/mariadb/master/etc/mysql/conf.d
```

## create mariadb-slave-1 volume
```
# mkdir -p ~/{님이 원하는 디렉토리}/docker-volume/mariadb/slave-1/var/lib/mysql
```
```
# mkdir -p ~/{님이 원하는 디렉토리}/docker-volume/mariadb/slave-1/etc/mysql/conf.d
```

## create mariadb-slave-2 volume
```
# mkdir -p ~/{님이 원하는 디렉토리}/docker-volume/mariadb/slave-2/var/lib/mysql
```
```
# mkdir -p ~/{님이 원하는 디렉토리}/docker-volume/mariadb/slave-2/etc/mysql/conf.d
```

## create docker-compose.yml
```yaml
version: '3.8'
services:
  mariadb-master:
    image: mariadb:10.8.3
    container_name: mariadb-master
    #restart: always
    environment:
      MYSQL_ROOT_PASSWORD_FILE: /run/secrets/mariadb-root-password
    volumes:
      - ./master/var/lib/mysql:/var/lib/mysql
      - ./master/etc/mysql/conf.d/:/etc/mysql/conf.d
    ports:
      - '13306:3306'
    secrets:
      - mariadb-root-password
  mariadb-slave-1:
    image: mariadb:10.8.3
    container_name: mariadb-slave-1
    #restart: always
    environment:
      MYSQL_ROOT_PASSWORD_FILE: /run/secrets/mariadb-root-password
    volumes:
      - ./slave-1/var/lib/mysql:/var/lib/mysql
      - ./slave-1/etc/mysql/conf.d/:/etc/mysql/conf.d
    ports:
      - 13307:3306
    secrets:
      - mariadb-root-password
    depends_on:
      - mariadb-master
  mariadb-slave-2:
    image: mariadb:10.8.3
    container_name: mariadb-slave-2
    #restart: always
    environment:
      MYSQL_ROOT_PASSWORD_FILE: /run/secrets/mariadb-root-password
    volumes:
      - ./slave-2/var/lib/mysql:/var/lib/mysql
      - ./slave-2/etc/mysql/conf.d/:/etc/mysql/conf.d
    ports:
      - 13308:3306
    secrets:
      - mariadb-root-password
    depends_on:
      - mariadb-master
secrets:
  mariadb-root-password:
    file: ./mariadb-root-password.txt
```

## create mariadb root password file
```
# vi ~/{님이 원하는 디렉토리}/docker-volume/mariadb/mariadb-root-password.txt
```
mariadb-root-password.txt 파일에 mariadb root password 작성할 것

## create mariadb-master my.cnf file
```
# vi ~/{님이 원하는 디렉토리}/docker-volume/mariadb/master/etc/mysql/conf.d/my.cnf
```
```
# 파일내용
[mysqld]
log-bin=mysql-bin
server-id=1
```

## create mariadb-slave-1 my.cnf file
```
# vi ~/{님이 원하는 디렉토리}/docker-volume/mariadb/slave-1/etc/mysql/conf.d/my.cnf
```
```
# 파일내용
[mysqld]
log-bin=mysql-bin
server-id=2
relay-log=relaylog
log-slave_updates=1
```

## create mariadb-slave-2 my.cnf file
```
# vi ~/{님이 원하는 디렉토리}/docker-volume/mariadb/slave-2/etc/mysql/conf.d/my.cnf
```
```
# 파일내용
[mysqld]
log-bin=mysql-bin
server-id=3
relay-log=relaylog
log-slave_updates=1
```

## run docker-compose
```
# docker-compose -f ~/{님이 원하는 디렉토리}/docker-volume/mariadb/docker-compose-mariadb.yml up -d
```

## mariadb-master docker container 진입
```
# docker exec -it mariadb-master /bin/bash
```
```
# mysql -u root -p
<input mariadb root password>
MariaDB [(none)]> create database random_matching;
MariaDB [(none)]> grant all privileges on random_matching.* to random_matching@'%' identified by 'password';
MariaDB [(none)]> grant replication slave on *.* to 'random_matching'@'%';
MariaDB [(none)]> flush privileges;
```

## mariadb-slave-1 docker container 진입
```
# docker exec -it mariadb-slave-1 /bin/bash
```
```
MariaDB [(none)]> create user 'random_matching'@'%' identified by 'password';
MariaDB [(none)]> grant all privileges on random_matching.* to random_matching@'%' identified by 'password';
MariaDB [(none)]> flush privileges;

MariaDB [(none)]> reset master;
MariaDB [(none)]> show master status\G;
*************************** 1. row ***************************
            File: mysql-bin.000001
        Position: 328
    Binlog_Do_DB:
Binlog_Ignore_DB:

MariaDB [(none)]> CHANGE MASTER TO MASTER_HOST='mariadb-master', MASTER_USER='random_matching', MASTER_PASSWORD='password', MASTER_LOG_FILE='mysql-bin.000001', MASTER_LOG_POS=328;
MariaDB [(none)]> start slave;
MariaDB [(none)]> show slave status\G;
*************************** 1. row ***************************
                Slave_IO_State: Waiting for master to send event
                   Master_Host: mariadb-master
                   Master_User: random_matching
                   Master_Port: 3306
                 Connect_Retry: 60
               Master_Log_File: mysql-bin.000004
           Read_Master_Log_Pos: 13439
                Relay_Log_File: relaylog.000005
                 Relay_Log_Pos: 13738
         Relay_Master_Log_File: mysql-bin.000004
              Slave_IO_Running: Yes
             Slave_SQL_Running: Yes
               Replicate_Do_DB:
           Replicate_Ignore_DB:
            Replicate_Do_Table:
        Replicate_Ignore_Table:
       Replicate_Wild_Do_Table:
   Replicate_Wild_Ignore_Table:
                    Last_Errno: 0
                    Last_Error:
                  Skip_Counter: 0
           Exec_Master_Log_Pos: 13439
               Relay_Log_Space: 13907
               Until_Condition: None
                Until_Log_File:
                 Until_Log_Pos: 0
            Master_SSL_Allowed: No
            Master_SSL_CA_File:
            Master_SSL_CA_Path:
               Master_SSL_Cert:
             Master_SSL_Cipher:
                Master_SSL_Key:
         Seconds_Behind_Master: 0
 Master_SSL_Verify_Server_Cert: No
                 Last_IO_Errno: 0
                 Last_IO_Error:
                Last_SQL_Errno: 0
                Last_SQL_Error:
   Replicate_Ignore_Server_Ids:
              Master_Server_Id: 1
                Master_SSL_Crl:
            Master_SSL_Crlpath:
                    Using_Gtid: No
                   Gtid_IO_Pos:
       Replicate_Do_Domain_Ids:
   Replicate_Ignore_Domain_Ids:
                 Parallel_Mode: optimistic
                     SQL_Delay: 0
           SQL_Remaining_Delay: NULL
       Slave_SQL_Running_State: Slave has read all relay log; waiting for more updates
              Slave_DDL_Groups: 38
Slave_Non_Transactional_Groups: 3
    Slave_Transactional_Groups: 2
```

## mariadb-slave-2 docker container 진입
```
# docker exec -it mariadb-slave-2 /bin/bash
```
```
MariaDB [(none)]> create user 'random_matching'@'%' identified by 'password';
MariaDB [(none)]> grant all privileges on random_matching.* to random_matching@'%' identified by 'password';
MariaDB [(none)]> flush privileges;

MariaDB [(none)]> reset master;
MariaDB [(none)]> show master status\G;
*************************** 1. row ***************************
            File: mysql-bin.000001
        Position: 328
    Binlog_Do_DB:
Binlog_Ignore_DB:

MariaDB [(none)]> CHANGE MASTER TO MASTER_HOST='mariadb-master', MASTER_USER='random_matching', MASTER_PASSWORD='password', MASTER_LOG_FILE='mysql-bin.000001', MASTER_LOG_POS=328;
MariaDB [(none)]> start slave;
MariaDB [(none)]> show slave status\G;
*************************** 1. row ***************************
                Slave_IO_State: Waiting for master to send event
                   Master_Host: mariadb-master
                   Master_User: random_matching
                   Master_Port: 3306
                 Connect_Retry: 60
               Master_Log_File: mysql-bin.000004
           Read_Master_Log_Pos: 13439
                Relay_Log_File: relaylog.000005
                 Relay_Log_Pos: 13738
         Relay_Master_Log_File: mysql-bin.000004
              Slave_IO_Running: Yes
             Slave_SQL_Running: Yes
               Replicate_Do_DB:
           Replicate_Ignore_DB:
            Replicate_Do_Table:
        Replicate_Ignore_Table:
       Replicate_Wild_Do_Table:
   Replicate_Wild_Ignore_Table:
                    Last_Errno: 0
                    Last_Error:
                  Skip_Counter: 0
           Exec_Master_Log_Pos: 13439
               Relay_Log_Space: 13907
               Until_Condition: None
                Until_Log_File:
                 Until_Log_Pos: 0
            Master_SSL_Allowed: No
            Master_SSL_CA_File:
            Master_SSL_CA_Path:
               Master_SSL_Cert:
             Master_SSL_Cipher:
                Master_SSL_Key:
         Seconds_Behind_Master: 0
 Master_SSL_Verify_Server_Cert: No
                 Last_IO_Errno: 0
                 Last_IO_Error:
                Last_SQL_Errno: 0
                Last_SQL_Error:
   Replicate_Ignore_Server_Ids:
              Master_Server_Id: 1
                Master_SSL_Crl:
            Master_SSL_Crlpath:
                    Using_Gtid: No
                   Gtid_IO_Pos:
       Replicate_Do_Domain_Ids:
   Replicate_Ignore_Domain_Ids:
                 Parallel_Mode: optimistic
                     SQL_Delay: 0
           SQL_Remaining_Delay: NULL
       Slave_SQL_Running_State: Slave has read all relay log; waiting for more updates
              Slave_DDL_Groups: 38
Slave_Non_Transactional_Groups: 3
    Slave_Transactional_Groups: 2
```
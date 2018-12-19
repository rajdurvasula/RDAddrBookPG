#!/bin/bash
/root/setup_db_host_ip.sh
java -jar /root/app/RDAddrBook-0.0.1-SNAPSHOT.jar --server.port=$APP_PORT --spring.datasource.url=jdbc:postgresql://$PG_HOST:$PG_PORT/$APP_DB --spring.datasource.username=$PG_USER --spring.datasource.password=$PG_PASSWORD

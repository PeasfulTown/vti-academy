FROM mysql:8.0.44-debian

ADD ./DB_TestingSystem.sql /etc/mysql/DB_TestingSystem.sql
RUN cp /etc/mysql/DB_TestingSystem.sql /docker-entrypoint-initdb.d

EXPOSE 3306


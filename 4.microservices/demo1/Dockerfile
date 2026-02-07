# Sử dụng MySQL phiên bản 8 làm base image
FROM mysql:8

# Thiết lập biến môi trường cho MySQL
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=user_db
ENV MYSQL_USER=myuser
ENV MYSQL_PASSWORD=password

COPY init.sql /docker-entrypoint-initdb.d

# Expose cổng 3306
EXPOSE 3306

# Chạy MySQL server
CMD ["mysqld"]

CREATE TABLE usuarios
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(300) NOT NULL,
    PRIMARY KEY (id)
)
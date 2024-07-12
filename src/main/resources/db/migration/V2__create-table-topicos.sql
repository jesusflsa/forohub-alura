CREATE TABLE topicos
(
    id         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo     VARCHAR(100) NOT NULL,
    mensaje    VARCHAR(255) NOT NULL,
    status     TINYINT      NOT NULL DEFAULT 1,
    curso      varchar(100) NOT NULL,

    usuario_id BIGINT       NOT NULL REFERENCES usuarios (id)

)
CREATE DATABASE CLIENTEBD;

CREATE TABLE CLIENTEBD.CLIENTE (
  ID              BIGINT       NOT NULL AUTO_INCREMENT,
  NOME            VARCHAR(100) NOT NULL,
  DATA_NASCIMENTO DATE         NOT NULL,
  CONSTRAINT PK_CLIENTE PRIMARY KEY (ID)
);
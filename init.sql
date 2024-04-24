CREATE USER myuser WITH PASSWORD '123';
ALTER USER myuser WITH SUPERUSER; -- Esto podría ser necesario dependiendo de tus necesidades

CREATE DATABASE mydb;
GRANT ALL PRIVILEGES ON DATABASE mydb TO myuser;

\connect mydb myuser;

CREATE SEQUENCE empleado_clave_seq
 INCREMENT 1
 START 1
 MINVALUE 1
 MAXVALUE 2147483647
 CACHE 1;

CREATE TABLE empleado (
        clave integer NOT NULL DEFAULT nextval('empleado_clave_seq'::regclass),
        nombre character varying(255),
        direccion character varying(255),
        telefono character varying(20),
        CONSTRAINT empleado_pkey PRIMARY KEY (clave)
    );

GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO myuser;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO myuser;


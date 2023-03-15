CREATE ROLE "POSYSTEMAPPROLE" WITH
  LOGIN
  NOSUPERUSER
  INHERIT
  NOCREATEDB
  NOCREATEROLE
  NOREPLICATION;

CREATE ROLE "PO_SYSTEM_APP_USER" WITH
  LOGIN
  PASSWORD 'mynonsecretpassw0rd'
  NOSUPERUSER
  INHERIT
  NOCREATEDB
  NOCREATEROLE
  NOREPLICATION;

GRANT "POSYSTEMAPPROLE" TO "PO_SYSTEM_APP_USER";

CREATE ROLE "PO_SYSTEM_OWNER_USER" WITH
  LOGIN
  PASSWORD 'mysecret'
  SUPERUSER
  INHERIT
  CREATEDB
  CREATEROLE
  NOREPLICATION;
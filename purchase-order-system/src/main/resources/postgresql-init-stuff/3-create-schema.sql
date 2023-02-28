-- Revoking the ability to do anything in the Public schema
REVOKE CREATE ON SCHEMA public FROM PUBLIC;

-- Creating the base schema for the service
CREATE SCHEMA IF NOT EXISTS purchase_order_system_schema;

GRANT USAGE ON SCHEMA purchase_order_system_schema TO "POSYSTEMAPPROLE";
GRANT ALL ON SCHEMA purchase_order_system_schema TO "PO_SYSTEM_OWNER_USER";

ALTER DEFAULT PRIVILEGES IN SCHEMA purchase_order_system_schema GRANT INSERT, SELECT, UPDATE, DELETE ON tables TO "POSYSTEMAPPROLE";
ALTER ROLE "PO_SYSTEM_APP_USER" IN DATABASE purchase_order_system_db SET search_path TO purchase_order_system_schema;
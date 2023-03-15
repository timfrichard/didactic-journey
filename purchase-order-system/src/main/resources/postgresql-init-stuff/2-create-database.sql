CREATE DATABASE purchase_order_system_db
    WITH
    OWNER = 'PO_SYSTEM_OWNER_USER'
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

GRANT CONNECT ON DATABASE purchase_order_system_db TO "POSYSTEMAPPROLE";
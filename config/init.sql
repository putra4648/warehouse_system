CREATE USER postgres SUPERUSER;

-- Create Database for Keycloak
CREATE DATABASE wms_keycloak;

GRANT ALL PRIVILEGES ON DATABASE wms_keycloak TO admin;
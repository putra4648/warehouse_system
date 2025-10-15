## How to Use

For Authentication and Authorization I use OAuth2 

- Run `docker compose up -d` this will up the services needed
- Run `./mvnw spring-boot:run`
- Go to `http://localhost:9000`, sign with username `superadmin` and password `admin123`
- In Dashboard menu look for realms, Manage Realms and choose `wms` realm
- In Clients menu, choose client name `wms-client`, make sure this config is enabled in Capability config
    - Client authentication is __enabled__
    - Authorization is __enabled__
    - Authentication flow:
        - Standard flow is __enabled__
        - Direct access grant is __enabled__
- In Users menu, choose `user1`, if you want to change password in `Credentials` and choose `Reset Password`. Default password `user123`. In Details section remove `update password` in field `Required user actions`, check Email Verified is __enabled__ and then Save

### For REST API 

In this case I use Postman

- open `http://localhost:9000/realms/wms/.well-known/openid-configuration` and look for property `token_endpoint`
- for Authorization in Auth Type use OAuth2 and fill detail below
    - Grant type choose `Password Credentials`
    - Access token URL fill from `token_endpoint`
    - Client ID fill `wms-client`
    - Client password is from `Clients` menu &rarr; choose `wms-client` &rarr; in `Credentials` tab just copy `Client Secret`
    - Username is user username in this case `user1`
    - Password is user password in this case `user123`
    - Scope is can be anything default is `openid`
- just scroll to the down and click `Get New Access Token` &rarr; `Proceed` &rarr; `Use Token`
- done you can access REST API with these token

### For Swagger API Documentation

After starting the application, you can access the interactive API documentation:

- **Swagger UI**: `http://localhost:7000/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:7000/v3/api-docs`

To use Swagger UI with OAuth2 authentication:

1. Click the "Authorize" button in Swagger UI
2. In the OAuth2 dialog, enter:
   - **client_id**: `wms-client`
   - **client_secret**: Get this from Keycloak (`Clients` menu → `wms-client` → `Credentials` tab)
   - **username**: `user1`
   - **password**: `user123`
3. Click "Authorize" to authenticate
4. You can now test the API endpoints directly from Swagger UI



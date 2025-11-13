# Swagger API Documentation

This project now includes Swagger/OpenAPI documentation for all REST API endpoints.

## Accessing Swagger UI

Once the application is running, you can access the interactive API documentation at:

- **Swagger UI**: http://localhost:7000/swagger-ui.html
- **OpenAPI JSON**: http://localhost:7000/v3/api-docs

## Authentication with Swagger UI

The API uses OAuth2 authentication via Keycloak. To authenticate and test endpoints in Swagger UI:

1. **Setup Keycloak** (if not already done):
   - Start services: `docker compose up -d`
   - Access Keycloak: http://localhost:9000
   - Login with username `superadmin` and password `admin123`
   - Select `wms` realm
   - Ensure `wms-client` is configured with:
     - Client authentication: **enabled**
     - Authorization: **enabled**
     - Direct access grant: **enabled**

2. **Authenticate in Swagger UI**:
   - Click the **"Authorize"** button at the top of the Swagger UI page
   - In the OAuth2 dialog, enter:
     - **client_id**: `wms-client`
     - **client_secret**: Get from Keycloak (`Clients` → `wms-client` → `Credentials` tab)
     - **username**: `user1`
     - **password**: `user123`
   - Click **"Authorize"**
   - Close the dialog

3. **Test API Endpoints**:
   - Browse available endpoints by category
   - Click "Try it out" on any endpoint
   - Fill in required parameters
   - Click "Execute" to make the request
   - View the response below

## Features

- **Interactive Documentation**: Test API endpoints directly from the browser
- **OAuth2 Integration**: Seamless authentication with Keycloak
- **Request/Response Examples**: See sample payloads for all endpoints
- **Schema Definitions**: View data models and their properties
- **Organized by Tags**: Endpoints grouped by functional area (e.g., Warehouse, Inventory, etc.)

## Configuration

Swagger is configured in:
- `src/main/java/id/putra/wms/config/openapi/OpenAPIConfig.java` - OpenAPI configuration
- `src/main/resources/application.yaml` - Swagger UI settings
- `src/main/java/id/putra/wms/config/auth/SecurityConfig.java` - Security exceptions for Swagger endpoints

## Adding Documentation to New Controllers

To document a new controller, add these annotations:

```java
@RestController
@RequestMapping("/api/v1/your-path")
@Tag(name = "Your API", description = "Description of your API")
@SecurityRequirement(name = "oauth2")
public class YourController {
    
    @GetMapping
    @Operation(summary = "Short description", description = "Detailed description")
    public ResponseEntity<YourDto> yourMethod(
            @Parameter(description = "Parameter description") @RequestParam String param) {
        // method implementation
    }
}
```

## Dependencies

The Swagger integration uses:
- **springdoc-openapi-starter-webmvc-ui** version 2.8.4
- Automatically generates OpenAPI 3.0 specification
- Provides Swagger UI interface

## Troubleshooting

If Swagger UI is not accessible:
1. Ensure the application is running on port 7000
2. Check that `/swagger-ui/**` and `/v3/api-docs/**` are permitted in SecurityConfig
3. Verify the `springdoc` configuration in application.yaml
4. Check application logs for any startup errors related to SpringDoc

For OAuth2 authentication issues:
1. Verify Keycloak is running on port 9000
2. Ensure the `wms-client` is properly configured in Keycloak
3. Check that the user credentials are correct
4. Verify the JWT issuer URI in application.yaml matches your Keycloak realm

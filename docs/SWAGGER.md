# Swagger API Documentation

This project includes Swagger/OpenAPI documentation for all REST API endpoints.

## Accessing Swagger UI

Once the application is running, you can access the interactive API documentation at:

- **Swagger UI**: [http://localhost:7000/swagger-ui.html](http://localhost:7000/swagger-ui.html)
- **OpenAPI JSON**: [http://localhost:7000/v3/api-docs](http://localhost:7000/v3/api-docs)

## Authentication with Swagger UI

The API uses OAuth2 authentication via Auth0/Okta. To authenticate and test endpoints in Swagger UI:

1. **Auth0 Setup**:
   - Ensure you have an Auth0 account and have created a **Regular Web Application** or **Native App** (for PKCE) or an **API Resource**.
   - Your `application.yaml` should have the correct `okta.oauth2.issuer` and `okta.oauth2.audience`.
2. **Authenticate in Swagger UI**:
   - Click the **"Authorize"** button at the top of the Swagger UI page.
   - In the OAuth2 dialog, select the required scopes (if any).
   - The `client_id` should match your Auth0 Application Client ID.
   - Swagger is configured to use **Authorization Code Grant with PKCE**, which is the recommended flow for Auth0.
   - Click **"Authorize"**. You will be redirected to the Auth0 login page.
   - After successful login, you will be redirected back to Swagger UI.
   - Close the dialog.
3. **Test API Endpoints**:
   - Browse available endpoints by category.
   - Click "Try it out" on any endpoint.
   - Fill in required parameters.
   - Click "Execute" to make the request.
   - View the response below.

## Features

- **Interactive Documentation**: Test API endpoints directly from the browser.
- **OAuth2 Integration**: Seamless authentication with Auth0 using Okta Spring Boot Starter.
- **Request/Response Examples**: See sample payloads for all endpoints.
- **Schema Definitions**: View data models and their properties.
- **Organized by Tags**: Endpoints grouped by functional area (e.g., Warehouse, Inventory, etc.).

## Configuration

Swagger is configured in:

- `src/main/java/id/putra/wms/config/openapi/OpenAPIConfig.java` - OpenAPI configuration.
- `src/main/resources/application.yaml` - Swagger UI settings.
- `src/main/java/id/putra/wms/config/auth/SecurityConfig.java` - Security exceptions for Swagger endpoints.

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

- **springdoc-openapi-starter-webmvc-ui** version 2.8.4.
- **okta-spring-boot-starter** version 3.0.7 for OAuth2 integration.
- Automatically generates OpenAPI 3.0 specification.
- Provides Swagger UI interface.

## Troubleshooting

If Swagger UI is not accessible:

1. Ensure the application is running on port 7000.
2. Check that `/swagger-ui/**` and `/v3/api-docs/**` are permitted in `SecurityConfig`.
3. Verify the `springdoc` configuration in `application.yaml`.
4. Check application logs for any startup errors related to SpringDoc.

For OAuth2 authentication issues:

1. Verify your Auth0 Issuer and Audience in `application.yaml`.
2. Ensure that the Allowed Callback URLs in your Auth0 Application settings includes `http://localhost:7000/swagger-ui/oauth2-redirect.html`.
3. Check that the client_id used in Swagger matches your Auth0 client_id.
4. Verify that your Auth0 API (Audience) permits authorization code flows.

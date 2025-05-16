# Warehouse Management System

## How to 

You can use predefined Couchbase DB within Docker or you can configure yourself 

- Couchbase
  - Create your Couchbase cluster if you don't have one, e.g `warehouse_cluster`
  - Create your Couchbase bucket if you don't have one, e.g `warehouse`
  - Setting your DATABASE configuration, change this configuration
    - `spring.couchbase.connection-string=` - can be `couchbase://127.0.0.1` or anything
    - `spring.couchbase.username=` - couchbase user administrator
    - `spring.couchbase.password=` - couchbase administrator password
    - `spring.data.couchbase.bucket-name=` = couchbase bucket name
- Java 17+
- Maven or just use project embedded maven
- Docker with Compose support

Then run `./mvnw spring-boot:run`
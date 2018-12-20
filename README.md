# RDAddrBookPG

RDAddrBookPG is a simple Address Book application with PostgreSQL Database.

  - Spring Boot application
  - JPA
  - REST
  - Spring Boot Actuator

# Updates!

  - None

### Setup

To use this application you need the following:

    - JDK 1.8 latest release
    - Maven
    - PostgreSQL Database
    - Network connectivity to PostgreSQL Database

## Build
To generate runtime artifacts, do this:
```
$ mvn package -DskipTests
```

### Run
Run RDAddrBookPG application with parameters as given below:
*Note: Parameters with default values are provided in few cases. In few other cases, you are required to provide valid values.*
```
$ export APP_PORT=9080
$ export PG_HOST_IP=<IP ADDRESS of PostgreSQL VM/Machine>
$ export PG_HOST=<Hostname of PostgreSQL VM/Machine>
$ export PG_PORT=5432
$ export APP_DB=rdaddrbookdb
$ export PG_USER=postgres
$ export PG_PASSWORD=postgres
$ ./start_app.sh
```

## Deployment with AWS ECS
To deploy this application as a Container service on AWS ECS, use the taskdef.json file.

    - Pre-requisites:
      - Create ECR repositories and upload docker images as needed
      - Create GitHub repository and upload application code

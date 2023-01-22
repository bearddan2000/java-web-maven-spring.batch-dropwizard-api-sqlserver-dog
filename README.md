# java-web-maven-spring.batch-dropwizard-api-sqlserver-dog

## Description
A POC for dropwizard api using hibernate to connect to sqlserver.

## Tech stack
- java
- maven
  - spring batch
  - dropwizard
  - hibernate
  - sqlserver drivers

## Docker stack
- maven:3-openjdk-17
- mcr.microsoft.com/mssql/server:2022-latest

## To run
`sudo ./install.sh -u`
[Endpoint](http://localhost/dogs)

## To stop (optional)
`sudo ./install.sh -d`

## For help
`sudo ./install.sh -h`

## Credit
[Offical tutorial](rd.io/en/latest/manual/hibernate.DropWizardHibernateExample)

# quarkus-funqy-azure

Examples on how Quarkus Funqy can be used to deploy the exact same function to various FaaS providers (AWS Lambda, Azure Functions, Google Cloud Functions, Knative Funq)

This project covers deploying to Azure Functions.

You can run this code locally with `quarkus run`.  Then you can send the following curl request:

```bash
curl -X POST -d "{\"planet\":\"Risa\"}" -H 'Content-Type: application/json' http://localhost:8081/api/landingrequest
```

To deploy the functions to Azure Cloud, make sure you have the Azure CLI and Azure Functions Core Tools, and are logged in to your Azure account. You should probably also make the app name unique in the application.properties. Then you can deploy with the Quarkus CLI.

```bash
quarkus deploy
```

or

```bash
mvn quarkus:deploy
```

Then you can test the function by sending the above request again (replacing the localhost:8081 bit with the actual URI of course). eg.

```bash
curl -X POST -d "{\"planet\":\"Risa\"}" -H 'Content-Type: application/json' https://azure-funqy-http.azurewebsites.net/api/landingrequest
```

To delete the function you will need to call the Azure CLI directly (make sure to use the app name set in your app properties)

```bash
az functionapp delete --name azure-funqy-http --resource-group quarkus
```

---

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-funqy-http-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.



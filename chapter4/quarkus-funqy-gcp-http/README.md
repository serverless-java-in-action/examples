# quarkus-funqy-gcp-http

Examples on how Quarkus Funqy can be used to deploy the exact same function to various FaaS providers (AWS Lambda, Azure Functions, Google Cloud Functions, Knative Funq)

This project covers deploying Google Cloud Functions for the http protocol

You can use `quarkus dev`` to test the Funqy functions, but to a more realistic Google Cloud Function test you will need to get gcp's java-function invoker. You can download it via Maven using the following command:

```bash
mvn dependency:copy \
    -Dartifact='com.google.cloud.functions.invoker:java-function-invoker:1.3.0' \
    -DoutputDirectory=.
```

Run the function locally (make sure to build your project first eg. `mvn package`)

```bash
    java -jar java-function-invoker-1.3.0.jar \
         --classpath target/quarkus-funqy-gcp-http-1.0.0-SNAPSHOT-runner.jar \
         --target io.quarkus.gcp.functions.http.QuarkusHttpFunction
```

```bash
curl -X POST -d "{\"planet\":\"Venus\"}" -H 'Content-Type: application/json' http://localhost:8080/api/landingRequest
```

To deploy to Google Cloud functions, make sure you have the gcloud CLI and are logged in to your gcp account. Then run the following command (customize to your needs):

```bash
 gcloud functions deploy quarkus-funqy \
    --gen2 \
    --entry-point=io.quarkus.gcp.functions.http.QuarkusHttpFunction \
    --runtime=java21 \
    --trigger-http \
    --allow-unauthenticated \
    --source=target/deployment \
    --region=europe-west1
```

Then you can test the function by sending the above request again (replacing the localhost:8080 bit with the actual URI of course). eg.

```bash
curl -X POST -d "{\"planet\":\"Risa\"}" -H 'Content-Type: application/json' https://quarkus-funqy-jnpad64g4q-ew.a.run.app/api/landingRequest
```

To delete the function:

```bash
gcloud functions delete --gen2 quarkus-funqy --region=europe-west1
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

# quarkus-funqy-gcp

Examples on how Quarkus Funqy can be used to deploy the exact same function to various FaaS providers (AWS Lambda, Azure Functions, Google Cloud Functions, Knative Funq)

This project covers deploying to Google Cloud Functions and using Cloud Events.

To run this code locally, you will need to get gcp's java-function invoker. You can download it via Maven using the following command:

```bash
mvn dependency:copy \
    -Dartifact='com.google.cloud.functions.invoker:java-function-invoker:1.3.0' \
    -DoutputDirectory=.
```

Now you can run the function locally:

```bash
    java -jar java-function-invoker-1.3.0.jar \
         --classpath target/quarkus-funqy-gcp-cloud-events-1.0.0-SNAPSHOT-runner.jar \
         --target io.quarkus.funqy.gcp.functions.FunqyCloudEventsFunction
```

Then you can send the following curl request with a Cloud Event payload:

```bash
curl localhost:8080 \
  -X POST \
  -H "Content-Type: application/json" \
  -H "ce-id: 123451234512345" \
  -H "ce-specversion: 1.0" \
  -H "ce-time: 2020-01-02T12:34:56.789Z" \
  -H "ce-type: org.acme.quarkus-funqy-gcp-cloud-events" \
  -H "ce-source: space" \
  -H "ce-subject: Landing Request" \
  -d '{
        "planet": "Risa"
      }'
```

Verify in your logs that you see the message "Approved for landing on planet Risa"

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

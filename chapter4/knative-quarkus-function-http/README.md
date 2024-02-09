# Function project

This example project contains a single function: `functions.Function.function()`,
the function accepts a request of type LandingDetails and returns a landing approval for a given planet.

## Local execution
Make sure that `Java 17+ SDK` is installed.

To start server locally run `./mvnw quarkus:dev`.
The command starts and http server and automatically watches for changes of source code.
If source code changes the change will be propagated to running server. It also opens debugging port `5005`
so debugger can be attached if needed.

To run test locally run `./mvnw test`.

You can send a request with curl:

```shell script
curl localhost:8080 \
  -X POST \
  -H "Content-Type: application/json" \
  -d '{
        "planet": "Risa", "weight": 500
      }'
```

## The `func` CLI

It's recommended to set `FUNC_REGISTRY` environment variable.
```shell script
# replace ~/.bashrc by your shell rc file
# replace docker.io/johndoe with your registry
export FUNC_REGISTRY=quay.io/kevindubois
echo "export FUNC_REGISTRY=quay.io/kevindubois" >> ~/.bashrc 
```

### Building

This command builds OCI image for the function.

```shell script
func build
```

By default, JVM build is used.
To enable native build set following environment variables to `func.yaml`:
```yaml
buildEnvs:
- name: BP_NATIVE_IMAGE
  value: "true"

```

### Running

This command runs the func locally in a container
using the image created above.
```shell script
func run
```

### Deploying

This commands will build and deploy the function into cluster.

```shell script
func deploy # also triggers build
```

## Function invocation

Do not forget to set `URL` variable to the route of your function.

You get the route by following command.
```shell script
func info
```

### cURL

```shell script
URL=http://localhost:8080/
curl -v ${URL} \
  -X POST
  -H "Content-Type:application/json" \
  -d '{
        "planet": "Risa", "weight": 500
      }'
# OR
curl -v ${URL}?planet=Risa&weight=500"
```

### HTTPie

```shell script
URL=http://localhost:8080/
http -v ${URL} \
  message=$(whoami)
# OR
URL="http://localhost:8080/?message=$(whoami)"
http -v ${URL}
```

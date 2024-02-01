# Temporal Spring Boot Hello
The purpose of this example is to demonstrate how to run a simple workflow using the 
[Temporal Spring Boot Autoconfigure](https://github.com/temporalio/sdk-java/tree/master/temporal-spring-boot-autoconfigure-alpha)
using a Spring Boot Console application. This is a simple way to get started. One drawback is that you cannot 
expose [SDK Metrics](https://docs.temporal.io/dev-guide/java/observability), which are critical for running in production.  

If you want an example that exposes SDK Metrics, you can find one [here](https://github.com/temporalio/samples-java/tree/main/springboot) and another one [here](https://github.com/rross/temporal-cloud-run/tree/main/app).  

This example also demonstrates how to run locally, running within a Docker container, and in [Temporal Cloud](https://temporal.io/cloud)

Within the application, there is another Spring Boot Console application that can start a new workflow. 
   
## Running Locally
Start Temporal Server locally
```shell
temporal server start-dev
```

To run the app locally, without using a container:

```shell
./gradlew bootRun
```

To initiate a workflow using the console application:

```shell
./gradlew startWorkflow
```

To initiate the workflow using a local development server from the command line:

```shell
temporal workflow start \
  --workflow-id Test1 \
  --type HelloWorkflow \
  --task-queue HelloTemporalWorld \
  --input '{ "name": "First LastName"}'
```
## Run the Worker using Docker

Build the docker container by running the following command: 

```shell
./buildcontainer.sh 
```

By default, this container is expecting Temporal Server to be running locally.

## Running Worker in Docker Using Temporal Cloud

Set the following environment variables: 
```shell
export TEMPORAL_NAMESPACE="<namespace>.<accountId>>"
export TEMPORAL_ENDPOINT=<namespace>.<accountId>.tmprl.cloud:7233
export TEMPORAL_CLIENT_KEY="/path/to/your/key"
export TEMPORAL_CLIENT_CERT="/path/to/your/cert.pem"
# optional
# export TEMPORAL_INSECURE_TRUST_MANAGER="true"
```
Then start the worker by running the following command:

```shell
./runontc.sh
```

To initiate a workflow using the console application:

```shell
./startworkflowontc.sh 
```

To initiate the workflow using Temporal Cloud from the command line, set the following
environment variables. These need to match the same values that were set above:

```shell
temporal env set dev.namespace $TEMPORAL_NAMESPACE
temporal env set dev.address $TEMPORAL_ENDPOINT
temporal env set dev.tls-cert-path $TEMPORAL_CLIENT_CERT
temporal env set dev.tls-key-path $TEMPORAL_CLIENT_KEY 
```

Then reference the name of the environment in your temporal command:

```shell
temporal workflow start \
  --workflow-id Test1 \
  --type HelloWorkflow \
  --task-queue HelloTemporalWorld \
  --input '{ "name": "First LastName"}' \
  --env dev
```







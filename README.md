# Connecting via VisualVM to Quarkus Native Image

This project tests how to connect to a [Quarkus native image](https://quarkus.io/)
using [VisualVM](https://visualvm.github.io/) (i.e. via JMX).

Compile native image using:

```shell script
./mvnw verify -Dnative
```

Run the generated native executable via:

```shell script
./target/quarkus-native-startup-*-runner
```

The executable should now show up in VisualVM as `io.quarkus.runner.GeneratedMain`.

The only change needed to include JMX support is adding the following to `application.properties`:

`quarkus.native.additional-build-args=--enable-monitoring=all`

A request can be sent via:

```shell script
curl -X POST --location "http://localhost:8080/are-you-ready" \
    -H "Content-Type: application/json" \
    -d '{
          "activity": "party"
        }'
```

If successful, it should print: *Let's get started with party!*

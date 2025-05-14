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

`quarkus.native.monitoring=all`

This allows local connections via *jvmstat*. This is enough to monitor e.g. garbage collection runs.

It is also possible to connect via JMX. In that case, the native executable must be started via:

```shell script
./target/quarkus-native-startup-*-runner \
  -Dcom.sun.management.jmxremote \
  -Dcom.sun.management.jmxremote.port=1099 \
  -Dcom.sun.management.jmxremote.rmi.port=1099 \
  -Dcom.sun.management.jmxremote.ssl=false \
  -Dcom.sun.management.jmxremote.authenticate=false \
  -Djava.rmi.server.hostname=127.0.0.1
```

However, this is not useful as of now as VisualVM doesn't fully support native executables yet.

A REST request can be sent using *curl*:

```shell script
curl -X POST --location "http://localhost:8080/are-you-ready" \
    -H "Content-Type: application/json" \
    -d '{
          "activity": "party"
        }'
```

If successful, it should print: *Let's get started with party!*

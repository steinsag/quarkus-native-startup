# Connecting via VisualVM to Quarkus Native Image

This project tests how to connect to a [Quarkus native image](https://quarkus.io/)
using [VisualVM](https://visualvm.github.io/) (i.e. via JMX).

Compile native image using:

```shell
./mvnw verify -Dnative
```

Run the generated native executable via:

```shell
./target/quarkus-native-startup-*-runner
```

The executable should now show up in VisualVM as `io.quarkus.runner.GeneratedMain`.

The only change needed to include JMX support is adding the following to `application.properties`:

`quarkus.native.monitoring=all`

This allows local connections via *jvmstat*. This is enough to monitor e.g. garbage collection runs.

It is also possible to connect via JMX. In that case, the native executable must be started via:

```shell
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

```shell
curl -X POST --location "http://localhost:8080/are-you-ready" \
    -H "Content-Type: application/json" \
    -d '{
          "activity": "partying"
        }'
```

If successful, it should print: *Let's get started with partying!*

To put some heavy load on the server, use a benchmark tool such as [wrk](https://github.com/wg/wrk). A *wrk.lua* script
is provided and can be used as follows:

```shell
wrk -t26 -c600 -d25s http://localhost:8080/are-you-ready -s wrk.lua
```

The native executable using all available system memory. Memory can be limited via `-Xmx` JVM parameter:

```shell
./target/quarkus-native-startup-*-runner -Xmx256M
```

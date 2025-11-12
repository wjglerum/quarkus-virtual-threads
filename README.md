# Concurrency Crossroads: Reactive Programming vs Virtual Threads

A Quarkus demo that shows different concurrency styles for a simple REST API that pours beverages and persists them:

- Blocking (Platform Threads + Hibernate ORM)
- Reactive (Mutiny + Hibernate Reactive)
- Virtual Threads (Virtual Threads + Hibernate ORM)
- Structured Concurrency (Structured Concurrency + Hibernate ORM)

The project exposes endpoints for each style and stores results using repositories. It is intended for learning, not
production.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Requirements

- JDK 26 early access
- Docker or Podman running

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## REST endpoints

See `examples.http` for ready-made requests (usable with IDE HTTP client tools).

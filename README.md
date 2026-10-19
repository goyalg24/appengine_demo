# appengine_demo

A minimal Java 17 "hello" web app built with Maven for Google App Engine. It contains a Jakarta Servlet (`HelloController`, mapped to `/hello` in `web.xml`, returns `Hello from HelloController!`) and a standalone embedded-Jetty launcher (`Main`) that serves `/hello?name=...` returning JSON such as `{"message": "Hello, World!"}` on `$PORT` (default 8080).

## Project structure

```
.
├── pom.xml                     # Maven build, packaging: war
├── app.yaml                    # App Engine config (runtime java17)
├── .gcloudignore
└── src/main
    ├── java/com/example
    │   ├── HelloController.java   # Servlet for /hello
    │   └── Main.java              # Embedded Jetty server with HelloServlet
    └── webapp/WEB-INF
        ├── appengine-web.xml
        └── web.xml
```

## Setup

Requirements: JDK 17, Maven, and the `gcloud` CLI for deployment.

## Usage

Build the WAR (output: `target/appengine-demo-1.0-SNAPSHOT.war`):

```bash
mvn clean package
```

Deploy to App Engine:

```bash
gcloud app deploy app.yaml
```

## Notes

- `app.yaml` sets the entrypoint to `target/appengine-demo-1.0-SNAPSHOT-jar-with-dependencies.jar`, but `pom.xml` does not configure the assembly plugin that would produce that JAR (it builds a WAR). Add `maven-assembly-plugin` with `Main` as the main class, or change the entrypoint, before deploying.
- Build artifacts (`target/`, `*.war`) are not committed; build them from source.

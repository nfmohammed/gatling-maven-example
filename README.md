gatling-maven-plugin-demo
=========================

Prerequisites:

- Java8 or Java11
- Scala 2.12. Note: Gatling not compatible with 2.11 or 2.13
- Explicitly Set JAVA_HOME if needed

Commands:

    $ mvn gatling:test  (runs all simulations)
    $ mvn gatling:test -Dgatling.simulationClass=computerdatabase.Example1BasicSimulation

Notes:

- By default, Gatling runs single simulation. To enable multiple simulation run, use plugin configuration `runMultipleSimulations`, see `pom.xml`   

References:

- Repo: https://github.com/gatling/gatling-maven-plugin-demo

- Performance testing site: http://computer-database.gatling.io/computers

- Installation: https://gatling.io/docs/current/installation#installation

- QuickStart: https://gatling.io/docs/current/quickstart/

- Advanced: https://gatling.io/docs/current/advanced_tutorial#advanced-tutorial

- Maven Setup: https://gatling.io/docs/2.2/extensions/maven_plugin/

package computerdatabase

import io.gatling.core.Predef.{Simulation, rampUsers, scenario}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class Example3FeederFile extends Simulation{
  val httpProtocol = http
    .baseUrl("http://computer-database.gatling.io") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  /**
   * Using random data in our searches
   */
  val users = scenario("Users").exec(SearchWithFeeder.search)

  setUp(
    /**
     * Creating a setup where 10 users are doing random searches
     */
    users.inject(rampUsers(10) during (10 seconds))
  ).protocols(httpProtocol)

}

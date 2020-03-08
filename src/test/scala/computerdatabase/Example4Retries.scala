package computerdatabase

import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class Example4Retries extends Simulation {

  val httpProtocol = http
    .baseUrl("http://computer-database.gatling.io") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptCharsetHeader("UTF-8")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")


  /**
   * Edit2.edit is configured to fail randomly.
   * The random failures can be resolved with retries
   */
  val admins = scenario("Admins Retry")
      .tryMax(3) {
        Edit2.edit
      }

  setUp(
    admins.inject(rampUsers(2) during (10 seconds) )
  ).protocols(httpProtocol)

}

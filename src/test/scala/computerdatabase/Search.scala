package computerdatabase
import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
 * Basic Search with hard coded data
 */
object Search {

  val search = exec(http("Home") // let's give proper names, as they are displayed in the reports
    .get("/"))
    .pause(7)
    .exec(http("Search")
      .get("/computers?f=macbook"))
    .pause(2)
    .exec(http("Select")
      .get("/computers/6"))
    .pause(3)
}


/**
 * Search with data feeder
 */
object SearchWithFeeder {

  val feeder = csv("data/search.csv").random

  val search = exec(http("Home") // let's give proper names, as they are displayed in the reports
    .get("/"))
    .pause(1)
    .feed(feeder)
    .exec(http("Search")
      .get("/computers?f=${searchCriterion}")
      .check(css("a:contains('${searchComputerName}')", "href").saveAs("computerURL"))
    )
    .pause(1)
    .exec(http("Select")
      .get("${computerURL}"))
    .pause(1)
}

/**
 * In this scenario, we are browsing thru multiple pages.
 * Browse can very well be defined in it's own file, but we are doing it here.
 */
object Browse {

  def gotoPage(page: Int) = exec(http("Page " + page)
    .get("/computers?p=" + page))
    .pause(1)

  val browse = exec(gotoPage(0), gotoPage(1), gotoPage(2), gotoPage(3), gotoPage(4))
}

/**
 * This does same thing as Browse but we have improved the code
 */
object Browse2 {

  val browse = repeat(5, "n") { // 1
    exec(http("Page ${n}")
      .get("/computers?p=${n}")) // 2
      .pause(1)
  }
}

/**
 * Edit can be declared in it's own class but we are doing it here for simplicity
 */

object Edit {

  val edit = exec(http("Form")
    .get("/computers/new"))
    .pause(1)
    .exec(
      http("Post")
        .post("/computers")
        .formParam("name", "abc mouse club house")
        .formParam("introduced", "2020-03-07")
        .formParam("discontinued", "2024-03-09")
        .formParam("company", "1")
        .check(status.is(session => 200))
    )

}


import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import akka.util.duration._
import bootstrap._
import assertions._

class CocktailFactorySimulation extends Simulation {
  val port = System.getProperty("server.port")
  val server = System.getProperty("server.host")

  val httpConf = httpConfig
    .baseURL("http://"+server+":" + port + "/cocktailfactory")
    .acceptHeader("application/json, text/plain, */*")
    .acceptCharsetHeader("ISO-8859-1,utf-8;q=0.7,*;q=0.3")
    .acceptEncodingHeader("gzip,deflate,sdch")
    .acceptLanguageHeader("en-US,en;q=0.8,fr;q=0.6")
    .connection("keep-alive")
    .userAgentHeader("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
    .warmUp("http://"+server+":"+port+"/cocktailfactory/index.html")

  val headers_13 = Map(
    "Accept" -> """text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"""
  )

  val headers_24 = Map(
    "X-Requested-With" -> """XMLHttpRequest"""
  )

  val scn = scenario("Search cocktail")
    .during(15) {
      exec(http("home")
        .get("/")
        .headers(headers_13)
        .check(status.is(200))
      )
        .exec(http("all")
        .get("/services/cocktail/list")
        .headers(headers_24)
        .check(status.is(200))
      )
        .exec(http("r")
        .get("/services/cocktail/filter/r")
        .headers(headers_24)
        .check(status.is(200))
      )
        .exec(http("ru")
        .get("/services/cocktail/filter/ru")
        .headers(headers_24)
        .check(status.is(200))
      )
        .exec(http("rum")
        .get("/services/cocktail/filter/rum")
        .headers(headers_24)
        .check(status.is(200))
      )
        .exec(http("ru")
        .get("/services/cocktail/filter/ru")
        .headers(headers_24)
        .check(status.is(200))
      )
        .exec(http("r")
        .get("/services/cocktail/filter/r")
        .headers(headers_24)
        .check(status.is(200))
      )
        .exec(http("all")
        .get("/services/cocktail/list")
        .headers(headers_24)
        .check(status.is(200))
      )
        .exec(http("v")
        .get("/services/cocktail/filter/v")
        .headers(headers_24)
        .check(status.is(200))
      )
        .exec(http("vo")
        .get("/services/cocktail/filter/vo")
        .headers(headers_24)
        .check(status.is(200))
      )
        .exec(http("vod")
        .get("/services/cocktail/filter/vod")
        .headers(headers_24)
        .check(status.is(200))
      )
        .exec(http("vodk")
        .get("/services/cocktail/filter/vodk")
        .headers(headers_24)
        .check(status.is(200))
      )
        .exec(http("vodka")
        .get("/services/cocktail/filter/vodka")
        .headers(headers_24)
        .check(status.is(200))
      )
    }

  setUp(scn.users(30).ramp(5 milliseconds).protocolConfig(httpConf))

  assertThat(global.responseTime.mean.lessThan(300))
}
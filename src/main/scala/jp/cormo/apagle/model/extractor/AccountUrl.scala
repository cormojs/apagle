package jp.cormo.apagle.model.extractor

import java.net.{URI, URISyntaxException}

import scala.util.parsing.combinator.RegexParsers

object AccountAtUrl {
  def apply(name: String, host: String) = s"https://$host/@$name"

  def unapply(arg: String): Option[(String, String)] = {
    try {
      val url = new URI(arg)
      url.getScheme match {
        case "https" => url.getPath.charAt(0) match {
          case '@' => Some((url.getPath.substring(1), url.getHost))
          case _ => None
        }
        case _ => None
      }
    } catch {
      case e: URISyntaxException => None
    }
  }
}

object Acct {
  def apply(name: String, host: String) = s"acct:$name@$host"

  def unapply(arg: String): Option[(String, String)] = {
    object AcctParser extends RegexParsers {
      def apply(input: String): Option[(String, String)] = {
        val name = """[a-zA-z]+[0-9a-zA-Z]*""".r
        val domain = """([0-9a-zA-Z][a-zA-Z0-9\-]{1,61}[a-zA-Z0-9]\.)+[a-zA-Z]+""".r
        val num = """[1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]"""
        val ipWithPort = s"""($num)\\.($num)\\.($num)\\.($num):\\d+""".r

        val pattern = "acct:" ~ name ~ "@" ~ (domain | ipWithPort) ^^ { case _ ~ n ~ _ ~ h => (n, h) }

        parseAll(pattern, input) match {
          case Success((name_, host_), _) => Some((name_, host_))
          case n =>
            println(n)
            None
        }
      }
    }

    AcctParser.apply(arg)
  }
}
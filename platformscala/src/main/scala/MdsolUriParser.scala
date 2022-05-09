import scala.util.matching.Regex

trait MdsolUriParser {
  val mdsolUriRegex: Regex =
    raw"""com:mdsol:(\w+):([\da-fA-F]{8}-[\da-fA-F]{4}-[\da-fA-F]{4}-[\da-fA-F]{4}-[\da-fA-F]{12})""".r


}
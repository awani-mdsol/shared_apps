trait MdsolParsingException extends Exception {
  val message: String
}

trait MdsolUriParsingException extends MdsolParsingException
trait UuidParsingException extends MdsolParsingException

case class InvalidUuidException(override val message: String) extends UuidParsingException
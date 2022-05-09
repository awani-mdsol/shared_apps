import java.util.UUID
import scala.util.Try

object UuidParser {

  private def stringToUuid(uuidStr: String): Either[UuidParsingException, UUID] = {
    if(uuidStr.length == 36)
      Try(UUID.fromString(uuidStr)).toEither.left.map(t => InvalidUuidException(t.getMessage))
    else Left(InvalidUuidException(s"$uuidStr is not a valid UUID."))
  }

  def unsafeStringToUuid(uuidStr: String): UUID = {
    stringToUuid(uuidStr) match {
      case Right(uuid) => uuid
      case Left(error) => {
        println(error.message)
        throw error
      }
    }
  }
}

object uuitdte extends App {
  //val s = "strehfbdghbhb4444hbhbhbhbhbhbhbhbhbh"
  //val valid = "11ac8393-ae15-44da-aeaf-1c14bb4f4bfb"
  //val s = "11ac8393$ae15-44da-aeaf-1c14bb4f4bfb"
  val s = "str"
  println(s.length)

  println {
    UuidParser.unsafeStringToUuid(s)
  }
}
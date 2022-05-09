import java.util.UUID

abstract class MdsolType(val value: String)

object MdsolType {
  case object APP extends MdsolType("apps")
  case object CDS extends MdsolType("client_division_schemes")
  case object SE extends MdsolType("study_environments")
}

sealed trait PrefixedUri {
  def prefixedUriStr: String
}

object MdsolUri {
  private val uriSeparator: String = ":"
  private val mdsolPrefix: String = "com" + uriSeparator + "mdsol"
}

trait MdsolUri extends PrefixedUri {
  import MdsolUri._
  val uuid: UUID //abstract
  def mdsolType: MdsolType //abstract
  lazy val prefix: String = s"$mdsolPrefix$uriSeparator${mdsolType.value}"

  lazy val prefixedValue: String = s"$prefix$uriSeparator$uuid"

  override def prefixedUriStr: String = prefixedValue

  @deprecated("Use prefixedValue", "05/08/2019")
  lazy val value: String = prefixedValue

  override def toString: String = prefixedValue
  lazy val uuidValue: String = uuid.toString
}

final case class StudyEnvironmentUri(override val uuid: UUID) extends MdsolUri {
  override val mdsolType: MdsolType = MdsolType.SE
}
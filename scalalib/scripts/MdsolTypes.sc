abstract class MdsolType(val value: String)

object MdsolType {
  case object APP extends MdsolType("apps")

  case object CDS extends MdsolType("client_division_schemes")

  case object SE extends MdsolType("study_environments")
}

import MdsolType._

val x: MdsolType.SE.type = SE
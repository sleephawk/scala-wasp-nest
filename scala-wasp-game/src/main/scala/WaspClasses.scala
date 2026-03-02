case class Queen(hp: Int = 80) extends Wasp {
  val name = "Queen"
  val damage = 7
}

case class Worker(hp: Int = 68) extends Wasp {
  val name = "Worker"
  val damage = 10
}

case class Drone(hp: Int = 60) extends Wasp {
  val name = "Drone"
  val damage = 12
}


 class Nest() {
  val nestInstance: List[Wasp] = List(Queen()) ++ List.fill(5)(Worker()) ++ List.fill(8)(Drone())
}

trait Wasp {
  def name: String
  def hp: Int
  def damage: Int
}
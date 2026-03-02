import scala.io.StdIn.readLine

@main def main(): Unit =
  val startingNest = new Nest
  val nest = startingNest.nestInstance
  runGame(0, nest)

  def runGame(acc: Int, nestInPlay: List[Wasp], replay: Boolean = false): String | Unit = {
      if (acc <= 0 && !replay) {
      printWaspNest() 
      println("Welcome to the hive! Your name is?")
      val firstName = readLine()
      printWasp()
      println("Welcome " + firstName)
      println("You stand before a wasp's nest, and it's up to you to kill every last wasp.")
      println("")
      println("Problem is you're using a peashooter, but don't worry! You have the needlepoint accuracy of a greek deity.")
      println("")
      println("Knock them out one by one, or take out the queen to destroy the hive in one swoop")}

      if (acc <= 0){
      println("CONTROLS:")
      println("SHOOT: type 'return' or press enter")}

        val command = readLine()
        if (command == "return" || command.isEmpty()) {

        val i = scala.util.Random.nextInt(nestInPlay.length)
        val hitWasp = nestInPlay(i)
        if (hitWasp.isInstanceOf[Queen]) {
        printBalloon()
        println("You hit the Queen! Congratulations")
        println("It took you " + acc + " shots to do it, but you got it done!")
        println("I hear a-buzzin across the road from here, seems your work isn't done yet...")
        println("Play again?")
        println("Y|N")
        val continue: String = readLine()
        if (continue == "Y" || continue == "") {
          val cn = new Nest
          val continueNest = cn.nestInstance
          runGame(0, continueNest, true)
        } else {
          println("Great work today, maybe next time")
        }
        } else {
          //CORE GAME LOGIC
        val updatedWasp = hitWasp match {
          case w: Worker => w.copy(hp = w.hp - w.damage)
          case d: Drone  => d.copy(hp = d.hp - d.damage)
        }
          val newNest = nest.updated(i, updatedWasp)
          println("You hit " + updatedWasp)
          println("The nest is still alive!")
          println(newNest.foreach(println))
          val n: Int = acc + 1
          println("you've attacked this many times: " + n)

          runGame(n, newNest)
      }} else {
        println("please enter a valid command")
      }
      }




    
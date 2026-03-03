import scala.io.StdIn.readLine

@main def main(): Unit =
  val startingNest = new Nest
  val nest = startingNest.nestInstance
  runGame(0, nest)

  def runGame(acc: Int, nestInPlay: List[Wasp], replay: Boolean = false): String | Unit = {
      if (acc <= 0 && !replay) {
      printWaspNest() 
      println("Welcome to the nest! Your name is?")
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
      println("SHOOT: type 'fire'")}

        val command = readLine()
        if (command == "fire") {

        val i = scala.util.Random.nextInt(nestInPlay.length)
        val hitWasp = nestInPlay(i)
        if (hitWasp.isInstanceOf[Queen]) {
        printBalloon()
        println("You hit the Queen! Congratulations")
        println("It took you " + (acc + 1) + " shots to do it, but you got it done!")
        println("I hear a-buzzin across the road from here, seems your work isn't done yet...")
        println("Play again? Type 'restart' to play or 'exit' to leave")
        println("restart | exit")
        val continue: String = readLine()
        if (continue == "restart") {
          val cn = new Nest
          val continueNest = cn.nestInstance
          runGame(0, continueNest, true)
        } else if (continue == "exit") {
          println("Great work today, maybe next time")
        } else {
          println("We'll take that as a no...")
        }
        } else {
          //CORE GAME LOGIC
        val updatedWasp = hitWasp match {
          case w: Worker => w.copy(hp = w.hp - w.damage)
          case d: Drone  => d.copy(hp = d.hp - d.damage)
        }
          val newNest = nestInPlay.updated(i, updatedWasp)
          printBlast()
          println("You just hit " + updatedWasp + " for " + updatedWasp.damage)
          println("The nest is still alive! Shoot damnit shoot!")
          newNest.foreach(println)
          val cleanedNest = newNest.filterNot(n => n.hp == 0)
          val n: Int = acc + 1
          println("you've attacked this many times: " + n)
          runGame(n, cleanedNest)

      }} else {
        println("please enter a valid command")
        runGame(acc, nestInPlay)
      }
      }




    
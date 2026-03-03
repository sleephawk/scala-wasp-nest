import scala.io.StdIn.readLine

@main def main(): Unit =
  val startingNest = new Nest
  val nest = startingNest.nestInstance
  runGame(0, nest)

  def runGame(acc: Int, nestInPlay: List[Wasp], replay: Boolean = false, name: String = null): String | Unit = {
      var gameName: String = name
      if (acc <= 0 && !replay) {
      printWaspNest() 
      println("Welcome to the nest! Your name is?")
      gameName = readLine()
      
      printWasp()
      println("Welcome " + name)
      println("You stand before a wasp's nest, and it's up to you to kill every last wasp.")
      println("")
      println("Problem is you're using a peashooter, but don't worry! You have the needlepoint accuracy of a greek deity.")
      println("")
      println("Knock them out one by one, or take out the queen to destroy the hive in one swoop")}

      if (acc <= 0){
      println("CONTROLS:")
      println("fire': shoots a wasp")
      println("'auto': autofire through the game; see how many shots it takes")
      println("'quit': end the game....already...")}
        val command = readLine()
        if (command == "fire") {
        //CORE GAME LOGIC P1
        val i = scala.util.Random.nextInt(nestInPlay.length)
        val hitWasp = nestInPlay(i)
          //CORE GAME LOGIC P2
        val updatedWasp = hitWasp match {
          case q: Queen => q.copy(hp = q.hp - q.damage)
          case w: Worker => w.copy(hp = w.hp - w.damage)
          case d: Drone  => d.copy(hp = d.hp - d.damage)
        }
        
        val newNest = nestInPlay.updated(i, updatedWasp)
        printBlast()
        println("You just hit " + updatedWasp + " for " + updatedWasp.damage)
        val cleanedNest = newNest.filterNot(n => n.hp <= 0)
        if (!cleanedNest.exists { case _: Queen => true; case _ => false}) {
          printBalloon()
          println("You killed the Queen! Congratulations")
          println("It took you " + (acc + 1) + " shots to do it, but you got it done!")
          println("I hear a-buzzin across the road from here, seems your work isn't done yet...")
          println("Play again? Type 'restart' to play or 'exit' to leave")
          println("restart | quit")
          val continue: String = readLine()
          if (continue == "restart") {
            val cn = new Nest
            val continueNest = cn.nestInstance
            runGame(0, continueNest, true, gameName)
          } else if (continue == "quit") {
            println("Great work today, maybe next time")
          } else {
            println("We'll take that as a no...")
        }   
        } else if (cleanedNest.length < nestInPlay.length) {
          println("looks like you got one of the f£^&$rs, great job " + name + "!")
          cleanedNest.foreach(println)
          val n: Int = acc + 1
          println("you've used this many bullets: " + n)
          runGame(n, cleanedNest, false, gameName)
        } else {
        println("The nest is still alive! Damnit " + name + " shoot!")
        cleanedNest.foreach(println)
        val n: Int = acc + 1
        println("you've used this many bullets: " + n)
        runGame(n, cleanedNest, false, gameName)

      }} else if (command == "auto"){
          println("")
      } else if (command == "quit"){
          println("Well bye I guess. I guess I'll be terminated by these wasps then will I?")
          println("I know you must be tired of using your gun powers but really? We need you friend.")
          println("Who else is going to.....hang on...I hear buzzing....no.....NOOOOOOOO")
      } else {
        println("please enter a valid command")
        runGame(acc, nestInPlay, false, gameName)
      }
      }




    
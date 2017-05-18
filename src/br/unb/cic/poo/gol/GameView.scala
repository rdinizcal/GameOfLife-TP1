package br.unb.cic.poo.gol

import scala.io.StdIn.{readInt, readLine}

object GameView {
  
	private final val LINE = "+-----+"
	private final val DEAD_CELL = "|     |"
	private final val ALIVE_CELL = "|  o  |"
	
	private final val INVALID_OPTION = 0
	private final val MAKE_CELL_ALIVE = 1
	private final val NEXT_GENERATION = 2
	private final val AUTO = 3
	private final val HALT = 4
	
  private var auto = false;
  /**
   * Define as regras do jogo
   */
	def setup {
	 var option = -1; 
	 if(GameEngine.rules.length == 0){
	    println("Não há regras disponíveis")
	    return
	  }
	  
	  do{
  	  println("Regras disponíveis:")
  	  for(i <- (1 until GameEngine.rules.length+1)) {
  	    println( "["+i+"] " + GameEngine.rules.apply(i-1).toString() )
  	  }
	    print("\nQual regra será utilizada no jogo? ")
	  
	    option = readInt - 1
	  }while(option < 0 || option > GameEngine.rules.length);
	  
	  GameEngine.setRule(option)
	}
  
  /**
	 * Atualiza o componente view (representado pela classe GameBoard),
	 * possivelmente como uma resposta a uma atualizacao do jogo.
	 */
	def update {
		printFirstRow
		printLine
		
		for(i <- (0 until GameEngine.height)) {
		  for(j <- (0 until GameEngine.width)) {
		    print(if (GameEngine.isCellAlive(i, j))  ALIVE_CELL else DEAD_CELL);
		  }
		  println("   " + i)
		  printLine
		}
		if(!auto) printOptions
	}
  
  private def printOptions {
	  
	  var option = 0
	  println("\n\n")
	  
	  do{
	    println("Rule: "+GameEngine.rule)
	    println("Select one of the options: \n \n"); 
			println("[1] Make a cell alive");
			println("[2] Next generation");
			println("[3] Auto");
			println("[4] Halt");
		
			print("\n \n Option: ");
			
			option = parseOption(readLine)
	  }while(option == 0)
	  
	  option match {
      case MAKE_CELL_ALIVE => makeCellAlive
      case NEXT_GENERATION => nextGeneration
      case AUTO => autoPlay
      case HALT => halt
    }
	}
  
  private def makeCellAlive {
	  
	  var i = 0
	  var j = 0
	  
	  do {
      print("\n Inform the row number (0 - " + (GameEngine.height - 1) + "): ")
      i = readInt
      
      print("\n Inform the column number (0 - " + (GameEngine.width - 1) + "): ")
      j = readInt
      
    } while(!GameEngine.validPosition(i,j))
      
    GameController.makeCellAlive(i, j)
	}
	
  private def autoPlay = {
    auto = true;
    try {
      while(auto){
        nextGeneration
        println("Press CTRL-C to stop")
        Thread.sleep(2000)
      }
    } catch {
      case e : InterruptedException => {
        auto = false
      }
    }
  }
  
  private def nextGeneration = GameController.nextGeneration
  private def halt = GameController.halt
	
  
	def parseOption(option: String): Int = option match {
    case "1" => MAKE_CELL_ALIVE
    case "2" => NEXT_GENERATION
    case "3" => AUTO
    case "4" => HALT
    case _ => INVALID_OPTION
  }
	
  
  /* Imprime uma linha usada como separador das linhas do tabuleiro */
	private def printLine() {
	  for(j <- (0 until GameEngine.width)) {
	    print(LINE)
	  }
	  println()
	}
  
  /*
	 * Imprime os identificadores das colunas na primeira linha do tabuleiro
	 */
	private def printFirstRow {
		println("\n \n");
		
		for(j <- (0 until GameEngine.width)) {
		  print("   " + j + "   ")
		}
		println()
	}
  
}
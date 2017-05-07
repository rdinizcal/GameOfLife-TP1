package br.unb.cic.poo.gol.controller

import br.unb.cic.poo.gol.model.Statistics
import br.unb.cic.poo.gol.GameView
import br.unb.cic.poo.gol.model.ConwayEngine

/**
 * Relaciona o componente View com o componente Model. 
 * 
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object GameController {
  
  val engine = new ConwayEngine();
  
  def start {
    GameView.update
  }
  
  def halt() {
    //oops, nao muito legal fazer sysout na classe Controller
    println("\n \n")
    Statistics.display
    System.exit(0)
  }

  def makeCellAlive(i: Int, j: Int) {
    try {
			engine.makeCellAlive(i, j)
			GameView.update
		}
		catch {
		  case ex: IllegalArgumentException => {
		    println(ex.getMessage)
		  }
		}
  }
  
  def nextGeneration {
    engine.nextGeneration
    GameView.update
  }
  
  def getHeight : Int = engine.height;
  
  def getWidth : Int = engine.width;
  
  def isCellAlive (i : Int, j : Int) : Boolean = engine.isCellAlive(i, j);
}
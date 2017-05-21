package br.unb.cic.poo.gol.controller

import br.unb.cic.poo.gol.model.Statistics
import br.unb.cic.poo.gol.GameEngine
import br.unb.cic.poo.gol.view.GameView

/**
 * Relaciona o componente View com o componente Model. 
 */
object GameController {

  def makeCellAlive(i: Int, j: Int) {
    try {
			GameEngine.makeCellAlive(i, j)
			GameView.update
		}
		catch {
		  case ex: IllegalArgumentException => {
		    println(ex.getMessage)
		  }
		}
  }
  
  def nextGeneration {
    GameEngine.nextGeneration
    GameView.update
  }
  
}
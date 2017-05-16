package br.unb.cic.poo.gol.controller

import br.unb.cic.poo.gol.model.Statistics

import br.unb.cic.poo.gol.model.GameEngine
import br.unb.cic.poo.gol.view.GameView
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.badlogic.gdx.backends.lwjgl.LwjglApplication

/**
 * Relaciona o componente View com o componente Model. 
 * 
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object GameController {
  val config = new LwjglApplicationConfiguration()
  val game = new GameEngine
  val view = new GameView(game)
  val app = new LwjglApplication(game, config)
  
  def start {
  config.width = 1920
  config.height = 1080
   // view.setup
    view.update
  }
  
  def halt() {
    //oops, nao muito legal fazer sysout na classe Controller
    println("\n \n")
    Statistics.display
    System.exit(0)
  }

  def makeCellAlive(i: Int, j: Int) {
    try {
			game.makeCellAlive(i, j)
		}
		catch {
		  case ex: IllegalArgumentException => {
		    println(ex.getMessage)
		  }
		}
  }
  
  def makeCellDead(i: Int, j: Int) {
    try {
			game.makeCellDead(i, j)
		}
		catch {
		  case ex: IllegalArgumentException => {
		    println(ex.getMessage)
		  }
		}
  }
  
  def clearBoard{
    for (i <- (0 until game.height)) {
      for (j <- (0 until game.width)) {
        makeCellDead(i, j)
      }
    }
  }
  
  def nextGeneration {
    game.nextGeneration
  }
  
}
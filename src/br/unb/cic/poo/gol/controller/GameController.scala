package br.unb.cic.poo.gol.controller

import br.unb.cic.poo.gol.model.Statistics
import br.unb.cic.poo.gol.GameEngine
import br.unb.cic.poo.gol.view.GameView

/**
 * Relaciona o componente View com o componente Model. 
 */
object GameController {
  
  var currentState = GameEngine.currentState
  var savedStates = GameEngine.savedStates

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
  
  def makeCellDead(i: Int, j: Int) {
    try {
			GameEngine.makeCellDead(i, j)
			GameView.update
		}
		catch {
		  case ex: IllegalArgumentException => {
		    println(ex.getMessage)
		  }
		}
  }
  
  
  def isCellAlive(i: Int, j: Int): Boolean ={
    try {
      GameView.update
			GameEngine.isCellAlive(i, j)
			
		}
		catch {
		  case ex: IllegalArgumentException => {
		    println(ex.getMessage)
		  }
		}
		return GameEngine.isCellAlive(i, j)
  }
  
  def nextGeneration {
    GameEngine.nextGeneration
    GameView.update
  }
  
  def undo() {
    GameEngine.undo
    GameView.update
  }
  
  def redo() {
    GameEngine.redo
    GameView.update
  }
  
  def clear {
    GameEngine.clear
    GameView.update
  }
  
 
  
}
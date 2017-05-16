package br.unb.cic.poo.gol.model

import br.unb.cic.poo.gol.model.Rule

import br.unb.cic.poo.gol.controller.GameController

class HighLife extends Rule {
   
  override def toString() : String = "High Life Rule"
    
  override def shouldKeepAlive(i: Int, j: Int): Boolean = {
    (GameController.game.cells(i)(j).isAlive) &&
      (GameController.game.numberOfNeighborhoodAliveCells(i, j) == 2 || GameController.game.numberOfNeighborhoodAliveCells(i, j) == 3)
  }

  override def shouldRevive(i: Int, j: Int): Boolean = {
    (!GameController.game.cells(i)(j).isAlive) &&
      (GameController.game.numberOfNeighborhoodAliveCells(i, j) == 3 || GameController.game.numberOfNeighborhoodAliveCells(i, j) == 6)
  }
  
}
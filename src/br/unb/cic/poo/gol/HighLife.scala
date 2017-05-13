package br.unb.cic.poo.gol

class HighLife extends Rule {
   
  override def toString() : String = "High Life"
   
  def shouldKeepAlive(i: Int, j: Int): Boolean = {
    (GameEngine.cells(i)(j).isAlive) &&
      (GameEngine.numberOfNeighborhoodAliveCells(i, j) == 2 || GameEngine.numberOfNeighborhoodAliveCells(i, j) == 3)
  }

  def shouldRevive(i: Int, j: Int): Boolean = {
    (!GameEngine.cells(i)(j).isAlive) &&
      (GameEngine.numberOfNeighborhoodAliveCells(i, j) == 3 || GameEngine.numberOfNeighborhoodAliveCells(i, j) == 6)
  }
  
}
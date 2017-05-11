package br.unb.cic.poo.gol.model

class ConwayEngine extends GameEngine {
  
  override def shouldKeepAlive(i: Int, j: Int): Boolean = {
    (cells(i)(j).isAlive) &&
      (numberOfNeighborhoodAliveCells(i, j) == 2 || numberOfNeighborhoodAliveCells(i, j) == 3)
  }
  
  
  override def shouldRevive(i: Int, j: Int): Boolean = {
    (!cells(i)(j).isAlive) && 
      (numberOfNeighborhoodAliveCells(i, j) == 3)
  }
  
   /*
	 * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
	 * de referencia identificada pelos argumentos (i,j).
	 */
  private def numberOfNeighborhoodAliveCells(i: Int, j: Int): Int = {
    var alive = 0
    for(a <- (i - 1 to i + 1)) {
      for(b <- (j - 1 to j + 1)) {
        if (super.validPosition(a, b)  && (!(a==i && b == j)) && cells(a)(b).isAlive) {
					alive += 1
				}
      }
    }
    alive
  }
}
package br.unb.cic.poo.gol

trait Rule {
  
  /**
   * Define se a cell(i,j) deve continuar viva
   */
  def shouldKeepAlive(i: Int, j: Int): Boolean;
  
   /**
   * Define se a cell(i,j) deve reviver
   */
  def shouldRevive(i: Int, j: Int): Boolean;
 
  /**
	 * Verifica se uma posicao (i, j) referencia uma celula valida no tabuleiro.
	 */
  def validPosition(i: Int, j: Int) =
    i >= 0 && i < GameEngine.height && j >= 0 && j < GameEngine.width;
}
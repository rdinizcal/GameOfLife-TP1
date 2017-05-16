package br.unb.cic.poo.gol.model


import br.unb.cic.poo.gol.controller.GameController

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
   * Define o nome da regra
   */
  override def toString() : String;
  
  /**
	 * Verifica se uma posicao (i, j) referencia uma celula valida no tabuleiro.
	 */
  def validPosition(i: Int, j: Int) =
    i >= 0 && i < GameController.game.height && j >= 0 && j < GameController.game.width;
}
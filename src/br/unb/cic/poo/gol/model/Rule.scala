package br.unb.cic.poo.gol.model

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
  
}
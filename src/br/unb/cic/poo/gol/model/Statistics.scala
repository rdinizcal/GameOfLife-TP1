package br.unb.cic.poo.gol.model

/**
 * Classe usada para computar as estatisticas 
 * do GoL.
 * 
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br)
 */
object Statistics {
  
  private var revivedCells = 0
	private var killedCells = 0
  
	
	def getRevivedCells : Int = revivedCells

	def recordRevive = revivedCells += 1
	
	def removeRevive = revivedCells -= 1

  def getKilledCells: Int = killedCells
	
  def recordKill = killedCells += 1 
  
}
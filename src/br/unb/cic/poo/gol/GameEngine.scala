package br.unb.cic.poo.gol

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.MutableList

import com.google.inject.Guice
import br.unb.cic.poo.gol.model.Statistics
import br.unb.cic.poo.gol.model.Rule
import br.unb.cic.poo.gol.model.RuleModule
import br.unb.cic.poo.gol.model.Cell
import br.unb.cic.poo.gol.view.GameView
import scala.swing.event.ButtonClicked
import scala.swing.Button
import scala.swing.AbstractButton
import br.unb.cic.poo.gol.model.Originator
import br.unb.cic.poo.gol.model.CareTaker



object GameEngine {
  
  val injector = Guice.createInjector(new RuleModule)
  val rules = injector.getInstance(classOf[RuleComponent]).getRules().toSeq
  var rule : Rule = rules.apply(0)
    
  val height = Main.height
  val width = Main.width
  
  var autoplay = false

  var cells = Array.ofDim[Cell](height, width)
  
  //variaveis do memento
  val caretaker = new CareTaker
  val originator = new Originator
  var savedStates, currentState: Int = 0
  
  for (i <- (0 until height)) {
    for (j <- (0 until width)) {
      cells(i)(j) = new Cell
    }
  }
  
  def setRule (index : Int) {
    rule = rules.apply(index)
  }
  
  /*
   *  Calcula uma nova geracao do ambiente
   */
  def nextGeneration {

    val mustRevive = new ListBuffer[Cell]
    val mustKill = new ListBuffer[Cell]

    for (i <- (0 until height)) {
      for (j <- (0 until width)) {
        if (rule.shouldRevive(i, j)) {
          mustRevive += cells(i)(j)
        } else if ((!rule.shouldKeepAlive(i, j)) && cells(i)(j).isAlive) {
          mustKill += cells(i)(j)
        }
      }
    }

    for (cell <- mustRevive) {
      cell.revive
      Statistics.recordRevive
    }

    for (cell <- mustKill) {
      cell.kill
      Statistics.recordKill
    }
    
    originator.set(cells)
    caretaker.addState(originator.store())
    savedStates += 1
    currentState += 1
    print("Saved States: " + savedStates)
    GameView.undoButton.enabled_=(true)
    

  }
  
  def undo(){
    if (currentState >=1){
      currentState -= 1
      cells = originator.restore(caretaker.getState(currentState))
      GameView.update
      GameView.redoButton.enabled_=(true)
    }else{
      GameView.undoButton.enabled_=(false)
    }
  }
  
  def redo(){
    if ((savedStates - 1) > currentState){
      currentState+=1
      cells = originator.restore(caretaker.getState(currentState))
      GameView.update
      GameView.undoButton.enabled_=(true)
    }else{
      GameView.redoButton.enabled_=(false)
    }
  }
  /*
   * Limpa o board
   * */
  def clear{
    for (i <- (0 until height)) {
      for (j <- (0 until width)) {
        if (isCellAlive(i, j)){
          makeCellDead(i, j)
        }
      }
    }
  }

  /**
   * Torna a celula de posicao (i, j) viva
   *
   * @param i posicao vertical da celula
   * @param j posicao horizontal da celula
   *
   * @throws InvalidParameterException caso a posicao (i, j) nao seja valida.
   */
  @throws(classOf[IllegalArgumentException])
  def makeCellAlive(i: Int, j: Int) = {
    if (validPosition(i, j)) {
      cells(i)(j).revive
      Statistics.recordRevive
    } else {
      throw new IllegalArgumentException
    }
  }
  
  @throws(classOf[IllegalArgumentException])
  def makeCellDead(i: Int, j: Int) = {
    if (validPosition(i, j)) {
      cells(i)(j).kill
      Statistics.removeRevive
    } else {
      throw new IllegalArgumentException
    }
  }

  /**
   * Verifica se uma celula na posicao (i, j) estah viva.
   *
   * @param i Posicao vertical da celula
   * @param j Posicao horizontal da celula
   * @return Verdadeiro caso a celula de posicao (i,j) esteja viva.
   *
   * @throws InvalidParameterException caso a posicao (i,j) nao seja valida.
   */
  @throws(classOf[IllegalArgumentException])
  def isCellAlive(i: Int, j: Int): Boolean = {
    if (validPosition(i, j)) {
      cells(i)(j).isAlive
    } else {
      throw new IllegalArgumentException
    }
  }

  /**
   * Retorna o numero de celulas vivas no ambiente.
   * Esse metodo eh particularmente util para o calculo de
   * estatisticas e para melhorar a testabilidade.
   *
   * @return  numero de celulas vivas.
   */
  def numberOfAliveCells {
    var aliveCells = 0
    for (i <- (0 until height)) {
      for (j <- (0 until width)) {
        if (isCellAlive(i, j)) aliveCells += 1
      }
    }
  }

  /**
	 * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
	 * de referencia identificada pelos argumentos (i,j).
	 */
  def numberOfNeighborhoodAliveCells(i: Int, j: Int): Int = {
    var alive = 0
    
    for (line <- (i - 1 to i + 1)) {
      for (col <- (j - 1 to j + 1)) {
        val a = if (line == -1) height-1 else if (line == height) 0 else line
        val b = if (col == -1) width-1 else if (col == width) 0 else col
        if (validPosition(a, b) && (!(line == i && col == j)) && cells(a)(b).isAlive) {
          alive += 1
        }
      }
    }
    alive
  }
  
  /**
	 * Verifica se uma posicao (i, j) referencia uma celula valida no tabuleiro.
	 */
  def validPosition(i: Int, j: Int) = (i >= 0) && (i < GameEngine.height) && 
                                      (j >= 0) && (j < GameEngine.width);
  
  /*
   * Auto Play
   * */
    def autoPlay{
    }

}
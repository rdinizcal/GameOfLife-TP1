package br.unb.cic.poo.gol.model

import scala.collection.mutable.ListBuffer
import com.google.inject.Guice
import br.unb.cic.poo.gol.model.RuleModule
import br.unb.cic.poo.gol.model.Statistics
import br.unb.cic.poo.gol.Main
import br.unb.cic.poo.gol.model.Rule
import br.unb.cic.poo.gol.model.RuleComponent
import br.unb.cic.poo.gol.model.RuleModule
import br.unb.cic.poo.gol.model.Statistics
import br.unb.cic.poo.gol.model.Cell
import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import br.unb.cic.poo.gol.view.GameView


class GameEngine extends Game{
  
  val injector = Guice.createInjector(new RuleModule)
  val rules = injector.getInstance(classOf[RuleComponent]).getRules().toSeq
  var rule : Rule = null
    
  val height = Main.height
  val width = Main.width
  
  var batch : SpriteBatch = _

  val cells = Array.ofDim[Cell](height, width)
  
  for (y <- (0 until height)) {
    for (x <- (0 until width)) {
      cells(y)(x) = new Cell
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

    for (y <- (0 until height)) {
      for (x <- (0 until width)) {
        if (rule.shouldRevive(y, x)) {
          mustRevive += cells(y)(x)
        } else if ((!rule.shouldKeepAlive(y, x)) && cells(y)(x).isAlive) {
          mustKill += cells(y)(x)
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

  }
  
   override def create : Unit = {
     batch = new SpriteBatch()
     this.setScreen(new GameView(this))
   }
   
   override def render() : Unit = {
     super.render()
	}
	
	override def dispose() : Unit = {
		batch.dispose();
		
	}

  /**
   * Torna a celula de posicao (y, x) viva
   *
   * @param y posicao vertical da celula
   * @param x posicao horizontal da celula
   *
   * @throws InvalidParameterException caso a posicao (y, x) nao seja valida.
   */
  @throws(classOf[IllegalArgumentException])
  def makeCellAlive(y: Int, x: Int) = {
    if (rule.validPosition(y, x)) {
      cells(y)(x).revive
      Statistics.recordRevive
    } else {
      throw new IllegalArgumentException
    }
  }
  
  /**
   * Torna a celula de posicao (y, x) morta
   *
   * @param y posicao vertical da celula
   * @param x posicao horizontal da celula
   *
   * @throws InvalidParameterException caso a posicao (y, x) nao seja valida.
   */
  @throws(classOf[IllegalArgumentException])
  def makeCellDead(y: Int, x: Int) = {
    if (rule.validPosition(y, x)) {
      cells(y)(x).kill
      if(cells(y)(x).isAlive){
        Statistics.removeRevive
      }
    } else {
      throw new IllegalArgumentException
    }
  }

  /**
   * Verifica se uma celula na posicao (y, x) estah viva.
   *
   * @param i Posicao vertical da celula
   * @param j Posicao horizontal da celula
   * @return Verdadeiro caso a celula de posicao (y,x) esteja viva.
   *
   * @throws InvalidParameterException caso a posicao (y,x) nao seja valida.
   */
  @throws(classOf[IllegalArgumentException])
  def isCellAlive(y: Int, x: Int): Boolean = {
    if (rule.validPosition(y, x)) {
      cells(y)(x).isAlive
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
    for (y <- (0 until height)) {
      for (x <- (0 until width)) {
        if (isCellAlive(y, x)) aliveCells += 1
      }
    }
  }

  /*
	 * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
	 * de referencia identificada pelos argumentos (y,x).
	 */
  def numberOfNeighborhoodAliveCells(y: Int, x: Int): Int = {
    var alive = 0
    for (a <- (y - 1 to x + 1)) {
      for (b <- (y - 1 to x + 1)) {
        if (rule.validPosition(a, b) && (!(a == y && b == x)) && cells(a)(b).isAlive) {
          alive += 1
        }
      }
    }
    alive
  }

}
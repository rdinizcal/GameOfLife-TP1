package br.unb.cic.poo.gol

import scala.collection.mutable.ListBuffer
import scala.util.control.TailCalls.TailRec
import scala.annotation.tailrec

/**
 * Representa a Game Engine do GoL
 *
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object GameEngine {

  val rule: Rule = new ConwayRule

  val height = Main.height
  val width = Main.width

  val cells = Array.ofDim[Cell](height, width)

  for (i <- (0 until height)) {
    for (j <- (0 until width)) {
      cells(i)(j) = new Cell
    }
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
    if (rule.validPosition(i, j)) {
      cells(i)(j).revive
      Statistics.recordRevive
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
    if (rule.validPosition(i, j)) {
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

  /*
	 * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
	 * de referencia identificada pelos argumentos (i,j).
	 */
  def numberOfNeighborhoodAliveCells(i: Int, j: Int): Int = {
    var alive = 0
    for (a <- (i - 1 to i + 1)) {
      for (b <- (j - 1 to j + 1)) {
        if (rule.validPosition(a, b) && (!(a == i && b == j)) && cells(a)(b).isAlive) {
          alive += 1
        }
      }
    }
    alive
  }

}
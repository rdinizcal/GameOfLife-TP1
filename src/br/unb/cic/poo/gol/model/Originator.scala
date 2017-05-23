package br.unb.cic.poo.gol.model

import scala.collection.mutable.MutableList

import br.unb.cic.poo.gol._

object originator {
  
  var state: Array[Array[Cell]] = Array.ofDim[Cell](GameEngine.height, GameEngine.width)
  private var aux: Array[Array[Cell]] = Array.ofDim[Cell](GameEngine.height, GameEngine.width)
  
  def set(newState: Array[Array[Cell]]){
    println("\nFrom Originator: Current Version\n")
    state = newState
  }

  def store():Array[Array[Cell]]={
    println("From Originator: Saving to Memento\n")
    aux=state
    return aux
  }
  
  def restore(s: Array[Array[Cell]]): Array[Array[Cell]]={
    state = aux
    println("\nFrom Originator: Previous State Saved in Memento\n")
    return state
  }
  
}
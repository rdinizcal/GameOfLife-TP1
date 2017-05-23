package br.unb.cic.poo.gol.model

import scala.collection.mutable.MutableList

import br.unb.cic.poo.gol._

class Originator {
  
  var state: Array[Array[Cell]] = Array.ofDim[Cell](GameEngine.height, GameEngine.width)
  
  
  def set(newState: Array[Array[Cell]]){
    println("From Originator: Current Version")
    state = newState
  }

  def store(): Memento={
    println("From Originator: Saving to Memento")
    return new Memento(state)
  }
  
  def restore(m: Memento): Array[Array[Cell]]={
    state = m.getSavedState
    println("From Originator: Previous State Saved in Memento")
    return state
  }
  
}
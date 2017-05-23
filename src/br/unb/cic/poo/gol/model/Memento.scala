package br.unb.cic.poo.gol.model

class Memento (stateSave: Array[Array[Cell]]){
  private var state: Array[Array[Cell]] = stateSave
  
  def getSavedState: Array[Array[Cell]]= {
    return state
  }
}
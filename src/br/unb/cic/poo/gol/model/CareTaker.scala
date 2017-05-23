package br.unb.cic.poo.gol.model

import scala.collection.mutable.MutableList
import scala.collection.mutable.ArrayBuffer
import java.util.ArrayList

object caretaker {
  val savedStates: ArrayBuffer[Array[Array[Cell]]] = new ArrayBuffer[Array[Array[Cell]]]
  
  def addState(s: Array[Array[Cell]]){
    savedStates.+=(s)
    return
  }
  
  def getState(i: Int):Array[Array[Cell]]={
    return savedStates(i)
  }
}
package br.unb.cic.poo.gol.model

import scala.collection.mutable.MutableList
import scala.collection.mutable.ArrayBuffer
import java.util.ArrayList

class CareTaker {
  val savedStates: ArrayBuffer[Memento] = new ArrayBuffer[Memento]
  
  def addState(m: Memento){
    savedStates.+=(m)
    return
  }
  
  def getState(i: Int):Memento={
    return savedStates(i)
  }
}
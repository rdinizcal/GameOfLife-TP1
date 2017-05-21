package br.unb.cic.poo.gol

import scala.collection.mutable.ListBuffer
import br.unb.cic.poo.gol.controller.GameController
import br.unb.cic.poo.gol.view.UI

object Main {
  
  val height = 10
  val width = 10
  
  def main(args: Array[String]){
    UI.visible = true
    UI.centerOnScreen()
    
    GameController.start
  }
}
package br.unb.cic.poo.gol.view

import scala.swing._
import java.awt.Color
import br.unb.cic.poo.gol.Main
import scala.collection.mutable.MutableList
import br.unb.cic.poo.gol.GameEngine

object UI extends MainFrame {
  
  val width = 640
  val height = 480
  
  title = "Game of Life"
  preferredSize = new Dimension(width,height)
  
  
  /******************* TOP MENU COMPONENTS *******************/
  val ruleLabel = new Label("Rule: ")
  ruleLabel.preferredSize = new Dimension(40,30)
  val ruleComboBox = new ComboBox(GameEngine.rules)
  ruleComboBox.preferredSize = new Dimension(200,30)
  
  /******************** TABLE COMPONENTS ********************/
  val cells  = Array.ofDim[Button](Main.height,Main.width)
  
  for (i <- (0 until Main.height)) {
    for (j <- (0 until Main.height)) {
      cells(i)(j) = new Button()
      cells(i)(j).background_=(Color.WHITE)
    }
  }
  
  /****************** RIGHT MENU COMPONENTS ******************/
  
  
  
  contents = new BoxPanel(Orientation.Vertical){
    contents += Swing.VStrut(5)
    //Menu de cima
    contents += new FlowPanel {
      contents += ruleLabel
      contents += Swing.HStrut(10)
      contents += ruleComboBox
      
      
      maximumSize = new Dimension(width, 30)
    }
    contents += Swing.VStrut(10)
    contents += new BoxPanel(Orientation.Horizontal){
      
      contents += Swing.HStrut(20)
      //Tabuleiro
      contents += new BoxPanel(Orientation.Vertical){
        
        contents += new GridPanel(Main.height,Main.width){
            for (i <- (0 until Main.height)) {
              for (j <- (0 until Main.height)) {
                contents += cells(i)(j) 
            }
          }
        }
        contents += new Label("Statistics: X revived cells Y killed cells")
      }
      
      contents += Swing.HStrut(20)
      //Menu lateral
      contents+= new BoxPanel(Orientation.Vertical){
        contents += Swing.VStrut(10)
        contents += new Button("Next Gen")
        contents += Swing.VStrut(10)
        contents += new Button("Play / Stop")
        contents += Swing.VStrut(10)
        contents += new Button("Undo")
        contents += Swing.VStrut(10)
        
        maximumSize = new Dimension(120, height)
      }
      
    }
  }
}
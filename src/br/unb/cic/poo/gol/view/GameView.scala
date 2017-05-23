package br.unb.cic.poo.gol.view

import br.unb.cic.poo.gol.controller.GameController
import br.unb.cic.poo.gol.GameEngine
import scala.swing.event._
import br.unb.cic.poo.gol.Main
import scala.swing.ComboBox
import scala.swing.Button
import scala.swing.Label
import scala.swing.Swing
import scala.swing.FlowPanel
import scala.swing.BoxPanel
import java.awt.Dimension
import java.awt.Color
import scala.swing.GridPanel
import br.unb.cic.poo.gol.model.Statistics
import scala.swing.Alignment
import scala.swing.Orientation
import br.unb.cic.poo.gol.model.Rule
import br.unb.cic.poo.gol.model.Originator
import br.unb.cic.poo.gol.model.CareTaker
import scala.swing.BorderPanel

object GameView extends scala.swing.MainFrame {
  
	val width = 640
  val height = 480
  
  
  
  title = "Game of Life"
  preferredSize = new Dimension(width,height)
  
  
  /******************* TOP MENU COMPONENTS *******************/
  val ruleLabel = new Label("Rule: ") { preferredSize = new Dimension(40,30) }
  val ruleComboBox = new ComboBox(GameEngine.rules) {preferredSize = new Dimension(200,30)}
  
  /******************** TABLE COMPONENTS ********************/
  val cells  = Array.ofDim[Button](Main.height,Main.width)
  
  for (i <- (0 until Main.height)) {
    for (j <- (0 until Main.height)) {
      cells(i)(j) = new Button() { 
        background = Color.LIGHT_GRAY
        reactions += {
          case ButtonClicked(self) => avaliator(i, j)
        }
      }
    }
  }
  
  val statistics = new Label() {
    horizontalTextPosition = Alignment.Left
    text = Statistics.getRevivedCells + " revived cells " +
                    Statistics.getKilledCells + " killed cells"                 
  }
  
  /****************** RIGHT MENU COMPONENTS ******************/
  val nextGenButton = new Button(" Next Gen  ") {
    reactions += {
      case ButtonClicked(nextGenButton) => nextGeneration()
    }
  }
  val autoPlayButton = new Button("Play / Stop"){
    reactions += {
      case ButtonClicked(autoPlayButton) => avaliateAutoPlay()  
    }  
  }
  val clearButton = new Button("    Clear      "){
    reactions += {
      case ButtonClicked(clearButton) => clear()
    }
  }
  val undoButton = new Button("     Undo     ") {
    reactions += {
      case ButtonClicked(undoButton) => undo()
    }
  }
  val redoButton = new Button("     Redo     ") {
    reactions += {
      case ButtonClicked(redoButton) => redo()
    }
  }
  
  undoButton.enabled_=(false)
  redoButton.enabled_=(false)
  
  /****************** SCREEN CONTENTS ******************/
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
        
        contents += new BorderPanel {
          maximumSize = new Dimension(width,30)
          add( statistics, BorderPanel.Position.West)
        }
      }
      
      contents += Swing.HStrut(20)
      //Menu lateral
      contents+= new BoxPanel(Orientation.Vertical){
        contents += Swing.VStrut(10)
        contents += nextGenButton
        contents += Swing.VStrut(10)
        contents += autoPlayButton
        contents += Swing.VStrut(10)
        contents += clearButton
        contents += Swing.VStrut(10)
        contents += undoButton
        contents += Swing.VStrut(10)
        contents += redoButton
        contents += Swing.VStrut(10)
        
        maximumSize = new Dimension(120, height)
      }
      
    }
  }  
  
  /****************** REACTIONS ******************/
  listenTo(ruleComboBox.selection)
  
  reactions += {
    case SelectionChanged(ruleComboBox) => setRule()
  }
  
  /****************** EVENT HANDLERS ******************/
  private def makeCellAlive(i : Int , j : Int) {
    GameController.makeCellAlive(i, j)
  }
  
  private def makeCellDead(i : Int , j : Int) {
    GameController.makeCellDead(i, j)
  }
  
  private def isCellAlive(i : Int , j : Int): Boolean = {
    GameController.isCellAlive(i,j)
  }
  
  private def avaliator(i : Int , j : Int) {
    if (GameController.isCellAlive(i,j)){
      makeCellDead(i, j)
    }else{
      makeCellAlive(i, j)
    }
  }
  
  private def nextGeneration() {
    GameController.nextGeneration
    updateStatisticsOnScreen
  }
  
  private def avaliateAutoPlay() {
  }
  
  private def undo(){
    GameController.undo()
    updateStatisticsOnScreen
  }
  
  private def redo(){
    GameController.redo
    updateStatisticsOnScreen
  }
  
  private def clear() {
    GameController.clear
    updateStatisticsOnScreen
  }
  
  private def setRule(){
   GameEngine.setRule(ruleComboBox.selection.index)
  }
  
  private def updateStatisticsOnScreen {
    statistics.text = Statistics.getRevivedCells + " revived cells " +
                      Statistics.getKilledCells + " killed cells"
  }
  
  /****************** EXTERNAL FUNCTIONS ****************/
  def update(){
    for (i <- (0 until Main.height)) {
      for (j <- (0 until Main.height)) {
        cells(i)(j).background = if (GameEngine.isCellAlive(i, j)) Color.BLUE else Color.LIGHT_GRAY 
      }
    }
  }
}
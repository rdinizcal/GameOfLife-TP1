package br.unb.cic.poo.gol.view

import scala.io.StdIn.readLine

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite

import br.unb.cic.poo.gol.controller.GameController
import br.unb.cic.poo.gol.model.GameEngine
import com.badlogic.gdx.Input

class GameView ( var game : GameEngine) extends Screen {
  
	
	var aliveTexture, deadTexture, selectorTexture, selectAliveTexture, selectDeadTexture, backgroundTexture, undoTexture, selectUndoTexture, nextgenTexture, selectNextgenTexture: Texture = _
  var aliveSprite, deadSprite, selectAliveSprite, selectDeadSprite : Sprite = _
  var i, j, x, y: Int = _ 
   
	
  
  /**
   * Define as regras do jogo
   */
	def setup {
	  
	 if(game.rules.length == 0){
	    println("Não há regras disponíveis")
	    return
	  }
	  	  
	  println("Regras disponíveis:")
	  for(i <- (0 until game.rules.length)) {
	    println( "["+i+"] " + game.rules.apply(i).toString() )
	  }
	  print("\nQual regra será utilizada no jogo?")
	  
	  var option = readLine().toInt
	  
	  game.setRule(option)
	}
  
  /**
	 * Atualiza o componente view (representado pela classe GameBoard),
	 * possivelmente como uma resposta a uma atualizacao do jogo.
	 */
	def update {
	  game.setRule(0)
		this.render(0.1f)
	}
  


  private def nextGeneration = GameController.nextGeneration
  private def halt = GameController.halt
	
	override def show(): Unit = {
    
     backgroundTexture = new Texture("GameBackground.png")
     
     nextgenTexture = new Texture("NextGen.png")
     selectNextgenTexture = new Texture("SelectedNextGen.png")
     
     undoTexture = new Texture("Undo.png")
     selectUndoTexture = new Texture("SelectedUndo.png")
     
     aliveTexture = new Texture("AliveCell.png")
     aliveSprite = new Sprite(aliveTexture)
     aliveSprite.setPosition(Gdx.graphics.getWidth()/2 - aliveSprite.getWidth()/2, Gdx.graphics.getHeight()/2 - aliveSprite.getHeight()/2)
     aliveSprite.setRotation(0f)
     aliveSprite.setScale(1f, 1f)
     
     deadTexture = new Texture("DeadCell.png")
     deadSprite = new Sprite(deadTexture)
     deadSprite.setPosition(Gdx.graphics.getWidth()/2 - deadSprite.getWidth()/2, Gdx.graphics.getHeight()/2 - deadSprite.getHeight()/2)
     deadSprite.setRotation(0f)
     deadSprite.setScale(1f, 1f)
     
     selectAliveTexture = new Texture("SelectedAliveCell.png")
     selectAliveSprite = new Sprite(selectAliveTexture)
     selectAliveSprite.setPosition(Gdx.graphics.getWidth()/2 - selectAliveSprite.getWidth()/2, Gdx.graphics.getHeight()/2 - selectAliveSprite.getHeight()/2)
     selectAliveSprite.setRotation(0f)
     selectAliveSprite.setScale(1f, 1f)
     
     selectDeadTexture = new Texture("SelectedDeadCell.png")
     selectDeadSprite = new Sprite(selectDeadTexture)
     selectDeadSprite.setPosition(Gdx.graphics.getWidth()/2 - selectDeadSprite.getWidth()/2, Gdx.graphics.getHeight()/2 - selectDeadSprite.getHeight()/2)
     selectDeadSprite.setRotation(0f)
     selectDeadSprite.setScale(1f, 1f)  
	}
   
  override def render(x$1: Float): Unit = {
    
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		//desenhando background e botoes nextgen e undo
		game.batch.draw(backgroundTexture, 80 + Gdx.graphics.getWidth()/2 - backgroundTexture.getWidth()/2, 38 + Gdx.graphics.getHeight()/2 - backgroundTexture.getHeight()/2)
		
		if(Gdx.input.getX() >= 1220 && Gdx.input.getX() <= 1220 + nextgenTexture.getWidth() && (Gdx.graphics.getHeight() - Gdx.input.getY()) >= 700 && (Gdx.graphics.getHeight() - Gdx.input.getY()) <= 700 + nextgenTexture.getHeight()){
		 game.batch.draw(selectNextgenTexture, 1220, 700)
		 if(Gdx.input.justTouched()){
		   GameController.nextGeneration
		 }
		}else{
		 game.batch.draw(nextgenTexture, 1220, 700)
		}
		
		if(Gdx.input.getX() >= 1200 && Gdx.input.getX() <= 1200 + undoTexture.getWidth() && (Gdx.graphics.getHeight() - Gdx.input.getY()) >= 374 && (Gdx.graphics.getHeight() - Gdx.input.getY()) <= 374 + undoTexture.getHeight()){
		 game.batch.draw(selectUndoTexture, 1200, 374)
		}else{
		 game.batch.draw(undoTexture, 1200, 374)
		}
		
		 for(y <- (0 until 10)){
		   for(x <- (0 until 10)){
		     //estabelecendo relação do y,x(células) com i,j(pixels)
		     j=Gdx.graphics.getHeight()/2 - 450/2 + 45*x
		     i=Gdx.graphics.getWidth()/2 - 450/2 + 45*y
		     
		     
		     
		     //desenhando celulas
		     //avaliando se celula ta viva ou morta
		     if(!game.isCellAlive(y, x)){
		       //pegando posição do mouse
		       if(Gdx.input.getX() >= i && Gdx.input.getX() <= i + deadSprite.getWidth() && (Gdx.graphics.getHeight() - Gdx.input.getY()) >= j && (Gdx.graphics.getHeight() - Gdx.input.getY()) <= j + deadSprite.getHeight()){
		         game.batch.draw(selectDeadSprite, i, j)
		         //pegando input do mouse(revivendo/matando celulas)
		         if(Gdx.input.justTouched()){
		           GameController.makeCellAlive(y, x)
		         }
		       }else{
		         game.batch.draw(deadSprite, i, j)
		       }
		     }else{
		       if(Gdx.input.getX() >= i && Gdx.input.getX() <= i + aliveSprite.getWidth() && (Gdx.graphics.getHeight() - Gdx.input.getY()) >= j && (Gdx.graphics.getHeight() - Gdx.input.getY()) <= j + aliveSprite.getHeight()){
		         game.batch.draw(selectAliveSprite, i, j)
		         if(Gdx.input.justTouched()){
		           GameController.makeCellDead(y, x)
		         }
		       }else{
		         game.batch.draw(aliveSprite, i, j)
		       }
		     }
		     
		   }
		 }
		
		
		//implementação rapida para teste de nextgeneration e halt
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
		  GameController.nextGeneration
		}
  	game.batch.end();
  	
    }
   
  override def dispose(): Unit = {
   }
  override def hide(): Unit = {}
  override def pause(): Unit = {}
  
  override def resize(x$1: Int,x$2: Int): Unit = {}
  override def resume(): Unit = {}
  
}

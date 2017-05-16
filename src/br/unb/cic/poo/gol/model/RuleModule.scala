package br.unb.cic.poo.gol.model

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import net.codingwell.scalaguice.ScalaMultibinder
import br.unb.cic.poo.gol.model.ConwayRule
import br.unb.cic.poo.gol.model.HighLife
import br.unb.cic.poo.gol.model.Rule

class RuleModule extends AbstractModule with ScalaModule{
  
  override def configure() : Unit = {
    val setBinder = ScalaMultibinder.newSetBinder[Rule](binder)
    setBinder.addBinding.to(classOf[ConwayRule])
    setBinder.addBinding.to(classOf[HighLife])
    
    //Implementação ideal porem https://github.com/codingwell/scala-guice/issues/50
    /*val mapBinder = ScalaMapBinder.newMapBinder[Int, Rule](binder)
    mapBinder.addBinding(1).to(classOf[ConwayRule])
    mapBinder.addBinding(2).to(classOf[HighLife])*/
  }
  
}
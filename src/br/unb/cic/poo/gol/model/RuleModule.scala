package br.unb.cic.poo.gol

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import scala.collection.mutable.MutableList
import net.codingwell.scalaguice.ScalaModule
import net.codingwell.scalaguice.ScalaMultibinder
import net.codingwell.scalaguice.ScalaMapBinder

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
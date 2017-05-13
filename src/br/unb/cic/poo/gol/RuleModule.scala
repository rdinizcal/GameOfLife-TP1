package br.unb.cic.poo.gol

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import scala.collection.mutable.MutableList

class RuleModule extends AbstractModule{
  
  override def configure(): Unit = {
    bind(classOf[Rule]).annotatedWith(Names.named("Conway")).to(classOf[ConwayRule])
    bind(classOf[Rule]).annotatedWith(Names.named("HighLife")).to(classOf[HighLife])
  }
  
}
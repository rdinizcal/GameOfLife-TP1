package br.unb.cic.poo.gol

import com.google.inject.AbstractModule
import com.google.inject.name.Names

class RuleModule extends AbstractModule {
  
  override protected def configure(): Unit = {
    bind(classOf[Rule]).to(classOf[HighLife])
    /*bind(classOf[Rule]).annotatedWith(Names.named("Conway")).to(classOf[ConwayRule])
    bind(classOf[Rule]).annotatedWith(Names.named("HighLife")).to(classOf[HighLife])*/
  }
  
}
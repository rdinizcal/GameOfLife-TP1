package br.unb.cic.poo.gol

import com.google.inject.Inject
import scala.collection.mutable.MutableList
import com.google.inject.name.Named

class RuleComponent @Inject()(
    @Named("Conway") conway : Rule,
    @Named("HighLife") highLife : Rule
    ) {
     val ruleList = new MutableList[Rule]();
     
     ruleList += conway
     ruleList += highLife;
     
     def getRuleList() = ruleList
}
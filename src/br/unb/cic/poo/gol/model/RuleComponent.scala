package br.unb.cic.poo.gol.model

import com.google.inject.Inject
import scala.collection.mutable.MutableList
import com.google.inject.name.Named
import scala.collection.immutable.HashSet
import com.google.inject.Provider
import br.unb.cic.poo.gol.model.Rule

class RuleComponent @Inject()(val rules : Set[Rule]) {
  def getRules() = rules
}
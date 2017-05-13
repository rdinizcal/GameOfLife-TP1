package br.unb.cic.poo.gol

import com.google.inject.Inject
import scala.collection.mutable.MutableList
import com.google.inject.name.Named
import scala.collection.immutable.HashSet
import com.google.inject.Provider

class RuleComponent @Inject()(val rules : Set[Rule]) {
  def getRules() = rules
}
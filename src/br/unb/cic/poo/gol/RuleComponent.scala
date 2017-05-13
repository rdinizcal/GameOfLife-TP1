package br.unb.cic.poo.gol

import com.google.inject.Inject

class RuleComponent @Inject()(val rule : Rule) {
    def getRule() : Rule = rule;
}
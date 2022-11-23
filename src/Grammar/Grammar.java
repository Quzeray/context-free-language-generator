package Grammar;

import java.util.List;

public class Grammar {
    private final List<Character> terminals;
    private final List<Character> nonTerminals;
    private final char mainSymbol;
    private final List<DerivationRule> derivationRules;

    public Grammar(List<Character> terminals, List<Character> nonTerminals, char mainSymbol,
                   List<DerivationRule> derivationRules) {
        this.terminals = terminals;
        this.nonTerminals = nonTerminals;
        this.mainSymbol = mainSymbol;
        this.derivationRules = derivationRules;
    }
}

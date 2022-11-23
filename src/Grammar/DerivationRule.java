package Grammar;

import java.util.List;

public class DerivationRule {
    private final char nonTerminal;
    private final String terminals;

    public DerivationRule(char nonTerminal, String terminals) {
        this.nonTerminal = nonTerminal;
        this.terminals = terminals;
    }
}

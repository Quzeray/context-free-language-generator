package Grammar;

import java.util.ArrayList;
import java.util.List;

public class Grammar {
    private final List<Character> terminals;
    private final List<Character> nonTerminals;
    private final char mainSymbol;
    private final ProductionRules grammarRules;

    public Grammar(List<Character> terminals, List<Character> nonTerminals, char mainSymbol,
                   ProductionRules grammarRules) {
        this.terminals = terminals;
        this.nonTerminals = nonTerminals;
        this.mainSymbol = mainSymbol;
        this.grammarRules = grammarRules;
    }

    public List<String> getStrings() {
        List<String> out = new ArrayList<>();
        StringBuilder main = new StringBuilder();

        StringBuilder start = new StringBuilder(String.valueOf(mainSymbol));

        main.append(replace(start, grammarRules.getRulesByMainSymbol(mainSymbol)));

        out.add(main.toString());

        return out;
    }

    private String replace(StringBuilder string, ProductionRule[] rules) {
        for (Character terminal : nonTerminals) {
            if (string.indexOf(String.valueOf(terminal)) == -1) return string.toString();
        }

        for (ProductionRule rule : rules) {
            int terminalIndex = string.indexOf(rule.getLeftSide());
            string.replace(terminalIndex, terminalIndex, rule.getRightSide());

            for (int i = 0; i < string.length(); i++) {
                if (terminals.contains(string.charAt(i))){
                    replace(string, grammarRules.getAll());
                }
            }
        }

        return string.toString();
    }
}

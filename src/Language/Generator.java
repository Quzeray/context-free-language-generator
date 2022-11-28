package Language;

import java.util.*;

public class Generator {
    private final Grammar grammar;
    private final List<String> completedStrings;
    private final Queue<StringBuilder> queue;
    private final int maxLength;
    private final int minLength;

    public Generator(Grammar grammar, int minLength, int maxLength) {
        this.grammar = grammar;
        this.maxLength = maxLength;
        this.minLength = minLength;
        completedStrings = new ArrayList<>();
        queue = new LinkedList<>();
    }

    public List<String> getStrings() {
        char mainSymbol = grammar.getMainSymbol();
        StringBuilder sb = new StringBuilder(mainSymbol + "");
        useRule(sb);

        return completedStrings;
    }

    private void useRule(StringBuilder string) {
        if (string.length() > maxLength) {
            return;
        }

        if (isComplete(string) && (string.length() >= minLength && string.length() <= maxLength)) {
            if (!completedStrings.contains(string.toString()))
                completedStrings.add(string.toString());
            return;
        }


        ProductionRule[] rules = grammar.getRules().getForString(String.valueOf(string));
        int ruleCount = 0;
        for (ProductionRule rule : rules) {
            ruleCount++;
            StringBuilder newString = string;
            if (canUseRule(string, rule)) {
                if (ruleCount < rules.length) {
                    newString = new StringBuilder(newString);
                    System.out.println(newString);
                }
                parse(newString, rule);
                useRule(newString);
            }
        }
    }

    private void parse(StringBuilder string, ProductionRule rule) {
        int index = string.indexOf(String.valueOf(rule.getLeftSide()));
        string.replace(index, index + 1, rule.getRightSide());
    }

    private boolean isComplete(StringBuilder string) {
        for (char nonTerminal : grammar.getNonTerminals()) {
            if (string.indexOf(String.valueOf(nonTerminal)) != -1)
                return false;
        }
        return true;
    }

    private boolean canUseRule(StringBuilder string, ProductionRule rule) {
        return string.indexOf(String.valueOf(rule.getLeftSide())) != -1;
    }

}

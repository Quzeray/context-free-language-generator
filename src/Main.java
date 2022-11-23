import Grammar.Grammar;
import Grammar.ProductionRule;
import Grammar.ProductionRules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Character> terminals = new ArrayList<>(Collections.singletonList('a'));
        List<Character> nonTerminals = new ArrayList<>(Collections.singletonList('S'));

        ProductionRule r1 = new ProductionRule("S", "A");
        ProductionRule r2 = new ProductionRule("S", "B");
        ProductionRule r3 = new ProductionRule("A", "a");
        ProductionRule r4 = new ProductionRule("B", "b");

        ProductionRules rules = new ProductionRules(r1, r2, r3, r4);

        Grammar grammar = new Grammar(terminals, nonTerminals, 'S', rules);

        System.out.println(Arrays.toString(rules.getRulesByMainSymbol('S')));
        System.out.println();
        System.out.println(String.join("; \n", grammar.getStrings()) + '.');
    }
}
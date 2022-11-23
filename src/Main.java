import Grammar.DerivationRule;
import Grammar.Grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Character> terminals = new ArrayList<>(Arrays.asList('a', 'b'));
        List<Character> nonTerminals = new ArrayList<>(Collections.singletonList('A'));

        List<DerivationRule> rules = new ArrayList<>(Arrays.asList(
                new DerivationRule('S', "ab"),
                new DerivationRule('A', "b")
        ));
        Grammar grammar = new Grammar(terminals, nonTerminals, 'S', rules);


    }
}
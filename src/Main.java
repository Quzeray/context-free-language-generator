import Language.Exception.LanguageException;
import Language.Generator;
import Language.Grammar;
import Language.ProductionRule;
import Language.ProductionRules;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        char[] terminals = new char[]{'b'};
        char[] nonTerminals = new char[]{'S', 'B'};


        ProductionRules rules = new ProductionRules(
                new ProductionRule('S', "SB"),
                new ProductionRule('S', "B"),
                new ProductionRule('B', "b"));

        try {
            Grammar grammar = new Grammar(terminals, nonTerminals, rules, 'S');
            Generator generator = new Generator(grammar, 0, 5);
            List<String> strs = generator.getStrings();
            strs.sort(String::compareTo);
            System.out.println(strs);
        } catch (LanguageException e) {
            throw new RuntimeException(e);
        }


    }
}
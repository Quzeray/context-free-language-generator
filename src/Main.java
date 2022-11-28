import Language.Exception.LanguageException;
import Language.Generator;
import Language.Grammar;
import Language.ProductionRule;
import Language.ProductionRules;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        char[] terminals = new char[]{'1', '2', '3', '4', '5', '6'};
        char[] nonTerminals = new char[]{'S', 'T', 'F'};


        ProductionRules rules = new ProductionRules(
                new ProductionRule('S', "T"),
                new ProductionRule('S', "-T"),
                new ProductionRule('T', "F"),
                new ProductionRule('F', "1"),
                new ProductionRule('F', "2"));

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
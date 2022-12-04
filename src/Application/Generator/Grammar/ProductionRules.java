package Application.Generator.Grammar;

import Application.Generator.Exception.GrammarException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class ProductionRules {
    private final List<ProductionRule> rules;

    public ProductionRules(final ProductionRule... rules) {
        this.rules = Arrays.asList(rules);
    }

    public ProductionRules(String text) throws GrammarException {
        this.rules = new ArrayList<>();
        try {
            text = text.replaceAll("\\s+", "");
            String[] textArray = text.split(",");
            ProductionRule[] rules = new ProductionRule[textArray.length];
            for (String s : textArray) {
                String[] row = s.split("->");
                this.rules.add(new ProductionRule(row[0].charAt(0), row[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GrammarException("Невозможно создать грамматику с такими правилами");
        }
    }

    public ProductionRule[] getBySymbol(char symbol) {
        return rules.stream()
                .filter(r -> r.getLeftSide() == symbol)
                .toArray(ProductionRule[]::new);
    }

    public ProductionRule[] getForString(String string) {
        return rules.stream()
                .filter(r -> string.contains(String.valueOf(r.getLeftSide())))
                .toArray(ProductionRule[]::new);
    }

    public int getSize() {
        return rules.size();
    }

    public ProductionRule[] getAll() {
        return rules.toArray(new ProductionRule[0]);
    }

    @Override
    public String toString() {
        return rules.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("; \n"));
    }
}

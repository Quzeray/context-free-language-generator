package Grammar;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class ProductionRules {
    private final List<ProductionRule> rules;

    public ProductionRules(ProductionRule... rules) {
        this.rules = Arrays.asList(rules);
    }

    public ProductionRule[] getRulesByMainSymbol(Character mainSymbol) {
        return rules.stream()
                .filter(r -> Objects.equals(r.getLeftSide(), String.valueOf(mainSymbol)))
                .toArray(i -> new ProductionRule[i]);
    }

    public ProductionRule[] getAll() {
        return rules.stream().toArray((IntFunction<ProductionRule[]>) ProductionRule[]::new);
    }

    @Override
    public String toString() {
        return rules.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("; \n"));
    }
}

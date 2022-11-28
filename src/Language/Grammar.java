package Language;

import Language.Exception.LanguageException;

import java.util.HashSet;
import java.util.Set;
@SuppressWarnings("unused")
public class Grammar {
    private final Set<Character> nonTerminals;
    private final Set<Character> terminals;
    private final ProductionRules grammarRules;
    private final char mainSymbol;

    {
        this.nonTerminals = new HashSet<>();
        this.terminals = new HashSet<>();
    }

    public Grammar(final char[] terminals, final char[] nonTerminals,
                   final ProductionRules grammarRules, final char mainSymbol) throws LanguageException {
        setNonTerminals(nonTerminals);
        setTerminals(terminals);
        checkRules(grammarRules);
        this.grammarRules = grammarRules;
        checkCorrectSymbol(mainSymbol);
        this.mainSymbol = mainSymbol;
    }

    private void setNonTerminals(final char[] nonTerminals) throws LanguageException {
        setChars(this.nonTerminals, "Нетерминал", nonTerminals);
    }

    private void setTerminals(final char[] terminals) throws LanguageException {
        setChars(this.terminals, "Терминал", terminals);
    }

    private void setChars(Set<Character> set, String type, final char[] chars) throws LanguageException {
        if (chars.length == 0) {
            throw new LanguageException(type + "ы должны содержать хотя бы один элемент");
        }

        for (char ch : chars) {
            if (!set.add(ch)) {
                throw new LanguageException(type + " не может повторяться");
            }
        }
    }

    private void checkRules(ProductionRules rules) throws LanguageException {
        if (rules == null || rules.getSize() == 0){
            throw new LanguageException("Должно быть хотя бы одно правило");
        }
    }

    private void checkCorrectSymbol(char symbol) throws LanguageException {
        if (!(symbol >= 'A' && symbol <= 'Z')) {
            throw new LanguageException("Некорректный основной символ");
        }

        if (!nonTerminals.contains(symbol)) {
            throw new LanguageException("Основной символ не находится в множестве нетерминалов");
        }
    }

    public Set<Character> getNonTerminals() {
        return nonTerminals;
    }

    public Set<Character> getTerminals() {
        return terminals;
    }

    public ProductionRules getRules() {
        return grammarRules;
    }

    public char getMainSymbol() {
        return mainSymbol;
    }
}

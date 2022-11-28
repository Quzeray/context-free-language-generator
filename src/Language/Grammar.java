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
        setChars(this.nonTerminals, "����������", nonTerminals);
    }

    private void setTerminals(final char[] terminals) throws LanguageException {
        setChars(this.terminals, "��������", terminals);
    }

    private void setChars(Set<Character> set, String type, final char[] chars) throws LanguageException {
        if (chars.length == 0) {
            throw new LanguageException(type + "� ������ ��������� ���� �� ���� �������");
        }

        for (char ch : chars) {
            if (!set.add(ch)) {
                throw new LanguageException(type + " �� ����� �����������");
            }
        }
    }

    private void checkRules(ProductionRules rules) throws LanguageException {
        if (rules == null || rules.getSize() == 0){
            throw new LanguageException("������ ���� ���� �� ���� �������");
        }
    }

    private void checkCorrectSymbol(char symbol) throws LanguageException {
        if (!(symbol >= 'A' && symbol <= 'Z')) {
            throw new LanguageException("������������ �������� ������");
        }

        if (!nonTerminals.contains(symbol)) {
            throw new LanguageException("�������� ������ �� ��������� � ��������� ������������");
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

package Language;

import Language.Exception.LanguageException;
import jdk.nashorn.internal.ir.IfNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        List<Character> nonTerminalFromRules = Arrays.stream(rules.getAll())
                .map(ProductionRule::getLeftSide)
                .sorted(Character::compareTo)
                .distinct()
                .collect(Collectors.toList());

        List<Character> nonTerminalsFromGrammar = nonTerminals.stream()
                .sorted(Character::compareTo)
                .collect(Collectors.toList());

        if (!(nonTerminalFromRules.equals(nonTerminalsFromGrammar))) {
            throw new LanguageException("�������� ���������� ���������� (�������������� ����������� � ��������)");
        }

        if (rules.getSize() == 0){
            throw new LanguageException("������ ���� ���� �� ���� �������");
        }

        List<String> terminalFromRules = Arrays.stream(rules.getAll())
                .map(p -> p.getRightSide().replaceAll("[A-Z]", ""))
                .filter(s -> !s.equals(""))
                .sorted(String::compareTo)
                .distinct()
                .collect(Collectors.toList());

        List<String> terminalsFromGrammar = terminals.stream()
                .map(String::valueOf)
                .sorted(String::compareTo)
                .collect(Collectors.toList());

        if (!(nonTerminalFromRules.equals(nonTerminalsFromGrammar))) {
            throw new LanguageException("�������� ���������� ���������� (�������������� ��������� � ��������)");
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

package Application.Generator.Grammar;

import Application.Generator.Exception.GrammarException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
                   final ProductionRules grammarRules, final String mainSymbol) throws GrammarException {
        setNonTerminals(nonTerminals);
        setTerminals(terminals);
        checkRules(grammarRules);
        this.grammarRules = grammarRules;
        checkCorrectSymbol(mainSymbol);
        this.mainSymbol = mainSymbol.charAt(0);
    }

    private void setChars(Set<Character> set, String type, final char[] chars) throws GrammarException {
        if (chars.length == 0) {
            throw new GrammarException(type + "� ������ ��������� ���� �� ���� �������");
        }

        for (char ch : chars) {
            if (!set.add(ch)) {
                throw new GrammarException(type + " �� ����� �����������");
            }
        }
    }

    private void checkRules(ProductionRules rules) throws GrammarException {
        if (rules == null || rules.getSize() == 0) {
            throw new GrammarException("������ ���� ���� �� ���� �������");
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
            throw new GrammarException("�������� ���������� ���������� (�������������� ����������� � �������� " +
                    "��� �� ��� ����������� ������������� ��������)");
        }

        if (rules.getSize() == 0) {
            throw new GrammarException("������ ���� ���� �� ���� �������");
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

        if (!(terminalFromRules.equals(terminalsFromGrammar))) {
            throw new GrammarException("�������� ���������� ���������� (�������������� ��������� � �������� " +
                    "��� �� ��� ��������� ������������� ��������)");
        }
    }

    private void checkCorrectSymbol(String s) throws GrammarException {
        if (s.length() != 1) {
            throw new GrammarException("� �������� ��������� ������� ����� ���� ���� � ������ ����� ������");
        }
        char symbol = s.charAt(0);
        if (!(symbol >= 'A' && symbol <= 'Z')) {
            throw new GrammarException("������������ �������� ������");
        }

        if (!nonTerminals.contains(symbol)) {
            throw new GrammarException("�������� ������ �� ��������� � ��������� ������������");
        }
    }

    public Set<Character> getNonTerminals() {
        return nonTerminals;
    }

    private void setNonTerminals(final char[] nonTerminals) throws GrammarException {
        setChars(this.nonTerminals, "����������", nonTerminals);
    }

    public Set<Character> getTerminals() {
        return terminals;
    }

    private void setTerminals(final char[] terminals) throws GrammarException {
        setChars(this.terminals, "��������", terminals);
    }

    public ProductionRules getRules() {
        return grammarRules;
    }

    public char getMainSymbol() {
        return mainSymbol;
    }

    @Override
    public String toString() {
        return "G = (" + nonTerminals + "), " + terminals + ", P, " + mainSymbol + "), ��� P:\n" +
                grammarRules.toString();
    }
}

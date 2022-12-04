package Application.UI;

import Application.Generator.Exception.GrammarException;
import Application.Generator.Generator;
import Application.Generator.Grammar.Grammar;
import Application.Generator.Grammar.ProductionRules;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

import static Application.UI.Settings.*;
import static java.lang.Integer.parseInt;

public class MainFrame extends JFrame {
    private JTextField nonTerminalsTextField;
    private JTextField terminalsTextField;
    private JTextField mainSymbolTextField;
    private JTextField minLengthTextField;
    private JTextField maxLengthTextField;
    private JTextArea productionRulesTextArea;
    private JTextArea grammarTextArea;
    private JTextArea stringsTextArea;

    public MainFrame() throws HeadlessException {

    }

    @Override
    protected void frameInit() {
        super.frameInit();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Генератор цепочек языка");
        setIconImage(ICON.getImage());
        setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        setContentPane(contentPane);


        add(createLeftPanel());
        add(createBorder(SwingConstants.VERTICAL));
        add(createRightPanel());
    }

    private JPanel createLeftPanel() {
        JPanel left = new JPanel();
        left.setBackground(Color.WHITE);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        JPanel fieldPanel = new JPanel();
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));

        nonTerminalsTextField = createTextField("Нетерминалы", fieldPanel);
        terminalsTextField = createTextField("Терминалы", fieldPanel);
        mainSymbolTextField = createTextField("Основной символ", fieldPanel);
        minLengthTextField = createTextField("Минимальная длина", fieldPanel);
        maxLengthTextField = createTextField("Максимальная длина", fieldPanel);
        fieldPanel.add(new JLabel("Список правил:"));
        productionRulesTextArea = createTextArea(fieldPanel);

        left.add(fieldPanel);

        left.add(createBorder(SwingConstants.HORIZONTAL));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        JButton generateButton = createButton("Сгенерировать", buttonPanel, this::generate);
        left.add(buttonPanel);

        return left;
    }

    private JPanel createRightPanel() {
        JPanel right = new JPanel();
        right.setBackground(Color.WHITE);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));


        grammarTextArea = createTextArea(right);
        right.add(createBorder(SwingConstants.HORIZONTAL));
        stringsTextArea = createTextArea(right);
        return right;
    }

    private JTextArea createTextArea(JComponent container) {
        JTextArea text = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        text.setLineWrap(true);
        text.setBorder(new EmptyBorder(20, 20, 20, 20));
        container.add(scrollPane);
        return text;
    }

    private JTextField createTextField(String title, JComponent container) {
        JPanel row = new JPanel();
        row.setLayout(new GridLayout(1, 2, 20, 0));
        row.setBackground(Color.WHITE);
        row.setMaximumSize(new Dimension(400, 25));
        JLabel label = new JLabel(title);
        JTextField field = new JTextField();
        field.setMinimumSize(new Dimension(200, 25));
        row.add(label);
        row.add(field);
        container.add(row);

        return field;
    }

    private JPanel createBorder(int direction) {
        Dimension d = new Dimension(0, 0);
        JPanel border = new JPanel();
        border.setBackground(Color.GRAY);
        if (direction == SwingConstants.HORIZONTAL) {
            border.setLayout(new BoxLayout(border, BoxLayout.X_AXIS));
            d = new Dimension(0, 1);
            border.add(Box.createHorizontalGlue());
        } else if (direction == SwingConstants.VERTICAL) {
            border.setLayout(new BoxLayout(border, BoxLayout.Y_AXIS));
            d = new Dimension(1, 0);
            border.add(Box.createVerticalGlue());
        }
        border.setPreferredSize(d);
        return border;
    }

    private JButton createButton(String text, JComponent container, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        container.add(button);
        return button;
    }

    private void generate(ActionEvent event) {
        char[] terminals = String.join("", terminalsTextField.getText().replaceAll("\\s+", "")
                .split(",")).toCharArray();
        char[] nonTerminals = String.join("", nonTerminalsTextField.getText().replaceAll("\\s+", "")
                .split(",")).toCharArray();
        try {
            ProductionRules productionRules = new ProductionRules(productionRulesTextArea.getText());
            Grammar grammar = new Grammar(terminals, nonTerminals,
                    productionRules, mainSymbolTextField.getText());
            grammarTextArea.setText(grammar.toString());

            Generator generator = new Generator(grammar,
                    parseInt(minLengthTextField.getText()), parseInt(maxLengthTextField.getText()));
            String strings = generator.getStrings().stream().sorted(String::compareTo).collect(Collectors.toList()).toString();
            stringsTextArea.setText("Цепочки языка:\n" + strings.replaceAll("[\\[.+?\\]]", ""));


        } catch (GrammarException e) {
            JOptionPane.showMessageDialog(this.getOwner(), e.getMessage(), "Ошибка",
                    JOptionPane.WARNING_MESSAGE);
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this.getOwner(), "Ограничиывающие значения должны быть числовыми",
                    "Ошибка", JOptionPane.WARNING_MESSAGE);
        }
    }
}

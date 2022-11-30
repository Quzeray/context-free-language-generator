package Application.UI;

import javafx.scene.layout.BorderPane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

import static Application.UI.Settings.*;

public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException {

    }

    @Override
    protected void frameInit() {
        super.frameInit();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Морские битвы");
        setIconImage(ICON.getImage());
        setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        setContentPane(contentPane);


        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        add(left);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        JTextField textField = new JTextField("text");
        textField.setMaximumSize(new Dimension(100, 50));
        JTextField textField2 = new JTextField("text");
        textField2.setMaximumSize(new Dimension(100, 50));
        fieldPanel.add(textField);
        fieldPanel.add(textField2);
        left.add(fieldPanel);

        left.add(createBorder(SwingConstants.HORIZONTAL));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("Сохранить"));
        buttonPanel.add(new JButton("Создать"));
        left.add(buttonPanel);


        add(createBorder(SwingConstants.VERTICAL));


        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        add(right);

        JPanel cont = new JPanel();
        JTextArea grammar = new JTextArea();
        cont.add(grammar);
        grammar.setBorder(new EmptyBorder(20, 20, 20, 20));
        grammar.setText("dasfgasdfassssssssssssssssssssssssssssssssssssssssssssssssssfas");
        right.add(cont);

        right.add(createBorder(SwingConstants.HORIZONTAL));

        JPanel cont2 = new JPanel();
        JTextArea language = new JTextArea();
        cont2.add(language);
        language.setBorder(new EmptyBorder(20, 20, 20, 20));
        language.setText("dasfgasdfasfas");
        right.add(cont2);
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

}

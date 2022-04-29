package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;


public class Calculator extends JFrame {
    JLabel ResultLabel, EquationLabel;
    JPanel upperJpanel, buttonsPanel, lowerPanel;

    // store operator and operands

    int lastDotIndex;
    JButton Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Add, Subtract, Divide, Multiply, Equals, Clear, Dot, Delete;
    boolean isDecimal = false, isDivisionByZero = false, isFormatChecked = true;

    public Calculator() {
        super("Calculator");
        //    op = a = b = "";
        setSize(300, 400);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUpperJPanel();
        setLowerJPanel();
    }

    void setUpperJPanel() {
        upperJpanel = new JPanel(new FlowLayout());
        upperJpanel.setPreferredSize(new Dimension(300, 75));
        upperJpanel.setBackground(Color.pink);

        ResultLabel = new JLabel();
        ResultLabel.setName("ResultLabel");
        ResultLabel.setPreferredSize(new Dimension(260, 25));
        ResultLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        upperJpanel.add(ResultLabel);

        EquationLabel = new JLabel();
        EquationLabel.setName("EquationLabel");
        EquationLabel.setPreferredSize(new Dimension(260, 25));
        upperJpanel.add(EquationLabel);
        this.add(upperJpanel);
    }

    void setLowerJPanel() {
        lowerPanel = new JPanel();
        lowerPanel.setPreferredSize(new Dimension(300, 265));
        lowerPanel.setBackground(Color.green);
        buttonsPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        buttonsPanel.setPreferredSize(new Dimension(250, 250)); //  setBounds(0, 50, 300, 300);
        buttonsPanel.setBackground(Color.green);
        createButtons();
        lowerPanel.add(buttonsPanel);
        this.add(lowerPanel);
    }

    void createButtons() {

        buttonsPanel.add(new JButton()).setVisible(false);
        buttonsPanel.add(new JButton()).setVisible(false);
        Clear = new JButton("C");
        Clear.setName("Clear");
        Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EquationLabel.setForeground(Color.BLACK);
                EquationLabel.setText("");
                ResultLabel.setText("");
                isDecimal = false;
                isDivisionByZero = false;
            }
        });
        buttonsPanel.add(Clear);

        Delete = new JButton("Del");
        Delete.setName("Delete");
        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteLastChar();
            }
        });
        buttonsPanel.add(Delete);

        Seven = new JButton("7");
        Seven.setName("Seven");
        Seven.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Seven.getText()));
        buttonsPanel.add(Seven);

        Eight = new JButton("8");
        Eight.setName("Eight");
        Eight.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Eight.getText()));
        buttonsPanel.add(Eight);

        Nine = new JButton("9");
        Nine.setName("Nine");
        Nine.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Nine.getText()));
        buttonsPanel.add(Nine);

        Divide = new JButton("\u00F7");
        Divide.setName("Divide");
        Divide.addActionListener(e -> {
            if (!EquationLabel.getText().isEmpty()) {
                if (isLastCharOperator()) {
                    deleteLastChar();
                }
                EquationLabel.setText(EquationLabel.getText() + Divide.getText());
                if (!isFormatChecked) {
                    checkEquationFormat();
                }
            }
        });
        buttonsPanel.add(Divide);

        Four = new JButton("4");
        Four.setName("Four");
        Four.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Four.getText()));
        buttonsPanel.add(Four);

        Five = new JButton("5");
        Five.setName("Five");
        Five.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Five.getText()));
        buttonsPanel.add(Five);

        Six = new JButton("6");
        Six.setName("Six");
        Six.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Six.getText()));
        buttonsPanel.add(Six);

        Multiply = new JButton("\u00D7");
        Multiply.setName("Multiply");
        Multiply.addActionListener(e -> {
            if (!EquationLabel.getText().isEmpty()) {
                if (isLastCharOperator()) {
                    deleteLastChar();
                }
                EquationLabel.setText(EquationLabel.getText() + Multiply.getText());
                if (!isFormatChecked) {
                    checkEquationFormat();
                }
            }
        });

        buttonsPanel.add(Multiply);

        One = new JButton("1");
        One.setName("One");
        One.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + One.getText()));
        buttonsPanel.add(One);

        Two = new JButton("2");
        Two.setName("Two");
        Two.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Two.getText()));
        buttonsPanel.add(Two);

        Three = new JButton("3");
        Three.setName("Three");
        Three.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Three.getText()));
        buttonsPanel.add(Three);

        Add = new JButton("\u002B");
        Add.setName("Add");
        Add.addActionListener(e -> {
            if (!EquationLabel.getText().isEmpty()) {
                if (isLastCharOperator()) {
                    deleteLastChar();
                }

                EquationLabel.setText(EquationLabel.getText() + Add.getText());
                if (!isFormatChecked) {
                    checkEquationFormat();
                }
            }
        });
        buttonsPanel.add(Add);

        Dot = new JButton(".");
        Dot.setName("Dot");
        Dot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                /*
                if (!EquationLabel.getText().isEmpty()) {
                    lastDotIndex = EquationLabel.getText().length() - 1;
                    isOperandBeforeDot = getOperator(EquationLabel.getText().charAt(lastDotIndex)) == null;
                    //     checkEquationFormat();
                } else {
                    //     isOperatorBeforeDot = true;
                    //   checkEquationFormat();

                }
                 */
                EquationLabel.setText(EquationLabel.getText() + Dot.getText());
                lastDotIndex = EquationLabel.getText().length() - 1;
                isFormatChecked = false;
                //  isOperandBeforeDot = true;
                //       isDecimal = true;
                //       }
            }
        });
        buttonsPanel.add(Dot);

        Zero = new JButton("0");
        Zero.setName("Zero");
        Zero.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Zero.getText()));
        buttonsPanel.add(Zero);

        Equals = new JButton("=");
        Equals.setName("Equals");
        // Equals.addActionListener(e -> ResultLabel.setText(calculate(EquationLabel.getText())));
        Equals.addActionListener(e -> {

                    if (getPrecedence(EquationLabel.getText().charAt(EquationLabel.getText().length() - 1)) != -1) {
                        redEquationLabel();
                    } else {
                        EquationLabel.setForeground(Color.BLACK);
                        String str = EquationLabel.getText();
                        //  System.out.println(str); //test
                        str = infixToPostfix(str);
                        //   System.out.println(str); //test
                        double ans = getAnswerFromPostfix(str);
                        //       if (!isDivisionByZero) {
                        if ((int) ans == ans) {
                            ResultLabel.setText(String.valueOf((int) ans));
                        } else {
                            ResultLabel.setText(String.valueOf(ans));
                        }
                        lastDotIndex = 0;
                    }

                }
        );
        buttonsPanel.add(Equals);

        Subtract = new JButton("\u2212");
        Subtract.setName("Subtract");
        Subtract.addActionListener(e -> {
            if (!EquationLabel.getText().isEmpty()) {
                if (isLastCharOperator()) {
                    deleteLastChar();
                }
                EquationLabel.setText(EquationLabel.getText() + "-");
                if (!isFormatChecked) {
                    checkEquationFormat();
                }
            }
        });
        buttonsPanel.add(Subtract);
    }

    private void checkEquationFormat() {
     //   System.out.println("Last dot index  : " + lastDotIndex);

        if (lastDotIndex == 0 ||
                String.valueOf(EquationLabel.getText().charAt(lastDotIndex - 1)).matches("\\D")) {

            EquationLabel.setText(
                    EquationLabel.getText().substring(0, lastDotIndex)
                            + 0 + EquationLabel.getText().substring(lastDotIndex));
            isFormatChecked = true;
        } else if (getOperator(EquationLabel.getText().charAt(lastDotIndex + 1)) != null) {
            EquationLabel.setText(
                    EquationLabel.getText().substring(0, lastDotIndex + 1)
                            + 0 + EquationLabel.getText().substring(lastDotIndex + 1));
            isFormatChecked = true;
        }



           /*
                 if (getOperator(EquationLabel.getText().charAt(lastDotIndex)) == null) {
            EquationLabel.setText(
                    EquationLabel.getText().substring(0, lastDotIndex)
                            + 0 + EquationLabel.getText().substring(lastDotIndex));
            //      isOperandBeforeDot = false;
        }
else {
            EquationLabel.setText(
                    EquationLabel.getText().substring(0, lastDotIndex + 1)
                            + "0" + EquationLabel.getText().substring(lastDotIndex + 1));
        }
           if (EquationLabel.getText().matches(".+\\D\\..+")) {
            System.out.println("I miss a zero before dot");
        }
            */

    }

    private void redEquationLabel() {
        EquationLabel.setForeground(Color.RED.darker());
    }

    private boolean isLastCharOperator() {
        return getOperator(EquationLabel.getText().charAt(EquationLabel.getText().length() - 1)) != null;
    }

    private void deleteLastChar() {
        if (EquationLabel.getText().length() > 0) {
            EquationLabel.setText(EquationLabel.getText()
                    .substring(0, EquationLabel.getText().length() - 1));
        }
    }


    private String getOperator(char ch) {
        if (ch == '^') {
            return "power";
        } else if (ch == '\u00F7') {
            return "divide";
        } else if (ch == '\u00D7') {
            return "multiply";
        } else if (ch == '\u002B') {
            return "add";
        } else if (ch == '-') {
            return "subtract";
        }
        return null;
    }


    private double getAnswerFromPostfix(String str) {
        String[] strArray = str.split("\\s+");
        Stack<String> st = new Stack<>();
        for (String s : strArray) {
            if (getOperator(s.charAt(0)) == null) {
                st.push(s);
            } else {
                String operator = getOperator(s.charAt(0));
                double num1 = Double.parseDouble(st.pop());
                double num2 = Double.parseDouble(st.pop());
                double ans = 0;
                if ("add".equals(operator)) {
                    ans = num2 + num1;
                } else if ("subtract".equals(operator)) {
                    ans = num2 - num1;
                } else if ("multiply".equals(operator)) {
                    ans = num2 * num1;
                } else if ("divide".equals(operator)) {
                    if (num1 == 0) {
                        redEquationLabel();
                        isDivisionByZero = true;
                    } else {
                        ans = num2 / num1;
                    }
                }
                st.push(String.valueOf(ans));
            }
        }
        return Double.parseDouble(st.peek());
    }

    private String infixToPostfix(String str) {
        Stack<Character> stack = new Stack<>();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (getPrecedence(str.charAt(i)) == -1) {
                ans.append(str.charAt(i));
            } else {
                char currentCh = str.charAt(i);
                if (stack.empty() || getPrecedence(stack.peek()) < getPrecedence(currentCh)) {
                    ans.append(" ");
                    stack.push(str.charAt(i));
                } else {
                    while (!stack.empty() && getPrecedence(stack.peek()) >= getPrecedence(currentCh)) {
                        ans.append(" ").append(stack.pop()).append(" ");
                    }
                    stack.push(currentCh);
                }
            }
        }
        while (!stack.empty()) {
            ans.append(" ").append(stack.peek());
            stack.pop();
        }
        return ans.toString();
    }

    private int getPrecedence(char ch) {
        if (ch == '^') {
            return 3;
        } else if (ch == '\u00F7' || ch == '\u00D7') {  //   / and *
            return 2;
        } else if (ch == '\u002B' || ch == '-') { // + and -
            return 1;
        }
        return -1;
    }
}




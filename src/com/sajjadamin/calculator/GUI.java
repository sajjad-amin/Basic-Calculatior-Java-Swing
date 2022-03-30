package com.sajjadamin.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Stack;

public class GUI extends JFrame implements ActionListener {
    private final GuiHelper guiHelper;
    private Container container;
    private JLabel inputLabel, outputLabel;
    private final Stack<String> inputStack;
    private final Calculator calculator;

    public GUI() throws HeadlessException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.guiHelper = new GuiHelper();
        this.inputStack = new Stack<>();
        this.calculator = new Calculator();
        initComponent();
    }
    private void initComponent() {
        this.setTitle("Basic Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0,0,350,460);
        this.setLocationRelativeTo(this);
        this.setResizable(false);
        container = this.getContentPane();
        container.setLayout(null);
        calculatorDisplay();
        calculatorKeypad();
        this.setVisible(true);
    }

    private void calculatorDisplay() {
        JPanel display = new JPanel();
        display.setBounds(10,10,310,100);
        display.setBackground(Color.LIGHT_GRAY);
        display.setLayout(null);
        container.add(display);
        inputLabel = new JLabel("0");
        inputLabel.setBounds(0,0,300,60);
        inputLabel.setFont(new Font("",Font.PLAIN,20));
        inputLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        display.add(inputLabel);
        outputLabel = new JLabel();
        outputLabel.setBounds(0,50,300,40);
        outputLabel.setFont(new Font("",Font.PLAIN,30));
        outputLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        display.add(outputLabel);
    }

    private void calculatorKeypad() {
        JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnPoint, btnEqual, btnPlus, btnMinus, btnMul, btnDiv, btnBack, btnC, btnCE, btnPercent;
        // Row 1
        btnPercent = guiHelper.createButton(container,"%",10,120,70,50);
        btnCE = guiHelper.createButton(container,"CE",90,120,70,50);
        btnC = guiHelper.createButton(container,"C",170,120,70,50);
        btnBack = guiHelper.createButton(container,"←",250,120,70,50);
        // Row 2
        btn7 = guiHelper.createButton(container,"7",10,180,70,50);
        btn8 = guiHelper.createButton(container,"8",90,180,70,50);
        btn9 = guiHelper.createButton(container,"9",170,180,70,50);
        btnDiv = guiHelper.createButton(container,"÷",250,180,70,50);
        // Row 3
        btn4 = guiHelper.createButton(container,"4",10,240,70,50);
        btn5 = guiHelper.createButton(container,"5",90,240,70,50);
        btn6 = guiHelper.createButton(container,"6",170,240,70,50);
        btnMul = guiHelper.createButton(container,"×",250,240,70,50);
        // Row 4
        btn1 = guiHelper.createButton(container,"1",10,300,70,50);
        btn2 = guiHelper.createButton(container,"2",90,300,70,50);
        btn3 = guiHelper.createButton(container,"3",170,300,70,50);
        btnMinus = guiHelper.createButton(container,"-",250,300,70,50);
        // Row 5
        btnPoint = guiHelper.createButton(container,".",10,360,70,50);
        btn0 = guiHelper.createButton(container,"0",90,360,70,50);
        btnEqual = guiHelper.createButton(container,"=",170,360,70,50);
        btnPlus = guiHelper.createButton(container,"+",250,360,70,50);
        // Add action listeners
        btnPlus.addActionListener(this);
        btnMinus.addActionListener(this);
        btnMul.addActionListener(this);
        btnDiv.addActionListener(this);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);
        btn7.addActionListener(this);
        btn8.addActionListener(this);
        btn9.addActionListener(this);
        btn0.addActionListener(this);
        btnC.addActionListener(this);
        btnCE.addActionListener(this);
        btnBack.addActionListener(this);
        btnPercent.addActionListener(this);
        btnPoint.addActionListener(this);
        btnEqual.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = guiHelper.filterSymbol(e.getActionCommand());
        String character = e.getActionCommand();
        if(command.equals("<=") && inputStack.size() > 0){
            inputStack.pop();
        } else if(command.equals("C") || command.equals("CE")){
            inputStack.clear();
            if(command.equals("C")){
                outputLabel.setText("");
            }
        } else if(!command.equals("=") && !command.equals("<=")) {
            inputStack.push(character);
        }
        StringBuilder input = new StringBuilder();
        for (String s : inputStack) {
            input.append(s);
        }
        if(command.equals("=") && inputStack.size() > 0){
            updateDisplay(input.toString(),true);
        } else {
            updateDisplay(input.toString(), false);
        }
    }

    private void updateDisplay(String input, Boolean hasOutput) {
        inputLabel.setText(input.equals("") ? "0" : input);
        if(hasOutput){
            BigDecimal output = calculator.calculate(input);
            outputLabel.setText(output.toString());
        }
    }
}

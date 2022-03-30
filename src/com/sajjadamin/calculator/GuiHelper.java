package com.sajjadamin.calculator;

import javax.swing.*;
import java.awt.*;

public class GuiHelper {
    public JButton createButton(Container container, String label, int xPos, int yPos, int width, int height){
        JButton btn = new JButton(label);
        btn.setBounds(xPos,yPos,width,height);
        btn.setMargin(new Insets(0,0,0,0));
        btn.setFont(new Font("", Font.PLAIN, 20));
        container.add(btn);
        return btn;
    }
    public String filterSymbol(String s){
        String filteredSymbol = s;
        switch (filteredSymbol) {
            case "←":
                filteredSymbol = "<=";
                break;
            case "×":
                filteredSymbol = "*";
                break;
            case "÷":
                filteredSymbol = "/";
                break;
        }
        return filteredSymbol;
    }
}

package UI.JTEXT;

import CONST.Const;

import javax.swing.*;
import java.awt.*;

public class Prompter extends JTextArea {
    public Prompter()
    {
        Font bold = new Font("Arial", Font.ITALIC, Const.clockFontSize);
        this.setBackground(Color.WHITE);
        this.setFont(bold);
        this.setForeground(Color.BLUE);
        this.setText("good luck!!!");
    }
    public void setCurrText(String text)
    {
        this.setText(text);
    }
    public JTextArea getJtext()
    {
        return this;
    }
}

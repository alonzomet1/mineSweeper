package UI.JTEXT;

import CONST.Const;

import javax.swing.*;
import java.awt.*;

public class TextClock extends JTextArea {
    public TextClock()
    {
        Font bold = new Font("Arial", Font.BOLD, Const.clockFontSize);
        this.setBackground(Color.BLACK);
        this.setFont(bold);
        this.setForeground(Color.RED);
        this.setText("00:00");
    }
    public void updateTime(String time)
    {
        this.setText(time);
    }
    public JTextArea getJtext()
    {
        return this;
    }
}

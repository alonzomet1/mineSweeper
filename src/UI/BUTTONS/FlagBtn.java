package UI.BUTTONS;

import CONST.Const;
import MODEL.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlagBtn extends JButton {
    public FlagBtn()
    {
        this.setButtonState(10);
        this.setMaximumSize(new Dimension(Const.iconSize, Const.iconSize));
        ActionListener clicked = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Model.reverseFlagMode();
                if(Model.getFlagMode())
                {
                    setButtonState(13);
                }
                else
                {
                    setButtonState(10); //red flag
                }
            }
        };
        this.addActionListener(clicked);
    }
    public void setButtonState(int state)
    {
        this.setIcon(new ImageIcon(getClass().getClassLoader().getResource(Integer.toString(state) + ".png")));
    }
    public void greenFlag()
    {
        this.setIcon(new ImageIcon(getClass().getClassLoader().getResource( "greenFlag.png")));
    }
}

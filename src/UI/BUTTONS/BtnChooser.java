package UI.BUTTONS;

import CONST.Const;
import MODEL.Model;
import com.sun.org.apache.xpath.internal.operations.Mod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BtnChooser extends JButton {
    public BtnChooser(int ID)
    {
        this.setName(Integer.toString(ID));
        ActionListener clicked = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosenOption = ((JComponent) e.getSource()).getName();
                int difficulty = Integer.parseInt(chosenOption);
                Model.closeMenu();
                Model.initiateGame(difficulty);

            }
        };
        this.addActionListener(clicked);
    }
    public JButton getButton()
    {
        return this;
    }
}

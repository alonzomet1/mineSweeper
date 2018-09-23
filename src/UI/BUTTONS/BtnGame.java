package UI.BUTTONS;

import CONST.Const;
import CONTROL.BoardControl;
import MAIN.Run;
import MODEL.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BtnGame extends JButton {

    public BtnGame() {
        setButtonState(Const.HIDDEN);
        ActionListener clicked = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] cordinates;
                cordinates = convertStrToArr(((JComponent)e.getSource()).getName());
                if(Model.getFlagMode())
                {
                    Model.turnBtnToFlag(cordinates[0], cordinates[1]);
                }
                else
                {
                    Model.updateUI(cordinates[0], cordinates[1]);
                }
            }
        };
        this.addActionListener(clicked);
    }



    private int[] convertStrToArr(String str)
    {
        String[] a = str.split(",");
        int[] cordinates = new int[]{Integer.parseInt(a[0]), Integer.parseInt(a[1])};
        return cordinates;
    }
    public void setButtonState(int state)
    {
        this.setIcon(new ImageIcon(getClass().getClassLoader().getResource(Integer.toString(state) + ".png")));
    }
    public JButton getButton()
    {
        return this;
    }
}

package UI.PANELS;

import CONST.Const;
import com.sun.org.apache.bcel.internal.generic.FALOAD;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements BasicPanel {
    public GamePanel()
    {
        this.setLayout(new GridLayout(Const.col, Const.row));
        this.setVisible(false);//will set true when the game is initiated
        this.setPreferredSize(new Dimension(Const.row * Const.iconSize, Const.col * Const.iconSize));
    }


    public void addComp(Component b)
    {
        this.add(b);
    }

    public JPanel getPanel()
    {
        return this;
    }

    @Override
    public void close() {
        this.setVisible(false);
    }

    public void showPanel()
    {
        this.setVisible(true);
    }

}

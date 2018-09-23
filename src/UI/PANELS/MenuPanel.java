package UI.PANELS;

import CONST.Const;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel implements BasicPanel {
    public MenuPanel()
    {
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(Const.HARD, 1, 0, 10)); //hard is number of options
        this.setVisible(true);
        this.setPreferredSize(new Dimension(Const.row * Const.iconSize, Const.col * Const.iconSize));
    }
    public JPanel getPanel()
    {
        return this;
    }
    public void close()
    {
        this.setVisible(false);
    }
    public void addComp(Component b)
    {
        this.add(b);
    }

    @Override
    public void showPanel() {
        this.setVisible(true);
    }
}

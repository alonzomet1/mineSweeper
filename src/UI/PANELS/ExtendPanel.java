package UI.PANELS;

import CONST.Const;

import javax.swing.*;
import java.awt.*;

public class ExtendPanel extends JPanel implements BasicPanel {
    public ExtendPanel()
    {
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(Const.row * Const.iconSize, Const.ExtendedPanelHeight));
        this.setVisible(true);
        this.setLayout(new BorderLayout());
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    public void close() {
        this.setVisible(false);
    }

    @Override
    public void addComp(Component b) {
        this.add(b);
    }

    @Override
    public void showPanel() {
        this.setVisible(true);
    }
}

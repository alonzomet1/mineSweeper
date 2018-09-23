package UI;
import java.awt.*;
import javax.swing.*;

import CONST.Const;
public class Screen extends JFrame {

    public Screen(String title) {
        super(title);
        this.setPreferredSize(new Dimension(Const.row * Const.iconSize, Const.col * Const.iconSize + Const.iconSize + Const.buttonGap));
    }
    public void packAndShow()
    {
        //The pack method sizes the frame so that all its contents are at or above their preferred sizes
        this.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(dim.width / 3 - this.getSize().width/2, dim.height / 3 - this.getSize().height / 2);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void changeSize(int width, int height)
    {
        this.setSize(new Dimension(width, height));
    }
    //for testing
    public void addComp(Component b)
    {
        this.add(b);
    }
    public void addCompo(Component b)
    {
        this.add(b, BorderLayout.NORTH);
    }
    public JFrame getScreen()
    {
        return this;
    }
    public void adjustScreenLayout()
    {
        this.getContentPane().setLayout(new BorderLayout());
    }

}

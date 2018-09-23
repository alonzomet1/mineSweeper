package UI.PANELS;

import javax.swing.*;
import java.awt.*;

public interface BasicPanel {
    public JPanel getPanel();
    public void close();
    public void addComp(Component b);
    public void showPanel();
}


package view;

import javax.swing.*;

import java.util.Objects;

public class HomePanel extends JPanel {
    public HomePanel(){
        ImageIcon backgroundIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/background.png")));
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundIcon);

        add(backgroundLabel);
    }
}

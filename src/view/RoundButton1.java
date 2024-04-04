package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;

class RoundButton1 extends JButton {
	
	private static final long serialVersionUID = 1L;

	public RoundButton1(String label) {
        super(label);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setPreferredSize(new Dimension(30, 30));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }
    
    
}
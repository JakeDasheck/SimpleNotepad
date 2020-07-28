package SimpleNotepad;

import javax.swing.*;
import java.awt.*;

public class CircleIcon implements Icon {
	private Color color;
	private int w = 20;

	CircleIcon(Color c) {
		color = c;

	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(color);
		w = ((JComponent) c).getHeight();
		int diameter = w / 2;
		g.fillOval(x + w / 2, y + w / 4 , diameter, diameter);
	}

	@Override
	public int getIconWidth() {
		return w;
	}

	@Override
	public int getIconHeight() {
		return w;
	}

}

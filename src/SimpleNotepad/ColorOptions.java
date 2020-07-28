package SimpleNotepad;

import java.awt.Color;

public class ColorOptions {
	private Color color;
	private CircleIcon icon;

	public ColorOptions(Color color) {
		this.color = color;
		icon = new CircleIcon(color);
	}

	public Color getColor() {
		return color;
	}

	public CircleIcon getIcon() {
		return icon;
	}
}

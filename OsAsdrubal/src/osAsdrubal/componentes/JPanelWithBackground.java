package osAsdrubal.componentes;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

public class JPanelWithBackground extends JPanel {
	
	private static final long serialVersionUID = 1L;
	Image imageOrg = null;
	Image image = null;
	{
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				int w = JPanelWithBackground.this.getWidth();
				int h = JPanelWithBackground.this.getHeight();
				image = w > 0 && h > 0 ? imageOrg.getScaledInstance(w, h, java.awt.Image.SCALE_DEFAULT) : imageOrg;
				JPanelWithBackground.this.repaint();
			}
		});
	}

	public JPanelWithBackground(Image i) {
		imageOrg = i;
		image = i;
		setOpaque(false);
	}

	public void paint(Graphics g) {
		if (image != null)
			g.drawImage(image, 0, 0, null);
		super.paint(g);
	}
}

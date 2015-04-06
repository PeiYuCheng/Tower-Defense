package buttons;

import img.Images;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import controllers.ButtonSelector;
import towerModels.Tower;

public abstract class CustomButton extends JComponent implements Serializable {

	protected static final Color REGULAR_BUTTON_COLOR = new Color(0,0,0,0);
	protected static final Color HOVERED_BUTTON_COLOR = new Color(0,0,0,20);
	protected static final Color SELECTED_BUTTON_COLOR = new Color(254,140,255,255);
	protected int button_spacing;
	protected Point position;
	protected Dimension size;
	private boolean selected;
	protected boolean hovered;
	private MouseMaster mouse_master;
	protected ButtonSelector button_selector;
	protected Images images;

	public CustomButton(int posX, int posY, int width, int height) {
		super();
		position = new Point(posX, posY);
		size = new Dimension(width, height);
		setLocation(position);
		setPreferredSize(size);
		setOpaque(false);

		button_selector = ButtonSelector.getInstance();
		images = Images.getUniqueInstance();
		mouse_master = new MouseMaster();
		addMouseListener(mouse_master);

	}

	@Override
	protected void paintComponent(Graphics g) {
		setLocation(position);
		drawButtonBackground(g);
		drawButton(g);
		super.paintComponent(g);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return size;
	}

	public void drawButtonBackground(Graphics g) {

		g.setColor(REGULAR_BUTTON_COLOR);
		g.fillRect(0, 0, size.width-1, size.height-1);
		g.setColor(Color.white);
		g.drawRect(0, 0, size.width-1, size.height-1);

	}
	
	public void drawButton(Graphics g) {
		if (selected) {
			g.setColor(new Color(254,140,255,100));
			g.fillRect(0, 0, size.width, size.height);
		}
		else if (hovered) {
			g.setColor(new Color(0,0,0,50));
			g.fillRect(0, 0, size.width, size.height);
		}
	}

	public Tower getNewTower() {
		return null;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setHovered(boolean hovered) {
		this.hovered = hovered;
	}

	public boolean isSelected() {
		return selected;
	}

	public boolean isHovered() {
		return hovered;
	}

	protected abstract void OnClick();
	protected abstract void OnPress();
	protected abstract void OnRelease();

	protected void OnEnter() {
		hovered = true;
	}

	protected void OnExit() {
		hovered = false;
	}

	private class MouseMaster extends MouseAdapter implements Serializable {

		@Override
		public void mouseClicked(MouseEvent e) {
			OnClick();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			OnEnter();
			super.mouseEntered(e);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			OnExit();
			super.mouseExited(e);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			OnPress();
			super.mousePressed(e);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			OnRelease();
			super.mouseReleased(e);
		}
	}
}

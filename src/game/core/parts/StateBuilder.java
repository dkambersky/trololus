package game.core.parts;

import java.util.Arrays;

import game.core.states.BasicState;
import game.util.MoreColors;
import game.util.Util;

import org.newdawn.slick.Color;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.TextField;

/**
 * The StateBuilder.java class responsible for handling all the different
 * drawable elements in the game's states.
 * 
 * @author xCabbage [github.com/xcabbage]
 * 
 * @info for the Trololus project [github.com/xcabbage/trololus] created
 *       7.2.2013 15:50:28
 */
public class StateBuilder {

	// declare the container and the three arrays
	AppGameContainer gc;
	AbstractComponent[] components = new AbstractComponent[1];
	Label[] labels = new Label[1];
	ContentPane[] panes = new ContentPane[1];
	private int driftRequestedX;
	private int driftRequestedY;
	private AbstractComponent driftingComponent;

	// constructor
	public StateBuilder(AppGameContainer gc) {
		this.gc = gc;

	}

	// interface methods to create the new objects and queue their addition to
	// the arrays
	public void addTextField(int x, int y, int width, int height) {
		TextField field = new TextField(gc, Util.fontEntry, x, y, width, height);
		field.setConsumeEvents(true);
		addComponents(field);

	}

	public void driftComponentTo(int targetX, int targetY,
			AbstractComponent comp) {
		driftRequestedX = targetX;
		driftRequestedY = targetY;
		driftingComponent = comp;

	}

	public void driftComponentContinue(BasicState state) {

		int targetX = driftRequestedX;
		int targetY = driftRequestedY;

		int posX = driftingComponent.getX();
		int posY = driftingComponent.getY();

		int speed = 5;
		if ((targetX - posX) * (targetX - posX) + (targetY - posY)
				* (targetY - posY) > 0) {

			driftingComponent.setLocation(posX + ((targetX - posX) / speed),
					posY + ((targetY - posY) / speed));

			if ((targetX - posX) * (targetX - posX) + (targetY - posY)
					* (targetY - posY) < 100) {
				driftingComponent.setLocation(targetX, targetY);

				state.driftRequested = true;

			}
			posX = driftingComponent.getX();
			posY = driftingComponent.getY();

		}

	}

	public void driftComponentTo(int targetX, int targetY, Label comp) {

		int posX = (int) comp.getBounds().getX();
		int posY = (int) comp.getBounds().getY();
		@SuppressWarnings("unused")
		int speed = 4;
		while ((targetX - posX) * (targetX - posX) + (targetY - posY)
				* (targetY - posY) > 0) {

			// comp.setLocation(posX + ((targetX - posX) / speed), posY
			// + ((targetY - posY) / speed));

			if ((targetX - posX) * (targetX - posX) + (targetY - posY)
					* (targetY - posY) < 1.2) {
				posX = targetX;
				posY = targetY;

			}
			// posX = comp.getX();
			// posY = comp.getY();
		}
	}

	public void addButton(int x, int y, String image) throws SlickException {
		MouseOverAreaDav button = new MouseOverAreaDav(gc, new Image(image), x,
				y);
		addComponents(button);
	}

	public void addButton(int x, int y, Image image) throws SlickException {
		MouseOverAreaDav button = new MouseOverAreaDav(gc, image, x, y);
		addComponents(button);
	}

	public void addLabel(int type, int x, int y, String string)
			throws SlickException {
		Label label = new Label(type, x, y, string);
		addLabels(label);
	}

	public void addLabel(int type, float x, float y, String string)
			throws SlickException {
		Label label = new Label(type, x, y, string);
		addLabels(label);
	}

	public void addLabel(float x, float y, String string, float scale)
			throws SlickException {
		Label label = new Label(x, y, string, scale);
		addLabels(label);
	}

	public void addContentPane(int x, int y, int width, int height) {
		ContentPane pane = new ContentPane(gc, x, y, width, height);
		addPanes(pane);
	}

	public void addContentPane(float x, float y, int width, int height) {
		System.out.println("running ffii from SB");
		ContentPane pane = new ContentPane(gc, x, y, width, height);
		addPanes(pane);
	}

	public void addContentPane(float x, float y, float width, float height) {
		ContentPane pane = new ContentPane(gc, x, y, width, height);
		addPanes(pane);
	}

	public void addContentPane(float x, float y, float width, float height,
			Rectangle area) {
		ContentPane pane = new ContentPane(gc, x, y, width, height);
		addPanes(pane);
	}

	public void addContentPane(float x, float y, int xOffset, int yOffset,
			int width, int height) {
		ContentPane pane = new ContentPane(gc, x, y, xOffset, yOffset, width,
				height);
		addPanes(pane);
	}

	// internal methods extending the arrays
	void addComponents(AbstractComponent... component) {
		int lengthBeginning = components.length;

		// if components array is full, extend the array
		if (components[components.length - 1] != null) {
			components = Arrays.copyOf(components, lengthBeginning
					+ component.length);
		}

		// add the requested components to the array
		for (int a = 0; a < component.length; a++) {

			if (components[0] == null) {
				components[lengthBeginning + a - 1] = component[a];
			} else {
				components[lengthBeginning + a] = component[a];
			}

		}

	}

	void addPanes(ContentPane... pane) {
		int lengthBeginning = panes.length;

		// if panes array is full, extend the array
		if (panes[panes.length - 1] != null) {
			panes = Arrays.copyOf(panes, lengthBeginning + pane.length);
		}

		// add the requested panes to the array
		for (int a = 0; a < pane.length; a++) {

			if (panes[0] == null) {
				panes[lengthBeginning + a - 1] = pane[a];
			} else {
				panes[lengthBeginning + a] = pane[a];
			}

		}

	}

	void addLabels(Label... label) {
		int lengthBeginning = labels.length;

		// if labels array is full, extend the array
		if (labels[labels.length - 1] != null) {
			labels = Arrays.copyOf(labels, lengthBeginning + label.length);
		}

		// add the requested labels to the array
		for (int a = 0; a < label.length; a++) {

			if (labels[0] == null) {
				labels[lengthBeginning + a - 1] = label[a];
			} else {
				labels[lengthBeginning + a] = label[a];
			}

		}

	}

	// interface methods returning any of the stored variables
	public AbstractComponent getComponent(int a) {
		if (a == -1)
			return components[components.length - 1];
		else
			return components[a];
	}

	public Label getLabel(int a) {
		if (a < 0)
			return labels[labels.length + a];

		else
			return labels[a];
	}

	public ContentPane getPane(int a) {
		if (a == -1)
			return panes[panes.length - 1];
		else
			return panes[a];
	}

	// drawing of the current situation
	public void drawComponents(Graphics g) throws SlickException {

		if (panes[0] != null)
			for (int a = 0; a < panes.length; a++) {
				panes[a].render();

			}
		if (components[0] != null)
			for (int a = 0; a < components.length; a++) {
				components[a].render(gc, g);

			}

		if (labels[0] != null)
			for (int a = 0; a < labels.length; a++) {
				Color col = g.getColor();
				g.setColor(MoreColors.getTrans(labels[a].getColor(),
						labels[a].getAlpha()));
				labels[a].render();
				g.setColor(col);
			}

	}

	public void rescale() {
		if (panes[0] != null)
			for (int a = 0; a < panes.length; a++) {
				panes[a].rescale();

			}
		if (components[0] != null)
			for (int a = 0; a < components.length; a++) {
				// components[a].rescale();

			}

		if (labels[0] != null)
			for (int a = 0; a < labels.length; a++) {
				labels[a].rescale();

			}

	}

	/**
	 * @param i
	 * @param j
	 * @param width
	 * @param height
	 */

}

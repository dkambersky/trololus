package game.core.parts;

import game.core.Trololus;
import game.util.MoreColors;
import game.util.Util;

import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;

/**
 * The Label.java class representing a single label (text entry or image) in the
 * game.
 * 
 * @author xCabbage [github.com/xcabbage]
 * 
 * @info for the Trololus project [github.com/xcabbage/trololus] created
 *       18.2.2013 15:41:29
 */
public class Label {
	int x, y;
	String string;
	TrueTypeFont font;
	Rectangle gui;
	Image img;
	int type;

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @return the font
	 */
	public TrueTypeFont getFont() {
		return font;
	}

	/**
	 * @param font
	 *            the font to set
	 */
	public void setFont(TrueTypeFont font) {
		this.font = font;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	Color color;

	public Rectangle containingElement;
	private int currW;
	private int scaling = 0;
	private float scalingX;
	private float scalingY;
	private boolean inited = false;
	private ContentPosition position;
	private boolean centering;
	private boolean isVisible = true;
	private int xOffset;
	private int yOffset;
	private Rectangle backgroundRect;
	private Color backgroundBorder;
	private Color backgroundFill;
	private boolean backgroundEnabled;
	private Image backgroundImage;
	private boolean backgroundImgEnabled;
	private int backgroundOffsetY = 20;
	private int backgroundOffsetX = 20;
	private Rectangle container;

	/**
	 * @param x
	 * @param y
	 * @param string
	 * @param font
	 */

	public void fadeIn(long timeMillis) {
		final Timer time = new Timer();

		time.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				if (getAlpha() <= 0.97f) {
					setAlpha(getAlpha() + 0.02f);
				} else
					time.cancel();

			}
		}, 0, timeMillis / 50);

	}

	public void fadeOut(long timeMillis) {
		final Timer time = new Timer();

		time.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				if (getAlpha() != 0f) {
					setAlpha(getAlpha() - 0.02f);
				} else
					time.cancel();
			}

		}, 0, timeMillis / 50);

	}

	public void setVisible(boolean visible) {
		isVisible = visible;
	}

	public void toggleVisibility() {
		isVisible = !isVisible;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setContainer(Rectangle rect) {
		containingElement = rect;
	}

	public void setPosition(ContentPosition pos) {
		position = pos;
		scaling = 3;
		rescale();
	}

	/**
	 * @param pos
	 *            the ContentPosition of the component (search the enum and/or
	 *            source/jdoc for values)
	 * @param x
	 *            the x offset of the component from the ContentPosition default
	 * @param y
	 *            the Y offset of the component from the ContentPosition default
	 */
	public void setPosition(ContentPosition pos, int x, int y) {
		position = pos;
		scaling = 3;
		xOffset = x;
		yOffset = y;
		rescale();

	}

	public void setBackground(Rectangle rect, Color fill, Color border) {
		backgroundRect = rect;
		backgroundFill = fill;
		backgroundBorder = border;
		backgroundEnabled = true;
	}

	public void setBackground(Color fill, Color border) {
		backgroundRect = getBounds();
		backgroundFill = fill;
		backgroundBorder = border;
		backgroundEnabled = true;
	}

	public void setBackground(String path) throws SlickException {
		backgroundRect = getBounds();
		backgroundImage = new Image(path);
		backgroundImage = backgroundImage.getScaledCopy((int) getBounds()
				.getWidth() + backgroundOffsetX, (int) getBounds().getHeight()
				+ backgroundOffsetY);
		backgroundImgEnabled = true;

	}

	public Rectangle getBounds() {
		int width = 0, height = 0;

		switch (type) {
		case 1:
			width = font.getWidth(string);
			height = font.getHeight();
			break;
		case 2:
			width = img.getWidth();
			height = img.getHeight();
			break;
		}

		return new Rectangle(x, y, width, height);
	}

	// CONSTRUCTORS
	public Label(int x, int y, String string, TrueTypeFont font) {
		super();
		this.x = x;
		this.y = y;
		this.string = string;
		this.font = font;
		this.type = 1;
		commonInit();

	}

	public Label(int type, int x, int y, String content) throws SlickException {
		super();
		this.x = x;
		this.y = y;
		this.type = type;

		if (type == 2) {
			this.img = new Image(content);
		} else if (type == 1) {
			this.string = content;
			this.font = new TrueTypeFont(new Font("Garamond", 10, 25), true);
		} else
			Util.print("Wrong Label type initialized: Type " + type
					+ "; Content: " + content);
		commonInit();

	}

	public Label(float x, float y, String content, float scale)
			throws SlickException {
		super();

		scaling = 1;
		scalingX = x;
		scalingY = y;
		type = 2;
		this.img = new Image(content);
		img = img.getScaledCopy(scale);
		commonInit();

	}

	public Label(int type, float x, float y, String content)
			throws SlickException {
		super();

		scaling = 1;

		scalingX = x;
		scalingY = y;

		this.type = type;

		if (type == 2) {
			this.img = new Image(content);

		} else if (type == 1) {
			this.string = content;
			this.font = new TrueTypeFont(new Font("Garamond", 10, 25), true);
		} else
			Util.print("Wrong Label type initialized: Type " + type
					+ "; Content: " + content);
		commonInit();
	}

	public Label(int type, float x, float y, String content, Rectangle rect)
			throws SlickException {
		super();
		container = rect;
		scaling = 1;

		scalingX = x;
		scalingY = y;

		this.type = type;

		if (type == 2) {
			this.img = new Image(content);

		} else if (type == 1) {
			this.string = content;
			this.font = new TrueTypeFont(new Font("Garamond", 10, 25), true);
		} else
			Util.print("Wrong Label type initialized: Type " + type
					+ "; Content: " + content);
		commonInit();
	}

	private void commonInit() {
		containingElement = new Rectangle(0, 0, Trololus.app.getWidth(),
				Trololus.app.getHeight());
		currW = (int) containingElement.getWidth();
		color = new Color(Color.white);

		rescale();
	}

	// SCALING METHODS
	public void scaleToWidth(float arg) {
		int newWidth = (int) (arg * containingElement.getWidth());
		img = img.getScaledCopy(newWidth,
				((img.getWidth() * img.getHeight()) / newWidth));
		rescale();
	}

	public void scaleToHeight(float arg) {
		int newHeight = (int) (arg * containingElement.getHeight());
		img = img.getScaledCopy((newHeight * img.getWidth()) / img.getHeight(),
				newHeight);
		rescale();

	}

	public void scaleToWidth(int arg) {

		img = img
				.getScaledCopy(arg, ((img.getWidth() * img.getHeight()) / arg));
		rescale();
	}

	public void scaleToHeight(int arg) {

		img = img.getScaledCopy((arg * img.getWidth()) / img.getHeight(), arg);
		rescale();

	}

	public void rescale() {

		int containerW, containerH;

		if (container != null) {
			float scaleForContainer = (float) Trololus.app.getWidth()
					/ (float) currW;

			container = new Rectangle(container.getMinX() * scaleForContainer,
					container.getMinY() * scaleForContainer,
					container.getWidth() * scaleForContainer,
					container.getHeight() * scaleForContainer);

			containerW = (int) container.getWidth();
			containerH = (int) container.getHeight();
		} else {
			containerW = Trololus.app.getWidth();
			containerH = Trololus.app.getHeight();
		}

		if (type == 2) {

			img = img.getScaledCopy((float) Trololus.app.getWidth()
					/ (float) currW);

		}

		switch (scaling) {
		case 0:
			break;
		case 1: {
			x = (int) (containerW * scalingX);
			y = (int) (containerH * scalingY);
			break;
		}
		case 2: {
			int scale = x;
			x = (int) (containerW * scalingX);
			y = (int) (containerH * scalingY);

			if (inited)
				img.getScaledCopy(scale / x);
			else
				inited = true;
			break;
		}
		case 3: {
			// initialize starting values
			int contentW = 0, contentH = 0;
			if (type == 1) {
				contentW = font.getWidth(string);
				contentH = font.getHeight();
			} else if (type == 2) {
				contentH = img.getHeight();
				contentW = img.getWidth();
			} else
				Util.print("An error occured in a label : type not properly initialized");

			// set the actual new X's and Y's
			switch (position) {
			case TopLeft:
				x = 0;
				y = 0;
				break;
			case TopCenter:
				x = containerW / 2 - contentW / 2;
				y = 0;
				break;
			case TopRight:
				x = containerW - contentW;
				y = 0;
				break;
			case CenterLeft:
				x = 0;
				y = (containerH / 2) - (contentH / 2);
				break;
			case Center:
				x = (containerW / 2) - (contentW / 2);
				y = (containerH / 2) - (contentH / 2);
				break;
			case CenterRight:
				x = containerW - contentW;
				y = (containerH / 2) - (contentH / 2);
				break;
			case BottomLeft:
				x = 0;
				y = containerH - contentH;
				break;
			case BottomCenter:
				x = containerW / 2 - contentW / 2;
				y = containerH - contentH;
				break;
			case BottomRight:
				x = containerW - contentW;
				y = containerH - contentH;
				break;
			default:
				Util.print("Label Position fucked up - unknown value passed to the method");
				break;

			}

			break;
		}

		default: {
			Util.print("Label incorrectly initialized - scaling has fucked up");
			break;
		}

		}
		if (container != null) {
			x = (int) (x + container.getMinX());
			y = (int) (y + container.getMinY());
		}
		x = x + xOffset;
		y = y + yOffset;
		currW = Trololus.app.getWidth();
	}

	public void setScale(float scale) {
		img = img.getScaledCopy(scale);
	}

	// ALPHA AND RENDERING METHODS
	public void setAlpha(float alpha) {
		color = MoreColors.getTrans(color, alpha);
	}

	public float getAlpha() {
		return color.a;
	}

	public void render() {
		if (isVisible) {

			if (backgroundEnabled) {
				Graphics g = Trololus.app.getGraphics();

				g.setColor(MoreColors.getTrans(backgroundBorder,
						color.getAlpha()));
				g.draw(backgroundRect);
				g.setColor(MoreColors.getTrans(backgroundFill, color.getAlpha()));
				g.fill(backgroundRect);
			}

			if (backgroundImgEnabled) {
				backgroundImage.setAlpha(getAlpha());

				backgroundImage.draw(x - backgroundOffsetX / 2, y
						- backgroundOffsetY / 2);
			}

			if (!centering) {
				switch (type) {
				case 1: {
					font.drawString(x, y, string, color);
					break;
				}
				case 2: {
					img.draw(x, y);
					break;
				}
				default:
					Util.print("Label " + string
							+ "can't be drawn; Wrong label type.");
					break;
				}
			} else {
				switch (type) {
				case 1: {
					font.drawString(x - font.getWidth(string),
							y - font.getHeight(), string, color);
					break;
				}
				case 2: {
					img.draw(x - (img.getWidth() / 2), y
							- (img.getHeight() / 2));
					break;
				}
				default:
					Util.print("Label " + string
							+ "can't be drawn; Wrong label type.");
					break;
				}

			}

		}
	}

	public void render(Rectangle rect) {
		if (isVisible) {

			if (backgroundEnabled) {
				Graphics g = Trololus.app.getGraphics();

				g.setColor(MoreColors.getTrans(backgroundBorder,
						color.getAlpha()));
				g.draw(backgroundRect);
				g.setColor(MoreColors.getTrans(backgroundFill, color.getAlpha()));
				g.fill(backgroundRect);
			}

			if (backgroundImgEnabled) {
				backgroundImage.setAlpha(getAlpha());

				backgroundImage.draw(
						(x - backgroundOffsetX / 2) + rect.getMinX(),
						rect.getMinY() + y - backgroundOffsetY / 2);
			}

			if (!centering) {
				switch (type) {
				case 1: {
					font.drawString(x, y, string, color);
					break;
				}
				case 2: {
					img.draw(x + rect.getMinX(), y + rect.getMinY());
					break;
				}
				default:
					Util.print("Label " + string
							+ "can't be drawn; Wrong label type.");
					break;
				}
			} else {
				switch (type) {
				case 1: {
					font.drawString(x - font.getWidth(string) + rect.getMinX(),
							y - font.getHeight() + rect.getMinY(), string,
							color);
					break;
				}
				case 2: {
					img.draw(rect.getMinX() + x - (img.getWidth() / 2),
							rect.getMinY() + y - (img.getHeight() / 2));
					break;
				}
				default:
					Util.print("Label " + string
							+ "can't be drawn; Wrong label type.");
					break;
				}

			}

		}
	}

	/**
	 * @param b
	 */
	public void setCentering(boolean b) {
		centering = b;

	}

	@Override
	public String toString() {
		return "Label [x=" + x + ", y=" + y + ", string=" + string + ", type="
				+ type + ", container=" + container + ", scaling=" + scaling
				+ ", scalingX=" + scalingX + ", scalingY=" + scalingY
				+ ", xOffset=" + xOffset + ", yOffset=" + yOffset + "]";
	}

}
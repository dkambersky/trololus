package game.core.states;

import game.core.parts.Clouds;
import game.core.parts.ContentPosition;
import game.core.parts.GameComponent;
import game.core.parts.Image;
import game.core.parts.Timer;
import game.core.states.gamestate.Shop;
import game.util.Util;

import org.lwjgl.input.Controllers;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicState {
	public GameComponent a;
	public static int ID = 6;

	@Override
	public void createContent(GameContainer gc, StateBasedGame game)
			throws SlickException {
		// create independent content
		sb.addLabel(1, 0.5f, 0.5f, "playtest screen");
		sb.getLabel(-1).setFont(Util.fontGreatHeader);
		sb.getLabel(-1).setPosition(ContentPosition.TopCenter, 0, 50);

		// down here is nina's
		components.add(new Shop(this, gc, gc.getGraphics()));
		components.add(new Clouds( this, gc, gc.getGraphics()));
		
		
		// down here is david's
		// components test
		components.add(new Timer(this, gc, gc.getGraphics()));

	}

	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		base_UI_disabled = true;
		super.init(gc, game);
		StateTitle = "playtest game";
		input.initControllers();
		Util.print("Number of controllers: " + input.getControllerCount());
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame mainGame, int delta)
			throws SlickException {
		super.update(gc, mainGame, delta);

		if (Util.debugAxisSoutEnabled) {
			try {
				System.out
						.println(Controllers.getController(3).getAxisValue(0));
			} catch (Exception e) {
				System.out
						.println("An error occurred printing out the axis value");
			}
		}

	}

	@Override
	public void render(GameContainer gc, StateBasedGame mainGame, Graphics g)
			throws SlickException {

		renderDiffGfx(gc, mainGame, g, stateRes);

	}

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);

		switch (key) {
		case Input.KEY_2:
			System.out.println(components.get(0));
			break;

		default:
			System.out.println("Key Pressed in debug");

		}

	}
}
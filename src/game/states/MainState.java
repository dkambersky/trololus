// Class created by xCabbage [github.com/xcabbage] 
// for the trololus project [github.com/xcabbage/trololus]

/**
 * 
 */
package game.states;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



/**
 * @author David
 * 
 */
public class MainState extends BasicGameState {
	public static final int ID = 1;

	private StateBasedGame game;

	public int getID() {
		return ID;
	}

	String menu1, menu2, menu3;
	String S_ingame, S_loading, S_title, S_postgame;
	public Image background;

	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		background = new Image("resources/Splash/UI/menu.png");
		this.game = game;

		// initialize strings
		menu1 = "MENU1";
		S_ingame = "INGAME";
		S_loading = "The Game is loading! woooo";
		S_title = "TROLOLUS NIGHTLY BUILD 0,0";
		S_postgame = "The after game lobby.";
	}

	public void update(GameContainer gc, StateBasedGame mainGame, int delta)throws SlickException {
		Input input = gc.getInput();
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
//		if (mouseX)

	}

	public void render(GameContainer gc, StateBasedGame mainGame, Graphics g)
			throws SlickException {
		background.draw(0, 0, (float) 0.5);
		g.drawString(S_title, 100, 100);
		

	}
}
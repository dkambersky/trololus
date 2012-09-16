// Class created by xCabbage [github.com/xcabbage] 
// for the trololus project [github.com/xcabbage/trololus]

/**
 * 
 */
package game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author David
 * 
 */
public class MenuState extends BasicGameState {
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
		background = new Image("/resources/Splash/UI/menu.png");
		this.game = game;

		// initialize strings
		menu1 = "MENU1";
		S_ingame = "INGAME";
		S_loading = "The Game is loading! woooo";
		S_title = "TROLOLUS NIGHTLY BUILD 0,0";
		S_postgame = "The after game lobby.";
	}

	public void update(GameContainer gc, StateBasedGame mainGame, int delta)
			throws SlickException {

	}

	public void render(GameContainer gc, StateBasedGame mainGame, Graphics g)
			throws SlickException {
//		background.draw(50, 0, (float) 0.5);
		g.drawString("MenuPepa",100,100);
		;

	}
}

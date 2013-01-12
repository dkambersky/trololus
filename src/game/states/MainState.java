// Class created by xCabbage [github.com/xcabbage] 
// for the trololus project [github.com/xcabbage/trololus]

/**
 * 
 */
package game.states;

import game.core.Trololus;

import java.awt.Dimension;
import java.nio.file.Path;

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
public class MainState extends BasicState {

	public static int ID = 0;
	float glowF;
	int glow;

	public int getID() {
		return ID;
	}

	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		super.init(gc, game);
		background = new Image("/resources/Splash/UI/menu2.png");
	}

	public void update(GameContainer gc, StateBasedGame mainGame, int delta)
			throws SlickException {
		
		Input input = gc.getInput();
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();
//		glow = isOverButton(mouseX,mouseY);
		if (input.isKeyDown(46)) {
			System.out.println("Entering Menu state. [source: Main]");
			game.enterState(1);
		}
		if (input.isKeyDown(1)) {
			System.out.println("Shutting Down.. [command: Main]");
			System.exit(0);
		}
	}

	public void render(GameContainer gc, StateBasedGame mainGame, Graphics g)
			throws SlickException {
		
		if (Trololus.drawing) {
			background.draw(0, backgroundY, backgroundScale);
			
//			glowF = (float) (glowF + .02);
//			glow = (int) glowF;
//			if (glowF > 7)
//				glowF = 0;
			drawMenu(11, g);
			g.drawString(S_title, 320, 20);
		} else {
			;
		}

	}
}

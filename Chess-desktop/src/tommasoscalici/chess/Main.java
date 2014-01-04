
package tommasoscalici.chess;

import tommasoscalici.chess.ChessGame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.*;

public class Main {
	
	public static void main(String[] args) {
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		
		cfg.title = "Chess";
		cfg.useGL20 = true;
		cfg.width = 1280;
		cfg.height = 720;
		
		new LwjglApplication(new ChessGame(), cfg);
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}
}
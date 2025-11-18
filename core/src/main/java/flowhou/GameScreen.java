package flowhou;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

    private final Flowhou game;
    private final Game rootGame; // tu clase Game (Node2D)

    public GameScreen(Flowhou game) {
        this.game = game;

        // Obtener instancia del juego real
        rootGame = Game.getInstance();
    }

    @Override
    public void render(float delta) {
        // Limpia pantalla
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Dibujar tu sistema Node2D
        game.gameBatch.begin();
        rootGame.update(delta);
        rootGame.draw(game.gameBatch);
        game.gameBatch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override public void show() {}
    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}

    @Override
    public void dispose() {
        // NO destruir instancia aquí
        // Porque Game usa Singleton
    }
}
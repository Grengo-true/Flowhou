package flowhou;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Clase principal del juego Flowhou.
 * Ahora extiende Game para soportar pantallas (Screen).
 */
public class Flowhou extends Game {

    public SpriteBatch gameBatch;
    public SpriteBatch uiBatch;
    public BitmapFont font;

    @Override
    public void create() {

        // Batches compartidos para todas las pantallas
        gameBatch = new SpriteBatch();
        uiBatch = new SpriteBatch();

        font = new BitmapFont();

        // Mostrar el menú principal al iniciar
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        // Se asegura de llamar al render() de la pantalla activa
        super.render();
    }

    @Override
    public void dispose() {
        // Disponer recursos compartidos
        if (gameBatch != null) gameBatch.dispose();
        if (uiBatch != null) uiBatch.dispose();
        if (font != null) font.dispose();

        // También dispose de la pantalla actual
        if (getScreen() != null) getScreen().dispose();
    }
}
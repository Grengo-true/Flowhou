package flowhou;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class MainMenuScreen implements Screen {

    private final Flowhou game;   // referencia al ApplicationAdapter principal
    private Stage stage;
    private Skin skin;
    private OrthographicCamera camera;

    // Elementos de UI
    private TextButton playButton;
    private TextButton exitButton;
    private Label title;

    public MainMenuScreen(Flowhou game) {
        this.game = game;

        // Cámara simple para UI
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Parámetros UI básicos sin depender de assets externos
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        title = new Label("FLOWHOU", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        title.setFontScale(2.5f);

        playButton = new TextButton("Jugar", skin);
        exitButton = new TextButton("Salir", skin);

        // Acción botón JUGAR
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                // Cargar gameplay REAL
            	game.setScreen(new GameScreen(game));
            }
        });

        // Acción botón SALIR
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.add(title).padBottom(40f).row();
        table.add(playButton).width(200).padBottom(20f).row();
        table.add(exitButton).width(200).row();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void show() { }
    @Override public void hide() { }
    @Override public void pause() { }
    @Override public void resume() { }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}

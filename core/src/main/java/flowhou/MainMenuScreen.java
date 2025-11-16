package flowhou;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music; // Importar Music
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture; // Importar Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable; // Importar Drawable
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class MainMenuScreen implements Screen {

    private final Flowhou game;
    private Stage stage;
    private Skin skin;
    private OrthographicCamera camera;

    // --- Nuevos Assets ---
    private Music menuMusic;
    private Texture backgroundTexture;
    // ---------------------

    // Elementos de UI
    private TextButton playButton;
    private TextButton exitButton;
    private Label title;

    public MainMenuScreen(Flowhou game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // --- Cargar Assets ---
        // Asegúrate de que estos archivos existan en tu carpeta 'assets'
        try {
             menuMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/menu_music.mp3"));
             backgroundTexture = new Texture(Gdx.files.internal("backgrounds/MenuBackgrund.png"));
        } catch (Exception e) {
             // Manejo básico de error si no encuentra los archivos
             Gdx.app.error("MainMenuScreen", "No se pudieron cargar los assets", e);
             // Podrías cargar assets por defecto o simplemente salir
        }
       
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // --- Asignar Fondo ---
        if (backgroundTexture != null) {
            table.setBackground(new TextureRegionDrawable(backgroundTexture));
        }
        // ---------------------

        title = new Label("FLOWHOU", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        title.setFontScale(2.5f);

        playButton = new TextButton("Jugar", skin);
        exitButton = new TextButton("Salir", skin);

        // Acción botón JUGAR
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
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
        // Limpia la pantalla (el fondo de la tabla se dibujará solo)
        Gdx.gl.glClearColor(0, 0, 0.1f, 1); // Este color solo se verá si el fondo no carga
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        camera.setToOrtho(false, width, height); // Buena práctica actualizar la cámara también
    }

    @Override 
    public void show() { 
        if (menuMusic != null) {
            menuMusic.setLooping(true);
            menuMusic.play();
        }
    }

    @Override 
    public void hide() { 
        if (menuMusic != null) {
            menuMusic.pause();
        }
    }

    @Override public void pause() { }
    @Override public void resume() { }

    @Override
    public void dispose() {
        // Libera todos los recursos
        stage.dispose();
        skin.dispose();
        if (menuMusic != null) {
            menuMusic.dispose();
        }
        if (backgroundTexture != null) {
            backgroundTexture.dispose();
        }
    }
}
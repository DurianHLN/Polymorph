package durianhln.polymorph.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import durianhln.polymorph.Polymorph;
import durianhln.polymorph.util.Dimension;

/**
 *
 * @author Jason
 *
 */


public class DeathScreen implements Screen {
    private Polymorph polymorph;
    private Dimension screenSize; //TODO remove this
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private TextureAtlas textureAtlas; //TODO repack all raw and remove this
    private Music DeathScreenMusic;//TODO find death music
    private TextureRegion background;//TODO make a unique background
    private Stage stage;
    private int score;

    public DeathScreen(Polymorph polymorph,int playerscore) {
        AssetManager assetManager = polymorph.getAssetManager();
        TextureAtlas textureAtlas = assetManager.get(Polymorph.MASTER_PATH, TextureAtlas.class);
        this.polymorph = polymorph;
        score=playerscore;

        DeathScreenMusic = assetManager.get(Polymorph.MAIN_MENU_MUSIC_PATH);
        DeathScreenMusic.setLooping(true);

        background = textureAtlas.findRegion("background"); //TODO make a unique background for the death screen
        screenSize = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenSize.width, screenSize.height); //change this
        batch=new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        stage = new Stage();
        stage.clear();
        font = new BitmapFont(false);

        textureAtlas = assetManager.get(Polymorph.MASTER_PATH, TextureAtlas.class);
        initButtons(score,textureAtlas);
        Gdx.input.setInputProcessor(stage);

    }

    public void initButtons(int score,TextureAtlas buttonAtlas) {
        Skin buttonSkin = new Skin();
        buttonSkin.addRegions(buttonAtlas);

        ImageButton playButton = new ImageButton(buttonSkin.getDrawable("playbutton"),
        		                                                        buttonSkin.getDrawable("playbutton"));
        playButton.setSize(screenSize.width/3, screenSize.height/6);
        playButton.setPosition(screenSize.width/2-playButton.getWidth()/2,
        		               screenSize.height/2-playButton.getHeight()/2);
        playButton.addListener(new InputListener() {

        	@Override
        	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        		polymorph.setScreen(new GameScreen(polymorph));
        		DeathScreenMusic.stop();
        		return true;
        	}
        });

        stage.addActor(playButton);

    }

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, screenSize.width, screenSize.height);
        font.draw(stage.getBatch(), String.format("Score: %d\n", score), screenSize.width/3, screenSize.height-screenSize.height/3);
        stage.getBatch().end();
        stage.draw();
    }

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}



}

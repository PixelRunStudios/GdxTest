package com.github.pixelrunstudios.GdxTest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GdxTest extends ApplicationAdapter {

	protected BitmapFont font;
	protected SpriteBatch batch;
	private Texture img;
	protected PlatformIndependent platform;
	protected OrthographicCamera camera;
	protected ShapeRenderer renderer;

	protected int width;
	protected int height;

	protected GdxTest(){

	}

	public GdxTest(PlatformIndependent platform){
		this.platform = platform;
	}

	@Override
	public void create () {
		//Creates the SpriteBatch
		batch = new SpriteBatch();
		//Creates the ShapeRenderer
		renderer = new ShapeRenderer();

		//Loads the image
		img = new Texture(Gdx.files.internal("cat.jpg"));
		//Loads the font
		font = new BitmapFont(Gdx.files.internal("font/helvetica-neue-20.fnt"));

		//Calculates the screen width and height
		width = platform.getFrameWidth();
		height = platform.getFrameHeight();
		//Creates a camera with width and height equal to screen size
		camera = new OrthographicCamera(width, height);
		//Moves the camera to (0, 0)
		camera.translate(width/2, height/2);
	}

	@Override
	public void render () {
		//Time passed since last frame
		final float delta = Gdx.graphics.getDeltaTime();

		//Speed of screen movement in pixels per second
		float speed = 100;

		//Translates the screen when an arrow key is pressed
		if(Gdx.input.isKeyPressed(Keys.UP)){
			camera.translate(0, speed * delta);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			camera.translate(0, -speed * delta);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			camera.translate(speed * delta, 0);
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			camera.translate(-speed * delta, 0);
		}

		//Updates the camera
		camera.update();

		//Updates the renderer
		renderer.setProjectionMatrix(camera.combined);
		//Updates the batch
		batch.setProjectionMatrix(camera.combined);

		//Clears the background to blue
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Begin drawing sprites
		batch.begin();
		//Draw the image
		batch.draw(img, 0, 0);
		//Draw the string
		font.setColor(Color.BLACK);
		font.draw(batch, "Hello, world!", 100, 100);
		//End drawing sprites
		batch.end();

		//Begin drawing shapes
		renderer.begin(ShapeType.Line);
		//Draws the shape
		renderer.setColor(Color.RED);
		renderer.rect(50, 50, 100, 100);
		//End drawing shapes
		renderer.end();
	}
}

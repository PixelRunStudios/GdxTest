package com.github.pixelrunstudios.GdxTest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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

	protected GdxTest(){

	}

	public GdxTest(PlatformIndependent platform){
		this.platform = platform;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("cat.jpg"));
		font = new BitmapFont(Gdx.files.internal("font/helvetica-neue-20.fnt"));
		camera = new OrthographicCamera();
		renderer = new ShapeRenderer();
	}

	@Override
	public void render () {
		//Updates the camera
		camera.update();

		//Updates the renderer
		renderer.setProjectionMatrix(camera.combined);

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
		renderer.begin(ShapeType.Filled);
		//Draws the shape
		renderer.setColor(Color.RED);
		renderer.rect(50, 50, 100, 100);
		//End drawing shapes
		renderer.end();
	}
}

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
	protected SpriteBatch overlay;
	private Texture img;
	protected Platform platform;
	protected OrthographicCamera camera;
	protected ShapeRenderer renderer;

	protected int width;
	protected int height;

	private int catX = 0;
	private int catY = 0;
	private int snakePosX = 100;
	private int snakePosY = 100;

	private int snakeLength = 5;
	public static final int SNAKE_BLOCK_SIZE = 20;

	protected GdxTest(){

	}

	public GdxTest(Platform platform){
		this.platform = platform;
	}

	@Override
	public void create () {
		//Creates the SpriteBatch
		batch = new SpriteBatch();
		//Creates a static SpriteBatch
		overlay = new SpriteBatch();
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

	float currentUpdate = 0;
	float timeBetweenUpdate = 0.25f;

	@Override
	public void render () {

		//Time passed since last frame
		final float delta = Gdx.graphics.getDeltaTime();

		//Speed of screen movement in pixels per second
		float speed = 1000;

		//Translates the screen when an arrow key is pressed
		if(Gdx.input.isKeyPressed(Keys.UP)){
			//camera.translate(0, speed * delta);
			catY += speed*delta;
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			//camera.translate(0, -speed * delta);
			catY -= speed*delta;

		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			//camera.translate(speed * delta, 0);
			catX += speed*delta;

		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			//camera.translate(-speed * delta, 0);
			catX -= speed*delta;

		}

		currentUpdate += delta;
		if(currentUpdate > timeBetweenUpdate){
			currentUpdate = 0;
			update();
		}

		//Updates the camera
		camera.update();

		//Updates the renderer
		renderer.setProjectionMatrix(camera.combined);
		//Updates the batch
		batch.setProjectionMatrix(camera.combined);

		//Clears the background to blue
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Begin drawing shapes
		renderer.begin(ShapeType.Filled);
		//Draws the shape
		//renderer.setColor(Color.BLUE);
		//renderer.rect(catX-100, catY-100, img.getWidth()+2*100, img.getHeight()+2*100);
		renderer.setColor(Color.GRAY);
		for(int i = 0; i<snakeLength;i++){
			renderer.rect(snakePosX-i*SNAKE_BLOCK_SIZE+1,snakePosY+1,SNAKE_BLOCK_SIZE-2,SNAKE_BLOCK_SIZE-2);
		}
		//End drawing shapes
		renderer.end();

		//Begin drawing sprites
		//batch.begin();
		//Draw the image
		//batch.draw(img, catX, catY);
		//Draw the string
		//font.setColor(Color.BLACK);
		//font.draw(batch, "Hello, world!", 100, 100);
		//End drawing sprites
		//batch.end();

		/*
		//Begin drawing overlay
		overlay.begin();
		//Draw the string
		font.setColor(Color.BLACK);
		font.draw(overlay, String.format("x:%f, y:%f",
				camera.position.x, camera.position.y), 10,
				platform.getFrameHeight() - 10);
		//End drawing overlay
		overlay.end();

		//Begin drawing shapes
		renderer.begin(ShapeType.Line);
		//Draws the shape
		renderer.setColor(Color.RED);
		renderer.rect(50, 50, 100, 100);
		//End drawing shapes
		renderer.end();
		 */

	}

	public void update(){
		snakePosX +=20;
	}
}

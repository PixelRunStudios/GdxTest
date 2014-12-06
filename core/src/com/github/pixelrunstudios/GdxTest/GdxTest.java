package com.github.pixelrunstudios.GdxTest;

import java.util.LinkedList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class GdxTest extends ApplicationAdapter {

	protected BitmapFont font;
	protected SpriteBatch batch;
	protected SpriteBatch overlay;
	//private Texture img;
	protected Platform platform;
	protected OrthographicCamera camera;
	protected ShapeRenderer renderer;

	protected int width;
	protected int height;

	private int score;

	//private boolean executed;
	private boolean update;
	private boolean eaten;

	private boolean gameOver;

	private static final int INITIAL_SNAKE_LENGTH = 20;
	public static final int SBS = 20;

	BlockHolder blocks;

	//private int snakeLength;
	//NOTE: point at index 0 is the head
	LinkedList<Point> snake;
	LinkedList<String> directions;

	float currentUpdate;
	float timeBetweenUpdate;
	int counter;
	private String lastDir;
	boolean eat;

	Point food;

	protected GdxTest(){

	}

	public GdxTest(Platform platform){
		this.platform = platform;
	}

	public void reset(){
		//executed = false;
		gameOver = false;
		eaten = false;
		update = true;
		width = platform.getFrameWidth();
		height = platform.getFrameHeight();
		Point.maxX = width/SBS;
		Point.maxY = height/SBS;
		blocks = randomBlockHolder(Point.maxX, Point.maxY);
		snake = new LinkedList<Point>();
		directions = new LinkedList<String>();
		snake.clear();
		score = 0;
		for(int i = INITIAL_SNAKE_LENGTH-1; i>=0; i--){
			Point p = new Point(i+5,5);
			snake.add(p);
		}
		currentUpdate = 0;
		timeBetweenUpdate = 0.05f;
		counter = 0;
		lastDir = "RIGHT";
		eat = false;
		totalDelta = 0;
		//snakeLength = INITIAL_SNAKE_LENGTH;

	}

	private BlockHolder randomBlockHolder(int maxX, int maxY){
		int i = MathUtils.random(5);
		switch(i){
			case 0:
				return new BoxBlockHolder(maxX, maxY);
			case 1:
				return new CurveBlockHolder(maxX, maxY);
			case 2:
				return new CrossBlockHolder(maxX, maxY);
			case 3:
				return new BorderBlockHolder(maxX, maxY);
			case 4:
				return new MovingVerticalBlockHolder(maxX, maxY);
			case 5:
				return new EmptyBlockHolder();
			default:
				return null;
		}
	}

	@Override
	public void create () {
		reset();

		//Calculates the screen width and height

		//Creates the SpriteBatch
		batch = new SpriteBatch();
		//Creates a static SpriteBatch
		overlay = new SpriteBatch();
		//Creates the ShapeRenderer
		renderer = new ShapeRenderer();

		//Loads the image
		//img = new Texture(Gdx.files.internal("cat.jpg"));
		//Loads the font
		font = new BitmapFont(Gdx.files.internal("font/helvetica-neue-20.fnt"));

		//Creates a camera with width and height equal to screen size
		camera = new OrthographicCamera(width, height);
		//Moves the camera to (0, 0)
		camera.translate(width/2, height/2);
	}


	String next1;
	String next2;
	private float totalDelta;

	@Override
	public void render () {

		counter++;
		//Time passed since last frame
		final float delta = Gdx.graphics.getDeltaTime();
		if(!gameOver){
			totalDelta += delta;
		}

		//Speed of screen movement in pixels per second
		//float speed = 1000;

		//Translates the screen when an arrow key is pressed

		if(Gdx.input.isKeyJustPressed(Keys.UP)){
			if(next2 == null){
				next2 = "UP";
			}
			else if(next1 == null){
				next1 = "UP";
			}
			else{
				next2 = next1;
				next1 = "UP";
			}

		}
		else if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
			if(next2 == null){
				next2 = "DOWN";
			}
			else if(next1 == null){
				next1 = "DOWN";
			}
			else{
				next2 = next1;
				next1 = "DOWN";
			}
		}
		else if(Gdx.input.isKeyJustPressed(Keys.RIGHT)){
			if(next2 == null){
				next2 = "RIGHT";
			}
			else if(next1 == null){
				next1 = "RIGHT";
			}
			else{
				next2 = next1;
				next1 = "RIGHT";
			}
		}
		else if(Gdx.input.isKeyJustPressed(Keys.LEFT)){
			if(next2 == null){
				next2 = "LEFT";
			}
			else if(next1 == null){
				next1 = "LEFT";
			}
			else{
				next2 = next1;
				next1 = "LEFT";
			}
		}


		//executed = false;

		currentUpdate += delta;
		if(currentUpdate > timeBetweenUpdate){
			currentUpdate = 0;
			if(update){
				update();
			}
		}
		if(eaten || counter==1){
			eaten = false;
			outer: while(true){
				int foodX = MathUtils.random(width/SBS);
				int foodY = MathUtils.random(height/SBS);
				food = new Point(foodX,foodY);
				for(int i = 0; i < snake.size(); i++){
					if(!(food.getX() != snake.get(i).getX() && food.getY() != snake.get(i).getY())){
						continue outer;
					}
					if(!blocks.allowFood(food.getX(), food.getY())){
						continue outer;
					}
				}
				System.out.println(food);
				break;
			}
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




		renderer.begin(ShapeType.Filled);



		for(int i = 0; i<snake.size();i++){
			renderer.setColor(Color.GRAY);
			if(i == 0){
				renderer.setColor(Color.GREEN);
			}
			renderer.rect(snake.get(i).getX()*SBS+1,snake.get(i).getY()*SBS+1, SBS-2,SBS-2);

		}
		renderer.setColor(Color.BLUE);
		for(int i = 0; i < Point.maxX; i++){
			for(int j = 0; j < Point.maxY; j++){
				if(blocks.hasBlock(i, j, totalDelta)){
					renderer.rect(i*SBS+1,j*SBS+1, SBS-2,SBS-2);
				}
			}
		}
		renderer.setColor(Color.RED);
		renderer.rect(food.getX()*SBS+1, food.getY()*SBS+1, SBS-2, SBS-2);
		if(gameOver){
			update = false;
			gameOver();
		}
		renderer.end();

		batch.begin();
		font.setColor(Color.BLACK);
		font.draw(batch, "Score: " + score, 20.0f,50.0f);
		if(gameOver){
			gameOverText();
		}
		batch.end();


		if(gameOver){
			if(Gdx.input.isKeyPressed(Keys.ENTER)){
				reset();
			}
		}
	}

	public void update(){
		boolean edible = true;
		if(next2 != null && (next2.equals(lastDir) || next2.equals("LEFT") && lastDir.equals("RIGHT") || next2.equals("RIGHT") && lastDir.equals("LEFT") || next2.equals("UP") && lastDir.equals("DOWN") || next2.equals("DOWN") && lastDir.equals("UP"))){
			edible = false;
		}
		while(next2 != null){
			if(edible){
				lastDir = next2;
				next2 = next1;
				next1 = null;
				break;
			}
			else{
				next2 = next1;
				next1 = null;
			}
		}


		Point p;
		Point p1;
		int x = snake.get(0).getX();
		int y = snake.get(0).getY();

		if(lastDir.equals("UP")){
			//System.out.println("UP");
			p = new Point(x, y+1);
			if(eat){
				p1 = new Point(snake.get(snake.size()-1).getX(),snake.get(snake.size()-1).getY()-1);
				snake.addLast(p1);
				eat = false;
			}
		}
		else if(lastDir.equals("DOWN")){
			//System.out.println("DOWN");
			p = new Point(x, y-1);
			if(eat){
				p1 = new Point(snake.get(snake.size()-1).getX(),snake.get(snake.size()-1).getY()+1);
				snake.addLast(p1);
				eat = false;
			}
		}
		else if(lastDir.equals("LEFT")){
			//System.out.println("LEFT");
			p = new Point(x - 1, y);
			if(eat){
				p1 = new Point(snake.get(snake.size()-1).getX()+1,snake.get(snake.size()-1).getY());
				snake.addLast(p1);
				eat = false;
			}
		}
		else if(lastDir.equals("RIGHT")){
			//System.out.println("RIGHT " + width/SBS);
			p = new Point(x + 1, y);
			if(eat){
				p1 = new Point(snake.get(snake.size()-1).getX()-1,snake.get(snake.size()-1).getY());
				snake.addLast(p1);
				eat = false;
			}
		}
		else{
			throw new RuntimeException();
		}
		/*for(int i = 0; i<snakeLength; i++){
			System.out.println(snake.get(i).getX() + " " + snake.get(i).getY());
		}*/

		snake.pollLast();
		snake.addFirst(p);

		if(collision("self")){
			gameOver = true;
		}
		else if(collision("block")){
			gameOver = true;
		}
		else if(collision("food")){
			//snakeLength++;
			eat = true;
		}

		//executed = true;

	}

	public boolean collision(String item){
		boolean done = false;
		if(item.equals("self")){
			for(int i = 1; i<snake.size();i++){
				Point p = new Point(snake.get(0).getX(),snake.get(0).getY());
				Point p1 = new Point(snake.get(i).getX(),snake.get(i).getY());
				if(p.equals(p1)){
					done = true;
				}
			}
			return done;
		}
		if(item.equals("block")){
			for(Point p : snake){
				if(blocks.hasBlock(p.getX(), p.getY(), totalDelta)){
					return true;
				}
			}
			return false;
		}
		else if(item.equals("food")){
			boolean addScore = false;
			for(int i = 1; i<snake.size();i++){
				Point p = new Point(snake.get(0).getX(),snake.get(0).getY());
				if(p.equals(food)){
					done = true;
					eaten = true;
					addScore = true;
				}
			}
			if(addScore){
				score++;
			}
			return done;
		}
		return false;
	}
	public void gameOver(){
		renderer.setColor(Color.LIGHT_GRAY);
		renderer.rect(width/2-width/8,height/2-height/10,width/4,height/5);
	}
	public void gameOverText(){
		font.setColor(Color.BLACK);
		font.draw(batch, "GAME OVER", width/2-width/8 + 10, height/2-height/10+100);
		font.draw(batch, "Score: "+score, width/2-width/8 + 10, height/2-height/10+80);
		font.draw(batch, "Press <enter> to play again ", width/2-width/8 + 10, height/2-height/10+60);

	}


}

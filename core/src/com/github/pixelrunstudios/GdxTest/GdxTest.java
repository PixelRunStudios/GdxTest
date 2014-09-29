package com.github.pixelrunstudios.GdxTest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GdxTest extends ApplicationAdapter {

	protected BitmapFont font;
	protected SpriteBatch batch;
	private Texture img;
	protected PlatformIndependent platform;

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
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		font.setColor(Color.BLACK);
		font.draw(batch, "Hello, world!", 100, 100);
		batch.end();
	}
}

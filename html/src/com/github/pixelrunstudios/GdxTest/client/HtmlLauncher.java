package com.github.pixelrunstudios.GdxTest.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.github.pixelrunstudios.GdxTest.GdxTest;
import com.github.pixelrunstudios.GdxTest.PlatformIndependent;

public class HtmlLauncher extends GwtApplication implements PlatformIndependent{

	public static final int FRAME_WIDTH = 1280;
	public static final int FRAME_HEIGHT = 800;

	protected GdxTest core;

	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(FRAME_WIDTH, FRAME_HEIGHT);
	}

	@Override
	public ApplicationListener getApplicationListener () {
		return new GdxTest(this);
	}

	@Override
	public String getPlatform(){
		return "html";
	}

	@Override
	public void setCoreProgram(GdxTest core){
		this.core = core;
	}

	@Override
	public int getFrameWidth(){
		return FRAME_WIDTH;
	}

	@Override
	public int getFrameHeight(){
		return FRAME_HEIGHT;
	}
}
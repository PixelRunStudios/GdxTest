package com.github.pixelrunstudios.GdxTest.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.pixelrunstudios.GdxTest.GdxTest;
import com.github.pixelrunstudios.GdxTest.Platform;

public class DesktopLauncher implements Platform{

	public static final int FRAME_WIDTH = 1200;
	public static final int FRAME_HEIGHT = 800;

	protected GdxTest core;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FRAME_WIDTH;
		config.height = FRAME_HEIGHT;
		new LwjglApplication(new GdxTest(new DesktopLauncher()), config);
	}

	@Override
	public String getPlatformName(){
		return "desktop";
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

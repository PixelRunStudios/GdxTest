package com.github.pixelrunstudios.GdxTest.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.pixelrunstudios.GdxTest.GdxTest;
import com.github.pixelrunstudios.GdxTest.PlatformIndependent;

public class DesktopLauncher implements PlatformIndependent{
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new GdxTest(new DesktopLauncher()), config);
	}

	@Override
	public String getPlatform(){
		return "desktop";
	}
}

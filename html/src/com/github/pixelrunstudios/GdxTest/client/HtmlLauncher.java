package com.github.pixelrunstudios.GdxTest.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.github.pixelrunstudios.GdxTest.GdxTest;
import com.github.pixelrunstudios.GdxTest.PlatformIndependent;

public class HtmlLauncher extends GwtApplication implements PlatformIndependent{

	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}

	@Override
	public ApplicationListener getApplicationListener () {
		return new GdxTest(this);
	}

	@Override
	public String getPlatform(){
		return "html";
	}
}
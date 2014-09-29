package com.github.pixelrunstudios.GdxTest.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.github.pixelrunstudios.GdxTest.GdxTest;
import com.github.pixelrunstudios.GdxTest.PlatformIndependent;

public class AndroidLauncher extends AndroidApplication implements PlatformIndependent{
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new GdxTest(this), config);
	}

	@Override
	public String getPlatform(){
		return "android";
	}
}

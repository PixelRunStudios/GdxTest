package com.github.pixelrunstudios.GdxTest.android;

import android.graphics.Point;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.github.pixelrunstudios.GdxTest.GdxTest;
import com.github.pixelrunstudios.GdxTest.PlatformIndependent;

public class AndroidLauncher extends AndroidApplication implements PlatformIndependent{

	protected GdxTest core;

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

	@Override
	public void setCoreProgram(GdxTest core){
		this.core = core;
	}

	@Override
	public int getFrameWidth(){
		Point point = new Point();
		getWindowManager().getDefaultDisplay().getSize(point);
		return point.x;
	}

	@Override
	public int getFrameHeight(){
		Point point = new Point();
		getWindowManager().getDefaultDisplay().getSize(point);
		return point.y;
	}
}

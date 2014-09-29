package com.github.pixelrunstudios.GdxTest;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIScreen;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

public class IOSLauncher extends IOSApplication.Delegate implements Platform{

	protected GdxTest core;

	@Override
	protected IOSApplication createApplication() {
		IOSApplicationConfiguration config = new IOSApplicationConfiguration();
		return new IOSApplication(new GdxTest(this), config);
	}

	public static void main(String[] argv) {
		NSAutoreleasePool pool = new NSAutoreleasePool();
		UIApplication.main(argv, null, IOSLauncher.class);
		pool.close();
	}

	@Override
	public String getPlatformName(){
		return "ios";
	}

	@Override
	public void setCoreProgram(GdxTest core){
		this.core = core;
	}

	@Override
	public int getFrameWidth(){
		UIScreen mainScreen = UIScreen.getMainScreen();
		return (int) mainScreen.getBounds().getWidth();
	}

	@Override
	public int getFrameHeight(){
		UIScreen mainScreen = UIScreen.getMainScreen();
		return (int) mainScreen.getBounds().getHeight();
	}
}
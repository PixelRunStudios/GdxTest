package com.github.pixelrunstudios.GdxTest;

public interface PlatformIndependent{
	String getPlatform();
	void setCoreProgram(GdxTest core);
	int getFrameWidth();
	int getFrameHeight();
}

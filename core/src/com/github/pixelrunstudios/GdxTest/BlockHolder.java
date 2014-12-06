package com.github.pixelrunstudios.GdxTest;

public interface BlockHolder{
	boolean hasBlock(int x, int y, float totalDelta);
	boolean allowFood(int x, int y);
}

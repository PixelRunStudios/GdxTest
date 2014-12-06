package com.github.pixelrunstudios.GdxTest;

public class EmptyBlockHolder implements BlockHolder{

	@Override
	public boolean hasBlock(int x, int y, float totalDelta){
		return false;
	}

	@Override
	public boolean allowFood(int x, int y){
		return true;
	}

}

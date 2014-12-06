package com.github.pixelrunstudios.GdxTest;

public class BorderBlockHolder implements BlockHolder{

	protected int width;
	protected int height;

	public BorderBlockHolder(int width, int height){
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean hasBlock(int x, int y, float tick){
		if(x == 0 || x == width - 1 || y == 0 || y == height - 1){
			return true;
		}
		return false;
	}

	@Override
	public boolean allowFood(int x, int y){
		return !hasBlock(x, y, 0);
	}
}

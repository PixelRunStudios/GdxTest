package com.github.pixelrunstudios.GdxTest;

public class CrossBlockHolder implements BlockHolder{

	protected int width;
	protected int height;

	public CrossBlockHolder(int width, int height){
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean hasBlock(int x, int y, float tick){
		if(x == width/2 && y <= height * 3 / 4 && y >= height / 4
				|| y == height/2 && x <= width * 3 / 4 && x >= width / 4){
			return true;
		}
		if(y == 0 || y == height - 1){
			return true;
		}
		if(x == 0 || x == width - 1){
			return true;
		}
		return false;
	}

	@Override
	public boolean allowFood(int x, int y){
		return !hasBlock(x, y, 0);
	}

}

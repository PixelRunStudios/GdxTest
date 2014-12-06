package com.github.pixelrunstudios.GdxTest;

public class BoxBlockHolder implements BlockHolder{

	protected int width;
	protected int height;
	protected int leftX;
	protected int rightX;
	protected int bottomY;
	protected int topY;

	public BoxBlockHolder(int width, int height){
		this.width = width;
		this.height = height;
		leftX = width / 3;
		rightX = width * 2 / 3;
		bottomY = height / 3;
		topY = height * 2 / 3;
	}

	@Override
	public boolean hasBlock(int x, int y, float tick){
		if((x == leftX || x == rightX) && y >= bottomY && y <= topY ||
				(y == bottomY || y == topY) && x >= leftX && x <= rightX){
			return true;
		}
		if(x == 0 || x == width - 1 || y == 0 || y == height - 1){
			return true;
		}
		return false;
	}

	@Override
	public boolean allowFood(int x, int y){
		if(x == 0 || x == width - 1 || y == 0 || y == height - 1){
			return false;
		}
		if(x >= leftX && x <= rightX && y >= bottomY && y <= topY){
			return false;
		}
		return true;
	}
}

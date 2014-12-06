package com.github.pixelrunstudios.GdxTest;


public class MovingVerticalBlockHolder implements BlockHolder{

	protected int width;
	protected int height;

	public MovingVerticalBlockHolder(int width, int height){
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean hasBlock(int x, int y, float tick){
		if(hasPermanentBlock(x, y)){
			return true;
		}
		//int usable = width / 2;
		int tickPart = (int) (tick * 4 % width);
		tickPart -= width / 2;
		if(tickPart < 0){
			tickPart = -tickPart;
		}
		tickPart += width / 4;
		if(x == tickPart && y >= height / 4 && y <= height * 3 / 4){
			return true;
		}
		return false;
	}

	public boolean hasPermanentBlock(int x, int y){
		if(x == 0 || x == width - 1){
			return true;
		}
		if(y == height / 2 && (x <= width / 4 || x >= width * 3 / 4)){
			return true;
		}
		if(x % 2 == 0 && (y == 0 || y == height - 1)){
			return true;
		}
		return false;
	}

	@Override
	public boolean allowFood(int x, int y){
		return !hasPermanentBlock(x, y) && !(x >= width / 4 && x <= width * 3 / 4
				&& y >= height / 4 && y <= height * 3 / 4);
	}


}

package com.github.pixelrunstudios.GdxTest;

import com.badlogic.gdx.math.MathUtils;

public class CurveBlockHolder implements BlockHolder{

	protected int width;
	protected int height;

	public CurveBlockHolder(int width, int height){
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean hasBlock(int x, int y, float tick){
		double d = MathUtils.sin((float)(x/2/Math.PI));
		double d2 = MathUtils.sin((float)((x+1)/2/Math.PI));
		int y1 = (int)(d * height / 4 + height / 2);
		int y2 = (int)(d2 * height / 4 + height / 2);
		if(y >= y1 && y <= y2
				|| y <= y1 && y >= y2){
			return true;
		}
		return false;
	}

	@Override
	public boolean allowFood(int x, int y){
		return !hasBlock(x, y, 0);
	}

}

package com.github.pixelrunstudios.GdxTest;

public class Point{

	public static int maxX;
	public static int maxY;

	private int x;
	private int y;

	public Point(int x, int y){
		this.x = modulo(x, maxX);
		this.y = modulo(y, maxY);
	}

	public int modulo(int a, int b){
		int x = a % b;
		if(x < 0){
			x += b;
		}
		return x;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
	@Override
	public String toString(){
		return "(" + getX() + "," + getY() + ")";
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof Point){
			Point p = (Point) o;
			if(p.getX() == getX() && p.getY() == getY()){
				return true;
			}
		}
		return false;
	}
	@Override
	public int hashCode(){
		return getX() ^ getY();
	}
}

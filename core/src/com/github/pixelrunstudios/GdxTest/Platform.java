package com.github.pixelrunstudios.GdxTest;

/**
 * Interface for methods relating to specific platforms
 *
 * @author Markus Feng
 */
public interface Platform{

	/**
	 * Retruns the name of the platform
	 * @return the name of the platform
	 */
	String getPlatformName();

	/**
	 * Links the core program to the platform independent program
	 * @param core the program to link to
	 */
	void setCoreProgram(GdxTest core);

	/**
	 * Returns the frame width in pixels
	 * @return the frame width in pixels
	 */
	int getFrameWidth();

	/**
	 * Returns the frame height in pixels
	 * @return the frame height in pixels
	 */
	int getFrameHeight();
}

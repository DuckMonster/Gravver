package com.emilstrom.gravver.game;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;

import com.emilstrom.gravver.helper.Camera;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Emil on 2014-04-01.
 */
public class Game implements GLSurfaceView.Renderer {
	public static Game currentGame;

	public static float updateTime = 0;
	private static long oldTime;

	public void Game() {
		currentGame = this;

		currentCamera = new Camera();
	}

	public void logic() {
		//Calculate updatetime
		if (updateTime == 0) {
			oldTime = SystemClock.uptimeMillis();
		}

		long newTime = SystemClock.uptimeMillis();
		updateTime = (newTime - oldTime) * 0.001f;
		oldTime = newTime;
	}

	public void draw() {
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
	}


	//GL STUFF
	float projection[] = new float[16];
	public Camera currentCamera;

	public float[] getViewProjection() {
		float ret[] = new float[16];
		Matrix.multiplyMM(ret, 0, projection, 0, currentCamera.getView(), 0);
		return ret;
	}

	public void onSurfaceCreated(GL10 unused, EGLConfig config) {
		GLES20.glClearColor(1f, 0f, 0f, 1f);
	}

	public void onDrawFrame(GL10 unused) {
		logic();
		draw();
	}

	public void onSurfaceChanged(GL10 unused, int width, int height) {
		final int screenSize = 10;
		float ratio = (float)height / (float)width;

		Matrix.orthoM(projection, 0, -screenSize, screenSize, -ratio * screenSize, ratio * screenSize, 1f, 3f);
	}
}

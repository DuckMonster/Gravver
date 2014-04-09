package com.emilstrom.gravver.game;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;

import com.emilstrom.gravver.helper.Camera;
import com.emilstrom.gravver.helper.ShaderHelper;
import com.emilstrom.gravver.helper.Sprite;
import com.emilstrom.gravver.helper.Vertex;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Emil on 2014-04-01.
 */
public class Game implements GLSurfaceView.Renderer {
	public static Game currentGame;

	public static float updateTime = 0;
	private static long oldTime;


	Map currentMap;

	public Game() {
		currentGame = this;

		currentCamera = new Camera();
		currentMap = new Map();
	}

	public void logic() {
		//Calculate updatetime
		if (updateTime == 0) {
			oldTime = SystemClock.uptimeMillis();
		}

		long newTime = SystemClock.uptimeMillis();
		updateTime = (newTime - oldTime) * 0.001f;
		oldTime = newTime;

		currentMap.logic();
	}

	public void draw() {
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

		GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		GLES20.glEnable(GLES20.GL_BLEND);

		currentMap.draw();
	}


	//GL STUFF
	float projection[] = new float[16];
	public Camera currentCamera;

	public float gameWidth, gameHeight;

	public float[] getViewProjection() {
		float ret[] = new float[16];
		Matrix.multiplyMM(ret, 0, projection, 0, currentCamera.getView(), 0);
		return ret;
	}

	public void onSurfaceCreated(GL10 unused, EGLConfig config) {
		GLES20.glClearColor(0f, 0f, 0f, 1f);
	}

	public void onDrawFrame(GL10 unused) {
		logic();
		draw();
	}

	public void onSurfaceChanged(GL10 unused, int width, int height) {
		final int screenSize = 10;
		float ratio = (float)height / (float)width;

		gameWidth = screenSize * 2;
		gameHeight = ratio * gameWidth;

		Matrix.orthoM(projection, 0, -screenSize, screenSize, -ratio * screenSize, ratio * screenSize, 1f, 10f);

		ShaderHelper.loadShader();
		Art.loadAssets();
		PlayerController.recalculatePosition();
	}
}

package com.emilstrom.gravver;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.emilstrom.gravver.game.Game;

/**
 * Created by Emil on 2014-04-01.
 */
public class GameSurface extends GLSurfaceView {
	Game game;

	public GameSurface(Context c) {
		super(c);

		setEGLContextClientVersion(2);

		game = new Game();
		setRenderer(game);

		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}
}
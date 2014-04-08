package com.emilstrom.gravver;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import com.emilstrom.gravver.game.Game;
import com.emilstrom.gravver.helper.InputHelper;

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

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		float w = getWidth(), h = getHeight();

		int pointerIndex = e.getActionIndex(),
				pointerID = e.getPointerId(pointerIndex);

		switch(e.getActionMasked()) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_POINTER_DOWN:
				InputHelper.setPressed(pointerID, true);
				break;

			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
				InputHelper.setPressed(pointerID, false);
				break;
		}

		for(int p=0; p<e.getPointerCount(); p++) {
			int id = e.getPointerId(p);

			float x = (e.getX(p) - w/2) / (w/2);
			float y = (e.getY(p) - h/2) / (-h/2);

			InputHelper.setPosition(id, x, y);
		}

		return true;
	}
}
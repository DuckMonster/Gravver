package com.emilstrom.gravver;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Emil on 2014-04-01.
 */
public class Gravver extends Activity {
	public static final String TAG = "GravverTag";

	public static Context context;

	GameSurface surface;

	protected void onCreate(Bundle savedState) {
		super.onCreate(savedState);
		context = this;

		surface = new GameSurface(this);
		setContentView(surface);
	}
}

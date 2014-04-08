package com.emilstrom.gravver.game;

import com.emilstrom.gravver.R;
import com.emilstrom.gravver.helper.SpriteData;

/**
 * Created by Emil on 2014-04-02.
 */
public class Art {
	public static SpriteData blank = new SpriteData(R.drawable.blank),
		circle = new SpriteData(R.drawable.circle),
		ring = new SpriteData(R.drawable.ring);

	public static void loadAssets() {
		blank.loadAssets();
		circle.loadAssets();
		ring.loadAssets();
		ring.loadAssets();
	}
}

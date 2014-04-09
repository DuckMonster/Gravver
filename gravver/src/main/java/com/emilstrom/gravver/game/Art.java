package com.emilstrom.gravver.game;

import com.emilstrom.gravver.R;
import com.emilstrom.gravver.helper.SpriteData;
import com.emilstrom.gravver.helper.Tileset;
import com.emilstrom.gravver.helper.TilesetData;

/**
 * Created by Emil on 2014-04-02.
 */
public class Art {
	public static SpriteData blank = new SpriteData(R.drawable.blank),
		circle = new SpriteData(R.drawable.circle),
		ring = new SpriteData(R.drawable.ring),
		player = new SpriteData(R.drawable.player);

	public static TilesetData debreeSet = new TilesetData(R.drawable.debree, 16, 16);

	public static void loadAssets() {
		blank.loadAssets();
		circle.loadAssets();
		ring.loadAssets();

		player.loadAssets();

		debreeSet.loadAssets();
	}
}

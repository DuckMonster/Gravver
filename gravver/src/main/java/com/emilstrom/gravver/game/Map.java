package com.emilstrom.gravver.game;

import com.emilstrom.gravver.game.entity.Debree;
import com.emilstrom.gravver.game.entity.Player;
import com.emilstrom.gravver.helper.GameMath;
import com.emilstrom.gravver.helper.Timer;
import com.emilstrom.gravver.helper.Vertex;

/**
 * Created by Emil on 2014-04-02.
 */
public class Map {
	Timer debreeSpawnTimer = new Timer(5f, true);

	public Player player;
	public Debree debreeList[] = new Debree[30];
	int debreeListIndex = 0;

	public Map() {
		player = new Player();
	}

	public void spawnDebree() {
		int spawnIndex = debreeListIndex;

		while(debreeList[spawnIndex] != null)
			spawnIndex++;

		Vertex spawnPosition = new Vertex(
				(float)GameMath.getRndDouble(-Game.currentGame.gameWidth/2, Game.currentGame.gameWidth/2),
				Game.currentGame.gameHeight/2 + 2f
		);

		debreeList[spawnIndex] = new Debree(spawnPosition, player);
	}

	public void logic() {
		player.logic();


		debreeSpawnTimer.logic();

		if (debreeSpawnTimer.isDone()) {
			spawnDebree();
			debreeSpawnTimer.reset();
		}


		for(Debree d : debreeList) if (d != null) d.logic();
	}

	public void draw() {
		player.draw();
		for(Debree d : debreeList) if (d != null) d.draw();
	}
}
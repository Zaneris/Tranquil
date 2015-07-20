package ca.dev9.tranquil;

import ca.dev9.tranquil.chunk.Chunk;
import ca.dev9.tranquil.utils.Int3;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;

/**
 * Main player of the game.
 * @author Zaneris
 */
public class Player {
	public PerspectiveCamera cam;
	private Vector3 lastPosition;
	public final Int3 currentChunk;

	public Player(short depth) {
		currentChunk = new Int3();
		cam = new PerspectiveCamera(75f, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		cam.position.set(0f, 50f, 0f);
		cam.lookAt(1f, 49f, 1f);
		cam.near = 1.0f;
		cam.far = depth;
	}

	public void update() {
		currentChunk.set(cam.position);
		currentChunk.div(Chunk.CHUNK_SIZE);
		cam.update();
	}

	public boolean moved16() {
		return (lastPosition == null || cam.position.dst2(lastPosition)>256);
	}

	public void updateLastPosition() {
		lastPosition = cam.position.cpy();
	}

	public void axisInput(float x, float y) {
		float dT = Gdx.graphics.getDeltaTime();
		cam.position.add(x*dT*2f, 0f, x*dT*2f);
	}
}

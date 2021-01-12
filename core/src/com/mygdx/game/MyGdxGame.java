package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.net.GameClient;

public class MyGdxGame extends ApplicationAdapter {
    OrthographicCamera camera;
    SpriteBatch batch;

    Texture texture;

    GameClient client;

    @Override
    public void create() {
        client = new GameClient();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        texture = new Texture("bucket.png");

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.begin();
        //draw here
        for (Player player : client.players) {
            player.draw(texture, batch);
        }
        client.move();
        batch.end();
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}

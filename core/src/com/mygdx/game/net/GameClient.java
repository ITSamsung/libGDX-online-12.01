package com.mygdx.game.net;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.mygdx.game.Player;

import java.io.IOException;

public class GameClient extends Client {
    public Player player;
    public Player[] players;

    public GameClient() {
        super();
        start();
        player = new Player("Player");
        players = new Player[0];
        Network.register(this);
        addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                Network.RegisterPlayer registerPlayer = new Network.RegisterPlayer(player);
                sendTCP(registerPlayer);
            }

            @Override
            public void received(Connection connection, Object object) {
                Log.info("client", object.toString());
                if (object instanceof Network.UpdatePlayers) {
                    players = ((Network.UpdatePlayers) object).players;
                }
                if (object instanceof Network.MovePlayer) {
                    Player newPlayer = ((Network.MovePlayer) object).player;
                    for (int i = 0; i < players.length; i++) {
                        if (players[i].name.equals(newPlayer.name)) {
                            players[i] = newPlayer;
                            break;
                        }
                    }
                }
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    connect(5000, Network.host, Network.port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void move() {
        if (player.move()) {
            Log.info("client", player.toString());
            Network.MovePlayer movePlayer = new Network.MovePlayer(player);
            sendTCP(movePlayer);
        }
    }
}

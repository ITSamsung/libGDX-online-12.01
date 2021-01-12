package com.mygdx.game.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import com.mygdx.game.Player;
import com.mygdx.game.net.utils.GameConnection;

import java.io.IOException;

public class GameServer extends Server {
    public GameServer() throws IOException {
        super();
        Network.register(this);
        addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (!(connection instanceof GameConnection)) {
                    return;
                }
                Log.info("server", object.toString());

                if (object instanceof Network.RegisterPlayer) {
                    ((GameConnection) connection).player = ((Network.RegisterPlayer) object).player;

                    Player[] players = new Player[getConnections().length];
                    for (int i = 0; i < players.length; i++) {
                        players[i] = ((GameConnection) getConnections()[i]).player;
                    }

                    Network.UpdatePlayers updatePlayer = new Network.UpdatePlayers(players);
                    sendToAllTCP(updatePlayer);
                }
                if (object instanceof Network.MovePlayer) {
                    sendToAllTCP(object);
                }
            }
        });
        bind(Network.port);
        start();
    }

    @Override
    protected Connection newConnection() {
        return new GameConnection();
    }

    public static void main(String[] args) throws IOException {
        Log.set(Log.LEVEL_DEBUG);
        new GameServer();
    }
}

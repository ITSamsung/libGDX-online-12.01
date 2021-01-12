package com.mygdx.game.net;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.mygdx.game.Player;

import java.util.Arrays;

public class Network {
    public static final String host = "localhost";
    public static final int port = 5000;

    public static void register(EndPoint endpoint) {
        Kryo kryo = endpoint.getKryo();
        kryo.register(Player.class);
        kryo.register(Player[].class);
        kryo.register(Color.class);
        kryo.register(Rectangle.class);
        // events
        kryo.register(RegisterPlayer.class);
        kryo.register(UpdatePlayers.class);
        kryo.register(MovePlayer.class);
    }

    static public class RegisterPlayer {
        public Player player;

        public RegisterPlayer() {
            //NEED for kryo
        }

        public RegisterPlayer(Player player) {
            this.player = player;
        }

        @Override
        public String toString() {
            return "RegisterPlayer{" +
                    "player=" + player +
                    '}';
        }
    }

    static public class UpdatePlayers {
        public Player[] players;

        public UpdatePlayers() {
            //NEED for kryo
        }

        public UpdatePlayers(Player[] players) {
            this.players = players;
        }

        @Override
        public String toString() {
            return "UpdatePlayers{" +
                    "player=" + Arrays.toString(players) +
                    '}';
        }
    }

    static public class MovePlayer {
        public Player player;

        public MovePlayer() {
            //NEED for kryo
        }

        public MovePlayer(Player player) {
            this.player = player;
        }

        @Override
        public String toString() {
            return "MovePlayer{" +
                    "player=" + player +
                    '}';
        }
    }
}

package com.example.cincuentazo.models;

import com.example.cincuentazo.factories.PlayerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing GameModel.
 */
class GameModelTest {
    GameModel gameModel;
    PlayerFactory playerFactory;

    /**
     * Initialize the attributes.
     */
    @BeforeEach
    public void setUp() {
        gameModel = new GameModel();
        playerFactory = new PlayerFactory();
    }

    /**
     * Test to validate that there are 2 players.
     */
    @Test
    void testListTwoPlayers() {
        ArrayList<PlayerModel> players = new ArrayList<>();

        PlayerModel player1 = playerFactory.createPlayer("Jugador 1");
        PlayerModel player2 = playerFactory.createPlayer("Jugador 2");
        players.add(player1);
        players.add(player2);

        gameModel.setPlayers(players);

        assertEquals(2, gameModel.getPlayersSize());
    }

    /**
     * Test to validate that there are 2 players.
     */
    @Test
    void testListThreePlayers() {
        ArrayList<PlayerModel> players = new ArrayList<>();

        PlayerModel player1 = playerFactory.createPlayer("Jugador 1");
        PlayerModel player2 = playerFactory.createPlayer("Jugador 2");
        PlayerModel player3 = playerFactory.createPlayer("Jugador 3");
        players.add(player1);
        players.add(player2);
        players.add(player3);

        gameModel.setPlayers(players);

        assertEquals(3, gameModel.getPlayersSize());
    }
}
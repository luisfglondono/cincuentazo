package com.example.cincuentazo.factories;

import com.example.cincuentazo.models.PlayerModel;

/**
 * The PlayerFactory class is responsible for creating instances of PlayerModel.
 */
public class PlayerFactory {
    /**
     * Creates a new PlayerModel with the specified nickname.
     *
     * @param nickname the nickname of the player
     * @return a new instance of PlayerModel with the given nickname
     */
    public PlayerModel createPlayer(String nickname) {
        PlayerModel player = new PlayerModel();
        player.setNickname(nickname);
        return player;
    }
}

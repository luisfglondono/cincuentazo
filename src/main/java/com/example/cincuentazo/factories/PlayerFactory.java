package com.example.cincuentazo.factories;

import com.example.cincuentazo.models.PlayerModel;

public class PlayerFactory {
    public PlayerModel createPlayer(String nickname) {
        PlayerModel player = new PlayerModel();
        player.setNickname(nickname);
        return player;
    }
}

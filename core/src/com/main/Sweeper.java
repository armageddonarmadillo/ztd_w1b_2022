package com.main;

public class Sweeper extends Bullet {

    Sweeper(String type, int x, int y) {
        super("sweeper_" + type, x, y, 0f);
    }

    void be_collision(){
        if(Game.enemies.isEmpty()) return;
        for(Enemy e : Game.enemies) if(e.hitbox().overlaps(hitbox())) e.hp = 0;
    }
}

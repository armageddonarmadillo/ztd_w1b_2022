package com.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Wall {
    int x, y, w, h;
    String type;
    boolean active = true;
    int hp, max_hp;

    Wall(String type, int x, int y){
        this.type = type;
        w = 50;
        h = 50;
        this.x = lock(x, w);
        this.y = lock(y, h);
        hp = 5;
        max_hp = hp;
    }

    void draw(SpriteBatch b){
        active = hp > 0;
        b.draw(Resources.violet, x, y, w, h);
    }

    int lock(int coord, int sizer){
        return coord - coord % sizer;
    }

    Rectangle hitbox() { return new Rectangle(x, y, w, h); }
}

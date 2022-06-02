package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {
    int x, y, w, h, s, hp, hp_max;
    boolean active = true;
    String type;

    //Animation Variables
    int rows = 1, cols;
    float frame_time = 0.2f;
    Animation anim;
    TextureRegion[] frames;
    TextureRegion frame;

    Enemy(String type, int x, int y){
        this.type = type;
        this.x = x;
        this.y = y;
        this.cols = Maps.values.get("cols_" + type) == null ? 4 : Maps.values.get("cols_" + type);
        this.rows = Maps.values.get("rows_" + type) == null ? 1 : Maps.values.get("rows_" + type);
        this.w = (Maps.resources.get("enemies_"+type) == null ? Resources.zombie : Maps.resources.get("enemies_"+type)).getWidth() / cols;
        this.h = (Maps.resources.get("enemies_"+type) == null ? Resources.zombie : Maps.resources.get("enemies_"+type)).getHeight() / rows;
        this.s = Maps.values.get("speed_" + type) == null ? 2 : Maps.values.get("speed_" + type);
        this.hp = Maps.values.get("health_" + type) == null ? 3 : Maps.values.get("health_" + type);
        hp_max = hp;
        init_animation();
    }

    void update(){
        x-=s;
        active = !(x + w < 0) && !(hp <= 0);
        UI.life -= (x + w < 0) ? 1 : 0;
        UI.score += (hp <= 0) ? 1 : 0;
    }

    void draw(SpriteBatch b){
        if(x + 50 < 0 || x > 1024) return;
        frame_time += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)anim.getKeyFrame(frame_time, true);
        b.draw(frame, x, y);
        b.draw(Resources.create_texture(Color.WHITE), x, y + h, w, 5);
        b.draw(Resources.create_texture(Color.GREEN), x, y + h, hp * ((float)w / hp_max), 5);
    }

    void init_animation(){
        //splits given texture into individual cells using a provided cellwidth and cellheight to do so
        TextureRegion[][] sheet = TextureRegion.split((Maps.resources.get("enemies_"+type) == null ? Resources.zombie : Maps.resources.get("enemies_"+type)), w, h);

        frames = new TextureRegion[rows * cols];

        //shove each cell from the sheet into the frames array
        int index = 0;
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++)
                frames[index++] = sheet[r][c];

        anim = new Animation(frame_time, frames);
    }

    Rectangle hitbox(){
        return new Rectangle(x, y, w, h);
    }

}

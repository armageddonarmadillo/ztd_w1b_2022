package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {
    static int timer;
    int x, y, w, h;
    String type;

    //Animation Variables
    int rows = 1, cols;
    float frame_time = .2f;
    Animation anim;
    TextureRegion[] frames;
    TextureRegion frame;

    Tile(String type, int x, int y){
        this.type = type;
        this.x = x;
        this.y = y;
        frame_time = (timer++ % 21f) * .3f;
        this.cols = Maps.values.get("cols_tiles_" + type) == null ? 1 : Maps.values.get("cols_tiles_" + type);
        this.rows = Maps.values.get("rows_tiles_" + type) == null ? 1 : Maps.values.get("rows_tiles_" + type);
        this.w = (Maps.resources.get("tiles_"+type) == null ? Resources.tile : Maps.resources.get("tiles_"+type)).getWidth() / cols;
        this.h = (Maps.resources.get("tiles_"+type) == null ? Resources.tile : Maps.resources.get("tiles_"+type)).getHeight() / rows;
        init_animation();
    }

    void draw(SpriteBatch b){
        for(Cannon c : Game.cannons) if(occupancy(c.x, c.y)) { b.draw(Resources.tile_lava_occupied, x, y); return;  }
        frame_time += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)anim.getKeyFrame(frame_time, true);
        b.draw(frame, x, y);
    }

    void init_animation(){
        //splits given texture into individual cells using a provided cellwidth and cellheight to do so
        TextureRegion[][] sheet = TextureRegion.split((Maps.resources.get("tiles_"+type) == null ? Resources.zombie : Maps.resources.get("tiles_"+type)), w, h);

        frames = new TextureRegion[rows * cols];

        //shove each cell from the sheet into the frames array
        int index = 0;
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++)
                frames[index++] = sheet[r][c];

        anim = new Animation(frame_time, frames);
    }

    boolean occupancy(int x, int y){
        return this.x == x && this.y == y;
    }
}

package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Effect {
    int x, y, w, h;
    boolean active = true;
    String type;

    //Animation Variables
    int rows, cols;
    float frame_time;
    Animation anim;
    TextureRegion[] frames;
    TextureRegion frame;

    Effect(String type, int x, int y){
        this.type = type;
        this.cols = Maps.values.get("cols_" + type) == null ? 1 : Maps.values.get("cols_" + type);
        this.rows = Maps.values.get("rows_" + type) == null ? 1 : Maps.values.get("rows_" + type);
        this.w = (Maps.resources.get("effects_"+type) == null ? Resources.blank : Maps.resources.get("effects_"+type)).getWidth() / cols;
        this.h = (Maps.resources.get("effects_"+type) == null ? Resources.blank : Maps.resources.get("effects_"+type)).getHeight() / rows;
        frame_time = 1.f / (Maps.values.get("ft_" + type) == null ? .1f : (float)Maps.values.get("ft_" + type));
        this.x = x - w / 2;
        this.y = y - h / 2;
        init_animation();
    }

    void draw(SpriteBatch b){
        frame_time += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)anim.getKeyFrame(frame_time, false);
        b.draw(frame, x, y);
        active = !anim.isAnimationFinished(frame_time);
    }

    void init_animation(){
        //splits given texture into individual cells using a provided cellwidth and cellheight to do so
        TextureRegion[][] sheet = TextureRegion.split((Maps.resources.get("effects_"+type) == null ? Resources.blank : Maps.resources.get("effects_"+type)), w, h);

        frames = new TextureRegion[rows * cols];

        //shove each cell from the sheet into the frames array
        int index = 0;
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++)
                frames[index++] = sheet[r][c];

        anim = new Animation(frame_time, frames);
    }
}

package com.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class Game extends Scene{
    static Random r = new Random();
    static String t = "";

    //Lists
    static ArrayList<Enemy> enemies = new ArrayList<>();
    static ArrayList<Cannon> cannons = new ArrayList<>();
    static ArrayList<Bullet> bullets = new ArrayList<>();
    static ArrayList<Button> buttons = new ArrayList<>();
    static ArrayList<Tile> tiles = new ArrayList<>();

    Game(){init();}

    void init(){
        //clear lists
        enemies.clear();
        cannons.clear();
        bullets.clear();
        buttons.clear();

        //reset values
        UI.money = 10000;
        UI.life = 20;
        UI.score = 0;
        UI.wave = 0;

        //add buttons
        buttons.add(new Button("cannon", 200 + buttons.size() * 75, 525));
        buttons.get(buttons.size() - 1).selected = true;
        buttons.add(new Button("fire", 200 + buttons.size() * 75, 525));
        buttons.add(new Button("super", 200 + buttons.size() * 75, 525));
        buttons.add(new Button("treb", 200 + buttons.size() * 75, 525));

        //cover game board
        spawn_tiles(Main.gw, Main.gh);
    }

    @Override
    void tap(int x, int y) {
        for(Button b : buttons) if(b.hitbox().contains(x, y)) {
            t = b.type;
            deselect();
            b.selected = true;
            return;
        }
        for(Cannon c : cannons) if(c.hitbox().contains(x, y)) return;
        if(((y < 500 && y > 300) || (y < 200 && y > 0)) && (x < 1000)) cannons.add(new Cannon(t, x, y));
    }

    void deselect(){
        for(Button b : buttons) b.selected = false;
    }

    @Override
    void draw(SpriteBatch b) {
        update();
        b.draw(Resources.bg_template, 0, 0);
        for(Tile t : tiles) t.draw(b);
        UI.draw(b);
        for(Enemy e : enemies) e.draw(b);
        for(Cannon c : cannons) c.draw(b);
        for(Bullet bu : bullets) bu.draw(b);
        for(Button bt : buttons) bt.draw(b);
    }

    void update(){
        spawn_enemies();
        for(Enemy e : enemies) e.update();
        for(Cannon c : cannons) c.update();
        for(Bullet b : bullets) b.update();
        code_keeping();
    }

    void spawn_tiles(int gw, int gh){
        for(int x = 0; x < (gw / 50) + 1; x++) tiles.add(new Tile("wall_lava", x * 100, 500));
        for(int x = 0; x < (gw / 50) + 1; x++)
            for(int y = 0; y < (gh / 50); y++)
                if((((y * 50) < 500 && (y * 50) >= 300) || ((y * 50) < 200)) && (x < 1000)) tiles.add(new Tile("lava", x * 50, y * 50));
                else if((y * 50) < 500) tiles.add(new Tile("lava_path", x * 50, y * 50));

    }

    //TODO: Wave Spawning logic, here's an example but expand on it!
    String[] types = { "dif", "fast", "sriot", "riot", "penguin", "speedy", "blob_toxic", "blob_pink", "blob_molten", "wizard_red", "wizard_blue", "wizard_green" };
    void spawn_enemies(){
        if(!enemies.isEmpty()) return;
        UI.wave++;
        int wave_count = 1;
        ArrayList<String> typez = new ArrayList<String>();
        typez.add("dif");
        switch(UI.wave){
            case 10:
                wave_count = 25;
                typez.add("riot");
            case 5:
                wave_count += 10;
                typez.add("fast");
                typez.add("speedy");
            case 2:
                wave_count += 2;
        }

        for(int i = 0; i < wave_count; i++) {
            int rando = r.nextInt(typez.size());
            enemies.add(new Enemy(
                    typez.get(rando),
                    1024 + (50 * enemies.size()),
                    r.nextInt(500 - (Maps.resources.get("enemies_"+typez.get(rando)).getHeight()))
            ));
        }
    }

    void code_keeping(){
        for (Enemy e : enemies) if (!e.active) { enemies.remove(e); break; }
        for (Cannon c : cannons) if (!c.active) { cannons.remove(c); break; }
        for (Bullet b : bullets) if (!b.active) { bullets.remove(b); break; }
    }
}

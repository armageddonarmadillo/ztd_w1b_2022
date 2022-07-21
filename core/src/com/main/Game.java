package com.main;

import com.badlogic.gdx.graphics.Color;
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
    static ArrayList<Effect> effects = new ArrayList<>();
    static ArrayList<Wall> walls = new ArrayList<>();

    Game(){init();}

    void init(){
        //clear lists
        enemies.clear();
        cannons.clear();
        bullets.clear();
        buttons.clear();
        effects.clear();
        walls.clear();

        //reset values
        UI.money = 10000;
        UI.life = 20;
        UI.score = 0;
        UI.wave = 0;

        //add buttons
        buttons.add(new Button("cannon", 200 + buttons.size() * 75, 525));
        buttons.get(buttons.size() - 1).selected = true;
        buttons.get(buttons.size() - 1).locked = false;
        buttons.add(new Button("fire", 200 + buttons.size() * 75, 525));
        buttons.add(new Button("super", 200 + buttons.size() * 75, 525));
        buttons.add(new Button("treb", 200 + buttons.size() * 75, 525));
        buttons.add(new Button("wall", 200 + buttons.size() * 75, 525));
        buttons.get(buttons.size() - 1).w = 50;
        buttons.get(buttons.size() - 1).h = 50;
        buttons.add(new Button("firework", 200 + buttons.size() * 75, 525));

        buttons.add(new Button("pause", Main.gw - 75, 525));
        buttons.get(buttons.size() - 1).selected = false;
        buttons.get(buttons.size() - 1).locked = false;

        //cover game board
        spawn_tiles(Main.gw, Main.gh);
    }

    @Override
    void tap(int x, int y) {

        for(Button b : buttons)
            if(b.type.equals("pause") && b.hitbox().contains(x, y)) { Main.which = Main.screen.PAUSE; return; }
            else if(b.type.equals("wall") && ((y < 500 && y > 300) || (y < 200 && y > 0)) && (x < 1000) && b.selected) {
                for(Cannon c : cannons) if(c.hitbox().contains(x, y)) return;
                if(UI.money >= (Maps.values.get("cost_"+b.type) == null ? 10 : Maps.values.get("cost_"+b.type))) {
                    UI.money -= (Maps.values.get("cost_" +b.type) == null ? 10 : Maps.values.get("cost_"+b.type));
                    walls.add(new Wall("wall", x, y));
                }
                return;
            } else if (b.type.equals("firework") && y < 500 && b.selected) {
                if(UI.money >= (Maps.values.get("cost_"+b.type) == null ? 10 : Maps.values.get("cost_"+b.type))) {
                    UI.money -= (Maps.values.get("cost_" + b.type) == null ? 10 : Maps.values.get("cost_" + b.type));
                    bullets.add(new Sweeper("firework", x, y));
                }
                return;
            }

        for(Button b : buttons) if(b.t != null && !b.t.hidden)
                if(b.t.close.hitbox().contains(x, y) || (!b.t.hitbox().contains(x, y) && !b.hitbox().contains(x, y) && !anybutton(x, y))) { hidett(); return; }
                else if(!b.t.hidden && b.t.hitbox().contains(x, y)) return;


        for(Button b : buttons) if(b.hitbox().contains(x, y)) {
            if(b.locked) {
                if(b.t!=null && b.t.hidden){
                    hidett();
                    b.t.hidden = false;
                } else {
                    if(UI.money >= (Maps.values.get("unlock_"+b.type) == null ? 200 : Maps.values.get("unlock_"+b.type))){
                        b.locked = false;
                        UI.money -=  (Maps.values.get("unlock_"+b.type) == null ? 200 : Maps.values.get("unlock_"+b.type));
                        hidett();
                    }
                }
            } else {
                t = b.type;
                deselect();
                b.selected = true;
            }
            return;
        }
        for(Cannon c : cannons) if(c.hitbox().contains(x, y)) return;
        for(Wall w : walls) if(w.hitbox().contains(x, y)) return;
        if(((y < 500 && y > 300) || (y < 200 && y > 0)) && (x < 1000))
            if(UI.money >= (Maps.values.get("cost_"+t) == null ? 10 : Maps.values.get("cost_"+t))){
                UI.money -= (Maps.values.get("cost_"+t) == null ? 10 : Maps.values.get("cost_"+t));
                effects.add(new Effect("build", x, y));
                cannons.add(new Cannon(t, x, y));
            }
    }

    boolean anybutton(int x, int y){ for(Button b : buttons) if(b.hitbox().contains(x, y)) return true; return false;}

    void deselect(){
        for(Button b : buttons) b.selected = false;
    }

    void hidett(){ for(Button b : buttons) if(b.t!=null) b.t.hidden = true; }

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
        for(Wall w : walls) w.draw(b);
        for(Effect e : effects) e.draw(b);
    }

    void update(){
        System.out.println(Color.VIOLET);
        if(Main.which != Main.screen.PLAY) return;
        if(UI.life <= 0) {
            Main.which = Main.screen.LOSE;
            if(Main.p.getInteger("highest_score") < UI.score) Main.p.putInteger("highest_score", UI.score);
            if(Main.p.getInteger("highest_wave") < UI.wave) Main.p.putInteger("highest_wave", UI.wave);
            return;
        }
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
    String[] types = { "dif", "fast", "sriot", "penguin", "speedy", "blob_toxic", "blob_pink", "blob_molten", "wizard_red", "wizard_blue", "wizard_green" };
    ArrayList<String> typez = new ArrayList<>();
    int wave_count = 1;
    void spawn_enemies(){
        if(!enemies.isEmpty()) return;
        UI.wave++;
        switch(UI.wave % 10){
            case 0:
                ArrayList<String> typey = new ArrayList<>();
                for(String s : typez) if(!typey.contains(s)) typey.add(s);
                typez = typey;
                typez.add("riot");
                break;
            case 2:
            case 4:
                typez.add(types[r.nextInt(types.length)]);
                break;
            case 3:
            case 6:
            case 9:
                wave_count += 3;
                break;
            default:
                typez.add("default");
                break;
        }

        for(int i = 0; i < wave_count; i++) {
            int rando = r.nextInt(typez.size());
            enemies.add(new Enemy(
                    typez.get(rando),
                    1024 + (50 * enemies.size()),
                    r.nextInt(500 - (Maps.resources.get("enemies_"+typez.get(rando)) == null ? Resources.zombie : Maps.resources.get("enemies_"+typez.get(rando))).getHeight())
            ));
        }
    }

    void code_keeping(){
        for (Enemy e : enemies) if (!e.active) { effects.add(new Effect(r.nextInt() % 2 == 0 ? "burst_rainbow" : "burst_toxic", e.x + e.w / 2, e.y + e.h / 2)); enemies.remove(e); break; }
        for (Cannon c : cannons) if (!c.active) { effects.add(new Effect(r.nextInt() % 2 == 0 ? "boom" : "shock_purple", c.x + c.w / 2, c.y + c.h / 2)); cannons.remove(c); break; }
        for (Bullet b : bullets) if (!b.active) { bullets.remove(b); break; }
        for (Effect e : effects) if (!e.active) { effects.remove(e); break; }
        for (Wall w : walls) if (!w.active) { effects.add(new Effect("shield_shatter", w.x + w.w / 2, w.y + w.h / 2)); walls.remove(w); break; }
    }
}

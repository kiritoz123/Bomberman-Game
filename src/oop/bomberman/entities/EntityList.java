package oop.bomberman.entities;

import oop.bomberman.entities.block.Bomb;
import oop.bomberman.entities.block.Flame;
import oop.bomberman.entities.character.Bomber;
import oop.bomberman.entities.character.enemy.Enemy;

import java.util.ArrayList;
import java.util.List;

public class EntityList {
    public static final List<Entity> block = new ArrayList<>();
    public static final List<Character> enemy = new ArrayList<>();
    public static final List<Flame> flame = new ArrayList<>();
    public static final List<Enemy> enemies = new ArrayList<>();
    public static List<Bomb> bombs = new ArrayList<>();
    //public static Bomber bomberman;

    public static void clearList() {
        block.clear();
        enemy.clear();
    }

}

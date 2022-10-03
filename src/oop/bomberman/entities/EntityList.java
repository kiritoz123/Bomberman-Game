package oop.bomberman.entities;

import oop.bomberman.entities.character.Bomber;

import java.util.ArrayList;
import java.util.List;

public class EntityList {
    public static final List<Entity> block = new ArrayList<>();
    public static final List<Character> enemy = new ArrayList<>();

    public static Bomber bomberman;

    public static void clearList() {
        block.clear();
        enemy.clear();
    }

}

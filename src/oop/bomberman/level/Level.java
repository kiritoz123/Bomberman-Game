package oop.bomberman.level;

public abstract class Level {
    protected int _width = 20;
    protected int _height = 20;
    protected int _level;

    public Level() {

    }
    public abstract void loadLevel(int level);
    public abstract void createEntities();
    public int getWidth() {
        return _width;
    }
    public int getHeight() {
        return _height;
    }
    public int getLevel() {
        return _level;
    }

}

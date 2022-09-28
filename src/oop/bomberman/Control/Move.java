package oop.bomberman.Control;

import oop.bomberman.entities.character.Bomber;
import oop.bomberman.entities.character.character;
import oop.bomberman.graphics.Sprite;

public class Move {
    public static void checkRun(character chars) {
        if(chars instanceof Bomber && chars.getCount() > 0) {
            setDirection(chars.getDirection(),chars,4);
            chars.setCount(chars.getCount() - 1);
        }
    }
    public static void setDirection(String direction,character chars,int isMove) {
        switch (direction) {
            case "down":
                down_step(chars);
                chars.setY(chars.getY() + isMove);
                break;
            case "up":
                up_step(chars);
                chars.setY(chars.getY() - isMove);
                break;
            case "left":
                left_step(chars);
                chars.setX(chars.getX() - isMove);
                break;
            case "right":
                right_step(chars);
                chars.setX(chars.getX() + isMove);


                break;

        }
    }
    public static void down(character chars) {
        if (chars.getY() % 16 == 0 && chars.getX() % 16 == 0) {
            if (chars instanceof Bomber && chars.getY() < 448-32) {
                chars.setDirection("down");
                chars.setCount(4);
                checkRun(chars);
            }
        }
    }
    public static void up(character chars) {
        if (chars.getY() % 16 == 0 && chars.getX() % 16 == 0) {
            if (chars instanceof Bomber && chars.getY() > 32) {
                chars.setDirection("up");
                chars.setCount(4);
                checkRun(chars);
            }
        }
    }
    public static void left(character chars) {
        if (chars.getY() % 16 == 0 && chars.getX() % 16 == 0) {
            if (chars instanceof Bomber && chars.getX() > 32 ) {
                chars.setDirection("left");
                chars.setCount(4);
                checkRun(chars);
            }
        }
    }
    public static void right(character chars) {
        if (chars.getY() % 16 == 0 && chars.getX() % 16 == 0) {
            if (chars instanceof Bomber && chars.getX() < 768-32 ) {
                chars.setDirection("right");
                chars.setCount(4);
                checkRun(chars);
            }
        }
    }
    public static void down_step(character chars) {
        if(chars instanceof Bomber && chars.getY() % 4 == 0) {
            if (chars.getSwap() == 1) {
                chars.setImg(Sprite.player_down.getFxImage());
                chars.setSwap(2);
            } else if (chars.getSwap() == 2) {
                chars.setImg(Sprite.player_down_1.getFxImage());
                chars.setSwap(3);
            } else if (chars.getSwap() == 3) {
                chars.setImg(Sprite.player_down.getFxImage());
                chars.setSwap(4);
            }
            else {
                chars.setImg(Sprite.player_down_2.getFxImage());
                chars.setSwap(1);
            }
        }
    }
    public static void up_step(character chars) {
        if(chars instanceof Bomber && chars.getY() % 4 == 0) {
            if (chars.getSwap() == 1) {
                chars.setImg(Sprite.player_up.getFxImage());
                chars.setSwap(2);
            } else if (chars.getSwap() == 2) {
                chars.setImg(Sprite.player_up_1.getFxImage());
                chars.setSwap(3);
            } else if (chars.getSwap() == 3) {
                chars.setImg(Sprite.player_up.getFxImage());
                chars.setSwap(4);
            }
            else {
                chars.setImg(Sprite.player_up_2.getFxImage());
                chars.setSwap(1);
            }
        }
    }
    public static void left_step(character chars) {
        if(chars instanceof Bomber && chars.getX() % 4 == 0) {
            if (chars.getSwap() == 1) {
                chars.setImg(Sprite.player_left.getFxImage());
                chars.setSwap(2);
            } else if (chars.getSwap() == 2) {
                chars.setImg(Sprite.player_left_1.getFxImage());
                chars.setSwap(3);
            } else if (chars.getSwap() == 3) {
                chars.setImg(Sprite.player_left.getFxImage());
                chars.setSwap(4);
            }
            else {
                chars.setImg(Sprite.player_left_2.getFxImage());
                chars.setSwap(1);
            }
        }
    }
    public static void right_step(character chars) {
        if(chars instanceof Bomber && chars.getX() % 4 == 0) {
            if (chars.getSwap() == 1) {
                chars.setImg(Sprite.player_right.getFxImage());
                chars.setSwap(2);
            } else if (chars.getSwap() == 2) {
                chars.setImg(Sprite.player_right_1.getFxImage());
                chars.setSwap(3);
            } else if (chars.getSwap() == 3) {
                chars.setImg(Sprite.player_right.getFxImage());
                chars.setSwap(4);
            }
            else {
                chars.setImg(Sprite.player_right_2.getFxImage());
                chars.setSwap(1);
            }
        }
    }
}

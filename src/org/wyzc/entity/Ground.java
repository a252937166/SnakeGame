package org.wyzc.entity;

import org.wyzc.util.Global;

import java.awt.*;
import java.util.Random;

/**
 * 障碍物
 * Created by OyDn on 2016/2/6.
 */
public class Ground {
    private int[][] rocks = new int[Global.WIDTH + 1][Global.HEIGHT + 1];
    public Ground() {
        for (int y = 0; y <= Global.HEIGHT; y++) {
            for (int x = 0; x <= Global.WIDTH; x++) {
                if (y==0 || y==Global.HEIGHT || x==0 || x==Global.WIDTH) {
                    rocks[x][y] = 1;
                }
            }
        }
    }

    public int[][] getRocks() {
        return rocks;
    }

    public void drawMe(Graphics g) {
        System.out.println("障碍物正在画出自己");
        g.setColor(Color.green);
        for (int y = 0; y <= Global.HEIGHT; y++) {
            for (int x = 0; x <= Global.WIDTH; x++) {
                if (rocks[x][y] == 1) {
                    g.fill3DRect(x*Global.CELL_SIZE,y*Global.CELL_SIZE,Global.CELL_SIZE,Global.CELL_SIZE,true);
                }
            }
        }
    }

    /**
     * 蛇是否碰到障碍物
     * @param snake
     * @return
     */
    public boolean isEatBySnake (Snake snake) {
        System.out.println("判断蛇是否碰到了障碍物");
        for (int x = 0; x <= Global.WIDTH; x++) {
            for (int y = 0; y <= Global.HEIGHT; y++) {
                if (rocks[x][y] == 1 && snake.getHead().x == x && snake.getHead().y == y) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 设置食物点的位置
     * @return
     */
    public Point setPoint(Snake snake) {
        int x,y;
        do {
            x = new Random().nextInt(Global.WIDTH-2) + 1;
            y = new Random().nextInt(Global.HEIGHT-2) + 1;
            for (int i = 0; i < snake.getBody().size(); i++) {
                if(snake.getBody().get(i).equals(new Point(x,y))) {
                    rocks[x][y] = 2;
                }
            }
        }while (rocks[x][y] == 1 || rocks[x][y] == 2);
        for (int i = 0; i < snake.getBody().size(); i++) {
            for (int j = 0; j <= Global.HEIGHT; j++) {
                if (rocks[i][j] == 2) {
                    rocks[i][j] = 0;
                }
            }
        }
        return new Point(x,y);
    }
}

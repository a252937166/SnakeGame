package org.wyzc.entity;

import org.wyzc.util.Global;

import java.awt.*;

/**
 * 食物
 * Created by OyDn on 2016/2/6.
 */
public class Food extends Point {
    public void drawMe(Graphics g) {
        g.setColor(Color.red);
        System.out.println("食物正在画出自己");
        g.fill3DRect(x* Global.CELL_SIZE,y*Global.CELL_SIZE,Global.CELL_SIZE,Global.CELL_SIZE,true);
    }

    /**
     * 蛇是否碰到食物
     * 判断蛇头的点是否和食物的位置重合
     * 要得到蛇头
     * @param snake
     * @return
     */
    public boolean isEatBySnake (Snake snake) {
        Point head = snake.getHead();
        if (this.equals(head)) {
            return true;
        }
        System.out.println("判断蛇是否吃到了食物");
        return false;
    }
    public void addFood(Point p) {
        this.x = p.x;
        this.y = p.y;
    }
}

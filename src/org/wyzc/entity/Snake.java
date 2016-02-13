package org.wyzc.entity;

import org.wyzc.listener.SnakeListener;
import org.wyzc.util.Global;

import java.awt.*;
import java.util.LinkedList;

/**
 * 蛇
 * Created by OyDn on 2016/2/6.
 */
public class Snake {
    private SnakeListener snakeListener;
    private boolean life = true;

    public boolean isLife() {
        return life;
    }

    public void setLife(boolean life) {
        this.life = life;
    }

    private LinkedList<Point> body = new LinkedList<>();
    public static final int UP = 1;
    public static final int DOWN = -1;

    public LinkedList<Point> getBody() {
        return body;
    }

    public static final int LEFT = 3;
    public static final int RIGHT = -3;
    public int direction;//存储当前方向
    private int oldDirection,newDirection;
    private Point tail;//存储尾巴
    public Snake() {
        init();
    }

    /**
     * 获取蛇头
     * @return
     */
    public Point getHead() {
        return body.getFirst();
    }

    private void init() {
        int x = Global.WIDTH/2;
        int y = Global.HEIGHT/2;
        for (int i = 0; i < 3; i++) {
            body.add(new Point(x - i,y));
        }
        this.oldDirection = this.newDirection = RIGHT;
    }

    /**
     * 蛇移动的方法
     */
    public void move() {
        //去尾巴
        tail = body.removeLast();
        //加头-->得到当前头部
        int x = body.getFirst().x;
        int y = body.getFirst().y;
        //获得新的头部，要确定方向，才能确定新的头部
        if (this.oldDirection + this.newDirection != 0) {
            this.oldDirection = this.newDirection;
        }
        switch (oldDirection) {
            case UP:
                y--;
                if (y < 0) {
                    y = Global.HEIGHT - 1;
                }
                break;
            case DOWN:
                y++;
                if (y > Global.HEIGHT - 1) {
                    y = 0;
                }
                break;
            case LEFT:
                x--;
                if ( x < 0) {
                    x = Global.WIDTH - 1;
                }
                break;
            case RIGHT:
                x++;
                if (x > Global.WIDTH - 1) {
                    x = 0;
                }
                break;
        }
        body.addFirst(new Point(x,y));
        System.out.println("蛇正在移动....");
    }

    /**
     * 吃食物
     * @param food 食物
     */
    public void eatFood(Food food) {
        body.addLast(tail);
        System.out.println("蛇正在吃食物....");
    }

    /**
     * 改变方向
     */
    public void changeDirection(int direction) {
        this.newDirection = direction;
        System.out.println("蛇正在改变方向...");
    }

    /**
     * 画出自己
     */
    public void drawMe(Graphics g) {
        System.out.println("蛇正在画自己...");
        g.setColor(Color.blue);
        for (Point p : body) {
            g.fill3DRect(p.x*Global.CELL_SIZE,p.y*Global.CELL_SIZE,Global.CELL_SIZE,Global.CELL_SIZE,true);
        }
    }

    /**
     * 是否吃到自己
     * @return
     */
    public boolean isEatSelf () {
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).equals(getHead())) {
                return true;
            }
        }
        return false;
    }
    public void addSnakeListener(SnakeListener snakeListener) {
        if (snakeListener != null) {
            this.snakeListener = snakeListener;
        }
    }
    public void start() {
        new SnakeDriver().start();
    }
    private class SnakeDriver extends Thread {
        @Override
        public void run() {
            while (life) {
                move();
                snakeListener.snakeMove(Snake.this);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

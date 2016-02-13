package org.wyzc.control;

import org.wyzc.entity.Food;
import org.wyzc.entity.Ground;
import org.wyzc.entity.Snake;
import org.wyzc.listener.SnakeListener;
import org.wyzc.util.Global;
import org.wyzc.view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * 控制器
 * Created by OyDn on 2016/2/6.
 */
public class Controller extends KeyAdapter implements SnakeListener{
    private Snake snake;
    private Food food;
    private Ground ground;
    private GamePanel gamePanel;

    public Controller(Snake snake, Food food, Ground ground, GamePanel gamePanel) {
        this.snake = snake;
        this.food = food;
        this.ground = ground;
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                snake.changeDirection(Snake.UP);
                break;
            case KeyEvent.VK_DOWN:
                snake.changeDirection(Snake.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                snake.changeDirection(Snake.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                snake.changeDirection(Snake.RIGHT);
                break;
        }

    }

    @Override
    public void snakeMove(Snake snake) {
        int count = 0;
        System.out.println("判断蛇是否碰到自己、食物、障碍物");
        if (food.isEatBySnake(snake)) {
            snake.eatFood(food);
            System.out.println("判断长度");
            if(snake.getBody().size() == (Global.WIDTH - 1) * (Global.HEIGHT - 1)) {
                JOptionPane.showConfirmDialog(null,"You Win!");
                snake.setLife(false);
                System.exit(0);
            }
            food.addFood(ground.setPoint(snake));
        }
        if (ground.isEatBySnake(snake) || snake.isEatSelf()) {
            JOptionPane.showConfirmDialog(null,"GameOver!");
            snake.setLife(false);
            System.exit(0);
        }
        gamePanel.display(snake,food,ground);
    }

    /**
     * 游戏开始
     */
    public void startGame() {
        snake.start();
        food.addFood(ground.setPoint(snake));
    }


}

package org.wyzc.test;

import org.wyzc.control.Controller;
import org.wyzc.entity.Food;
import org.wyzc.entity.Ground;
import org.wyzc.entity.Snake;
import org.wyzc.util.Global;
import org.wyzc.view.GamePanel;

import javax.swing.*;

/**
 * Created by OyDn on 2016/2/6.
 */
public class SnakeGameTest {
    public static void main(String[] args) {
        //创建实体对象
        Snake snake = new Snake();
        Food food = new Food();
        Ground ground = new Ground();
        //视图对象的创建
        GamePanel gamePanel = new GamePanel();
        //控制器的创建
        Controller controller = new Controller(snake,food,ground,gamePanel);
        snake.addSnakeListener(controller);
        gamePanel.addKeyListener(controller);
        JFrame frame = new JFrame("贪吃蛇Version1.0");
        frame.setSize(Global.CELL_SIZE*Global.WIDTH + 100,Global.CELL_SIZE*Global.WIDTH + 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //让面板获得焦点
        gamePanel.setFocusable(true);
        frame.add(gamePanel);
        //启动游戏
        controller.startGame();
        //显示
        frame.setVisible(true);
    }
}

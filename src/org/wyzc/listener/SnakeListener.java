package org.wyzc.listener;

import org.wyzc.entity.Snake;

/**
 * 监听器
 * 只要监听蛇的移动
 * Created by OyDn on 2016/2/6.
 */
public interface SnakeListener {
    /**
     * 该方法监听蛇是否碰到了自己、食物、障碍物
     * @param snake
     */
    public void snakeMove(Snake snake);
}

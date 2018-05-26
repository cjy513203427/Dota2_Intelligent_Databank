package cn.demo.service1.util;

import redis.clients.jedis.Jedis;

/**
 * Created by hasee on 2018/5/25.
 */
public class JedisUtil {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("127.0.0.1");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
    }
}

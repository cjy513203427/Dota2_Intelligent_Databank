package cn.demo.service0.dao;

import cn.demo.service0.entity.Hero;

import java.util.List;

/**
 * Created by hasee on 2018/3/27.
 */
public interface HeroDao {
    /**
     * 列出英雄列表
     * @return
     */
    List<Hero> queryHero(Hero hero);

    Integer countQueryHero(Hero hero);
}

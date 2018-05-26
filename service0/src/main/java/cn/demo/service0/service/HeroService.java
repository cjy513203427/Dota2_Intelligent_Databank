package cn.demo.service0.service;

import cn.demo.service0.entity.Hero;
;

import java.util.List;

/**
 * Created by hasee on 2018/3/29.
 */
public interface HeroService {

    List<Hero> queryHero(Hero hero);
}

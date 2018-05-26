package cn.demo.service0.service.impl;

import cn.demo.service0.dao.HeroDao;
import cn.demo.service0.entity.Hero;
import cn.demo.service0.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by hasee on 2018/3/29.
 */
@Service
public class HeroServiceImpl implements HeroService {
    @Autowired
    private HeroDao heroDao;
    @Override
    public List<Hero> queryHero(Hero hero) {
        return heroDao.queryHero(hero);
    }

}

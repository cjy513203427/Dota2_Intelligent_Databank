package cn.demo.service0.controller;

import cn.demo.service0.entity.Hero;
import cn.demo.service0.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hasee on 2018/3/29.
 */
@RestController
@RequestMapping("/superadmin")
public class HeroController {
    @Autowired
    private HeroService heroService;

    @RequestMapping(value = "/queryHero",method = RequestMethod.GET)
    private Map<String,Object> listArea(@RequestParam(value="localizedName",required=false) String localizedName,
                                        @RequestParam(value="chinseName",required=false) String chineseName){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        Hero hero = new Hero();
        hero.setLocalizedName(localizedName);
        hero.setChineseName(chineseName);
        List<Hero> list = heroService.queryHero(hero);
        modelMap.put("heroList",list);
        return modelMap;
    }
}

package cn.demo.service1.service.impl;

import cn.demo.service1.bean.PlayersBean;
import cn.demo.service1.bean.SteamAccountBean;
import cn.demo.service1.dao.MatchDetailPlayersDao;
import cn.demo.service1.entity.Players;
import cn.demo.service1.entity.SteamAccount;
import cn.demo.service1.service.MatchDetailPlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hasee on 2018/5/21.
 */
@Service
public class MatchDetailPlayersServiceImpl implements MatchDetailPlayersService {
    @Autowired
    private MatchDetailPlayersDao matchDetailPlayersDao;
    @Override
    public void importMatchDetailPlayersFromSteamAPI(PlayersBean playersBean) {
        matchDetailPlayersDao.importMatchDetailPlayersFromSteamAPI(playersBean);
    }

    @Override
    public void importSteamAccountsFromSteamAPI(SteamAccountBean steamAccountBean) {
        matchDetailPlayersDao.importSteamAccountsFromSteamAPI(steamAccountBean);
    }

    @Override
    public Integer ifMatchIdExists(Long match_id) {
        return matchDetailPlayersDao.ifMatchIdExists(match_id);
    }

    @Override
    public Integer ifSteamAccountExists(Long steamid) {
        return matchDetailPlayersDao.ifSteamAccountExists(steamid);
    }

    @Override
    public List<Players> queryMatchDetailPlayers(Long match_id) {
        List<Players> list = null;
        list=matchDetailPlayersDao.queryMatchDetailPlayers(match_id);
        return list;
    }
}

package cn.demo.service1.service;

import cn.demo.service1.bean.PlayersBean;
import cn.demo.service1.bean.SteamAccountBean;
import cn.demo.service1.entity.Players;

import java.util.List;

/**
 * Created by hasee on 2018/5/21.
 */

public interface MatchDetailPlayersService {
    void importMatchDetailPlayersFromSteamAPI(PlayersBean playersBean);

    void importSteamAccountsFromSteamAPI(SteamAccountBean steamAccountBean);

    Integer ifMatchIdExists(Long match_id);

    Integer ifSteamAccountExists(Long steamid);

    List<Players> queryMatchDetailPlayers(Long match_id);
}

package cn.demo.service1.dao;

import cn.demo.service1.bean.PlayersBean;
import cn.demo.service1.bean.SteamAccountBean;
import cn.demo.service1.entity.Players;
import cn.demo.service1.entity.SteamAccount;

import java.util.List;

/**
 * Created by hasee on 2018/5/21.
 */
public interface MatchDetailPlayersDao {
    void importMatchDetailPlayersFromSteamAPI(PlayersBean playersBean);

    void importSteamAccountsFromSteamAPI(SteamAccountBean steamAccountBean);

    Integer ifMatchIdExists(Long match_id);

    Integer ifSteamAccountExists(Long steamid);

    List<Players> queryMatchDetailPlayers(Long match_id);
}

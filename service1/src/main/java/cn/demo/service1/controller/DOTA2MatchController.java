package cn.demo.service1.controller;

import cn.demo.service1.bean.PlayersBean;
import cn.demo.service1.bean.SteamAccountBean;
import cn.demo.service1.entity.*;
import cn.demo.service1.service.MatchDetailPlayersService;
import cn.demo.service1.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hasee on 2018/5/21.
 */
@RestController
@RequestMapping("/superadmin1")
public class DOTA2MatchController {
    @Value("${steam.key}")
    protected String steamKey;

    @Autowired
    MatchDetailPlayersService matchDetailPlayersService;

    @RequestMapping(value = "/getMatchHistory",method = RequestMethod.GET)
    private Map<String,Object> getMatchHistory(@RequestParam(value="account_id",required=false) Long account_id) throws IOException {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        String jsonArray = "";
        if (account_id == null || account_id .equals("")) {
            jsonArray = URLUtil.getUrlForMatchHistory("https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/v1/?key=" + steamKey + "&account_id=" + 191017855);
        }else{
            jsonArray = URLUtil.getUrlForMatchHistory("https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/v1/?key=" + steamKey + "&account_id=" + account_id);
        }
        List<MatchHistory> matchHistories = GsonUtil.getObjectList(jsonArray,MatchHistory.class);
        for (MatchHistory matchHistory:matchHistories){
            String startTime = String.valueOf(matchHistory.getStart_time());
            String normalStartTime = DateUtil.TimeStamp2Date(startTime);
            matchHistory.setString_start_time(normalStartTime);
        }
        Map<String,Object> data = new HashMap();
        modelMap.put("data",data);
        data.put("list",matchHistories);
        data.put("total",matchHistories.size());
        modelMap.put("success",true);
        return modelMap;
    }

    @RequestMapping(value = "/getMatchDetail",method = RequestMethod.GET)
    private Map<String,Object> getMatchDetail(@RequestParam(value="match_id",required=false) Long match_id) throws IOException {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        String jsonArray = "";
        if (match_id == null || match_id .equals("")) {
            jsonArray = URLUtil.getUrlForMatchDetail("https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/v1/?key=" + steamKey + "&match_id=" + "3839053706");
        }else{
            jsonArray = URLUtil.getUrlForMatchDetail("https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/v1/?key=" + steamKey + "&match_id=" + match_id);
        }
        List<MatchDetail> matchDetails = GsonUtil.getObjectList(jsonArray,MatchDetail.class);
        for (MatchDetail matchDetail:matchDetails){
            String startTime = String.valueOf(matchDetail.getStart_time());
            String normalStartTime = DateUtil.TimeStamp2Date(startTime);
            matchDetail.setString_start_time(normalStartTime);
            matchDetail.setPlayer_if_win(false);
            matchDetail.setString_duration(TimeUtil.secToMinute(matchDetail.getDuration()));
            List<Players> players = matchDetail.getPlayers();
            for(Players player:players){
                if(player.getAccount_id().equals(191017855L)&&player.getPlayer_slot()>=128
                        &&matchDetail.getRadiant_win().equals(false)){
                    matchDetail.setPlayer_if_win(true);
                }else if(player.getAccount_id().equals(191017855L)&&player.getPlayer_slot()<=4
                        &&matchDetail.getRadiant_win().equals(true)) {
                    matchDetail.setPlayer_if_win(true);
                }
            }
        }



        Map<String,Object> data = new HashMap();
        modelMap.put("data",data);
        data.put("list",matchDetails);
        data.put("total",matchDetails.size());
        modelMap.put("success",true);
        return modelMap;
    }

    @RequestMapping(value = "/getMatchDetailPlayers",method = RequestMethod.GET)
    private Map<String,Object> getMatchDetailPlayers(@RequestParam(value="match_id",required=false) Long match_id) throws IOException {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        String jsonArray = URLUtil.getUrlForMatchDetailPlayers("https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/v1/?key=" + steamKey + "&match_id=" + match_id);

        List<Players> players = GsonUtil.getObjectList(jsonArray,Players.class);
        PlayersBean playersBean = new PlayersBean();

        if(matchDetailPlayersService.ifMatchIdExists(match_id)==0) {
            for(Players player:players){
                playersBean.setAccount_id(player.getAccount_id());
                Long STEAM64ID = SteamUtil.generateSTEAMID64((player.getAccount_id()) );
                String jsonSteamAccountArray = URLUtil.getUrlForSteamAccount("https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v1/?key=" + steamKey + "&steamids=" + STEAM64ID);

                List<SteamAccount> steamAccounts = GsonUtil.getObjectList(jsonSteamAccountArray,SteamAccount.class);
                if(matchDetailPlayersService.ifSteamAccountExists(SteamUtil.generateSTEAMID64(player.getAccount_id()))==0) {
                    for (SteamAccount steamAccount : steamAccounts) {
                        SteamAccountBean steamAccountBean = new SteamAccountBean();
                        if (steamAccount != null) {
                            steamAccountBean.setSteamid(steamAccount.getSteamid());
                            steamAccountBean.setCommunityvisibilitystate(steamAccount.getCommunityvisibilitystate());
                            steamAccountBean.setPersonaname(steamAccount.getPersonaname());
                            String lastlogoff = String.valueOf(steamAccount.getLastlogoff());
                            steamAccountBean.setString_lastlogoff(DateUtil.TimeStamp2Date(lastlogoff));
                            steamAccountBean.setProfileurl(steamAccount.getProfileurl());
                            steamAccountBean.setAvatar(steamAccount.getAvatar());
                            steamAccountBean.setAvatarmedium(steamAccount.getAvatarmedium());
                            steamAccountBean.setAvatarfull(steamAccount.getAvatarfull());
                            steamAccountBean.setPersonastate(steamAccount.getPersonastate());
                            steamAccountBean.setPrimaryclanid(steamAccount.getPrimaryclanid());
                            if(steamAccount.getTimecreated() != null) {
                                String timecreated = String.valueOf(steamAccount.getTimecreated());
                                steamAccountBean.setString_timecreated(DateUtil.TimeStamp2Date(timecreated));
                            }else{
                                steamAccountBean.setString_timecreated(null);
                            }
                            steamAccountBean.setPersonastateflags(steamAccount.getPersonastateflags());
                            matchDetailPlayersService.importSteamAccountsFromSteamAPI(steamAccountBean);
                        }else{
                            steamAccountBean.setSteamid(76561202255233023L);
                            steamAccountBean.setCommunityvisibilitystate(null);
                            steamAccountBean.setPersonaname("匿名");
                            steamAccountBean.setLastlogoff(null);
                            steamAccountBean.setProfileurl(null);
                            steamAccountBean.setAvatar(null);
                            steamAccountBean.setAvatarmedium(null);
                            steamAccountBean.setAvatarfull(null);
                            steamAccountBean.setPersonastate(null);
                            steamAccountBean.setPrimaryclanid(null);
                            steamAccountBean.setString_timecreated(null);
                            steamAccountBean.setPersonastateflags(null);
                            matchDetailPlayersService.importSteamAccountsFromSteamAPI(steamAccountBean);
                        }
                    }
                }

                playersBean.setPlayer_slot(player.getPlayer_slot());
                playersBean.setHero_id(player.getHero_id());
                if(player.getItem_0()==0){
                    playersBean.setItem_0(1027);
                }else {
                    playersBean.setItem_0(player.getItem_0());
                }
                if(player.getItem_1()==0){
                    playersBean.setItem_1(1027);
                }else {
                    playersBean.setItem_1(player.getItem_1());
                }
                if(player.getItem_2()==0){
                    playersBean.setItem_2(1027);
                }else {
                    playersBean.setItem_2(player.getItem_2());
                }
                if(player.getItem_3()==0){
                    playersBean.setItem_3(1027);
                }else {
                    playersBean.setItem_3(player.getItem_3());
                }
                if(player.getItem_4()==0){
                    playersBean.setItem_4(1027);
                }else {
                    playersBean.setItem_4(player.getItem_4());
                }
                if(player.getItem_5()==0){
                    playersBean.setItem_5(1027);
                }else {
                    playersBean.setItem_5(player.getItem_5());
                }
                playersBean.setBackpack_0(player.getBackpack_0());
                playersBean.setBackpack_1(player.getBackpack_1());
                playersBean.setBackpack_2(player.getBackpack_2());
                playersBean.setKills(player.getKills());
                playersBean.setDeaths(player.getDeaths());
                playersBean.setAssists(player.getAssists());
                playersBean.setLeaver_status(player.getLeaver_status());
                playersBean.setLast_hits(player.getLast_hits());
                playersBean.setDenies(player.getDenies());
                playersBean.setGold_per_min(player.getGold_per_min());
                playersBean.setXp_per_min(player.getXp_per_min());
                playersBean.setLevel(player.getLevel());
                playersBean.setHero_damage(player.getHero_damage());
                playersBean.setTower_damage(player.getTower_damage());
                playersBean.setHero_healing(player.getHero_healing());
                playersBean.setGold(player.getGold());
                playersBean.setGold_spent(player.getGold_spent());
                playersBean.setScaled_hero_damage(player.getScaled_hero_damage());
                playersBean.setScaled_tower_damage(player.getTower_damage());
                playersBean.setScaled_hero_healing(player.getScaled_hero_healing());
                playersBean.setMatch_id(match_id);

                matchDetailPlayersService.importMatchDetailPlayersFromSteamAPI(playersBean);
            }
        }
        Map<String,Object> data = new HashMap<String,Object>();
        List<Players> newPlayers = matchDetailPlayersService.queryMatchDetailPlayers(match_id);
        modelMap.put("data",data);
        data.put("list",newPlayers);
        data.put("total",newPlayers.size());
        modelMap.put("success",true);
        return modelMap;
    }
}

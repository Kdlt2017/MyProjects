/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.kdlt.services;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.Remote;
import tn.kdlt.entities.Administrator;
import tn.kdlt.entities.Auth2FA;
import tn.kdlt.entities.Player;
import tn.kdlt.entities.Session;
import tn.kdlt.entities.User;

/**
 *
 * @author pc
 */
@Remote
public interface RankMeServiceRemote {
    
        //Authentication Services
    public User getUserByUsername(String username);
    public Auth2FA geAuth2FAByUid(String uid, String auth2FAToken);
    Session finalizeLogin(String uid, String auth2FAToken);
    String login(String username, String password) throws NoSuchAlgorithmException;
    int isLoggedIn(final String token);
    
    //Managing Users
    public Long createAdmin(Administrator admin);
    public Long createPlayer(Player player);
    
    //RANKING
    public List<Player> rankPlayers(List<Player> players);
    public List<Player> rankPlayersByCategory(String catName);
}

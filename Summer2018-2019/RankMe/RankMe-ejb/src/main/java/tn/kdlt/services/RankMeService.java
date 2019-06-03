/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.kdlt.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import tn.kdlt.entities.Administrator;
import tn.kdlt.entities.Auth2FA;
import tn.kdlt.entities.Player;
import tn.kdlt.entities.Session;
import tn.kdlt.entities.User;
import tn.kdlt.utils.MailSender;

/**
 *
 * @author pc
 */
@Stateless
public class RankMeService implements RankMeServiceRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "RankMe-ejb")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        try {
            user = em.createQuery("SELECT U FROM User U where U.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException nre) {
        }
        return user;
    }

    @Override
    public Auth2FA geAuth2FAByUid(String uid, String auth2FAToken) {
        TypedQuery<Auth2FA> query = em.createQuery("SELECT t FROM Auth2FA t WHERE t.uid = :uid", Auth2FA.class);
        query.setParameter("uid", uid);
        List<Auth2FA> auth2FAList = query.getResultList();
        if (!auth2FAList.isEmpty()) {
            Auth2FA auth2FA = auth2FAList.get(0);
            String strToken = Integer.toString(auth2FA.getToken());
            if (strToken.equals(auth2FAToken)) {
                return auth2FA;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Session finalizeLogin(String uid, String auth2FAToken) {
        Auth2FA auth2FA = this.geAuth2FAByUid(uid, auth2FAToken);
        if (auth2FA == null) {
            return null;
        }

        Session s = new Session();
        s.setUid(auth2FA.getUid());
        s.setUser(auth2FA.getUser());
        em.remove(auth2FA);
        em.persist(s);
        return s;
    }

    @Override
    public String login(String username, String password) throws NoSuchAlgorithmException {
        Logger.getAnonymousLogger().info("given password " + password + "Username " + username);
        byte[] pwd = password.getBytes(StandardCharsets.UTF_8);
        User user = this.getUserByUsername(username);

        if (user == null) {
            return null;
        }
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = new byte[pwd.length + user.getSalt().length];
        System.arraycopy(pwd, 0, hashBytes, 0, pwd.length);
        System.arraycopy(user.getSalt(), 0, hashBytes, pwd.length, user.getSalt().length);
        Logger.getAnonymousLogger().info("*PASS*****" + user.getPassword());
        ArrayList<Byte> a;
        a = new ArrayList<>();
        for (byte b : user.getPassword()) {
            a.add(b);
        }
        a.add((byte) 101);
        a.add((byte) 101);
        for (byte b : digest.digest(hashBytes)) {
            a.add(b);
        }
        a.add((byte) 101);
        a.add((byte) 101);

        String s = new String();
        for (byte b : a) {
            s += b;
        }

        Logger.getAnonymousLogger().info("tow byte arr " + s);
        if (Arrays.equals(user.getPassword(), digest.digest(hashBytes))) {
            // Create new 2FA token + random token for identification
            String uid = UUID.randomUUID().toString();
            int token = gen2FAToken();
            Auth2FA towFactorAuth = new Auth2FA(token, uid, user);
            // Persist 2FA token
            em.persist(towFactorAuth);
            try {
                // Send email
                // Username and password are redacted
                System.out.println("****" + towFactorAuth.getToken());
                if (MailSender.sendMail("smtp.gmail.com", "587",
                        "pidevnoreply@gmail.com", "pidevnoreply@gmail.com",
                        "pidevpidev", user.getEmail(), "Authentication code", "Your code is " + towFactorAuth.getToken())) {
                    return towFactorAuth.getUid();
                }

            } catch (MessagingException ex) {
                Logger.getAnonymousLogger().info("Erreur");
                //Logger.getLogger(AuthenticationFacade.class.getName()).log(Level.SEVERE, null, ex);

            }

        } else {
            return null;
        }
        return null;
    }

    @Override
    public int isLoggedIn(final String token) {
        return 0;
    }

    private String genToken() {
        return "succes";
    }

    private int gen2FAToken() {
        int randomNum = ThreadLocalRandom.current().nextInt(1666, 9999 + 1);
        return randomNum;
    }
    
    
    //MANAGING USERS
    
    @Override
    public Long createAdmin(Administrator admin){
        
        em.persist(admin);
        return admin.getId();
    }
    
    @Override
    public Long createPlayer(Player player){
        em.persist(player);
        return player.getId();
    }

    //RANKING
    @Override
    public List<Player> rankPlayers(List<Player> players) {

        Collections.sort(players, (Player a, Player b) -> a.getPoints() - (b.getPoints()));
        int[] Points = null;
        int j = 0;
        for (Player p : players) {
            Points[j] = p.getPoints();
            j++;
        }
        j = 0;

        int[] rankings = new int[players.size()];
        rankings[0] = 1;
        for (int i = 1; i < rankings.length; i++) {
            rankings[i] = (Points[i] == Points[i - 1]) ? rankings[i - 1] : i + 1;
        }

        for (Player p : players) {
            p.setRank(rankings[j]);
            j++;
        }
        return players;
    }
    
    @Override
    public List<Player> rankPlayersByCategory(String catName){
        
        List<Player> players = null;
        try {
            players = em.createQuery("SELECT P from Player P join P.awayGames A join P.homeGames H "
                    + "where A.category.nameCat = :catName "
                    + "AND H.category.nameCat = :catName ", Player.class)
                    .setParameter("catName", catName)
                    .getResultList();
        } catch (NoResultException nre) {
        }
        return this.rankPlayers(players);
    }

}

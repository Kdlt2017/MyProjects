/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.kdlt.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author pc
 */
@Entity
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @Enumerated(EnumType.STRING)
    private GameName gameName;
    
    private int homeScore;
    private int awayScore;

    @ManyToOne
    private GameCategory category;
    
    @ManyToOne
    private Player homePlayer;

    @ManyToOne
    private Player awayPlayer;

    public Game(GameStatus gameStatus, GameName gameName, int homeScore, int awayScore) {
        this.gameStatus = gameStatus;
        this.gameName = gameName;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public Game() {
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameName getGameName() {
        return gameName;
    }

    public void setGameName(GameName gameName) {
        this.gameName = gameName;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public GameCategory getCategory() {
        return category;
    }

    public void setCategory(GameCategory category) {
        this.category = category;
    }

    public Player getHomePlayer() {
        return homePlayer;
    }

    public void setHomePlayer(Player homePlayer) {
        this.homePlayer = homePlayer;
    }

    public Player getAwayPlayer() {
        return awayPlayer;
    }

    public void setAwayPlayer(Player awayPlayer) {
        this.awayPlayer = awayPlayer;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Game)) {
            return false;
        }
        Game other = (Game) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.kdlt.entities.Game[ id=" + id + " ]";
    }

}

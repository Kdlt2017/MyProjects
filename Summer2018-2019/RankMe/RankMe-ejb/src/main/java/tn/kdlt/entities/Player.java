/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.kdlt.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author pc
 */
@Entity
@DiscriminatorValue(value = "PLAYER")
public class Player extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    private int points;
    private int ranking;
    /*@ElementCollection
    @MapKeyColumn(name="Game")
    @Column(name="Score")
    @CollectionTable(name="Player_Games", joinColumns=@JoinColumn(name="player_id"))
    private  Map<Long, Integer> gameScores = new HashMap<Long, Integer>(); 
    */
    @OneToMany(mappedBy = "homePlayer")
    private  List<Game> homeGames = new ArrayList<>();
    
    @OneToMany(mappedBy = "awayPlayer")
    private  List<Game> awayGames = new ArrayList<>();

    public Player() {
    }

    public Player(String username, String email, String firstName, String lastName, byte[] password) {
        super(username, email, firstName, lastName, password);
    }
    
    
    

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRank() {
        return ranking;
    }

    public void setRank(int ranking) {
        this.ranking = ranking;
    }
/*
    public Map<Long, Integer> getGameScores() {
        return gameScores;
    }

    public void setGameScores(Map<Long, Integer> gameScores) {
        this.gameScores = gameScores;
    }
*/
    public List<Game> getHomeGames() {
        return homeGames;
    }

    public void setHomeGames(List<Game> homeGames) {
        this.homeGames = homeGames;
    }

    public List<Game> getAwayGames() {
        return awayGames;
    }

    public void setAwayGames(List<Game> awayGames) {
        this.awayGames = awayGames;
    }

    
    
    
    
}

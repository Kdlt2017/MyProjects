/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.kdlt.managedBeans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import tn.kdlt.services.RankMeServiceRemote;

/**
 *
 * @author pc
 */
@ManagedBean
@SessionScoped
public class RankMeBean implements Serializable{
    
    @EJB
    private RankMeServiceRemote service;
    
    private String login;
    private String pass;
    
    @PostConstruct
    void init(){
    
    }
}

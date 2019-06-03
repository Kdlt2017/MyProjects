/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.kdlt.managedBeans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import tn.kdlt.entities.Session;
import tn.kdlt.services.RankMeServiceRemote;
import tn.kdlt.utils.Authenticator;

/**
 *
 * @author pc
 */
@ManagedBean( name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{

    @EJB
    private RankMeServiceRemote service;

    private String login;
    private String pass;
    private String authUid;
    private String authToken;

    public RankMeServiceRemote getService() {
        return service;
    }

    public void setService(RankMeServiceRemote service) {
        this.service = service;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAuthUid() {
        return authUid;
    }

    public void setAuthUid(String authUid) {
        this.authUid = authUid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    
    
    
    
    
    
    
    public String doLogin() throws NoSuchAlgorithmException {
        String goTo = "";
        System.out.println("====Login" + login + " passssss===" + pass);
        authUid = service.login(login, pass);
        if (authUid != null) {
            goTo = "/twoFAConfirm?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage("Error", "Unknow Information");
            FacesContext.getCurrentInstance().addMessage("Error", msg);
        }
        System.out.println("ààààààààà" + goTo);
        return goTo;
    }

    public String doFinalizeLogin() {
        String goTo = "";
        Session session = service.finalizeLogin(authUid, authToken);
        if (session != null) {
            Authenticator.currentSession = session;
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", Authenticator.currentSession.getUser().getUsername());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            if (("Administrator").equals(Authenticator.currentSession.getUser().getRole())) {
                goTo = "/pages/admin/home?faces-redirect=true";//This is just for testing purpose until the actual page is created;
            } else {
                goTo = "/pages/player/home?faces-redirect=true";
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unsuccessful", "Check Your Code");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return goTo;
    }

}

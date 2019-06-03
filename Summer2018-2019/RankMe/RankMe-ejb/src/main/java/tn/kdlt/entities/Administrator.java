/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.kdlt.entities;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author pc
 */
@Entity
@DiscriminatorValue(value = "ADMIN")
public class Administrator extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    public Administrator() {
    }

    public Administrator(String username, String email, String firstName, String lastName) {
        super(username, email, firstName, lastName);
    }

    
    
}

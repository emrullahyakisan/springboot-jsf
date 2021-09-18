/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.jsf.session;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 *
 * @author Emrullah
 */
@Component
@SessionScope
public class SessionBean implements Serializable {

    private Object data;

    public Object getData() {
        System.out.println("Init Session Bean" + this);
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void gotoPage(String page) {
        ExternalContext ec = FacesContext.getCurrentInstance()
                .getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath()
                    + "/" + page + ".xhtml");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

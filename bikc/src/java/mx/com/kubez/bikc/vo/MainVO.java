/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.kubez.bikc.vo;

import mx.qubez.utilierias.dto.SessionDTO;

/**
 *
 * @author c-rlos
 */
public class MainVO {

    public static final long serialVersionUID = 112309872228765L;
    private SessionDTO session;

    public MainVO() {
        session = new SessionDTO("acceso");
        session.validaSession();
        
    }

}

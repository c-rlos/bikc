/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.kubez.bikc.vo;

import mx.com.kubez.bikc.dto.ConstanteDTO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import mx.qubez.utilierias.conexion.Factory;
import mx.qubez.utilierias.dto.SessionDTO;
import org.primefaces.context.RequestContext;

/**
 *
 * @author carlos.juarez
 */
@ManagedBean(name = "Acceso")
@RequestScoped
public class AccesoVO  {

    public static final long serialVersionUID = 11233432428765L;
//    private Session sesion;
    private String username;
    private String password;
    private SessionDTO session;

    public AccesoVO() {
        session = new SessionDTO("acceso");
        session.validaSession();
    }

//    private void writeObject(ObjectOutputStream out) throws IOException{
//        out.writeObject(username);
//        out.writeObject(password);
//        out.writeObject(session);
//    }
//    
//    private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException{
//        username = (String) is.readObject();
//        password = (String) is.readObject();
//        session = (SessionDTO) is.readObject();
//    }
    public String validaAcceso() {
        Connection con = null;
        String urlResponse = "";
        try {
            con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);

            urlResponse = session.validaAcceso(username, password, con);
            System.out.println(urlResponse);
            RequestContext context = RequestContext.getCurrentInstance();
            FacesMessage message = null;

//            if (username != null && username.equals("admin") && password != null && password.equals("admin")) {
//                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
//                try {
//                    FacesContext.getCurrentInstance().getExternalContext().redirect("main.jsf");
//                } catch (Exception ex) {
//                    Logger.getLogger(AccesoVO.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
////            loggedIn = false;
//                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de acceso", "Sus credenciales no son validas");
//            }

//        FacesContext.getCurrentInstance().addMessage(null, message);
//        context.addCallbackParam("loggedIn", loggedIn);
        } catch (Exception ex) {
            System.out.println("Error en AccesoVO.validaAcceso: " + ex.getMessage());
        } finally {
            Factory.close(con);

        }
        return urlResponse;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

package de.comparus.web.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by ibolshak on 5/26/17.
 */
@Component
public class RemoteJMXEntity{

    private boolean createConnection = false;

    private boolean sayHallo = false;
    private boolean getName = false;

    private String a = "";
    private String b = "";
    private String setName = "";

    public boolean getSayHallo() {
        return sayHallo;
    }

    public void setSayHallo(boolean sayHallo) {
        this.sayHallo = sayHallo;
    }

    public boolean getGetName() {
        return getName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public boolean getCreateConnection() {
        return createConnection;
    }

    public void setCreateConnection(boolean createConnection) {
        this.createConnection = createConnection;
    }

//--------------------------------------

    public void setGetName(boolean getName) {
        this.getName = getName;
    }

    public String getSetName() {
        return setName;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }





}

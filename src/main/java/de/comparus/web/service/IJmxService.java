package de.comparus.web.service;

import de.comparus.web.entity.RemoteJMXEntity;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ibolshak on 5/29/17.
 */
public interface IJmxService {

    public void remoteByEntity(RemoteJMXEntity remoteJMXEntity);

//    boolean createConnection();
//    boolean closeConnection();
//
//    void sayHello();
//    String addInteger(Integer a, Integer b);
//    void setName(String name);
//    String getName();
}

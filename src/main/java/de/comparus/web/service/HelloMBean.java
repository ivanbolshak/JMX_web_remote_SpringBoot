package de.comparus.web.service;

/**
 * Created by ibolshak on 5/24/17.
 */
public interface HelloMBean {

    void sayHello();

    int addInteger(int x, int y);

    String getName();

    void setName(String name);

    String returnName();

}

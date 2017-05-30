package de.comparus.web.service;

import de.comparus.web.entity.RemoteJMXEntity;
import org.springframework.stereotype.Service;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;

/**
 * Created by ibolshak on 5/26/17.
 */

@Service
public class JmxService implements IJmxService{

    private JMXServiceURL jmxServiceURL;
    private JMXConnector jmxConnector;
    private MBeanServerConnection mBeanServerConnection;
    private ObjectName objectNameBean;
    private HelloMBean helloMBeanProxy;


    public JmxService(){
//        createConnection();
    }

    public void sayHello(){
        helloMBeanProxy.sayHello();
        try {
            Thread.sleep(111);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public String addInteger(Integer a, Integer b){
        return String.valueOf(helloMBeanProxy.addInteger(a, b));
    }

    public void setName(String name){
        helloMBeanProxy.setName(name);
    }

    public String getName(){
        return helloMBeanProxy.getName();
    }

    public boolean createConnection(){
        try {
            this.jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
            this.jmxConnector = JMXConnectorFactory.connect(jmxServiceURL, null);

            this.mBeanServerConnection = jmxConnector.getMBeanServerConnection();

            this.objectNameBean = new ObjectName("de.comparus.jmx:type=Hello");
            this.helloMBeanProxy = JMX.newMBeanProxy(mBeanServerConnection, objectNameBean, HelloMBean.class, true);

            this.mBeanServerConnection.addNotificationListener(objectNameBean, new ClientListener(), null, null);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean closeConnection(){
        try {
            jmxConnector.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void remoteByEntity(RemoteJMXEntity remoteJMXEntity){

        if (remoteJMXEntity.getCreateConnection()){
            createConnection();
        }

        if (!remoteJMXEntity.getCreateConnection()){
            closeConnection();
            return;
        }

        if (remoteJMXEntity.getGetName()){
            getName();
        }

        if (remoteJMXEntity.getSayHallo()){
            sayHello();
        }

        if (!remoteJMXEntity.getSetName().equals("")){
            setName(remoteJMXEntity.getSetName());
            remoteJMXEntity.setSetName("");
        }

        if (!remoteJMXEntity.getA().equals("") || !remoteJMXEntity.getB().equals("")){

            try {
                addInteger(Integer.parseInt(remoteJMXEntity.getA()), Integer.parseInt(remoteJMXEntity.getB()));
                remoteJMXEntity.setA("");
                remoteJMXEntity.setB("");
            } catch (NumberFormatException e) {
//                e.printStackTrace();
            }
        }

    }



}

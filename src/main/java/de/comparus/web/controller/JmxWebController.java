package de.comparus.web.controller;

import de.comparus.web.entity.RemoteJMXEntity;
import de.comparus.web.service.ClientListener;
import de.comparus.web.service.IJmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by ibolshak on 5/26/17.
 */
@Controller
@SessionAttributes("remoteJMXEntity")
public class JmxWebController {

    @Autowired
    private IJmxService jmxService;

    @Autowired
    private RemoteJMXEntity remoteJMXEntity;

    @RequestMapping("/")
    public String startApp(ModelMap modelMap) {
        modelMap.addAttribute("remoteJMXEntity", remoteJMXEntity);
        return "remoteControl";
    }

    @RequestMapping("/remoteControl")
    public String remoteControl(RemoteJMXEntity remoteJMXEntity, ModelMap modelMap) {

        modelMap.addAttribute("remoteJMXEntity", remoteJMXEntity);

        System.out.println();

        jmxService.remoteByEntity(remoteJMXEntity);
        notificate(modelMap);

        return "remoteControl";
    }

    private void notificate(ModelMap modelMap) {

        modelMap.addAttribute("notification", ClientListener.getNotification());
        System.out.println();
    }

}

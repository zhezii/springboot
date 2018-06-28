package org.zz.myspringboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.zz.myspringboot.entity.User;
import org.zz.myspringboot.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zhou Wenzhe
 * @date 2018/6/27
 */
//@Controller
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("index")
    public ModelAndView findAll(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        List<User> list = userService.findAll();
        modelAndView.addObject("list",list);
        return modelAndView;
    }

    @PostMapping("/update")
    public Map update(@Valid Long id,String username,Integer age,String address){
        userService.update(id,username,age,address);
        Map map = new HashMap();
        map.put("success",1);
        return map;
    }

    @PostMapping("/add")
    //@ResponseBody
    public Map add(@Valid String username, Integer age, String address){
        userService.add(username,age,address);
        Map map = new HashMap();
        map.put("success",1);
        return map;
    }

    @GetMapping("/delete")
    public ModelAndView delete(@Valid Long id){
        ModelAndView modelAndView = new ModelAndView("redirect:/index");
        userService.delete(id);
        return modelAndView;
    }

    @PostMapping("/findUser")
    public User update(Integer id){

        ModelAndView modelAndView = new ModelAndView();
        User user =  userService.findUserById(id);
        return user;
    }
}

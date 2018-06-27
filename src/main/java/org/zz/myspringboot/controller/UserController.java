package org.zz.myspringboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.zz.myspringboot.entity.User;
import org.zz.myspringboot.service.UserService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Zhou Wenzhe
 * @date 2018/6/27
 */
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("index")
    public ModelAndView index(@Valid String username){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        //根据用户名查询人数
        int i = userService.findCountByUsername(username);
        //查询所有用户信息
        List<User> list = userService.findAll();
        modelAndView.addObject("list",list);
        modelAndView.addObject("count",i);
        return modelAndView;
    }
}

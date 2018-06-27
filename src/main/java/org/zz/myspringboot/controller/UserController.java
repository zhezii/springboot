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
    public ModelAndView findAll(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        List<User> list = userService.findAll();
        modelAndView.addObject("list",list);
        return modelAndView;
    }

    @GetMapping("/update")
    public ModelAndView update(@Valid Long id,String username,Integer age,String address){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        userService.update(id,username,age,address);
        modelAndView.addObject("msg","修改成功");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add(@Valid String username,Integer age,String address){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        userService.add(username,age,address);
        modelAndView.addObject("msg","添加成功");
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView delete(@Valid Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        userService.delete(id);
        modelAndView.addObject("msg","删除成功");
        return modelAndView;
    }
}

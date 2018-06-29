package org.zz.myspringboot.controller;

import com.github.pagehelper.PageInfo;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.zz.myspringboot.entity.User;
import org.zz.myspringboot.service.UserService;
import org.zz.myspringboot.utils.FileUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public ModelAndView findAll(@Valid Integer page,Integer pageSize) {
        page = 1;
        pageSize = 10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        List<User> list1 = userService.findAll(page,pageSize);
        modelAndView.addObject("list", list1);
        return modelAndView;
    }

    @PostMapping("/update")
    public Map update(@Valid Long id, String username, Integer age, String address) {
        userService.update(id, username, age, address);
        Map map = new HashMap();
        map.put("success", 1);
        return map;
    }

    @PostMapping("/add")
    //@ResponseBody
    public Map add(@Valid String username, Integer age, String address) {
        userService.add(username, age, address);
        Map map = new HashMap();
        map.put("success", 1);
        return map;
    }

    @GetMapping("/delete")
    public ModelAndView delete(@Valid Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/index");
        userService.delete(id);
        return modelAndView;
    }

    @PostMapping("/findUser")
    public User update(Integer id) {
        User user = userService.findUserById(id);
        return user;
    }

    @RequestMapping("/importFile")
    public void importFile(HttpServletResponse response) {
        List<User> userList = userService.findAll();
        FileUtil.exportExcel(userList, "花名册", "德玛西亚", User.class, "414.xls", response);

    }

    // 下载pdf文档
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/pdf");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=user.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        List<User> list = userService.findAll();
        for (User user : list) {
            PdfPTable table = new PdfPTable(3);
            PdfPCell cell = new PdfPCell();
            cell.setPhrase(new com.itextpdf.text.Paragraph(user.getUsername()));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new com.itextpdf.text.Paragraph(user.getAge().toString()));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new com.itextpdf.text.Paragraph(user.getAddress()));
            table.addCell(cell);
            document.add(table);
        }
        document.close();

    }

    @PostMapping("index1")
    public PageInfo<User> findAll1(@Valid Integer page, Integer pageSize) {
        page = 1;
        pageSize = 2;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        List<User> list1 = userService.findAll(page,pageSize);
        modelAndView.addObject("list", list1);
        return new PageInfo<>(list1);
    }

}


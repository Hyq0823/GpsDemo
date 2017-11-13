package com.spzh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by hyq on 2017/8/23.
 */
@Controller
@RequestMapping("/file")
public class FileController {


    @RequestMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest request, Model model) throws Exception {
        String fileName = file.getName();
        String originalFilename = file.getOriginalFilename();
        long size = file.getSize();

       String realPath = WebUtils.getRealPath(request.getServletContext(), "/");
        String path = request.getSession().getServletContext().getRealPath("/");


        File f = new File(path+"/uploads",originalFilename);
        if(!f.exists()){f.mkdirs();}
        FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(f));

        System.out.println(fileName);
        System.out.println(originalFilename);
        System.out.println(size);
        model.addAttribute("file",realPath+"/"+fileName);
        return "index";
    }





}

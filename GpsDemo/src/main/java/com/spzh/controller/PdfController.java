package com.spzh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("pdf")
public class PdfController {



    @RequestMapping("/parse")
    public String upload(HttpServletRequest request, Model model,String pdf_name) throws Exception {
        String contextPath = request.getServletContext().getContextPath();
        /*
        Pdf2htmlEXUtil.pdf2html("C:\\Users\\hyq\\Downloads\\pdf2htmlEX-0.12-win32-static-with-poppler-data\\pdf2htmlEX.exe",
                contextPath + pdf_name+".pdf",
                contextPath
                ,pdf_name+".html");
        model.addAttribute("html",pdf_name+".html");
        */
        model.addAttribute("html",pdf_name+".html");
        return "pdfview";
    }

    @RequestMapping({"","index"})
    public String pdfMain(){
        return "pdfmain";
    }
}

package com.geeksaga.forest.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.geeksaga.forest.common.util.FileUtil;
import com.geeksaga.forest.common.util.RequestUtils;
import com.geeksaga.forest.repositories.entity.Seed;
import com.geeksaga.forest.service.SeedService;

/**
 * @author geeksaga
 * @version 0.1
 */
@Controller
public class SeedController
{
    @Autowired
    private SeedService seedServcie;

    @RequestMapping(value = { "/seed/add" }, method = RequestMethod.GET)
    public ModelAndView save(Seed seed, ModelMap model)
    {
        return new ModelAndView("seed/add");
    }

    @RequestMapping(value = { "/seed/add" }, method = RequestMethod.POST)
    public ModelAndView add(Seed seed, WebRequest request) throws IOException
    // public ModelAndView add(Seed seed, @RequestParam("file") MultipartFile file, WebRequest request) throws IOException
    {
        System.out.println(seed);

        // if (!file.isEmpty())
        // {
        // byte[] bytes = file.getBytes();

        FileUtil.processFile(RequestUtils.getRequest(request), seed.getFile(), "0");
        // }

        return new ModelAndView("seed/add");
    }

    // @RequestMapping(value = "/form", method = RequestMethod.POST)
    // public String handleFormUpload(Seed seed, @RequestParam("file") MultipartFile file) throws IOException
    // {
    // if (!file.isEmpty())
    // {
    // byte[] bytes = file.getBytes();
    // // store the bytes somewhere
    // return "redirect:uploadSuccess";
    // }
    //
    // return "redirect:uploadFailure";
    // }

    // @RequestMapping(value = "/form", method = RequestMethod.POST)
    // public String handleFormUpload(@RequestParam("name") String name, @RequestParam("file") Part file) throws IOException
    // {
    // InputStream inputStream = file.getInputStream();
    // // store bytes from uploaded file somewhere
    //
    // return "redirect:uploadSuccess";
    // }

    // @ExceptionHandler(IOException.class)
    // public ResponseEntity<String> handleIOException(IOException ex) {
    // // prepare responseEntity
    // return responseEntity;
    // }

}
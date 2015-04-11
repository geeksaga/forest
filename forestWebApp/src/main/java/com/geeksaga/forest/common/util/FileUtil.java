/*
 * GeekSaga Class Infomation Library v0.0.1
 * 
 * http://geeksaga.com/
 * 
 * Copyright 2014 GeekSaga Foundation, Inc. and other contributors
 * 
 * Released under the MIT license http://geeksaga.com/license
 */

/**
 * @author geeksaga
 * @version 0.1
 */
package com.geeksaga.forest.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.entity.AttachFile;

public class FileUtil
{
    protected static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static List<AttachFile> processFile(HttpServletRequest request, List<MultipartFile> list, String code)
    {
        return processFile(request, list, "application", code);
    }

    public static List<AttachFile> processFile(HttpServletRequest request, List<MultipartFile> list, String bundleName, String code)
    {
        return processFile(request, list, bundleName, code, 100);
    }

    public static List<AttachFile> processFile(HttpServletRequest request, List<MultipartFile> list, String bundleName, String code,
            int thumbSize)
    {
        List<AttachFile> fileList = new ArrayList<AttachFile>();
        HttpSession session = request.getSession();

        try
        {
            String path = BundleUtils.getString(bundleName, "file.upload.path");

            if (path.equals("default"))
            {
                path = session.getServletContext().getRealPath("/upload/" + code);
            }
            else
            {
                path += File.separator + code;
            }

            File directory = new File(path);
            File thumbnailDirectory = new File(path + File.separator + "thumb");

            if (!directory.exists())
            {
                directory.mkdirs();
            }

            if (!thumbnailDirectory.exists())
            {
                thumbnailDirectory.mkdirs();
            }

            Iterator<MultipartFile> iterator = list.iterator();

            while (iterator.hasNext())
            {
                MultipartFile multipartFile = iterator.next();

                if (multipartFile.getSize() > 0)
                {
                    System.out.println(multipartFile);
                    
                    File destination = File.createTempFile("file_", "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename()),
                            directory);
                    // UploadInfo uploadInfo = (UploadInfo) session.getAttribute("uploadInfo");
                    // uploadInfo.setBytesRead(FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(destination)));
                    FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(destination));
                    
                    // uploadInfo.setStatus("done");
                    // session.setAttribute("uploadInfo", uploadInfo);

                    AttachFile file = new AttachFile();
                    file.setSid(KeyGenerator.generateKeyToLong());
                    file.setTargetId(code);
                    file.setAttachOrder("1");
                    file.setOriginalName(multipartFile.getOriginalFilename());
                    file.setFileName(destination.getName());
                    file.setFileExtension(FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
                    file.setFileSize(multipartFile.getSize());
                    // file.setFileDesc(SagaConstants.EMPTY_VALUE);
                    // file.setAttachType(SagaConstants.EMPTY_VALUE);
                    // file.setDownloadCnt("0");

                     ThumbnailGenerator.transform(directory.getPath() + File.separator + file.getFileName(), thumbnailDirectory.getPath()
                     + File.separator + file.getFileName(), thumbSize, thumbSize);

                    fileList.add(file);
                }
            }
        }
        catch (MaxUploadSizeExceededException e)
        {
            logger.info(e.getMessage(), e);
        }
        catch (IOException e)
        {
            logger.info(e.getMessage(), e);
        }

        return fileList;
    }
}
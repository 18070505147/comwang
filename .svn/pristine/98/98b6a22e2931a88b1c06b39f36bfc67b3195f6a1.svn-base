package com.chejet.cloud.controller;

import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.exception.BaseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author Neo.fang
 * @creatTime 2018/10/15 0015
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value="/public/image")
public class FileSeeController {

//    @Autowired
//    private FileUploadConfig fileUploadConfig;

    private static final String STORE_FILE_DIR="/usr/local/image/"; // LINUX文件保存的路径

    // 获取文件上传路径
    public static File getUploadFile(String path, String name) {
        File file = new File(STORE_FILE_DIR + File.separator + path + File.separator + name);
        if (!file.exists()){
            throw new BaseException(ErrorCodeEnum.RESOURCE_NOT_EXIST);
        }
        return file;
    }

    @RequestMapping(value = "/{filePath}/{fileName:.*}", method = RequestMethod.GET)
    public void show(@PathVariable("filePath") String filePath, @PathVariable("fileName") String fileName, HttpServletResponse response, HttpServletRequest request) {
        response.setDateHeader("Expires", 0L);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        InputStream inputStream = null;
        OutputStream os = null;
        try {
            inputStream = new FileInputStream(getUploadFile(filePath, fileName));
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    /**
     * 直接下载
     *
     * @param filePath
     * @param fileName
     * @param response
     * @param request
     */
//    @RequestMapping("/{filePath}/{fileName:.*}")
    public void download(@PathVariable("filePath") String filePath, @PathVariable("fileName") String fileName, HttpServletResponse response, HttpServletRequest request) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        InputStream inputStream = null;
        OutputStream os = null;
        try {
            inputStream = new FileInputStream(getUploadFile(filePath, fileName));

            os = response.getOutputStream();
            int length;
            byte[] b = new byte[2048];
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close(); // 关闭流
                } catch (IOException ignored) {
                }
            }
            if (os != null) {
                try {
                    os.close();
                    // 关闭流
                } catch (IOException ignored) {
                }
            }
        }
    }

}

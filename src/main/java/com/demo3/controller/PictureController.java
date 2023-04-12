package com.demo3.controller;

import com.demo3.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class PictureController {

    @RequestMapping("/showPic/{fileName}.{suffix}")
    public void showPicture(@PathVariable("fileName")String fileName,@PathVariable("suffix")String suffix,
                            HttpServletResponse response) {
        File imgFile = new File(Constants.IMG_PATH+fileName+"."+suffix);
        responseFile(response,imgFile);
    }

    @RequestMapping("/downloadPic/{fileName}.{suffix}")
    public void downloadPicture(@PathVariable("fileName") String fileName,
                                @PathVariable("suffix") String suffix,
                                HttpServletResponse response){
        // 设置下载的响应头信息
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + "headPic.jpg");
        File imgFile = new File(Constants.IMG_PATH + fileName + "." + suffix);
        responseFile(response, imgFile);
    }

    private void responseFile(HttpServletResponse response, File imgFile) {
        try(InputStream is = new FileInputStream(imgFile);
            OutputStream os = response.getOutputStream();){
            byte [] buffer = new byte[1024]; // 图片文件流缓存池
            while(is.read(buffer) != -1){
                os.write(buffer);
            }
            os.flush();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

}

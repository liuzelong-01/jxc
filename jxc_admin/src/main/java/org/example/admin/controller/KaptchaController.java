package org.example.admin.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.example.admin.model.KaptchaImageModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class KaptchaController {
    @Resource
    public DefaultKaptcha defaultKaptcha;

    @RequestMapping(value = "/image",method = RequestMethod.GET)
    public void Kaptcha(HttpSession session, HttpServletResponse response) throws IOException{
        response.setDateHeader("Expires",0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String kapText=defaultKaptcha.createText();
        session.setAttribute("kaptcha_key",new KaptchaImageModel(kapText,2*60));
        ServletOutputStream outputStream = response.getOutputStream();
        BufferedImage bufferedImage=defaultKaptcha.createImage(kapText);
        ImageIO.write(bufferedImage,"jpg",outputStream);
        outputStream.flush();


    }

}

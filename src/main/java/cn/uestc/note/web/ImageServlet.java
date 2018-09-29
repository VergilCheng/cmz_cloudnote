package cn.uestc.note.web;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * http协议的图片发送机制
 */
public class ImageServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //发送照片的规则规范：
        byte[] png = createPng();
        resp.setContentType("image/png");
        resp.setContentLength(png.length);
        //在消息body中发送消息数据
        resp.getOutputStream().write(png);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    //创建图片并编码为png格式
    private byte[] createPng() throws IOException {
        BufferedImage img =
                new BufferedImage(200,80,BufferedImage.TYPE_3BYTE_BGR);
        img.setRGB(100,40,0xfffff);
        //将图片编码为PNG

        //底层是byte数组，这个流是工作在内存中，读写也在在内存中，速度快
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(img,"png",out);
        out.close();
        byte[] data = out.toByteArray();
        return data;
    }
}

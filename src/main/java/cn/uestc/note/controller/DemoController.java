package cn.uestc.note.controller;


import cn.uestc.note.util.JsonResult;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 用于测试文件，图片等格式的文件上载和下载的案例控制器
 */
@Controller
@RequestMapping("/image")
public class DemoController {

    /**
     * @ResposeBody注解会自动处理返回值
     * 1.如果是javabean（数组或者集合或者对象，返回Json）
     * 2.如果是byte[]数组，则byte数组直接装入resp中。
     *
     * 在@RequestMapping中设置返回值的类型——contentLength：produces="......."。
     *
     * 不写produces的话返回的byte[]不会解码，会出现乱码。
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/image.do",method = RequestMethod.GET,produces = "image/png")
    @ResponseBody
    public byte[] image() throws Exception{
        return createPng();
    }

    /**
     * 下载图片，所需要写的注解内容
     * @param response
     * @return
     */
    @RequestMapping(value = "downLoad.do",method = RequestMethod.GET,
            produces = "application/octet-stream")
    @ResponseBody
    public byte[] downLoadImage(HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition","attachment;filename=\"demo.png\"");
        return createPng();
    }

    /**
     * 下载Excel
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "excel.do",method = RequestMethod.GET,
            produces = "application/octet-stream")
    @ResponseBody
    public byte[] downLoadExcel(HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition","attachment;filename=\"demo.xlsx\"");
        return createPng();
    }

    /**
     *
     * 上载文件名称
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload.do")
    @ResponseBody
    public JsonResult upload(MultipartFile userFile1,MultipartFile userfile2)
            throws IOException {
        //SpringMVC中可以利用MultipartFile
        //接收 上载的文件！
        //获取上载文件名，这个过程并没有将文件上传，要将文件上传入服务器需要IO流
        String file1 = userFile1.getOriginalFilename(); //Spring框架实现文件上载需要导入一个包，并在Spring-mvc中配置文件解析器。
        String file2 = userfile2.getOriginalFilename();
       //3种方法将文件传入服务器
        // 1. userFile1.transferTo(File file1);

        /*
            2.userFile1.getBytes();获取文件的全部数据
                适合处理小文件！！文件太大将造成过多的内存好用
         */
        /*
            3.userFile1.getInputStream
                获取上载文件的流，适合处理大文件，
                可以按照一个byte，一个byte的规格处理。

         */
        //创建服务器要保存文件的路径
        File dir =
                new File("C:/Users/cheng/Desktop/用于流实验的文件夹/demo");
        boolean exists = dir.mkdir();//检查该路径下是否存在该文件，如果没有则创建。如果没有写文件后缀，则创建一个文件夹

        File f1 = new File(dir,file1);//创建文件对象，保存在内存中，但是不保存在本地。
        File f2 = new File(dir,file2);
        //第一种保存文件。
        userFile1.transferTo(f1);
        //第三种保存文件:transferTo底层封装了这段代码。
        InputStream in1 = userFile1.getInputStream();
        FileOutputStream fos = new FileOutputStream(f1);
        int b;
        while((b = in1.read())!=-1){
            fos.write(b);
        }
        in1.close();
        fos.close();
        /**
         * 第三种保存文件的方式
         */
        InputStream in2 = userfile2.getInputStream();
        FileOutputStream out2 = new FileOutputStream(f2);
        byte[] b2 = new byte[8*1024];
        int n;
        while((n=in2.read(b2))!=-1){
            fos.write(b2,0,n);
        }
        in2.close();
        out2.close();



        return new JsonResult(true);


    }



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

    private byte[] createExcel() throws IOException{
        //创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表
        HSSFSheet sheet = workbook.createSheet("Demo");
        //创建数据行
        HSSFRow row = sheet.createRow(0);
        //创建行中的格子
        HSSFCell cell  = row.createCell(0);
        cell.setCellValue("Hello World");
        //读写内存中数据的低级流，而FileInputStream是读写硬盘文件的字节流，效率慢
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        out.close();
        return out.toByteArray();
    }

}

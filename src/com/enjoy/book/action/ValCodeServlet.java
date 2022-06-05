package com.enjoy.book.action;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 生成验证码
 */

//配置验证码路径
//loadOnStartUp在服务器启动的时候就把servlet准备好
@WebServlet(urlPatterns = "/code.let",loadOnStartup = 1)
public class ValCodeServlet extends HttpServlet {
    //设置rgb范围
    int colorLength = 256;
    Random random = new Random();
    /**
     * 获取随机字符串
     * @return
     */
    private String getRandomStr(){
        //1,0,l,o,i等数据会产生视觉干扰
        String str = "23456789ABCDEFGHJKMNOPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<4;i++){
            int index=random.nextInt(str.length());
            char letter = str.charAt(index);
            sb.append(letter);
        }
        return sb.toString();
    }

    /**
     * 获取背景颜色 0~255 rgb参数
     * @return
     */
    private Color getBackColor(){
        int red = random.nextInt(colorLength);
        int green = random.nextInt(colorLength);
        int blue = random.nextInt(colorLength);
        return new Color(red,green,blue);
    }

    /**
     * 获取前景色
     * @param bgColor
     * @return
     */
    private Color getForeColor(Color bgColor){
        int red = colorLength-bgColor.getRed();
        int green = colorLength-bgColor.getGreen();
        int blue = colorLength-bgColor.getBlue();
        return new Color(red,green,blue);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、设置相应格式为图片:jpg  设置MIME型jpg图片(Tomcat目录下的web.xml中查找)
        resp.setContentType("image/jpeg");

        //2、图像对象 缓冲区图片(BufferedImage) 创建之初为空
        BufferedImage bufferedImage = new BufferedImage(80,30,BufferedImage.TYPE_INT_BGR);

        //3、获取画布对象 只有画布才能进行改变
        Graphics g = bufferedImage.getGraphics();

        //4、设置背景颜色，设置画布颜色
        Color bgColor = getBackColor();
        g.setColor(bgColor);
        //5、画背景
        g.fillRect(0,0,80,30);

        //6、设置前景色
        Color foreColor = getForeColor(bgColor);
        g.setColor(foreColor);
        //设置字体
        g.setFont(new Font("黑体",Font.BOLD,26));
        //7、将随机字符串存到session中(session是一个会话的对象)
        String randomStr = getRandomStr();
        HttpSession session = req.getSession();
        session.setAttribute("code",randomStr);
//        将随机数画到画布中
        g.drawString(randomStr,10,28);

        //8、噪点(30个白色正方形)，给用户造成一些小障碍
        for(int i=0;i<30;i++){
            g.setColor(Color.white);
            int x = random.nextInt(80);
            int y = random.nextInt(30);
//            从x、y的位置开始画起，长宽为1的小方格
            g.fillRect(x,y,1,1);
        }

        //9、将这张内存的图片输出到响应流中,因为以上操作的图片还在内存中
//        设置输出流
        ServletOutputStream sos = resp.getOutputStream();
//        ImageIO以图片的形式将内存中的图片流以JPEG形式放到输出流中
        ImageIO.write(bufferedImage,"jpeg",sos);
    }
}

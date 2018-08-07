/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summerhomework;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * ref:http://nodamushi.hatenablog.com/entry/20111012/1318436587
 * @author altyjp
 */
public class SummerHomeWork {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {
            
            int c,r,g,b;
            int ans = 0;
            int allpxs = 0;
            int maxRGB = rgb(255,255,255);
            int minRGB = rgb(0,0,0);
            String filePath = "data/IMG_0958.bmp";
            
            String debug_filePath = "data/debug_IMG_0958.bmp";
            
            File file = new File(filePath);
            BufferedImage read = ImageIO.read(file);
            int w = read.getWidth(), h = read.getHeight();

            BufferedImage write =
                new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    c = read.getRGB(x, y);
                    r = r(c);
                    g = g(c);
                    b = b(c);
                    System.out.println("X:" + x + " Y:" + y + 
                            " R:" + r + " G:" + g + " B:" +b);
                    //画像を白と黒の2値にする。（2値化）
                    if(300 > r + b + g){
                        //白
                        write.setRGB(x,y,minRGB);
                    }else{
                        //黒
                        ans++;  //黒のピクセルとして計算する。
                        write.setRGB(x,y,maxRGB);
                    }
                    
                    allpxs++;
                }
            }
            
            BigDecimal parsent = new BigDecimal(ans).
                    divide(new BigDecimal(allpxs));
            
            BigDecimal area = new BigDecimal(16).multiply(parsent);
            /*
                black pixels:81122
                parsent of black pixel:0.5070125
                area(cm^2) :8.1122000
            */
            System.out.println("----------------------------------");
            System.out.println("black pixels:" + ans);
            System.out.println("parsent of black pixel:" + parsent);
            System.out.println("area(cm^2):" + area);
            File f2 = new File(debug_filePath);
            ImageIO.write(write, "bmp", f2);
            
        } catch (IOException ex) {
            Logger.getLogger(SummerHomeWork.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static int a(int c) {
        return c >>> 24;
    }

    public static int r(int c) {
        return c >> 16 & 0xff;
    }

    public static int g(int c) {
        return c >> 8 & 0xff;
    }

    public static int b(int c) {
        return c & 0xff;
    }

    public static int rgb(int r, int g, int b) {
        return 0xff000000 | r << 16 | g << 8 | b;
    }

    public static int argb(int a, int r, int g, int b) {
        return a << 24 | r << 16 | g << 8 | b;
    }

}

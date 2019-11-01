import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    private static double width;
    private static double height;
    private static double duration = 1000;
    private static int closes = 0;
//    private static BufferedImage image;
//    private static InputStream imin;

    public static void main(String[] args) throws InterruptedException, IOException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        while (true){
            drawNew();
            Thread.sleep((long)(Math.random()*duration));
        }
    }

    private static void drawNew() throws IOException {
        InputStream imin = Main.class.getResourceAsStream("/rember.png");

        JFrame frame = new JFrame("Error");
        frame.setSize(300,300);
        frame.setLocation((int)(Math.random()*(width-300)),(int)(Math.random()*(height-300)));
        frame.setVisible(true);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                duration *= 0.5;
                closes ++;
            }
        };
        frame.addWindowListener(exitListener);
        BufferedImage image = ImageIO.read(imin);
        Graphics g = frame.getGraphics();
        g.drawImage(image,0,0,300, 300, frame.getContentPane());
        g.setColor(new Color(0x11ff0000,true));
        for(int i=0; i < closes; i++){
            g.fillRect(0,0,300,300);
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep((long)(Math.random()*10000));
                    frame.dispose();
                } catch (InterruptedException e) {
                    frame.dispose();
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}

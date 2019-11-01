import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    private static double width;
    private static double height;

    public static void main(String[] args) throws InterruptedException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        while (true){
            drawNew();
            Thread.sleep((long)(Math.random()*1000));
        }
    }

    private static void drawNew(){
        try {
            InputStream imin = Main.class.getResourceAsStream("/rember.png");
            JFrame frame = new JFrame("Error");
            frame.setSize(300,300);
            frame.setLocation((int)(Math.random()*(width-300)),(int)(Math.random()*(height-300)));
            frame.setVisible(true);
            BufferedImage image;
            image = ImageIO.read(imin);
            frame.getGraphics().drawImage(image,0,0,300, 300, frame.getContentPane());
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long)(Math.random()*10000));
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    } catch (InterruptedException e) {
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        e.printStackTrace();
                    }
                }
            });

            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

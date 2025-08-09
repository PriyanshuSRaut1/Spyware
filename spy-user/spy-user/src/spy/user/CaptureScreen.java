/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spy.user;

/**
 *
 * @author Brothers
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
public class CaptureScreen {

    /**
     * @param fileName
     * @param ipaddr
     * @return 
     */
    public File saveScreenshot(String fileName,String ipaddr,String id) {

		try {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle screenRectangle = new Rectangle(screenSize);
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(screenRectangle);
                        File f1 = new File("C:/Users/Akshay Bahadarpure/Documents/NetBeansProjects/New folder/spy-user/src/images/screenshots/"+id+"/");
                        boolean bool = f1.mkdir();  
			File f=new File("C:/Users/Akshay Bahadarpure/Documents/NetBeansProjects/New folder/spy-user/src/images/screenshots/"+id+"/"+fileName);
                        ImageIO.write(image, "jpg",f );
                        image.flush();
			System.out.println("saved.."+fileName);
                        return f;
		} catch (Exception e) {
			e.printStackTrace();
                         return null;
		}
	}
    public void callcapture(){
        int count = 1;
        JOptionPane.showMessageDialog(null,"Screen Capturing Start....","Alert",JOptionPane.INFORMATION_MESSAGE);
        while (true) {
            SimpleDateFormat dttm=new SimpleDateFormat("dd-mm-yyyy-HH-mm-ss");
            //this.saveScreenshot("C:/screenshots/screen_"+dttm.format(new Date())+".png");
            count++;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }//while
    }
    
}


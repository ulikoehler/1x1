/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JMassMailerFrame.java
 *
 * Created on 20.11.2008, 21:19:59
 */
package jmassmailer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author uli
 */
public class JMassMailerFrame extends javax.swing.JFrame
{

    /** Creates new form JMassMailerFrame */
    public JMassMailerFrame()
    {
        initComponents();
        try
        {
            //Init members
            digest = MessageDigest.getInstance("SHA-1");
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(JMassMailerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Read the save data (if the config file exists
        File configFile = new File(".jmassmailer");
        if (configFile.exists())
        {
            LineNumberReader lr = null;
            try
            {
                lr = new LineNumberReader(new FileReader(configFile));
                serverField.setText(lr.readLine());
                userNameField.setText(lr.readLine());
                targetField.setText(lr.readLine());
                fromField.setText(lr.readLine());
                countSpinner.setValue(new Long(lr.readLine()));
                lr.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(JMassMailerFrame.class.getName()).log(Level.SEVERE, null, ex);
            } 
            finally
            {
                try
                {
                    lr.close();
                }
                catch (IOException ex)
                {
                    Logger.getLogger(JMassMailerFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        serverLabel = new javax.swing.JLabel();
        serverField = new javax.swing.JTextField();
        userNameLabel = new javax.swing.JLabel();
        userNameField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        countLabel = new javax.swing.JLabel();
        countSpinner = new javax.swing.JSpinner();
        okButton = new javax.swing.JButton();
        targetLabel = new javax.swing.JLabel();
        targetField = new javax.swing.JTextField();
        fromLabel = new javax.swing.JLabel();
        fromField = new javax.swing.JTextField();
        randLenLabel = new javax.swing.JLabel();
        randLengthSpinner = new javax.swing.JSpinner();
        mailProgressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(i18n.getString("JMassMailerFrame.title")); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                mainFrameClosing(evt);
            }
        });

        serverLabel.setText(i18n.getString("JMassMailerFrame.serverLabel.text")); // NOI18N

        serverField.setText(i18n.getString("JMassMailerFrame.serverField.text")); // NOI18N

        userNameLabel.setText(i18n.getString("JMassMailerFrame.userNameLabel.text")); // NOI18N

        userNameField.setText(i18n.getString("JMassMailerFrame.userNameField.text")); // NOI18N

        passwordLabel.setText(i18n.getString("JMassMailerFrame.passwordLabel.text")); // NOI18N

        passwordField.setText(i18n.getString("JMassMailerFrame.passwordField.text")); // NOI18N

        countLabel.setText(i18n.getString("JMassMailerFrame.countLabel.text")); // NOI18N

        countSpinner.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(10000L), Long.valueOf(0L), null, Long.valueOf(1L)));

        okButton.setText(i18n.getString("JMassMailerFrame.okButton.text")); // NOI18N
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });

        targetLabel.setText(i18n.getString("JMassMailerFrame.targetLabel.text")); // NOI18N

        targetField.setText(i18n.getString("JMassMailerFrame.targetField.text")); // NOI18N

        fromLabel.setText(i18n.getString("JMassMailerFrame.fromLabel.text")); // NOI18N

        fromField.setText(i18n.getString("JMassMailerFrame.fromField.text")); // NOI18N

        randLenLabel.setText(i18n.getString("JMassMailerFrame.randLenLabel.text")); // NOI18N

        randLengthSpinner.setModel(new javax.swing.SpinnerNumberModel(10, 1, 100, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(serverLabel)
                                    .addComponent(userNameLabel)
                                    .addComponent(passwordLabel)
                                    .addComponent(countLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(userNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                    .addComponent(serverField, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                    .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                    .addComponent(countSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(targetLabel)
                                .addGap(47, 47, 47)
                                .addComponent(targetField, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(randLenLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(randLengthSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fromLabel)
                                .addGap(55, 55, 55)
                                .addComponent(fromField, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                            .addComponent(mailProgressBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverLabel)
                    .addComponent(serverField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userNameLabel)
                    .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(countLabel)
                    .addComponent(countSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(randLenLabel)
                    .addComponent(randLengthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(targetField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(targetLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fromField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mailProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_okButtonMouseClicked
    {//GEN-HEADEREND:event_okButtonMouseClicked
        try
        {
            Properties props = System.getProperties();
            props.put("mail.smtp.host", serverField.getText());
            props.put("mail.smtp.starttls.enable", true);
            props.put("mail.smtp.user", userNameField.getText());
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            Session session =
                    Session.getInstance(props,
                    new javax.mail.Authenticator()
                    {

                        @Override
                        protected PasswordAuthentication getPasswordAuthentication()
                        {
                            return new PasswordAuthentication(userNameField.getText(), new String(passwordField.getPassword()));
                        }
                    });
            long count = getCount();
            int randLength = getRandLength();
            //Initialize the transport object the
            Transport tr = session.getTransport("smtp");
            tr.connect(serverField.getText(), userNameField.getText(), new String(passwordField.getPassword()));
            //Maind send loop
            for (long i = 0; i < count; i++)
            {
                //Create the message
                MimeMessage message =
                        new MimeMessage(session);
                message.setFrom(new InternetAddress(fromField.getText()));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(targetField.getText()));
                message.setSubject(getRand(randLength));
                message.setText(getRand(randLength));
                message.saveChanges();
                //Send the message
                tr.sendMessage(message, message.getAllRecipients());
                //Add the message
            }
            tr.close();
        }
        catch (MessagingException ex)
        {
            Logger.getLogger(JMassMailerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_okButtonMouseClicked

    private void mainFrameClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_mainFrameClosing
    {//GEN-HEADEREND:event_mainFrameClosing
        try
        {
            FileWriter fout = new FileWriter(".jmassmailer");
            fout.write(serverField.getText() + "\n");
            fout.write(userNameField.getText() + "\n");
            fout.write(targetField.getText() + "\n");
            fout.write(fromField.getText() + "\n");
            fout.write(Long.toString(getCount()) + "\n");
            fout.close();
        }
        catch (IOException ex)
        {
            Logger.getLogger(JMassMailerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}//GEN-LAST:event_mainFrameClosing

    private String getRand(int len)
    {
        StringBuilder ret = new StringBuilder();
        for (; len > 0; len--)
        {
            ret.append(mt.nextChar());
        }
        return ret.toString();
    }

    private long getCount()
    {
        SpinnerNumberModel countModel =
                (SpinnerNumberModel) countSpinner.getModel();
        return countModel.getNumber().longValue();
    }

    private int getRandLength()
    {
        SpinnerNumberModel randLengthModel =
                (SpinnerNumberModel) randLengthSpinner.getModel();
        return randLengthModel.getNumber().intValue();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {

            public void run()
            {
                new JMassMailerFrame().setVisible(true);
            }
        });
    }
    private ResourceBundle i18n = ResourceBundle.getBundle("jmassmailer/Bundle");
    private MersenneTwisterFast mt = new MersenneTwisterFast();
    private MessageDigest digest;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel countLabel;
    private javax.swing.JSpinner countSpinner;
    private javax.swing.JTextField fromField;
    private javax.swing.JLabel fromLabel;
    private javax.swing.JProgressBar mailProgressBar;
    private javax.swing.JButton okButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel randLenLabel;
    private javax.swing.JSpinner randLengthSpinner;
    private javax.swing.JTextField serverField;
    private javax.swing.JLabel serverLabel;
    private javax.swing.JTextField targetField;
    private javax.swing.JLabel targetLabel;
    private javax.swing.JTextField userNameField;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}

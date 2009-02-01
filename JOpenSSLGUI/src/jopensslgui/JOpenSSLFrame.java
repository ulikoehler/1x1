/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JOpenSSLFrame.java
 *
 * Created on 29.01.2009, 15:22:29
 */
package jopensslgui;

import java.util.ResourceBundle;
import javax.swing.JFileChooser;

/**
 *
 * @author uli
 */
public class JOpenSSLFrame extends javax.swing.JFrame
{

    /** Creates new form JOpenSSLFrame */
    public JOpenSSLFrame()
    {
        initComponents();
        singleton = this;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainTabbedPane = new javax.swing.JTabbedPane();
        fileEncryptionPanel = new jopensslgui.FileEncryptionPanel();
        randomFilePanel = new jopensslgui.RandomFilePanel();
        statusPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle( i18n.getString("JOpenSSLFrame.title")); // NOI18N
        setLocationByPlatform(true);

        mainTabbedPane.addTab( i18n.getString("JOpenSSLFrame.fileEncryptionPanel.TabConstraints.tabTitle"), fileEncryptionPanel); // NOI18N
        mainTabbedPane.addTab( i18n.getString("JOpenSSLFrame.randomFilePanel.TabConstraints.tabTitle"), randomFilePanel); // NOI18N

        statusLabel.setText( i18n.getString("JOpenSSLFrame.statusLabel.text")); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusLabel)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
            .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainTabbedPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void displaySuccessMessage()
    {
        singleton.statusLabel.setText("Success!");
    }

    public static void displayErrorMessage(String message)
    {
        singleton.statusLabel.setText("Error:" + message);
    }

    public static void displayErrorMessage()
    {
        displayErrorMessage("");
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
                new JOpenSSLFrame().setVisible(true);
            }
        });
    }
    private ResourceBundle i18n = ResourceBundle.getBundle("jopensslgui/Bundle"); //NOI18N
    private static JOpenSSLFrame singleton = null; //MainFrame
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private jopensslgui.FileEncryptionPanel fileEncryptionPanel;
    private javax.swing.JTabbedPane mainTabbedPane;
    private jopensslgui.RandomFilePanel randomFilePanel;
    public javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables
}

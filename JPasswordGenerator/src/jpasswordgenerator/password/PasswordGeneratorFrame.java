/*
 * PasswordGeneratorFrame.java
 *
 * Created on 29. September 2008, 19:21
 */
package jpasswordgenerator.password;

import java.security.SecureRandom;
import jpasswordgenerator.utils.PasswordGenerator;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author  uli
 */
public class PasswordGeneratorFrame extends javax.swing.JFrame
{

    /** Creates new form PasswordGeneratorFrame */
    public PasswordGeneratorFrame()
    {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        characterSetButtonGroup = new javax.swing.ButtonGroup();
        useCharacterGroupsRadioButton = new javax.swing.JRadioButton();
        useSpecifiedCharacterSetRadioButton = new javax.swing.JRadioButton();
        characterSetField = new javax.swing.JTextField();
        upperLetterCheckBox = new javax.swing.JCheckBox();
        lowerLetterCheckBox = new javax.swing.JCheckBox();
        numbersCheckBox = new javax.swing.JCheckBox();
        specialCharactersCheckBox = new javax.swing.JCheckBox();
        whiteSpacesCheckBox = new javax.swing.JCheckBox();
        minusCheckBox = new javax.swing.JCheckBox();
        underlineCheckBox = new javax.swing.JCheckBox();
        lengthLabel = new javax.swing.JLabel();
        lengthSpinner = new javax.swing.JSpinner();
        qualityLabel = new javax.swing.JLabel();
        qualityBar = new javax.swing.JProgressBar();
        newPasswordLabel = new javax.swing.JLabel();
        newPasswordField = new javax.swing.JTextField();
        generatePasswordButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        extrasMenu = new javax.swing.JMenu();
        passwordListsItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(i18n.getString("PasswordGeneratorFrame.title")); // NOI18N

        characterSetButtonGroup.add(useCharacterGroupsRadioButton);
        useCharacterGroupsRadioButton.setSelected(true);
        useCharacterGroupsRadioButton.setText(i18n.getString("PasswordGeneratorFrame.useCharacterGroupsRadioButton.text")); // NOI18N

        characterSetButtonGroup.add(useSpecifiedCharacterSetRadioButton);
        useSpecifiedCharacterSetRadioButton.setText(i18n.getString("PasswordGeneratorFrame.useSpecifiedCharacterSetRadioButton.text")); // NOI18N

        upperLetterCheckBox.setSelected(true);
        upperLetterCheckBox.setText(i18n.getString("PasswordGeneratorFrame.upperLetterCheckBox.text")); // NOI18N

        lowerLetterCheckBox.setSelected(true);
        lowerLetterCheckBox.setText(i18n.getString("PasswordGeneratorFrame.lowerLetterCheckBox.text")); // NOI18N

        numbersCheckBox.setSelected(true);
        numbersCheckBox.setText(i18n.getString("PasswordGeneratorFrame.numbersCheckBox.text")); // NOI18N

        specialCharactersCheckBox.setText(i18n.getString("PasswordGeneratorFrame.specialCharactersCheckBox.text")); // NOI18N

        whiteSpacesCheckBox.setText(i18n.getString("PasswordGeneratorFrame.whiteSpacesCheckBox.text")); // NOI18N

        minusCheckBox.setText(i18n.getString("PasswordGeneratorFrame.minusCheckBox.text")); // NOI18N

        underlineCheckBox.setText(i18n.getString("PasswordGeneratorFrame.underlineCheckBox.text")); // NOI18N

        lengthLabel.setText(i18n.getString("PasswordGeneratorFrame.lengthLabel.text")); // NOI18N

        lengthSpinner.setModel(new SpinnerNumberModel(20, 1, 2500, 1));

        qualityLabel.setText(i18n.getString("PasswordGeneratorFrame.qualityLabel.text")); // NOI18N

        newPasswordLabel.setText(i18n.getString("PasswordGeneratorFrame.newPasswordLabel.text")); // NOI18N

        generatePasswordButton.setText(i18n.getString("PasswordGeneratorFrame.generatePasswordButton.text")); // NOI18N
        generatePasswordButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generatePasswordButtonMouseClicked(evt);
            }
        });

        extrasMenu.setMnemonic('e');
        extrasMenu.setText(i18n.getString("PasswordGeneratorFrame.extrasMenu.text")); // NOI18N

        passwordListsItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        passwordListsItem.setMnemonic('l');
        passwordListsItem.setText(i18n.getString("PasswordGeneratorFrame.passwordListsItem.text")); // NOI18N
        passwordListsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordListsItemActionPerformed(evt);
            }
        });
        extrasMenu.add(passwordListsItem);

        menuBar.add(extrasMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(useCharacterGroupsRadioButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(specialCharactersCheckBox)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(upperLetterCheckBox)
                                    .addComponent(lowerLetterCheckBox)
                                    .addComponent(numbersCheckBox))
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(underlineCheckBox)
                                    .addComponent(minusCheckBox)
                                    .addComponent(whiteSpacesCheckBox))))
                        .addGap(57, 57, 57))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(useSpecifiedCharacterSetRadioButton)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(characterSetField, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(newPasswordLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(generatePasswordButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lengthLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lengthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(qualityLabel)
                        .addGap(2, 2, 2)
                        .addComponent(qualityBar, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(useCharacterGroupsRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(upperLetterCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lowerLetterCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numbersCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(specialCharactersCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(useSpecifiedCharacterSetRadioButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(whiteSpacesCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(underlineCheckBox)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(characterSetField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lengthLabel)
                        .addComponent(lengthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(qualityLabel))
                    .addComponent(qualityBar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPasswordLabel)
                    .addComponent(newPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generatePasswordButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void generatePasswordButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generatePasswordButtonMouseClicked
    updateCharset();
    newPasswordField.setText(new String(pwgen.generatePassword(getLength())));
}//GEN-LAST:event_generatePasswordButtonMouseClicked

private void passwordListsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordListsItemActionPerformed
    pwlistFrame.setVisible(true);
}//GEN-LAST:event_passwordListsItemActionPerformed

    public void updateCharset()
    {
        //Build a charset
        String charset =
                PasswordGenerator.generateCharset(upperLetterCheckBox.isSelected(), lowerLetterCheckBox.isSelected(), numbersCheckBox.isSelected(), specialCharactersCheckBox.isSelected(), whiteSpacesCheckBox.isSelected(), minusCheckBox.isSelected(), underlineCheckBox.isSelected());
        //If the user has not selected any character sets, show an error message and return
        if (charset.length() == 0)
        {
            JOptionPane.showMessageDialog(this,
                    "No character sets selected",
                    "Select some character sets before trying to generate a password!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Generate a password and seed 
        pwgen.setCharset(charset);
    }

    public static void main(String[] argv)
    {
        new PasswordGeneratorFrame().setVisible(true);
    }
    private ResourceBundle i18n =
            ResourceBundle.getBundle("jpasswordgenerator/password/Bundle");
    private PasswordGenerator pwgen = new PasswordGenerator(new SecureRandom());
    private PasswordListGeneratorFrame pwlistFrame =
            new PasswordListGeneratorFrame(this); //alphanumeric charset is default
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup characterSetButtonGroup;
    private javax.swing.JTextField characterSetField;
    private javax.swing.JMenu extrasMenu;
    private javax.swing.JButton generatePasswordButton;
    private javax.swing.JLabel lengthLabel;
    private javax.swing.JSpinner lengthSpinner;
    private javax.swing.JCheckBox lowerLetterCheckBox;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JCheckBox minusCheckBox;
    private javax.swing.JTextField newPasswordField;
    private javax.swing.JLabel newPasswordLabel;
    private javax.swing.JCheckBox numbersCheckBox;
    private javax.swing.JMenuItem passwordListsItem;
    private javax.swing.JProgressBar qualityBar;
    private javax.swing.JLabel qualityLabel;
    private javax.swing.JCheckBox specialCharactersCheckBox;
    private javax.swing.JCheckBox underlineCheckBox;
    private javax.swing.JCheckBox upperLetterCheckBox;
    private javax.swing.JRadioButton useCharacterGroupsRadioButton;
    private javax.swing.JRadioButton useSpecifiedCharacterSetRadioButton;
    private javax.swing.JCheckBox whiteSpacesCheckBox;
    // End of variables declaration//GEN-END:variables
    public PasswordGenerator getPwgen()
    {
        return pwgen;
    }

    public int getLength()
    {
        SpinnerNumberModel lenModel =
                (SpinnerNumberModel) lengthSpinner.getModel();
        return lenModel.getNumber().intValue();
    }
}

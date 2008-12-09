/*
 * BirthdayCalculatorFrame.java
 *
 * Created on 09.12.2008, 13:47:34
 *
 * Just a gimmick application to calculate the weekday of your next birthdays
 */
package birthdaycalculator;

import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author uli
 */
public class BirthdayCalculatorFrame extends javax.swing.JFrame
{

    /** Creates new form BirthdayCalculatorFrame */
    public BirthdayCalculatorFrame()
    {
        initComponents();
        //Set the current date
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        setDay(cal.get(Calendar.DAY_OF_MONTH));
        monthComboBox.setSelectedIndex(cal.get(Calendar.MONTH));
        //Update the list
        dateChanged();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dayLabel = new javax.swing.JLabel();
        daySpinner = new javax.swing.JSpinner();
        monthLabel = new javax.swing.JLabel();
        monthComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        birthdayArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle( i18n.getString("BirthdayCalculatorFrame.title")); // NOI18N

        dayLabel.setText( i18n.getString("BirthdayCalculatorFrame.dayLabel.text")); // NOI18N

        daySpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 31, 1));
        daySpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                daySpinnerStateChanged(evt);
            }
        });
        daySpinner.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                daySpinnerPropertyChange(evt);
            }
        });
        daySpinner.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                daySpinnerVetoableChange(evt);
            }
        });

        monthLabel.setText( i18n.getString("BirthdayCalculatorFrame.monthLabel.text")); // NOI18N

        monthComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        monthComboBox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                monthComboBoxPropertyChange(evt);
            }
        });

        birthdayArea.setColumns(20);
        birthdayArea.setRows(5);
        jScrollPane1.setViewportView(birthdayArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dayLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(daySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(monthLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dayLabel)
                    .addComponent(daySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthLabel)
                    .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void monthComboBoxPropertyChange(java.beans.PropertyChangeEvent evt)//GEN-FIRST:event_monthComboBoxPropertyChange
    {//GEN-HEADEREND:event_monthComboBoxPropertyChange
        dateChanged();
    }//GEN-LAST:event_monthComboBoxPropertyChange

    private void daySpinnerPropertyChange(java.beans.PropertyChangeEvent evt)//GEN-FIRST:event_daySpinnerPropertyChange
    {//GEN-HEADEREND:event_daySpinnerPropertyChange
        dateChanged();
    }//GEN-LAST:event_daySpinnerPropertyChange

    private void daySpinnerStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_daySpinnerStateChanged
    {//GEN-HEADEREND:event_daySpinnerStateChanged
        dateChanged();
    }//GEN-LAST:event_daySpinnerStateChanged

    private void daySpinnerVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException//GEN-FIRST:event_daySpinnerVetoableChange
    {//GEN-HEADEREND:event_daySpinnerVetoableChange
        dateChanged();
    }//GEN-LAST:event_daySpinnerVetoableChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {

            public void run()
            {
                new BirthdayCalculatorFrame().setVisible(true);
            }
        });
    }

    private void setDay(int day)
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) daySpinner.getModel();
        sm.setValue(day);
    }

    private int getDay()
    {
        SpinnerNumberModel sm = (SpinnerNumberModel) daySpinner.getModel();
        return sm.getNumber().intValue();
    }

    private int getMonthIndex()
    {
        return monthComboBox.getSelectedIndex(); //+1: Index begins with 0
    }

    private void dateChanged()
    {
        //Clear the birthday text area
        birthdayArea.setText(""); //Seems to be most simply //NOI18N

        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.set(Calendar.DAY_OF_MONTH, getDay());
        cal.set(Calendar.MONTH, getMonthIndex());
        //Calendar data
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < years; i++)
        {
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1; //-1: Array index begins with 0
            //Build the output string
            sb.append(months[month]);
            sb.append(" "); //NOI18N
            sb.append(day);
            sb.append(" "); //NOI18N
            sb.append(year);
            sb.append(i18n.getString("_is_a_"));
            sb.append(days[dayOfWeek]);
            sb.append("\n"); //NOI18N
            //Show it in the GUI
            birthdayArea.append(sb.toString());
            //Clear the string builder
            sb.setLength(0);
            //Set the new year
            year++; //Next year
            cal.set(Calendar.YEAR, year);
        }
    }
    private static final int years = 50; //Years to calculate
    private ResourceBundle i18n = ResourceBundle.getBundle("birthdaycalculator/Bundle"); //NOI18N
    private String[] months =
    {
        i18n.getString("January"),
        i18n.getString("February"),
        i18n.getString("March"), i18n.
        getString("April"),
        i18n.getString("May"),
        i18n.getString("June"),
        i18n.getString("July"),
        i18n.getString("August"),
        i18n.getString("September"),
        i18n.getString("October"),
        i18n.getString("November"),
        i18n.getString("December")
    };
    private String[] days = //Must begin with Sunday due to calendar values
    {
        i18n.getString("Sunday"),
        i18n.getString("Monday"),
        i18n.getString("Tuesday"),
        i18n.getString("Wednesday"),
        i18n.getString("Thursday"),
        i18n.getString("Friday"),
        i18n.getString("Saturday")
    };
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea birthdayArea;
    private javax.swing.JLabel dayLabel;
    private javax.swing.JSpinner daySpinner;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox monthComboBox;
    private javax.swing.JLabel monthLabel;
    // End of variables declaration//GEN-END:variables
}

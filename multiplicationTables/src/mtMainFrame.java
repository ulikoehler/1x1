
import java.awt.Color;
import java.util.Random;

/*
 * mtMainFrame.java
 *
 * Created on 4. April 2008, 19:45
 */

/**
 *
 * @author  User
 */
public class mtMainFrame extends javax.swing.JFrame {
    
    /** Creates new form mtMainFrame */
    public mtMainFrame() {
        initComponents();
        generateNewExercise();
    }
    
    
    ///Custom code
    //Constants
    static final int maxRow = 10;
    //Global variables
    private Random rand = new Random();
    private mtSettingsFrame settingsFrame = new mtSettingsFrame();
    private mtStatisticsFrame statisticsFrame = new mtStatisticsFrame();
    private mtSettingsInterface settings;
    private int correctResult;   
    private boolean[][] solved = new boolean[maxRow][maxRow];
    private int[][] solvingValues = new int[maxRow][maxRow];
    private int maxSolvesThisRun; //Number of exercises until we have to reset the solved array
    private int solvesThisRun = 0; //Number of exercises already solved this run
    int firstFactor;
    int secondFactor;
    //Statistics counters
    private int overallSolved; //Overall solved exercises for this name
    private int rightSolved; //Exercises the name has anwered correctly
    private int falseSolved; //Exercises the name has anwered wrong   
    
    
    private void generateNewExercise()
    {
        //Get up-to-date version of settings intercae
        settings = settingsFrame.getSettingsInterface();
        
        //Check if exercises should be questioned more than once per run
        boolean askOnce = settings.options[0];
        
        //Predeclare variables and initialize vectorElements
        int vectorElements = settings.rows.size();
        //Calculate max solves this run
        maxSolvesThisRun = vectorElements * vectorElements;
        //Generate random numbers and check if this exercise has already been solved
        while(true)
            {
            firstFactor = settings.rows.elementAt(rand.nextInt(vectorElements));
            secondFactor = settings.rows.elementAt(rand.nextInt(vectorElements));
            if(!(solved[firstFactor-1][secondFactor-1]) || !(askOnce)) {break;}
            }
        
        firstFactorLabel.setText(Integer.toString(firstFactor));
        secondFactorLabel.setText(Integer.toString(secondFactor));
        correctResult = firstFactor * secondFactor;
        
        //Mark exercise as solved
        solved[firstFactor-1][secondFactor-1] = true;
        
        //Increment solvesThisRun and reset array if greater than maxSolvesThisRun
        solvesThisRun++;
        if(solvesThisRun == maxSolvesThisRun)
            {
                solvesThisRun = 0; //Reset Counter
                solved = new boolean[maxRow][maxRow]; //Reset array
            }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new java.awt.Panel();
        okButton = new javax.swing.JButton();
        correctnessLabel = new javax.swing.JLabel();
        nameLabel = new java.awt.Label();
        nameField = new javax.swing.JTextField();
        firstFactorLabel = new javax.swing.JLabel();
        secondFactorLabel = new javax.swing.JLabel();
        multLabel = new javax.swing.JLabel();
        isLabel = new javax.swing.JLabel();
        resultField = new javax.swing.JTextField();
        showSettingsFrameButton = new javax.swing.JButton();
        showStatisticsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("1x1");

        okButton.setText("OK");
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });

        correctnessLabel.setFont(new java.awt.Font("Tahoma", 1, 36));
        correctnessLabel.setForeground(new java.awt.Color(0, 255, 0));

        nameLabel.setText("Name:");

        nameField.setText("Kein Name");
        nameField.setToolTipText("Name des Schülers");
        nameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameFieldFocusLost(evt);
            }
        });

        firstFactorLabel.setFont(new java.awt.Font("Tahoma", 0, 48));
        firstFactorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstFactorLabel.setText("1");

        secondFactorLabel.setFont(new java.awt.Font("Tahoma", 0, 48));
        secondFactorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        secondFactorLabel.setText("2");

        multLabel.setFont(new java.awt.Font("Tahoma", 0, 24));
        multLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        multLabel.setText("<html>&#9679");

        isLabel.setFont(new java.awt.Font("Tahoma", 0, 48));
        isLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        isLabel.setText("=");

        resultField.setFont(new java.awt.Font("Tahoma", 0, 48));
        resultField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        resultField.setText("5");

        showSettingsFrameButton.setText("Einstellungen");
        showSettingsFrameButton.setToolTipText("Einstellungen anzeigen");
        showSettingsFrameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showSettingsFrameButtonMouseClicked(evt);
            }
        });

        showStatisticsButton.setText("Statistiken");
        showStatisticsButton.setToolTipText("Statistiken anzeigen");
        showStatisticsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showStatisticsButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(firstFactorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(multLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secondFactorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(isLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(resultField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(correctnessLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)
                                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(showSettingsFrameButton)
                                        .addGap(18, 18, 18)
                                        .addComponent(showStatisticsButton)))))))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(isLabel)
                        .addComponent(resultField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(secondFactorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(firstFactorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(multLabel)
                        .addGap(21, 21, 21)))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(correctnessLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(showSettingsFrameButton)
                            .addComponent(showStatisticsButton))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(59, 59, 59))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseClicked
        // TODO add your handling code here:
        int result = Integer.parseInt(resultField.getText());
        if (result == correctResult)
        {
            correctnessLabel.setForeground(Color.GREEN);
            correctnessLabel.setText("RICHTIG!");
            rightSolved++;
        }
        else
        {
            correctnessLabel.setForeground(Color.RED);
            correctnessLabel.setText("FALSCH:" + Integer.toString(correctResult));
            falseSolved++;
        }
        overallSolved++;
        //Write value into solving array
        solvingValues[firstFactor-1][secondFactor-1] = result;
        
        //Update statistics
        statisticsFrame.setOverallSolved(overallSolved);
        statisticsFrame.setRightSolved(rightSolved);
        statisticsFrame.setFalseSolved(falseSolved);
        //Update statistics table
        for(int i = 1; i < 10; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                //First set background color of the appropriate cell
                if(solvingValues[i-1][j] == (i*j+1)) {statisticsFrame.resultsTable.setValueAt("<html><p bgcolor=green>" + Integer.toString(solvingValues[i-1][j]) + "</p>", i, j);}
                else {statisticsFrame.resultsTable.setValueAt("<html><p bgcolor=red>" + Integer.toString(solvingValues[i-1][j]) + "</p>", i, j);}
            }
        }
        
        generateNewExercise();
    }//GEN-LAST:event_okButtonMouseClicked

    private void showSettingsFrameButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showSettingsFrameButtonMouseClicked
        // TODO add your handling code here:
        settingsFrame.setVisible(true);
    }//GEN-LAST:event_showSettingsFrameButtonMouseClicked

    private void nameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameFieldFocusLost
        // TODO add your handling code here:
        //Reset statistics counters 
        overallSolved = 0;
        rightSolved = 0;
        falseSolved = 0;
        //Write name into statistics frame
        statisticsFrame.setName(nameField.getText());
    }//GEN-LAST:event_nameFieldFocusLost

    private void showStatisticsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showStatisticsButtonMouseClicked
        // TODO add your handling code here:
        statisticsFrame.setVisible(true);
    }//GEN-LAST:event_showStatisticsButtonMouseClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mtMainFrame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel correctnessLabel;
    private javax.swing.JLabel firstFactorLabel;
    private javax.swing.JLabel isLabel;
    private java.awt.Panel mainPanel;
    private javax.swing.JLabel multLabel;
    private javax.swing.JTextField nameField;
    private java.awt.Label nameLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField resultField;
    private javax.swing.JLabel secondFactorLabel;
    private javax.swing.JButton showSettingsFrameButton;
    private javax.swing.JButton showStatisticsButton;
    // End of variables declaration//GEN-END:variables
    
}

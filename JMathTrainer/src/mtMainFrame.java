
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Random;
import java.awt.event.*;

/*
 * mtMainFrame.java
 *
 * Created on 4. April 2008, 19:45
 */
import javax.swing.*;

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
    private final DecimalFormat twoPlacesFormat = new DecimalFormat("#0.##");
    //Global variables
    private Random rand = new Random();
    private mtSettingsFrame settingsFrame = new mtSettingsFrame();
    private mtStatisticsFrame statisticsFrame = new mtStatisticsFrame(this);
    private mtSettingsInterface settings;
    private int correctResult;
    private boolean[][] multSolved = new boolean[maxRow][maxRow];
    private boolean[][] plusSolved = new boolean[maxRow][maxRow];
    private boolean[][] minusSolved = new boolean[maxRow][maxRow];
    private double[][] multTime = new double[maxRow][maxRow];
    private double[][] plusTime = new double[maxRow][maxRow];
    private double[][] minusTime = new double[maxRow][maxRow];
    private int[][] multSolvingValues = new int[maxRow][maxRow];
    private int[][] plusSolvingValues = new int[maxRow][maxRow];
    private int[][] minusSolvingValues = new int[maxRow][maxRow];
    private int maxSolvesThisRun; //Number of exercises until we have to reset the solved array
    private int solvesThisRun = 0; //Number of exercises already solved this run
    private mtOperator op = null;  //Buffers randomly generated operator
    private int firstFactor;
    private int secondFactor;
    private long exerciseStartTime; //The first time in ms the user sees the exercise
    //Statistics counters
    private int overallSolved; //Overall solved exercises for this name
    private int rightSolved; //Exercises the name has anwered correctly
    private int falseSolved; //Exercises the name has anwered wrong   
    
    
    private void generateNewExercise()
    {
        
        //Check if exercises should be questioned more than once per run
        boolean askOnce = settingsFrame.getOptions()[0];
        
        //Predeclare variables and initialize vector size variables
        int vectorElements = settingsFrame.getRows().size();
        int operators = settingsFrame.getOperators().size();
        
        //Calculate max solves this run
        maxSolvesThisRun = 3 * vectorElements * vectorElements; //3: Number of operators
        
        //Get random operator
        int operatorIndex = rand.nextInt(operators);
        op = settingsFrame.getOperators().elementAt(operatorIndex);
        
        //Set reference to the appropriate 2-dimensional solved array (depending on operator
        boolean[][] solved = null;
        switch(op)
        {
            case MULT:
                {
                    solved = multSolved;
                    break;
                }
            case PLUS:
                {
                    solved = plusSolved;
                    break;
                }
            case MINUS:
                {
                    solved = minusSolved;
                    break;
                }
            default: break;
        }
        
        //Generate random numbers and check if this exercise has already been solved
        while(true)
            {
            firstFactor = settingsFrame.getRows().elementAt(rand.nextInt(vectorElements));
            secondFactor = settingsFrame.getRows().elementAt(rand.nextInt(vectorElements));
            if(!(solved[firstFactor-1][secondFactor-1]) || !(askOnce)) {break;}
            }
        
        //Update GUI (factors
        firstFactorLabel.setText(Integer.toString(firstFactor));
        secondFactorLabel.setText(Integer.toString(secondFactor));
        
        //Update GUI and calculate correct result depending on the selected operator
        //and set the reference to the appropriate 
        JTable resultsTable = null; //Forward declaration
        switch(op) //Add 1 to avoid ArrayIndeyOutOfBounds exception
            {
            case MULT:
                {
                    correctResult = firstFactor * secondFactor;
                    operatorLabel.setText("<html>&#9679");
                    resultsTable = statisticsFrame.multResultsTable;
                    break;
                }
            case PLUS:
                {
                    correctResult = firstFactor + secondFactor;
                    operatorLabel.setText("+");
                    break;
                }
            case MINUS:
                {
                    correctResult = firstFactor - secondFactor;
                    operatorLabel.setText("-");
                    break;
                }
            default: break;
            }
        
        
        //Mark exercise as solved
        solved[firstFactor-1][secondFactor-1] = true;
        
        //Increment solvesThisRun and reset array if greater than maxSolvesThisRun
        solvesThisRun++;
        if(solvesThisRun == maxSolvesThisRun)
            {
                solvesThisRun = 0; //Reset Counter
                solved = new boolean[maxRow][maxRow]; //Reset array
                setSolvingValues(new int[maxRow][maxRow]); //Reset solving values
            }
        
        //Update timer
        exerciseStartTime = System.currentTimeMillis();
        
        //Mark text on the result field and focus it
        resultField.requestFocus();
        resultField.selectAll();
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
        nameField = new javax.swing.JTextField();
        firstFactorLabel = new javax.swing.JLabel();
        secondFactorLabel = new javax.swing.JLabel();
        operatorLabel = new javax.swing.JLabel();
        isLabel = new javax.swing.JLabel();
        resultField = new javax.swing.JTextField();
        showSettingsFrameButton = new javax.swing.JButton();
        showStatisticsButton = new javax.swing.JButton();
        timeDescriptorLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JMathTrainer");
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("mainFrame"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        okButton.setText("OK");
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });

        correctnessLabel.setFont(new java.awt.Font("Tahoma", 1, 36));
        correctnessLabel.setForeground(new java.awt.Color(0, 255, 0));

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

        operatorLabel.setFont(new java.awt.Font("Tahoma", 0, 24));
        operatorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        operatorLabel.setText("<html>&#9679");

        isLabel.setFont(new java.awt.Font("Tahoma", 0, 48));
        isLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        isLabel.setText("=");

        resultField.setFont(new java.awt.Font("Tahoma", 0, 48));
        resultField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        resultField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                resultFieldKeyTyped(evt);
            }
        });

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

        timeDescriptorLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        timeDescriptorLabel.setText("Zeit:");

        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        nameLabel.setText("Name:");

        timeLabel.setToolTipText("Benötigte Zeit für die letzte Aufgabe");

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
                                .addComponent(operatorLabel)
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
                                        .addComponent(showSettingsFrameButton)
                                        .addGap(18, 18, 18)
                                        .addComponent(showStatisticsButton))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(timeDescriptorLabel)
                                                .addGap(18, 18, 18)
                                                .addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                                            .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(nameLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))))))))
                .addContainerGap(374, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(isLabel)
                        .addComponent(resultField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(secondFactorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(firstFactorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(operatorLabel)
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
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLabel)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timeDescriptorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseClicked
        //Check if the user has solved the exercise correctly (catching when typed letters instead of numbers)
        int result = 0;
        try{result = Integer.parseInt(resultField.getText());}
        catch(NumberFormatException e)
        {
            //Set correctnessLabel to error message and select text in it to allow the user to correct it rapidly
            correctnessLabel.setForeground(Color.RED);
            correctnessLabel.setText("Eingabe!");
            resultField.requestFocus();
            resultField.selectAll();
            return;
        }
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
        getSolvingValues()[firstFactor-1][secondFactor-1] = result;
        
        //Update statistics
        statisticsFrame.setOverallSolved(overallSolved);
        statisticsFrame.setRightSolved(rightSolved);
        statisticsFrame.setFalseSolved(falseSolved);
        
        //Set reference to the appropriate resultsTable in statisticsFrame (depending on the operator)
        //and set the appropriate solved array reference; set appropriate solving and time array reference
        boolean[][] solved = null;
        double[][] timesFloatTable = null;
        JTable timeTable = null;
        JTable resultsTable = null; //Forward declaration
        switch(op) //Add 1 to avoid ArrayIndeyOutOfBounds exception
            {
            case MULT:
                {
                    correctResult = firstFactor * secondFactor;
                    operatorLabel.setText("<html>&#9679");
                    resultsTable = statisticsFrame.multResultsTable;
                    solved = multSolved;
                    timesFloatTable = multTime;
                    timeTable = statisticsFrame.multTimeTable;
                    break;
                }
            case PLUS:
                {
                    correctResult = firstFactor + secondFactor;
                    operatorLabel.setText("+");
                    resultsTable = statisticsFrame.plusResultsTable;
                    solved = plusSolved;
                    timesFloatTable = plusTime;
                    break;
                }
            case MINUS:
                {
                    correctResult = firstFactor - secondFactor;
                    operatorLabel.setText("-");
                    resultsTable = statisticsFrame.minusResultsTable;
                    solved = minusSolved;
                    timesFloatTable = minusTime;
                    break;
                }
            default: break;
            }
        
        //Calculate time it took to solve the exercise and write into appropriate table
        long timeNow = System.currentTimeMillis();
        double solvingTime = (timeNow - exerciseStartTime)*0.001;
        timesFloatTable[firstFactor-1][secondFactor-1] = solvingTime;
        String solvingTimeString = twoPlacesFormat.format(solvingTime);
        timeLabel.setText(solvingTimeString + "s"); //Update time label
        
        //Update appropriate statistics table if liveUpdate is on.
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                //Update cell color and value
                if(!solved[i][j])
                    {
                        resultsTable.setValueAt("<html><div bgcolor=dimgray align=center>&#160;&#160;&#160;</div>", i+1, j+1);
                        timeTable.setValueAt("<html><div bgcolor=dimgray align=center>&#160;&#160;&#160;</div>",i+1,j+1);
                    }
                else if(getSolvingValues()[i][j] == (i+1)*(j+1))
                    {
                        resultsTable.setValueAt("<html><div bgcolor=green align=center>&#160;" + Integer.toString(getSolvingValues()[i][j]) + "&#160;</div>", i+1, j+1);
                        timeTable.setValueAt("<html><div bgcolor=green align=center>&#160;" + solvingTimeString + "&#160;</div>", i+1, j+1);
                    }
                else
                    {
                        resultsTable.setValueAt("<html><div bgcolor=red align=center>&#160;" + Integer.toString(getSolvingValues()[i][j]) + "&#160;</div>", i+1, j+1);
                        timeTable.setValueAt("<html><div bgcolor=red align=center>&#160;" + solvingTimeString + "&#160;</div>", i+1, j+1);
                    }
                //Update time table
                
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

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        //Quit application
        System.exit(0);
        
    }//GEN-LAST:event_formWindowClosed

    private void resultFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_resultFieldKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            okButtonMouseClicked(null);
        }
        
    }//GEN-LAST:event_resultFieldKeyTyped
    
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
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel operatorLabel;
    private javax.swing.JTextField resultField;
    private javax.swing.JLabel secondFactorLabel;
    private javax.swing.JButton showSettingsFrameButton;
    private javax.swing.JButton showStatisticsButton;
    private javax.swing.JLabel timeDescriptorLabel;
    private javax.swing.JLabel timeLabel;
    // End of variables declaration//GEN-END:variables

    public boolean[][] getSolved() {
        return multSolved;
    }

    public int[][] getSolvingValues()
    {
        int[][] returnValue = null;
        switch(op)
        {
            case MULT:
                {returnValue =  multSolvingValues;break;}
            case PLUS:
                {returnValue =  plusSolvingValues;break;}
            case MINUS:
                {returnValue =  minusSolvingValues;break;}
            default: break;
        }
        return returnValue;
    }
    
    public void setSolvingValues(int[][] input)
    {
        switch(op)
        {
            case MULT:
                {multSolvingValues = input;break;}
            case PLUS:
                {plusSolvingValues = input;break;}
            case MINUS:
                {minusSolvingValues = input;break;}
            default: break;
        }
    }
}


import custom_classes.FrameUtils;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import java.sql.ResultSet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Kai Jie
 */
public class PuzzleFrame extends javax.swing.JFrame {
    final private MainLauncher mainLauncher;        
    final private GameLevelFrame gamelevelFrame;
    final private JButton[] buttons;
    Color darkBrown = new Color(180, 120, 50);
    public String level,demo,userEmail;
    private Timer timer;
    private int elapsedTime = 0;
    private ImageIcon image;
    private int count=0,comp,placenum=1,form1=1,gamenum=2,succhk=0;
    private final int[][] pos = {
        {1,1},{1,2},{1,3},{1,4},
        {2,1},{2,2},{2,3},{2,4},
        {3,1},{3,2},{3,3},{3,4},
        {4,1},{4,2},{4,3},{4,4}
    };
    

    /**
     * Creates new form PuzzleFrame
     * @param mainLauncher
     * @param level
     * @param gamelevelFrame
     */
    public PuzzleFrame(MainLauncher mainLauncher, String level, GameLevelFrame gamelevelFrame) {
        initComponents();
        
        // Use the utility method to set title and icon
        FrameUtils.setFrameTitleAndIcon(this, "PuzzlePath 15", "/resources/15.png");
        
        this.mainLauncher = mainLauncher;
        this.gamelevelFrame = gamelevelFrame;        
        this.demo = level;
        this.level = level;
        this.userEmail = mainLauncher.getUserEmail();
        this.buttons = new JButton[]{n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15};

        lbl_title.setText("Game Level : " + level);

        jPanel1.setBorder(new LineBorder(darkBrown, 5));
        jPanel1.setBackground(new Color(210, 180, 140, 255));
        jDesktopPane2.setBorder(new LineBorder(darkBrown, 5));
        jDesktopPane2.setBackground(new Color(210, 180, 140, 255));
        txt_count.setFocusable(false);
        
        Timer();
        buttonsDisabled();
        demoArrangement();
        lvl1Arrangement();
        lvl2Arrangement();
        lvl3Arrangement();

    }

    private void buttonsEnabled() {
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
    }
    
    private void buttonsDisabled() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }
    
    // Initialize the Timer
    private void Timer() {
        timer = new Timer(1000, (ActionEvent e) -> {
            elapsedTime++;
            updateTimerLabel();
        });
    }
    
    private void updateTimerLabel() {
        int minutes = (elapsedTime % 3600) / 60;
        int seconds = elapsedTime % 60;
        
        String formattedTime = String.format("%02d:%02d", minutes, seconds);
        lbl_time.setText("Time: " + formattedTime);
    }
    
    private void startTimer() {
        if (!timer.isRunning()) {
            timer.start();
            buttonsEnabled();
        }
    }

    private void pauseTimer() {
        if (timer.isRunning()) {
            timer.stop();
            buttonsDisabled();
        }
    }
    
    private void stopTimer() {
        if (timer.isRunning()) {
            timer.stop();
            buttonsDisabled(); 
        }
    }
    
    private void counter() {
        count++;
        txt_count.setText("No. of Moves: " + count);
    }
    
    private void handleAction(int posIndex, javax.swing.JButton button, int[][] poso_lvlx) {
        counter();

        // Logic to update positions and button states
        if (pos[posIndex][0] == pos[15][0] && pos[posIndex][1] == pos[15][1] - 1) {
            // Move tile right
            pos[posIndex][1] = pos[posIndex][1] + 1;
            pos[15][1] = pos[15][1] - 1;
            button.setBounds(button.getX() + button.getWidth(), button.getY(), button.getWidth(), button.getHeight());

        } else if (pos[posIndex][0] == pos[15][0] && pos[posIndex][1] == pos[15][1] + 1) {
            // Move tile left
            pos[posIndex][1] = pos[posIndex][1] - 1;
            pos[15][1] = pos[15][1] + 1;
            button.setBounds(button.getX() - button.getWidth(), button.getY(), button.getWidth(), button.getHeight());

        } else if (pos[posIndex][0] == pos[15][0] - 1 && pos[posIndex][1] == pos[15][1]) {
            // Move tile down
            pos[posIndex][0] = pos[posIndex][0] + 1;
            pos[15][0] = pos[15][0] - 1;
            button.setBounds(button.getX(), button.getY() + button.getHeight(), button.getWidth(), button.getHeight());

        } else if (pos[posIndex][0] == pos[15][0] + 1 && pos[posIndex][1] == pos[15][1]) {
            // Move tile up
            pos[posIndex][0] = pos[posIndex][0] - 1;
            pos[15][0] = pos[15][0] + 1;
            button.setBounds(button.getX(), button.getY() - button.getHeight(), button.getWidth(), button.getHeight());
        }
        
        button.repaint();

        // Check if the positions match the original positions
        if (count != 0) {
            int i, j;
            boolean match = true;
            for (i = 0; i < 16; i++) {
                for (j = 0; j < 2; j++) {
                    if (pos[i][j] != poso_lvlx[i][j]) {
                        match = false;
                        break;
                    }
                }
            }
            if (match) {
                succhk = 1;
                stopTimer();
                winInfoDialog();
            }
        }
    }

    private boolean checkDemo() {
        return "Demo".equals(level);
    }
    
    private void winInfoDialog() {
        image = new ImageIcon(getClass().getResource("/resources/win.png"));
        
        JOptionPane.showMessageDialog(null,
                    "Congratulations!\nYou have successfully completed the puzzle.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE,
                    image);
        
        if (checkDemo()) {
            FrameUtils.goToFrame(this, mainLauncher);
        } else {
            // Ask user if they want to save their score to the ranking
            int response = JOptionPane.showConfirmDialog(this,
                    "Would you like to save your score to the score board?",
                    "Save Score",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                try {
                    saveScore();
                    
                    // Logging or further actions
                    System.out.println("Score saved successfully.");
                } catch (HeadlessException e) {
                    JOptionPane.showMessageDialog(this,
                            "An error occurred while saving your score: " + e.getMessage(),
                            "Save Error",
                            JOptionPane.ERROR_MESSAGE);
                    
                    // Logging error
                    System.err.println("Error saving score: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Your score was not saved.",
                        "Score Not Saved",
                        JOptionPane.INFORMATION_MESSAGE);
                System.out.println("User chose not to save the score.");
            }
            
            FrameUtils.goToFrame(this, gamelevelFrame);
        }
    }
    
    // Convert elapsed time in seconds to SQL TIME format (HH:mm:ss)
    private String formatElapsedTime(int elapsedTimeInSeconds) {
        int hours = elapsedTimeInSeconds / 3600;
        int minutes = (elapsedTimeInSeconds % 3600) / 60;
        int seconds = elapsedTimeInSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    
    private void saveScore() {
        // Format timeSeconds as HH:mm:ss
        String time = formatElapsedTime(this.elapsedTime);

        // SQL queries
        String getUserIdQuery = "SELECT id FROM user WHERE email = ?";
        String updateScoreQuery = 
            "UPDATE score SET moves = ?, time_seconds = ?, timestamp = NOW() " +
            "WHERE user_id = ? AND level = ?";
        String insertScoreQuery = 
            "INSERT INTO score (user_id, level, moves, time_seconds, timestamp) " +
            "VALUES (?, ?, ?, ?, NOW())";

        try (Connection con = FrameUtils.getConnection();
             PreparedStatement getUserIdStmt = con.prepareStatement(getUserIdQuery)) {

            // Set email parameter to get the user ID
            getUserIdStmt.setString(1, userEmail);

            try (ResultSet rs = getUserIdStmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("id");

                    // First, try to update the existing score
                    try (PreparedStatement updateScoreStmt = con.prepareStatement(updateScoreQuery)) {
                        updateScoreStmt.setInt(1, this.count); // Moves
                        updateScoreStmt.setString(2, time);    // Time formatted as HH:mm:ss
                        updateScoreStmt.setInt(3, userId);     // User ID
                        updateScoreStmt.setString(4, this.level); // Level

                        int rowsUpdated = updateScoreStmt.executeUpdate();

                        // If no rows were updated, insert a new score record
                        if (rowsUpdated == 0) {
                            try (PreparedStatement insertScoreStmt = con.prepareStatement(insertScoreQuery)) {
                                insertScoreStmt.setInt(1, userId); // User ID
                                insertScoreStmt.setString(2, this.level); // Level
                                insertScoreStmt.setInt(3, this.count); // Moves
                                insertScoreStmt.setString(4, time); // Time formatted as HH:mm:ss

                                int rowsInserted = insertScoreStmt.executeUpdate();
                                if (rowsInserted > 0) {
                                    JOptionPane.showMessageDialog(null,
                                        "Your score has been successfully saved!",
                                        "Score Saved",
                                        JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(this,
                                        "Failed to insert new score record.",
                                        "Insert Error",
                                        JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,
                                "Your score has been successfully updated!",
                                "Score Updated",
                                JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                        "No user found with the provided email address.",
                        "User Not Found",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                "An error occurred: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }


    private void demoArrangement() {
        if(checkDemo()){
            // change position
            int[][] poso_demo = {
                {1,1},{1,2},{1,4},{2,3},
                {2,1},{2,2},{1,3},{2,4},
                {3,1},{3,3},{3,4},{4,3},
                {4,1},{3,2},{4,2},{4,4}
            };
        
            // change button number label
            n3.setText("4");
            n4.setText("7");
            n7.setText("3");
            
            n10.setText("11");            
            n11.setText("12");
            n12.setText("15");            
            n14.setText("10");
            n15.setText("14");
        
            // Add action listeners to buttons
            for (int i = 0; i < buttons.length; i++) {
                final int index = i;  
                buttons[i].addActionListener(evt -> handleAction(index, buttons[index], poso_demo));
            }
        }
    }

    private void lvl1Arrangement() {
        if("1".equals(level)){
            // change position
            int[][] poso_lvl1 = {
                {1,1},{1,2},{1,3},{1,4},
                {2,1},{3,3},{2,2},{2,4},
                {3,1},{2,3},{4,2},{3,4},
                {4,1},{3,2},{4,3},{4,4}
            };
        
            // change button number label
            n6.setText("11");
            n7.setText("6");
            
            n10.setText("7");
            n11.setText("14");
            n14.setText("10");
        
            // Add action listeners to buttons
            for (int i = 0; i < buttons.length; i++) {
                final int index = i;  
                buttons[i].addActionListener(evt -> handleAction(index, buttons[index], poso_lvl1));
            }
        }
    }

    private void lvl2Arrangement() {
        if("2".equals(level)){
            // change position
            int[][] poso_lvl2 = {
                {3,1},{4,3},{3,4},{3,2},
                {3,3},{1,3},{2,1},{4,1},
                {1,1},{2,4},{1,2},{2,2},
                {4,2},{2,3},{1,4},{4,4}
            };
        
            // change button number label
            n1.setText("9");
            n2.setText("15");
            n3.setText("12");
            n4.setText("10");
            
            n5.setText("11");
            n6.setText("3");
            n7.setText("5");
            n8.setText("13");
            
            n9.setText("1");
            n10.setText("8");
            n11.setText("2");
            n12.setText("6");
            
            n13.setText("14");
            n14.setText("7");
            n15.setText("4");
        
            // Add action listeners to buttons
            for (int i = 0; i < buttons.length; i++) {
                final int index = i;  
                buttons[i].addActionListener(evt -> handleAction(index, buttons[index], poso_lvl2));
            }
        }
    }

    private void lvl3Arrangement() {
        if("3".equals(level)){
            // change position
            int[][] poso_lvl3 = {
                {4,3},{1,1},{2,3},{3,4},
                {3,1},{3,3},{3,2},{4,1},
                {4,2},{1,2},{2,1},{2,2},
                {2,4},{1,3},{1,4},{4,4}
            };
        
            // change button number label
            n1.setText("15");
            n2.setText("1");
            n3.setText("7");
            n4.setText("12");
            
            n5.setText("9");
            n6.setText("11");
            n7.setText("10");
            n8.setText("13");
            
            n9.setText("14");
            n10.setText("2");
            n11.setText("5");
            n12.setText("6");
            
            n13.setText("8");
            n14.setText("3");
            n15.setText("4");
        
            // Add action listeners to buttons
            for (int i = 0; i < buttons.length; i++) {
                final int index = i;  
                buttons[i].addActionListener(evt -> handleAction(index, buttons[index], poso_lvl3));
            }
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
        txt_count = new javax.swing.JTextField();
        lbl_title = new javax.swing.JLabel();
        lbl_time = new javax.swing.JLabel();
        btn_start = new javax.swing.JButton();
        btn_pause = new javax.swing.JButton();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        n10 = new javax.swing.JButton();
        n12 = new javax.swing.JButton();
        n5 = new javax.swing.JButton();
        n7 = new javax.swing.JButton();
        n6 = new javax.swing.JButton();
        n8 = new javax.swing.JButton();
        n1 = new javax.swing.JButton();
        n13 = new javax.swing.JButton();
        n15 = new javax.swing.JButton();
        n11 = new javax.swing.JButton();
        n14 = new javax.swing.JButton();
        n4 = new javax.swing.JButton();
        n2 = new javax.swing.JButton();
        n9 = new javax.swing.JButton();
        n3 = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_count.setEditable(false);
        txt_count.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_count.setText("No. of Moves : 0");
        txt_count.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_countActionPerformed(evt);
            }
        });

        lbl_title.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_title.setForeground(new java.awt.Color(255, 255, 0));
        lbl_title.setText("Game: Levels X");

        lbl_time.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_time.setText("Time: 00:00");

        btn_start.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_start.setText("START");
        btn_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startActionPerformed(evt);
            }
        });

        btn_pause.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_pause.setText("PAUSE");
        btn_pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pauseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_pause, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_start, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_count)
                    .addComponent(lbl_time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_time)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_count, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_start, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pause, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 240));

        jDesktopPane2.setBackground(javax.swing.UIManager.getDefaults().getColor("tab_mouse_over_fill_dark_upper"));
        jDesktopPane2.setOpaque(false);

        n10.setText("10");
        n10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        n10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n10ActionPerformed(evt);
            }
        });
        jDesktopPane2.add(n10);
        n10.setBounds(60, 120, 60, 60);

        n12.setText("12");
        n12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        n12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n12ActionPerformed(evt);
            }
        });
        jDesktopPane2.add(n12);
        n12.setBounds(180, 120, 60, 60);

        n5.setText("5");
        n5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        n5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n5ActionPerformed(evt);
            }
        });
        jDesktopPane2.add(n5);
        n5.setBounds(0, 60, 60, 60);

        n7.setText("7");
        n7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        n7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n7ActionPerformed(evt);
            }
        });
        jDesktopPane2.add(n7);
        n7.setBounds(120, 60, 60, 60);

        n6.setText("6");
        n6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        n6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n6ActionPerformed(evt);
            }
        });
        jDesktopPane2.add(n6);
        n6.setBounds(60, 60, 60, 60);

        n8.setText("8");
        n8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        n8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n8ActionPerformed(evt);
            }
        });
        jDesktopPane2.add(n8);
        n8.setBounds(180, 60, 60, 60);

        n1.setText("1");
        n1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        n1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n1ActionPerformed(evt);
            }
        });
        jDesktopPane2.add(n1);
        n1.setBounds(0, 0, 60, 60);

        n13.setText("13");
        n13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        n13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n13ActionPerformed(evt);
            }
        });
        jDesktopPane2.add(n13);
        n13.setBounds(0, 180, 60, 60);

        n15.setText("15");
        n15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        n15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n15ActionPerformed(evt);
            }
        });
        jDesktopPane2.add(n15);
        n15.setBounds(120, 180, 60, 60);

        n11.setText("11");
        n11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        n11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n11ActionPerformed(evt);
            }
        });
        jDesktopPane2.add(n11);
        n11.setBounds(120, 120, 60, 60);

        n14.setText("14");
        n14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        n14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n14ActionPerformed(evt);
            }
        });
        jDesktopPane2.add(n14);
        n14.setBounds(60, 180, 60, 60);

        n4.setText("4");
        n4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        n4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n4ActionPerformed(evt);
            }
        });
        jDesktopPane2.add(n4);
        n4.setBounds(180, 0, 60, 60);

        n2.setText("2");
        n2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        n2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n2ActionPerformed(evt);
            }
        });
        jDesktopPane2.add(n2);
        n2.setBounds(60, 0, 60, 60);

        n9.setText("9");
        n9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        n9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n9ActionPerformed(evt);
            }
        });
        jDesktopPane2.add(n9);
        n9.setBounds(0, 120, 60, 60);

        n3.setText("3");
        n3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        n3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n3ActionPerformed(evt);
            }
        });
        jDesktopPane2.add(n3);
        n3.setBounds(120, 0, 60, 60);

        getContentPane().add(jDesktopPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 240, 240));

        btn_back.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_back.setForeground(new java.awt.Color(51, 0, 0));
        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 115, 30));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/info.png"))); // NOI18N
        jButton2.setText("Guide");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 290, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/main_bg.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, -1));

        jMenu1.setText("Menu");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/menu.png"))); // NOI18N
        jMenuItem1.setText("Home");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/close.png"))); // NOI18N
        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        FrameUtils.goToFrame(this, mainLauncher);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        if(!checkDemo()) {
            FrameUtils.goToFrame(this, gamelevelFrame);
        } else {
            FrameUtils.goToFrame(this, mainLauncher);
        }
        
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_startActionPerformed
        startTimer();
    }//GEN-LAST:event_btn_startActionPerformed

    private void btn_pauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pauseActionPerformed
        pauseTimer();
    }//GEN-LAST:event_btn_pauseActionPerformed

    private void txt_countActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_countActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_countActionPerformed

    private void n10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n10ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_n10ActionPerformed

    private void n12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n12ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_n12ActionPerformed

    private void n5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n5ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_n5ActionPerformed

    private void n7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n7ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_n7ActionPerformed

    private void n6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n6ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_n6ActionPerformed

    private void n8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n8ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_n8ActionPerformed

    private void n1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n1ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_n1ActionPerformed

    private void n13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n13ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_n13ActionPerformed

    private void n15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n15ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_n15ActionPerformed

    private void n11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n11ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_n11ActionPerformed

    private void n14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n14ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_n14ActionPerformed

    private void n4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n4ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_n4ActionPerformed

    private void n2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n2ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_n2ActionPerformed

    private void n9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n9ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_n9ActionPerformed

    private void n3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n3ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_n3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        image = new ImageIcon(getClass().getResource("/resources/guide.png"));

        // Display success dialog with custom icon
        JOptionPane.showMessageDialog(null,
                "To solve the puzzle, arrange the pieces in the \nsame sequence as shown in the adjacent image.",
                "Guide",
                JOptionPane.INFORMATION_MESSAGE,
                image);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(PuzzleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PuzzleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PuzzleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PuzzleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        MainLauncher mainLauncher = new MainLauncher();        
        GameLevelFrame gamelevelFrame = new GameLevelFrame(mainLauncher);
        String level = "x";
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PuzzleFrame(mainLauncher, level, gamelevelFrame).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_pause;
    private javax.swing.JButton btn_start;
    private javax.swing.JButton jButton2;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_time;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JButton n1;
    private javax.swing.JButton n10;
    private javax.swing.JButton n11;
    private javax.swing.JButton n12;
    private javax.swing.JButton n13;
    private javax.swing.JButton n14;
    private javax.swing.JButton n15;
    private javax.swing.JButton n2;
    private javax.swing.JButton n3;
    private javax.swing.JButton n4;
    private javax.swing.JButton n5;
    private javax.swing.JButton n6;
    private javax.swing.JButton n7;
    private javax.swing.JButton n8;
    private javax.swing.JButton n9;
    private javax.swing.JTextField txt_count;
    // End of variables declaration//GEN-END:variables
}

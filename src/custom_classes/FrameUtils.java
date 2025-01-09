package custom_classes;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kai Jie
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class FrameUtils {
    
    /**
     * Sets the title and icon image for a JFrame.
     *
     * @param frame the JFrame to configure
     * @param title the title to set for the JFrame
     * @param iconPath the path to the icon image file (relative to the class path)
     */
    public static void setFrameTitleAndIcon(JFrame frame, String title, String iconPath) {
        frame.setTitle(title);
        ImageIcon icon = new ImageIcon(frame.getClass().getResource(iconPath));
        frame.setIconImage(icon.getImage());
    }
    
    public static void goToFrame(JFrame this_frame, JFrame frame) {
        frame.setVisible(true);
        this_frame.dispose();
    }
    
    public static void addHoverEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.yellow); // Change text color on hover
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor
                button.setBorder(new LineBorder(Color.YELLOW, 2)); // Add yellow border with thickness 2
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(new Color(51, 0, 0)); // Change text color back to default
                button.setCursor(Cursor.getDefaultCursor()); // Change cursor back to default
                button.setBorder(new LineBorder(Color.GRAY, 1)); // Restore the default border
            }
        });
    }
    
    public static void addHoverMouse(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setCursor(Cursor.getDefaultCursor()); // Change cursor back to default
            }
        });
    }
    
    // Start getConnection()
    private static final String URL = "jdbc:mysql://localhost:3306/vp_puzzle15"; // phpmyadmin MySQL & DB name
    private static final String USER = "root";  //phpmyadmin user
    private static final String PASSWORD = "";  //phpmyadmin pw
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle the error as appropriate
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    // End getConnection()
    
}
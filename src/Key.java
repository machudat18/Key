import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Key {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JPanel controlPanel;
    private Thread thread;
    private int count;

    public Key() {
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame();
        mainFrame.setSize(600, 200);
        mainFrame.setLayout(new GridLayout(3, 1));
        headerLabel = new JLabel("", JLabel.CENTER);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
        mainFrame.setTitle("Tool auto click space của cậu ba");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        thread = new Thread(new Loader());
        thread.start();
        mainFrame.setVisible(true);
    }


    class Loader implements Runnable {
        Robot r;

        public Loader() {
            try {
                r = new Robot();
            } catch (AWTException awtException) {
                awtException.printStackTrace();
            }
        }

        public void run() {
            try {
                while (true) {
                    Thread.sleep(1000);
                    r.keyPress(KeyEvent.VK_SPACE);
                    r.keyRelease(KeyEvent.VK_SPACE);
                    count++;
                    headerLabel.setText("Đã nhấn space : " + count + " lần , tắt mẹ cái app đi để dừng \n, đéo làm nút đừng đâu");
                }

            } catch (InterruptedException e) {
                thread.stop();
                e.printStackTrace();
            }

        }
    }

}
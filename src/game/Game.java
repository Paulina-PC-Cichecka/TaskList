package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Game extends JFrame implements KeyListener {

    public GamePanel gamePanel;
    public boolean leftPressed, rightPressed;
    public JButton resultButton;
    public JButton stateButton;
    public JButton cancelButton;
    private JScrollPane scrollPane;
    private JList<String> jList;
    private DefaultListModel<String> listModel;
    private List<FutureHandler> tasks;

    public Game() {
        setTitle("Jakieś wąty???");

        tasks = new ArrayList<>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1380, 770));
        setLocation(200, 100);

        gamePanel = new GamePanel(this);

        add(gamePanel, BorderLayout.CENTER);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setPreferredSize(new Dimension(300, 770));
        add(listPanel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(resultButton = new JButton("Result"));
        buttonPanel.add(stateButton = new JButton("State"));
        buttonPanel.add(cancelButton = new JButton("Cancel"));
        resultButton.setFocusable(false);
        stateButton.setFocusable(false);
        cancelButton.setFocusable(false);
        listPanel.add(buttonPanel, BorderLayout.SOUTH);

        resultButton.addActionListener(event -> {
            int id = Integer.parseInt((jList.getSelectedValue().split(" ")[2]));
            FutureHandler task = tasks.get(id - 1);

            JOptionPane.showMessageDialog(
                    null, task.getResult(), "Result", JOptionPane.INFORMATION_MESSAGE
            );
        });

        stateButton.addActionListener(event -> {
            int id = Integer.parseInt((jList.getSelectedValue().split(" ")[2]));
            FutureHandler task = tasks.get(id - 1);

            JOptionPane.showMessageDialog(
                    null, task.getState(), "State", JOptionPane.INFORMATION_MESSAGE
            );
        });

        cancelButton.addActionListener(event -> {
            int id = Integer.parseInt((jList.getSelectedValue().split(" ")[2]));
            FutureHandler task = tasks.get(id - 1);

            gamePanel.cats.remove(task.cat);
            task.future.cancel(true);
        });

        listModel = new DefaultListModel<>();
        jList = new JList<>(listModel);
        scrollPane = new JScrollPane(jList);
        listPanel.add(scrollPane, BorderLayout.CENTER);
        jList.setFocusable(false);

        pack();
        setVisible(true);
        setResizable(false);
    }

    public void addTask(FutureHandler task) {
        tasks.add(task);
        listModel.addElement(task.toString());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}

import javax.swing.*;
import java.awt.event.*;

public class AdminMenuFrame extends JFrame {
    
    public AdminMenuFrame() {
        setTitle("Library Management System - Admin Menu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton addBookButton = new JButton("Add Book");
        addBookButton.setBounds(10, 20, 150, 25);
        panel.add(addBookButton);

        JButton removeBookButton = new JButton("Remove Book");
        removeBookButton.setBounds(10, 50, 150, 25);
        panel.add(removeBookButton);

        JButton viewUsersButton = new JButton("View Users");
        viewUsersButton.setBounds(10, 80, 150, 25);
        panel.add(viewUsersButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 110, 150, 25);
        panel.add(backButton);

        addBookButton.addActionListener(e -> {
            new AddBookFrame();
            dispose();
        });

        removeBookButton.addActionListener(e -> {
            new RemoveBookFrame();
            dispose();
        });

        viewUsersButton.addActionListener(e -> {
            new ViewUsersFrame();
            dispose();
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginFrame();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new AdminMenuFrame();
    }
}

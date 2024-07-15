import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;

    public RegistrationForm() {
        setTitle("Library Management System - Register");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(10, 80, 80, 25);
        panel.add(roleLabel);

        String[] roles = { "user", "admin" };
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setBounds(100, 80, 165, 25);
        panel.add(roleComboBox);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(10, 120, 80, 25);
        panel.add(registerButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 120, 80, 25);
        panel.add(backButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    registerUser();
                } catch (IOException | JSONException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(RegistrationForm.this, "Error registering user.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Main();
                dispose();
            }
        });
    }

    private void registerUser() throws IOException, JSONException {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String role = (String) roleComboBox.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JSONObject response = DatabaseConnection.register(username, password, role);
        if (response.getBoolean("success")) {
            JOptionPane.showMessageDialog(this, "User registered successfully!");
            dispose();
            new Main();
        } else {
            JOptionPane.showMessageDialog(this, response.getString("message"), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

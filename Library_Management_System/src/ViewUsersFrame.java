import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class ViewUsersFrame extends JFrame {

    public ViewUsersFrame() {
        setTitle("View Users");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("User Details");
        titleLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(10));

        JTextArea userDetailsArea = new JTextArea(10, 30);
        userDetailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(userDetailsArea);
        panel.add(scrollPane);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        panel.add(refreshButton);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        panel.add(backButton);

        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String usersInfo = getUsersInfo();
                    userDetailsArea.setText(usersInfo);
                } catch (IOException | JSONException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ViewUsersFrame.this, "Error fetching user details.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdminMenuFrame(); // Assuming AdminMenuFrame exists for navigation
            }
        });

        // Initial load of user details
        try {
            String usersInfo = getUsersInfo();
            userDetailsArea.setText(usersInfo);
        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(ViewUsersFrame.this, "Error fetching user details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getUsersInfo() throws IOException, JSONException {
        JSONObject response = DatabaseConnection.getUsers();
        if (response.getBoolean("success")) {
            StringBuilder sb = new StringBuilder();
            JSONArray usersArray = response.getJSONArray("users");
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject user = usersArray.getJSONObject(i);
                sb.append("User ID: ").append(user.getInt("id")).append("\n");
                sb.append("Username: ").append(user.getString("username")).append("\n");
                sb.append("Role: ").append(user.getString("role")).append("\n");
                sb.append("--------------------\n");
            }
            return sb.toString();
        } else {
            return "No users found.";
        }
    }

    public static void main(String[] args) {
        new ViewUsersFrame();
    }
}



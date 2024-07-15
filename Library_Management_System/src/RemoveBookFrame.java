import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException; 
import org.json.JSONObject;

public class RemoveBookFrame extends JFrame {
    public RemoveBookFrame() {
        setTitle("Remove Book");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Select Book:");
        titleLabel.setBounds(10, 20, 80, 25);
        panel.add(titleLabel);

        JComboBox<String> bookList = new JComboBox<>(); // Combo box to hold book titles
        try {
            // Fetch book titles from the server and populate the combo box
            JSONObject response = DatabaseConnection.getBookTitles();
            if (response.getBoolean("success")) {
                JSONArray titlesArray = response.getJSONArray("titles");
                for (int i = 0; i < titlesArray.length(); i++) {
                    bookList.addItem(titlesArray.getString(i));
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to fetch book titles.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        bookList.setBounds(100, 20, 165, 25);
        panel.add(bookList);

        JButton removeButton = new JButton("Remove");
        removeButton.setBounds(10, 80, 80, 25);
        panel.add(removeButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(180, 80, 80, 25);
        panel.add(backButton);

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call method to remove the selected book
                String selectedBook = (String) bookList.getSelectedItem();
                removeBook(selectedBook);
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminMenuFrame();
                dispose();
            }
        });
    }

    private void removeBook(String bookTitle) {
        // Implement book removal logic here
        try {
            JSONObject response = DatabaseConnection.deleteBook(bookTitle);
            if (response.getBoolean("success")) {
                JOptionPane.showMessageDialog(this, "Book removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to remove book: " + response.getString("message"), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


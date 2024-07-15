import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class AddBookFrame extends JFrame {
    private JTextField titleField;
    private JTextField authorField;
    private JTextField yearField;
    private JCheckBox availableCheckBox;

    public AddBookFrame() {
        setTitle("Add New Book");
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

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(10, 20, 80, 25);
        panel.add(titleLabel);

        titleField = new JTextField(20);
        titleField.setBounds(100, 20, 165, 25);
        panel.add(titleField);

        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setBounds(10, 50, 80, 25);
        panel.add(authorLabel);

        authorField = new JTextField(20);
        authorField.setBounds(100, 50, 165, 25);
        panel.add(authorField);

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setBounds(10, 80, 80, 25);
        panel.add(yearLabel);

        yearField = new JTextField(4);
        yearField.setBounds(100, 80, 165, 25);
        panel.add(yearField);

        JLabel availableLabel = new JLabel("Available:");
        availableLabel.setBounds(10, 110, 80, 25);
        panel.add(availableLabel);

        availableCheckBox = new JCheckBox();
        availableCheckBox.setBounds(100, 110, 165, 25);
        panel.add(availableCheckBox);

        JButton addButton = new JButton("Add Book");
        addButton.setBounds(10, 140, 100, 25);
        panel.add(addButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(120, 140, 100, 25);
        panel.add(backButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    addBook();
                } catch (IOException | JSONException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AddBookFrame.this, "Error adding book.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminMenuFrame();
                dispose();
            }
        });
    }

    private void addBook() throws IOException, JSONException {
        String title = titleField.getText();
        String author = authorField.getText();
        int year = Integer.parseInt(yearField.getText());
        boolean available = availableCheckBox.isSelected();

        JSONObject response = DatabaseConnection.addBook(title, author, year, available);
        if (response.getBoolean("success")) {
            JOptionPane.showMessageDialog(this, "Book added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new AdminMenuFrame();
        } else {
            JOptionPane.showMessageDialog(this, response.getString("message"), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

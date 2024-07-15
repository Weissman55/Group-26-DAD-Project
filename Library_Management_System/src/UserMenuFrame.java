import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import org.json.*;

public class UserMenuFrame extends JFrame {

    public UserMenuFrame() {
        setTitle("Library Management System - User Menu");
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

        JButton viewBooksButton = new JButton("View Books");
        viewBooksButton.setBounds(10, 20, 150, 25);
        panel.add(viewBooksButton);

        JButton borrowBookButton = new JButton("Borrow Book");
        borrowBookButton.setBounds(10, 50, 150, 25);
        panel.add(borrowBookButton);

        JButton returnBookButton = new JButton("Return Book");
        returnBookButton.setBounds(10, 80, 150, 25);
        panel.add(returnBookButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 110, 150, 25);
        panel.add(backButton);

        viewBooksButton.addActionListener(e -> {
            try {
                JSONObject response = DatabaseConnection.getBooks();
                if (response.getBoolean("success")) {
                    JSONArray booksArray = response.getJSONArray("books");
                    StringBuilder bookDetails = new StringBuilder();
                    for (int i = 0; i < booksArray.length(); i++) {
                        JSONObject book = booksArray.getJSONObject(i);
                        bookDetails.append("Title: ").append(book.getString("title")).append("\n");
                        bookDetails.append("Author: ").append(book.getString("author")).append("\n");
                        bookDetails.append("Year: ").append(book.getInt("year")).append("\n");
                        bookDetails.append("Available: ").append(book.getBoolean("available") ? "Yes" : "No").append("\n");
                        bookDetails.append("--------------------\n");
                    }
                    JTextArea textArea = new JTextArea(bookDetails.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));
                    JOptionPane.showMessageDialog(UserMenuFrame.this, scrollPane, "Books List", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(UserMenuFrame.this, "Failed to fetch books.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException | JSONException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(UserMenuFrame.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        borrowBookButton.addActionListener(e -> {
            String bookTitle = JOptionPane.showInputDialog(UserMenuFrame.this, "Enter the title of the book to borrow:");
            if (bookTitle != null && !bookTitle.isEmpty()) {
                try {
                    JSONObject response = DatabaseConnection.borrowBook(bookTitle);
                    if (response.getBoolean("success")) {
                        JOptionPane.showMessageDialog(UserMenuFrame.this, "Book borrowed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(UserMenuFrame.this, "Failed to borrow book: " + response.getString("message"), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | JSONException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(UserMenuFrame.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        returnBookButton.addActionListener(e -> {
            String bookTitle = JOptionPane.showInputDialog(UserMenuFrame.this, "Enter the title of the book to return:");
            if (bookTitle != null && !bookTitle.isEmpty()) {
                try {
                    JSONObject response = DatabaseConnection.returnBook(bookTitle);
                    if (response.getBoolean("success")) {
                        JOptionPane.showMessageDialog(UserMenuFrame.this, "Book returned successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(UserMenuFrame.this, "Failed to return book: " + response.getString("message"), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | JSONException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(UserMenuFrame.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginFrame();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new UserMenuFrame();
    }
}

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToDoApp extends JFrame {
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskInput;
    private JButton addButton;
    private JButton deleteButton;
    private ArrayList<String> tasks;

    public ToDoApp() {
        // Initialize the main frame
        setTitle("ToDo Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        
        // Initialize components
        tasks = new ArrayList<>();
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskInput = new JTextField();
        addButton = new JButton("Add Task");
        deleteButton = new JButton("Delete Selected");
        
        // Set up panel for input and buttons
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.add(taskInput, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        inputPanel.add(buttonPanel, BorderLayout.EAST);
        
        // Set up main layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(new JLabel("Your Tasks:"), BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        
        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });
        
        // Allow adding tasks by pressing Enter in text field
        taskInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        
        // Add main panel to frame
        add(mainPanel);
    }
    
    private void addTask() {
        String task = taskInput.getText().trim();
        if (!task.isEmpty()) {
            listModel.addElement(task);
            tasks.add(task);
            taskInput.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a task", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
            tasks.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoApp().setVisible(true);
            }
        });
    }
}

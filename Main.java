import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Main extends JFrame {
    private final GradeTracker tracker;
    
    
    private JTextField nameField;
    private JTextField gradeField;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    
    
    private JLabel totalLabel;
    private JLabel averageLabel;
    private JLabel highestLabel;
    private JLabel lowestLabel;

    
    private final Color BACKGROUND_COLOR = new Color(30, 30, 30);      
    private final Color PANEL_COLOR = new Color(45, 45, 48);           
    private final Color TEXT_PRIMARY = new Color(240, 240, 240);       
    private final Color TEXT_SECONDARY = new Color(180, 180, 180);     
    private final Color ACCENT_GREEN = new Color(0, 122, 255);         
    private final Color GRID_COLOR = new Color(60, 60, 60);             

    public Main() {
        tracker = new GradeTracker();
        setupGUI();
    }

    private void setupGUI() {
       
        setTitle("CodeAlpha - Student Grade Tracker");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        getContentPane().setBackground(BACKGROUND_COLOR);
        setLayout(new BorderLayout(15, 15));

       
        Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 15);

        
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        inputPanel.setBackground(PANEL_COLOR);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, GRID_COLOR),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        JLabel nameLabel = new JLabel("Student Name:");
        nameLabel.setForeground(TEXT_PRIMARY);
        nameLabel.setFont(mainFont);
        inputPanel.add(nameLabel);

        nameField = createModernTextField(15, mainFont);
        inputPanel.add(nameField);

        JLabel gradeLabel = new JLabel("Grade:");
        gradeLabel.setForeground(TEXT_PRIMARY);
        gradeLabel.setFont(mainFont);
        inputPanel.add(gradeLabel);

        gradeField = createModernTextField(6, mainFont);
        inputPanel.add(gradeField);

        JButton addButton = new JButton("Add Record");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addButton.setBackground(ACCENT_GREEN); 
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

       
        String[] columnNames = {"Student Name", "Grade"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        studentTable.setBackground(PANEL_COLOR);
        studentTable.setForeground(TEXT_PRIMARY);
        studentTable.setFont(mainFont);
        studentTable.setRowHeight(30);
        studentTable.setGridColor(GRID_COLOR);
        studentTable.setEnabled(false);
        studentTable.setShowVerticalLines(false); // Removes retro vertical column grid lines

        
        JTableHeader tableHeader = studentTable.getTableHeader();
        tableHeader.setBackground(BACKGROUND_COLOR);
        tableHeader.setForeground(TEXT_SECONDARY);
        tableHeader.setFont(titleFont);
        tableHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, GRID_COLOR));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setBackground(PANEL_COLOR);
        centerRenderer.setForeground(TEXT_PRIMARY);
        studentTable.setDefaultRenderer(Object.class, centerRenderer);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.getViewport().setBackground(PANEL_COLOR);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        
        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setBackground(BACKGROUND_COLOR);
        centerWrapper.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(GRID_COLOR), "STUDENT ROSTER", 0, 0, titleFont, TEXT_SECONDARY));
        centerWrapper.add(scrollPane, BorderLayout.CENTER);
        add(centerWrapper, BorderLayout.CENTER);

       
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBackground(PANEL_COLOR);
        statsPanel.setPreferredSize(new Dimension(280, 0));
        statsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(GRID_COLOR), "ANALYTICS DASHBOARD", 0, 0, titleFont, TEXT_SECONDARY),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        
        totalLabel = createDashboardCard("Total Students", "0");
        averageLabel = createDashboardCard("Class Average", "0.00");
        highestLabel = createDashboardCard("Highest Grade", "0.00");
        lowestLabel = createDashboardCard("Lowest Grade", "0.00");

        statsPanel.add(totalLabel);
        statsPanel.add(Box.createVerticalStrut(12));
        statsPanel.add(averageLabel);
        statsPanel.add(Box.createVerticalStrut(12));
        statsPanel.add(highestLabel);
        statsPanel.add(Box.createVerticalStrut(12));
        statsPanel.add(lowestLabel);

        add(statsPanel, BorderLayout.EAST);

        
        addButton.addActionListener(e -> handleAddStudent());
    }

    
    private JTextField createModernTextField(int columns, Font font) {
        JTextField textField = new JTextField(columns);
        textField.setFont(font);
        textField.setBackground(BACKGROUND_COLOR);
        textField.setForeground(TEXT_PRIMARY);
        textField.setCaretColor(TEXT_PRIMARY);
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRID_COLOR, 1),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        return textField;
    }

    
    private JLabel createDashboardCard(String prefix, String value) {
        JLabel label = new JLabel("<html><body style='color:#B0B0B0;'>" + prefix + ":<br><span style='color:#FFFFFF; font-size:14px;'><b>" + value + "</b></span></body></html>");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setOpaque(true);
        label.setBackground(BACKGROUND_COLOR);
        label.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRID_COLOR, 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        label.setMaximumSize(new Dimension(250, 60));
        return label;
    }

    private void updateCardText(JLabel label, String prefix, String value) {
        label.setText("<html><body style='color:#B0B0B0;'>" + prefix + ":<br><span style='color:#FFFFFF; font-size:14px;'><b>" + value + "</b></span></body></html>");
    }

    private void handleAddStudent() {
        String name = nameField.getText().trim();
        String gradeText = gradeField.getText().trim();

        if (name.isEmpty() || gradeText.isEmpty()) {
            showModernError("Fields cannot be empty!");
            return;
        }

        try {
            double grade = Double.parseDouble(gradeText);
            
            if (grade < 0 || grade > 100) {
                showModernError("Grade must be between 0 and 100.");
                return;
            }

            Student student = new Student(name, grade);
            tracker.addStudent(student);

            tableModel.addRow(new Object[]{student.getName(), String.format("%.2f", student.getGrade())});

           
            updateCardText(totalLabel, "Total Students", String.valueOf(tracker.getStudentCount()));
            updateCardText(averageLabel, "Class Average", String.format("%.2f", tracker.calculateAverage()));
            updateCardText(highestLabel, "Highest Grade", String.format("%.2f", tracker.getHighestGrade()));
            updateCardText(lowestLabel, "Lowest Grade", String.format("%.2f", tracker.getLowestGrade()));

            nameField.setText("");
            gradeField.setText("");
            nameField.requestFocus();

        } catch (NumberFormatException ex) {
            showModernError("Please enter a valid decimal number.");
        }
    }

    private void showModernError(String message) {
        UIManager.put("OptionPane.background", PANEL_COLOR);
        UIManager.put("Panel.background", PANEL_COLOR);
        UIManager.put("OptionPane.messageForeground", TEXT_PRIMARY);
        JOptionPane.showMessageDialog(this, message, "Notification", JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}
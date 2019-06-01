
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class RockyDryCleanersGUI {
    private final int MAX_ORDERS = 10;
    private Order [] orderArray = new Order[MAX_ORDERS];
    private int currentOrder = 0;
    
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField text1;
    private JTextField text2;
    private JTextArea area1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    
    private JFrame frame;

    public RockyDryCleanersGUI() {
        frame = new JFrame();
        
        panel1 = new JPanel();
        label1 = new JLabel("Rocky Dry Cleaners Management System");
        panel1.add(label1);
        
        panel2 = new JPanel();
        label2 = new JLabel("Customer name: ");
        text1 = new JTextField(20);
        panel2.add(label2);
        panel2.add(text1);
        panel1.add(panel2);
        
        panel3 = new JPanel();
        label3 = new JLabel("Number of plain garments: ");
        text2 = new JTextField(10);
        panel3.add(label3);
        panel3.add(text2);
        panel1.add(panel3);
        
        area1 = new JTextArea(5, 10);
        panel1.add(area1);       
        
        panel4 = new JPanel();
        button1 = new JButton("Enter");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enterDetails();
            }
        });
        panel4.add(button1);
        
        button2 = new JButton("Display All");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayValues();
            }
        });
        panel4.add(button2);
        
        button3 = new JButton("Search");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
        panel4.add(button3);
        
        button4 = new JButton("Exit");
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        panel1.add(panel4);
        
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        frame.add(panel1);
        frame.setResizable(false);
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void enterDetails(){
        if(currentOrder == MAX_ORDERS){
            JOptionPane.showMessageDialog(frame, "Maximum number of orders has been reached", 
                    "Rocky Dry Cleaners Management System", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (text1.getText().compareTo("") == 0){
            JOptionPane.showMessageDialog(frame, "You must enter a customer name", 
                    "Rocky Dry Cleaners Management System", JOptionPane.ERROR_MESSAGE);
            text1.requestFocus();
            return;
        }
        
        if (text2.getText().compareTo("") == 0){
            JOptionPane.showMessageDialog(frame, "You must enter number of garmenets", 
                    "Rocky Dry Cleaners Management System", JOptionPane.ERROR_MESSAGE);
            text2.requestFocus();
            return;
        }
        
        String customerName = text1.getText();
        int garments = Integer.parseInt(text2.getText());
        orderArray[currentOrder] = new Order(customerName, garments);
        currentOrder++;
    }
    
    public void displayHeading(){
        area1.setText(String.format("%-30s%-17s%-6s\n","Customer Name", "Garments", "Charge"));
        area1.append("\n---------------------------------------------------------------------");
    }
    
    public void displayValues(){
        if(currentOrder == 0){
            JOptionPane.showMessageDialog(frame, "No order entered", 
                    "Rocky Dry Cleaners Management System", JOptionPane.ERROR_MESSAGE);
            text1.requestFocus();
            return;
        }
        displayHeading();
        double totalGarments = 0;
        double totalCharges = 0;
        
        for(int i=0; i<currentOrder; i++){
            double garments = orderArray[i].getGarments();
            double charge = orderArray[i].calculateCharge();
            area1.append(String.format("\n%-30s%-17s$%5.2f\n",
                    orderArray[i].getCustomerName(), garments, charge));
            totalCharges += charge;
            totalGarments += garments;
        }
        totalGarments /= currentOrder;
        
        area1.append("\n---------------------------------------------------------------------");
        area1.append("\nAverage garments per order: " + totalGarments);
        area1.append("\nTotal charges: $" + totalCharges);
        text1.requestFocus();
    }
    
    public void search(){
        if (currentOrder == 0) {
            JOptionPane.showMessageDialog(frame, "No order entered",
                    "Rocky Dry Cleaners Management System", JOptionPane.ERROR_MESSAGE);
            text1.requestFocus();
            return;
        }
        
        String name = JOptionPane.showInputDialog(frame, "Enter a customer name to search", "Search", JOptionPane.PLAIN_MESSAGE);
        if (name == null || name.compareTo("") == 0) {
            JOptionPane.showMessageDialog(frame, "You must enter a search name",
                    "Rocky Dry Cleaners Management System", JOptionPane.ERROR_MESSAGE);
            text1.requestFocus();
            return;
        }
        
        int index = -1;
        for(int i=0; i<currentOrder; i++){
            if(name.compareTo(orderArray[i].getCustomerName()) == 0){
                index = i;
                break;
            }
        }
        if(index == -1){
            JOptionPane.showMessageDialog(frame, name + "not found",
                    "Rocky Dry Cleaners Management System", JOptionPane.ERROR_MESSAGE);
            text1.requestFocus();
        }
        else{
            displayHeading();
            area1.append(String.format("\n%-30s%-17s$%5.2f\n",
                    orderArray[index].getCustomerName(), orderArray[index].getGarments(), orderArray[index].calculateCharge()));
        }
    }
    
    public void exit(){
        JOptionPane.showMessageDialog(frame, "Thank you for using the Rocky Dry Cleaners Management System",
                    "Rocky Dry Cleaners Management System", JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }
    
    public static void main(String[] args) {
        new RockyDryCleanersGUI();
    }
    
    
}

package com.clothing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    
    // Simple data class inside the same file
    static class ClothingItem {
        String name;
        double price;
        String size;
        
        ClothingItem(String name, double price, String size) {
            this.name = name;
            this.price = price;
            this.size = size;
        }
        
        @Override
        public String toString() {
            return String.format("%s - $%.2f (%s)", name, price, size);
        }
    }
    
    public static void main(String[] args) {
        // Create sample clothing items
        List<ClothingItem> items = new ArrayList<>();
        items.add(new ClothingItem("T-Shirt", 19.99, "M"));
        items.add(new ClothingItem("Jeans", 49.99, "32"));
        items.add(new ClothingItem("Jacket", 89.99, "L"));
        items.add(new ClothingItem("Dress", 39.99, "S"));
        
        // Create GUI
        SwingUtilities.invokeLater(() -> createAndShowGUI(items));
    }
    
    private static void createAndShowGUI(List<ClothingItem> items) {
        // Main window
        JFrame frame = new JFrame("Clothing Store");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        
        // Main panel
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel title = new JLabel("👕 Simple Clothing Store", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(title, BorderLayout.NORTH);
        
        // Center - Products list
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (ClothingItem item : items) {
            listModel.addElement(item.toString());
        }
        
        JList<String> productList = new JList<>(listModel);
        productList.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JScrollPane scrollPane = new JScrollPane(productList);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Bottom panel - Cart info
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        
        JLabel cartLabel = new JLabel("Cart: 0 items | Total: $0.00", SwingConstants.CENTER);
        cartLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        JButton addButton = new JButton("Add to Cart");
        addButton.addActionListener(e -> {
            String selected = productList.getSelectedValue();
            if (selected != null) {
                JOptionPane.showMessageDialog(frame, 
                    "Added: " + selected, 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, 
                "Thank you for your purchase!", 
                "Checkout", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        buttonPanel.add(addButton);
        buttonPanel.add(checkoutButton);
        
        bottomPanel.add(cartLabel);
        bottomPanel.add(buttonPanel);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        
        // Add panel to frame
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
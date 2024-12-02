package gui;

import javax.swing.*;
import java.awt.event.*;
import gui.BaseConverter;
import gui.HistoryManager;

public class ConverterGUI {
    public void start() {
        // Create the main frame
        JFrame frame = new JFrame("Multi-Base Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null); // Set layout to null for custom positioning

        // Create and set bounds for components
        JLabel primaryBaseLabel = new JLabel("Input Base:");
        primaryBaseLabel.setBounds(20, 20, 100, 25);
        frame.add(primaryBaseLabel);

        String[] bases = {"Binary", "Decimal", "Hexadecimal", "Octal"};
        JComboBox<String> baseSelector = new JComboBox<>(bases);
        baseSelector.setBounds(120, 20, 150, 25);
        frame.add(baseSelector);

        JLabel inputLabel = new JLabel("Input:");
        inputLabel.setBounds(20, 60, 100, 25);
        frame.add(inputLabel);

        JTextField inputField = new JTextField();
        inputField.setBounds(120, 60, 200, 25);
        frame.add(inputField);

        JLabel binaryLabel = new JLabel("Binary:");
        binaryLabel.setBounds(20, 100, 100, 25);
        frame.add(binaryLabel);

        JTextField binaryField = new JTextField();
        binaryField.setBounds(120, 100, 200, 25);
        binaryField.setEditable(false);
        frame.add(binaryField);

        JLabel decimalLabel = new JLabel("Decimal:");
        decimalLabel.setBounds(20, 140, 100, 25);
        frame.add(decimalLabel);

        JTextField decimalField = new JTextField();
        decimalField.setBounds(120, 140, 200, 25);
        decimalField.setEditable(false);
        frame.add(decimalField);

        JLabel hexLabel = new JLabel("Hexadecimal:");
        hexLabel.setBounds(20, 180, 100, 25);
        frame.add(hexLabel);

        JTextField hexField = new JTextField();
        hexField.setBounds(120, 180, 200, 25);
        hexField.setEditable(false);
        frame.add(hexField);

        JLabel octalLabel = new JLabel("Octal:");
        octalLabel.setBounds(20, 220, 100, 25);
        frame.add(octalLabel);

        JTextField octalField = new JTextField();
        octalField.setBounds(120, 220, 200, 25);
        octalField.setEditable(false);
        frame.add(octalField);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(20, 260, 100, 30);
        frame.add(convertButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(140, 260, 100, 30);
        frame.add(resetButton);

        HistoryManager.addShowHistoryButton(frame);

        binaryLabel.setVisible(false);
        binaryField.setVisible(false);

        // Add item listener for the base selector
        baseSelector.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedBase = (String) baseSelector.getSelectedItem();

                // Hide labels and fields for the selected input base
                binaryLabel.setVisible(!selectedBase.equals("Binary"));
                binaryField.setVisible(!selectedBase.equals("Binary"));
                decimalLabel.setVisible(!selectedBase.equals("Decimal"));
                decimalField.setVisible(!selectedBase.equals("Decimal"));
                hexLabel.setVisible(!selectedBase.equals("Hexadecimal"));
                hexField.setVisible(!selectedBase.equals("Hexadecimal"));
                octalLabel.setVisible(!selectedBase.equals("Octal"));
                octalField.setVisible(!selectedBase.equals("Octal"));

                inputField.setText("");
                binaryField.setText("");
                decimalField.setText("");
                hexField.setText("");
                octalField.setText("");
            }
        });

        // Add action listener for the Convert button
        convertButton.addActionListener(e -> {
            try {
                String input = inputField.getText();
                String selectedBase = (String) baseSelector.getSelectedItem();

                // Use BaseConverter for conversion
                BaseConverter.Result result = BaseConverter.convert(input, selectedBase);

                binaryField.setText(result.binary);
                decimalField.setText(result.decimal);
                hexField.setText(result.hexadecimal);
                octalField.setText(result.octal);

                // Save history
                if (!HistoryManager.isDuplicateEntry(result)) {
                    HistoryManager.saveHistory(result);
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add action listener for the Reset button
        resetButton.addActionListener(e -> {
            inputField.setText("");
            binaryField.setText("");
            decimalField.setText("");
            hexField.setText("");
            octalField.setText("");
            baseSelector.setSelectedItem("Binary");
        });

        frame.setVisible(true);
    }
}

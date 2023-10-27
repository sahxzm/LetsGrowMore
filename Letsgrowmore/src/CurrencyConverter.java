import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.text.DecimalFormat;

public class CurrencyConverter extends JFrame {
    private final double[] exchangeRates = {1.0, 1.31, 476.57, 5.47, 1.71, 132.53, 19554.94, 95.21, 71.17, 162.74};

    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JTextField amountInput;
    private JTextField resultOutput;
    private JLabel fromCurrencyLabel;
    private JLabel toCurrencyLabel;

    public CurrencyConverter() {
        initUI();
    }

    private void initUI() {
        setTitle("Currency Converter");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        fromCurrency = new JComboBox<>(new String[]{"Units", "US Dollar", "Nigerian Naira", "Brazilian Real", "Canadian Dollar", "Kenyan Shilling", "Indonesian Rupiah", "Indian Rupee", "Philippine Pisco", "Pakistani Rupee"});
        toCurrency = new JComboBox<>(new String[]{"Units", "US Dollar", "Nigerian Naira", "Brazilian Real", "Canadian Dollar", "Kenyan Shilling", "Indonesian Rupiah", "Indian Rupee", "Philippine Pisco", "Pakistani Rupee"});
        amountInput = new JTextField();
        resultOutput = new JTextField();
        fromCurrencyLabel = new JLabel("From currency of");
        toCurrencyLabel = new JLabel("To currency of");

        fromCurrencyLabel.setBounds(20, 20, 120, 20);
        fromCurrency.setBounds(150, 20, 200, 30);
        amountInput.setBounds(150, 70, 200, 30);
        resultOutput.setBounds(150, 170, 200, 30);
        fromCurrencyLabel.setBounds(20, 20, 120, 20);
        toCurrencyLabel.setBounds(20, 120, 120, 20);
        toCurrency.setBounds(150, 120, 200, 30);

        add(fromCurrency);
        add(toCurrency);
        add(amountInput);
        add(resultOutput);
        add(fromCurrencyLabel);
        add(toCurrencyLabel);

        fromCurrency.addItemListener(this::fromCurrencyItemStateChanged);
        toCurrency.addItemListener(this::toCurrencyItemStateChanged);

        JButton convertButton = new JButton("Convert Currency");
        convertButton.setBounds(100, 220, 200, 30);
        convertButton.addActionListener(this::convertButtonActionPerformed);
        add(convertButton);
    }

    private void fromCurrencyItemStateChanged(ItemEvent evt) {
        fromCurrencyLabel.setText("From currency of (" + fromCurrency.getSelectedItem() + ")");
    }

    private void toCurrencyItemStateChanged(ItemEvent evt) {
        toCurrencyLabel.setText("To currency of (" + toCurrency.getSelectedItem() + ")");
    }

    private void convertButtonActionPerformed(ActionEvent evt) {
        String amountText = amountInput.getText();
        if (amountText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an amount to convert.");
            return;
        }

        try {
            double amountToConvert = Double.parseDouble(amountText);
            int fromIndex = fromCurrency.getSelectedIndex();
            int toIndex = toCurrency.getSelectedIndex();
            double fromRate = exchangeRates[fromIndex];
            double toRate = exchangeRates[toIndex];

            double convertedAmount = amountToConvert * (fromRate / toRate);
            resultOutput.setText(new DecimalFormat("0.00").format(convertedAmount));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverter converter = new CurrencyConverter();
            converter.setVisible(true);
        });
    }
}

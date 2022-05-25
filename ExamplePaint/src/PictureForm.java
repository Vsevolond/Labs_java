import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PictureForm {
    private JPanel mainPanel;
    private JSpinner radiusSpinner;
    private JTextField areaField;
    private CanvasPanel canvasPanel;

    public PictureForm ( ) {
        radiusSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int radius = (int)radiusSpinner.getValue();
                canvasPanel.setRadius(radius);
                double area = Math.PI * radius * radius;
                areaField.setText(String.format("%.2f", area));
            }
        });
        radiusSpinner.setValue(20);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Окружность");
        frame.setContentPane(new PictureForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

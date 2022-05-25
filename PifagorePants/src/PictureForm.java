import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PictureForm {

    private JPanel mainPanel;
    private JSpinner spinnerA;
    private JSpinner spinnerB;
    private JLabel a;
    private JLabel b;
    private CanvasPanel canvasPanel;

    public PictureForm() {
        spinnerA.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int a = (int)spinnerA.getValue();
                canvasPanel.setA(a);
            }
        });
        spinnerB.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int b = (int)spinnerB.getValue();
                canvasPanel.setB(b);
            }
        });
        canvasPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouse) {
                canvasPanel.click(mouse.getX(), mouse.getY());
            }
        });
        spinnerA.setValue(60);
        spinnerB.setValue(80);
        canvasPanel.setColorA(0);
        canvasPanel.setColorB(1);
        canvasPanel.setColorC(2);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Пифагоровы штаны");
        frame.setContentPane(new PictureForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

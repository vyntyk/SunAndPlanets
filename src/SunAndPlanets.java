import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

class SunAndPlanets {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new SolarSystemFrame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

class SolarSystemFrame extends JFrame {
    private JPanel jPanel;
    private JButton jButton;
    private JLabel[] planetLabels;

    public SolarSystemFrame() throws Exception {
        initFrame();
        initComponents();
        addComponents();
        startAnimation();
    }

    private void initFrame() {
        setTitle("Solar System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 1080);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        jPanel = new JPanel();
        jPanel.setBackground(Color.BLACK);
        jButton = new JButton("Button");

        String[] planets = {"Sun", "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
        int[] resolutions = {130, 20, 30, 40, 30, 60, 80, 55, 30};
        planetLabels = new JLabel[planets.length];

        for (int i = 0; i < planets.length; i++) {
            planetLabels[i] = new JLabel(getImage(planets[i], resolutions[i]));
        }
    }

    private void addComponents() {
        for (JLabel label : planetLabels) {
            jPanel.add(label);
        }
        add(jButton, BorderLayout.NORTH);
        add(jPanel, BorderLayout.CENTER);
    }

    private void startAnimation() {
        ActionListener actionListener = new ActionListener() {
            double count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                jButton.setVisible(false);
                planetLabels[0].setLocation(475, 475);
                drawOrbitingSphere(count, planetLabels[1], getWidth() / 2 - planetLabels[1].getHeight() / 2, 75);
                drawOrbitingSphere(count * 0.9, planetLabels[2], getWidth() / 2 - planetLabels[2].getHeight() / 2, 105);
                drawOrbitingSphere(count * 0.8, planetLabels[3], getWidth() / 2 - planetLabels[3].getHeight() / 2, 145);
                drawOrbitingSphere(count * 0.7, planetLabels[4], getWidth() / 2 - planetLabels[4].getHeight() / 2, 185);
                drawOrbitingSphere(count * 0.6, planetLabels[5], getWidth() / 2 - planetLabels[5].getHeight() / 2, 235);
                drawOrbitingSphere(count * 0.4, planetLabels[6], getWidth() / 2 - planetLabels[6].getHeight() / 2, 310);
                drawOrbitingSphere(count * 0.3, planetLabels[7], getWidth() / 2 - planetLabels[7].getHeight() / 2, 390);
                drawOrbitingSphere(count * 0.2, planetLabels[8], getWidth() / 2 - planetLabels[8].getHeight() / 2, 440);
                count += 0.1;
            }
        };
        Timer timer = new Timer(10, actionListener);
        timer.start();
    }

    private ImageIcon getImage(String s, int resolution) {
        try {
            Image image = ImageIO.read(new File("src/" + s + ".png"));
            image = image.getScaledInstance(resolution, resolution, Image.SCALE_DEFAULT);
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void drawOrbitingSphere(double timeInterval, JLabel j, double orbit, double orbitR) {
        double orbitX = orbit;
        double orbitY = orbit;
        double orbitRadius = orbitR;
        double orbitSpeed = Math.PI / 16;

        double radian = orbitSpeed * timeInterval;
        double drawX = orbitX + orbitRadius * Math.cos(radian);
        double drawY = orbitY + orbitRadius * Math.sin(radian);

        j.setLocation((int) drawX, (int) drawY);
    }
}

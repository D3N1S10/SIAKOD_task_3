import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PriorityQueueVisualizer extends JPanel {
    private final List<Long> heapInsertTimes;
    private final List<Long> arrayInsertTimes;
    private final List<Long> heapExtractTimes;
    private final List<Long> arrayExtractTimes;

    public PriorityQueueVisualizer(List<Long> heapInsertTimes, List<Long> arrayInsertTimes, List<Long> heapExtractTimes, List<Long> arrayExtractTimes) {
        this.heapInsertTimes = heapInsertTimes;
        this.arrayInsertTimes = arrayInsertTimes;
        this.heapExtractTimes = heapExtractTimes;
        this.arrayExtractTimes = arrayExtractTimes;

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(this);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();
        int padding = 50;
        int graphWidth = width - 2 * padding;

        long maxTime = Math.max(Math.max(heapInsertTimes.stream().max(Long::compare).orElse(0L),
                        arrayInsertTimes.stream().max(Long::compare).orElse(0L)),
                Math.max(heapExtractTimes.stream().max(Long::compare).orElse(0L),
                        arrayExtractTimes.stream().max(Long::compare).orElse(0L)));

        g2d.setColor(Color.BLUE);
        drawLineAndPoints(g2d, heapInsertTimes, padding, height, graphWidth, maxTime);

        g2d.setColor(Color.RED);
        drawLineAndPoints(g2d, arrayInsertTimes, padding, height, graphWidth, maxTime);

        g2d.setColor(Color.GREEN);
        drawLineAndPoints(g2d, heapExtractTimes, padding, height, graphWidth, maxTime);

        g2d.setColor(Color.ORANGE);
        drawLineAndPoints(g2d, arrayExtractTimes, padding, height, graphWidth, maxTime);

        drawLegend(g2d, padding, height);

        int numPoints = Math.min(heapInsertTimes.size(), 10);
        for (int i = 0; i < numPoints; i++) {
            int x = padding + (i * graphWidth / (numPoints - 1));
            g2d.setColor(Color.BLACK);
            g2d.drawString(String.valueOf(i + 1), x - 5, height - padding + 20);
        }

        for (int i = 0; i <= 10; i++) {
            long timeLabel = (maxTime * i) / 10;
            int y = height - padding - (int) ((double) timeLabel / maxTime * (height - 2 * padding));
            g2d.setColor(Color.BLACK);
            g2d.drawString(timeLabel + " ms", padding - 40, y + 5);
        }
    }

    private void drawLineAndPoints(Graphics2D g2d, List<Long> times, int padding, int height, int graphWidth, long maxTime) {
        int numPoints = times.size();

        for (int i = 0; i < numPoints; i++) {
            int x = padding + (i * graphWidth / (numPoints - 1));
            int y = height - padding - (int) ((double) times.get(i) / maxTime * (height - 2 * padding));

            g2d.fillOval(x - 3, y - 3, 6, 6);

            if (i > 0) {
                int prevX = padding + ((i - 1) * graphWidth / (numPoints - 1));
                int prevY = height - padding - (int) ((double) times.get(i - 1) / maxTime * (height - 2 * padding));
                g2d.drawLine(prevX, prevY, x, y);
            }
        }
    }

    private void drawLegend(Graphics2D g2d, int padding, int height) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect(padding + 400, height - 300, 15, 15);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Вставка (Куча)", padding + 420, height - 290);

        g2d.setColor(Color.RED);
        g2d.fillRect(padding + 400, height - 270, 15, 15);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Вставка (Массив)", padding + 420, height - 250);

        g2d.setColor(Color.GREEN);
        g2d.fillRect(padding + 400, height - 240, 15, 15);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Извлечение (Куча)", padding + 420, height - 220);

        g2d.setColor(Color.ORANGE);
        g2d.fillRect(padding + 400, height - 210, 15, 15);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Извлечение (Массив)", padding + 420, height - 190);
    }
}

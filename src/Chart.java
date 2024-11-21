import javax.swing.*;
import java.awt.*;

public class Chart extends JPanel {

    private long heapInsertTime;
    private long arrayInsertTime;
    private long heapExtractTime;
    private long arrayExtractTime;

    public void GraphPanel(long heapInsertTime, long arrayInsertTime, long heapExtractTime, long arrayExtractTime) {
        this.heapInsertTime = heapInsertTime;
        this.arrayInsertTime = arrayInsertTime;
        this.heapExtractTime = heapExtractTime;
        this.arrayExtractTime = arrayExtractTime;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        int padding = 50;
        int barWidth = (width - 2 * padding) / 4;

        long maxTime = Math.max(Math.max(heapInsertTime, arrayInsertTime), Math.max(heapExtractTime, arrayExtractTime));

        int heapInsertHeight = (int) ((double) heapInsertTime / maxTime * (height - 2 * padding));
        int arrayInsertHeight = (int) ((double) arrayInsertTime / maxTime * (height - 2 * padding));
        int heapExtractHeight = (int) ((double) heapExtractTime / maxTime * (height - 2 * padding));
        int arrayExtractHeight = (int) ((double) arrayExtractTime / maxTime * (height - 2 * padding));

        g2d.setColor(Color.BLUE);
        g2d.fillRect(padding, height - padding - heapInsertHeight, barWidth, heapInsertHeight);
        g2d.setColor(Color.RED);
        g2d.fillRect(padding + barWidth, height - padding - arrayInsertHeight, barWidth, arrayInsertHeight);
        g2d.setColor(Color.GREEN);
        g2d.fillRect(padding + 2 * barWidth, height - padding - heapExtractHeight, barWidth, heapExtractHeight);
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(padding + 3 * barWidth, height - padding - arrayExtractHeight, barWidth, arrayExtractHeight);

        g2d.setColor(Color.BLACK);
        g2d.drawString("Insert Heap", padding, height - padding + 20);
        g2d.drawString("Insert Array", padding + barWidth, height - padding + 20);
        g2d.drawString("Extract Heap", padding + 2 * barWidth, height - padding + 20);
        g2d.drawString("Extract Array", padding + 3 * barWidth, height - padding + 20);

        g2d.drawString(heapInsertTime + " ns", padding, height - padding - heapInsertHeight - 10);
        g2d.drawString(arrayInsertTime + " ns", padding + barWidth, height - padding - arrayInsertHeight - 10);
        g2d.drawString(heapExtractTime + " ns", padding + 2 * barWidth, height - padding - heapExtractHeight - 10);
        g2d.drawString(arrayExtractTime + " ns", padding + 3 * barWidth, height - padding - arrayExtractHeight - 10);
    }
}

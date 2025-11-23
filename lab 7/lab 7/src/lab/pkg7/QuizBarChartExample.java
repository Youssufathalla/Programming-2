package lab.pkg7;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JFrame;

public class QuizBarChartExample {

    public static void main(String[] args) {
     displayInsights();
    }
    public static void  displayInsights(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(72, "Quiz Average", "Lesson 1");
        dataset.addValue(81, "Quiz Average", "Lesson 2");
        dataset.addValue(67, "Quiz Average", "Lesson 3");
        dataset.addValue(92, "Quiz Average", "Lesson 4");

        JFreeChart barChart = ChartFactory.createBarChart(
                "Quiz Averages per Lesson",
                "Lesson",
                "Average Score",
                dataset,
                PlotOrientation.VERTICAL,
                true, 
                true,  
                false  
        );

        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        plot.setRangeGridlinePaint(java.awt.Color.GRAY);

        JFrame frame = new JFrame("Instructor Dashboard - Quiz Bar Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        ChartPanel chartPanel = new ChartPanel(barChart);
        frame.add(chartPanel);

        frame.setVisible(true);
    }
}

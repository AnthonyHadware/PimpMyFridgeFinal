package view;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

@SuppressWarnings("serial")
public class LineChart extends ApplicationFrame{
	private JFreeChart lineChart;
	@SuppressWarnings("unused")
	private DefaultCategoryDataset dataset;
	private XYSeries tempOut;
	private XYSeries tempIn;
	
	private int compteur;
	
	public LineChart( String applicationTitle , String chartTitle )
	   {
	      super(applicationTitle);
	      lineChart = ChartFactory.createXYLineChart(
	         chartTitle,
	         "Temps","°C",
	         createDataset(),
	         PlotOrientation.VERTICAL,
	         true,true,false);
	         
	      lineChart.setBackgroundPaint(new Color(0xFF,0xFF,0xFF));
	      lineChart.getTitle().setPaint(new Color(0xFF, 0xFF, 0xFF));
	      
	      
	      ChartPanel chartPanel = new ChartPanel( lineChart );
	      chartPanel.setPreferredSize( new java.awt.Dimension( 590 , 380 ) );
	      setContentPane( chartPanel );
	      compteur = 0;
	   }

	private XYSeriesCollection createDataset( )
	{
		XYSeriesCollection dataset = new XYSeriesCollection();
		tempOut = new XYSeries("Température extérieure");
		tempIn = new XYSeries("Température intérieure");

		dataset.addSeries(tempOut);
		dataset.addSeries(tempIn);
		
		this.tempIn.setMaximumItemCount(20);
		this.tempOut.setMaximumItemCount(20);
		
		this.tempIn.add(0,0);
		this.tempOut.add(0,0);

		return dataset;
	}

	public JFreeChart getJChart() {
		
		
		return lineChart;
	}
	
	public void addData(float tempIn,float tempOut)
	{
		compteur++;
		this.tempIn.add(compteur,tempIn);
		this.tempOut.add(compteur,tempOut);

	}
}

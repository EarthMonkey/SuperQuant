package presentation.OptionalStock;

import java.util.ArrayList;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import businesslogicservice.stockContrastblservice.StockContrastBLService;
import presentation.repaintComponent.MySpiderWebPlot;

public class SpiderChart {
	private double data[];
	private JFreeChart chart;

	public SpiderChart(ArrayList<String> nameList, StockContrastBLService stockContrastBL) {

		String series[] = { "�ǵ���", "�о���", "��ӯ��", "������" };

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (String name : nameList) {
			data = stockContrastBL.getData(name);
			for (int i = 0; i < series.length; i++) {
				dataset.addValue(data[i], name, series[i]);
			}
		}

		MySpiderWebPlot spiderwebplot = new MySpiderWebPlot(dataset);
		spiderwebplot.setStartAngle(0D); // ��һ��������ĽǶ�
		spiderwebplot.setInteriorGap(0.40000000000000002D); // ͼ��ʾ�Ĵ�С
		spiderwebplot.setToolTipGenerator(new StandardCategoryToolTipGenerator());

		spiderwebplot.setTicks(3);

		chart = new JFreeChart("", TextTitle.DEFAULT_FONT, spiderwebplot, false);
		LegendTitle legendtitle = new LegendTitle(spiderwebplot);
		legendtitle.setPosition(RectangleEdge.BOTTOM);
		chart.addSubtitle(legendtitle);
	}
	
	public ChartPanel getChart() {
		return new ChartPanel(chart);
	}
}

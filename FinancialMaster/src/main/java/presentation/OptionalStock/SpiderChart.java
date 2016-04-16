package presentation.OptionalStock;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

import businesslogicservice.stockContrastblservice.StockContrastBLService;
import presentation.repaintComponent.MySpiderWebPlot;

public class SpiderChart {
	private double data[];
	private JFreeChart chart;

	public SpiderChart(ArrayList<String> nameList, StockContrastBLService stockContrastBL) {

		String series[] = { "�����ǵ���", "�����о���", "������ӯ��", "����������", "�����ɽ����ȶ���", "�����ɽ����ȶ���" };

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (String name : nameList) {
			int isEmpty = 0;// �����ж��Ƿ�û���ղ��б�

			if (!name.equals("")) {
				data = stockContrastBL.getData(name);
				isEmpty = data.length;
			}
			for (int i = 0; i < series.length; i++) {
				if (isEmpty > 0) {
					dataset.addValue(data[i], name, series[i]);
				} else {
					dataset.addValue(10, name, series[i]);
				}
			}
		}

		MySpiderWebPlot spiderwebplot = new MySpiderWebPlot(dataset);
		spiderwebplot.setStartAngle(0D); // ��һ��������ĽǶ�
		spiderwebplot.setInteriorGap(0.2D); // ͼ��ʾ�Ĵ�С
		spiderwebplot.setToolTipGenerator(new StandardCategoryToolTipGenerator());
		spiderwebplot.setBackgroundPaint(null);

		// spiderwebplot.setLabelFont(new Font("����", Font.PLAIN, 12));
		spiderwebplot.setTicks(4);

		chart = new JFreeChart("", TextTitle.DEFAULT_FONT, spiderwebplot, false);
		LegendTitle legendtitle = new LegendTitle(spiderwebplot);
		legendtitle.setPosition(RectangleEdge.BOTTOM);
		chart.addSubtitle(legendtitle);
		chart.setAntiAlias(true);
		chart.setBorderPaint(null);
	}

	public ChartPanel getChart() {
		ChartPanel chartPanel = new ChartPanel(chart);

		// ͨ�������ַŴ���С�Ŵ����嶼�Ŵ���С
		chartPanel.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (-3 == e.getUnitsToScroll()) {
					chart.setPadding(
							new RectangleInsets(chart.getPadding().getTop() - 10, chart.getPadding().getBottom() - 10,
									chart.getPadding().getLeft() - 10, chart.getPadding().getRight() - 10));
				} else if (3 == e.getUnitsToScroll()) {
					chart.setPadding(
							new RectangleInsets(chart.getPadding().getTop() + 10, chart.getPadding().getBottom() + 10,
									chart.getPadding().getLeft() + 10, chart.getPadding().getRight() + 10));
				}
			}
		});

		chartPanel.setOpaque(false);
		return chartPanel;
	}
}

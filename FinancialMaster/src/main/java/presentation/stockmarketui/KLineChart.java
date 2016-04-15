package presentation.stockmarketui;

import java.util.Calendar;
import java.util.Date;
import java.awt.Color;
import java.awt.Paint;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SegmentedTimeline;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import org.jfree.ui.RectangleEdge;

import presentation.repaintComponent.MyCandlestickRender;

@SuppressWarnings("serial")
public class KLineChart extends JPanel {

	JFreeChart chart;
	Date startDate;
	Date endDate;

	// �����Ŵ���Сk��
	public KLineChart(String data[][], int index, int startPos) {
		// TODO Auto-generated constructor stub
		CreateChart(data, index, startPos);
	}

	// һ���ʼ��
	public KLineChart(String data[][], int index) {
		CreateChart(data, index, 0);
	}

	@SuppressWarnings("deprecation")
	public void CreateChart(String[][] data, int index, int startPos) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		double highValue = Double.MIN_VALUE; // ����K�����ݵ��е����ֵ
		double minValue = Double.MAX_VALUE; // ����K�����ݵ��е���Сֵ
		double high2Value = Double.MIN_VALUE; // ���óɽ��������ֵ
		double min2Value = Double.MAX_VALUE; // ���óɽ��������ֵ

		try {
			startDate = df.parse(data[startPos][0]);
			endDate = df.parse(data[data.length - 1][0]);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		// endDate����һ��ʹ��������ʾ��ȫ
		Calendar c = Calendar.getInstance();
		c.setTime(endDate);
		int dayAdd = c.get(Calendar.DATE);
		c.set(Calendar.DATE, dayAdd + 1);
		endDate = c.getTime();

		OHLCSeries series = new OHLCSeries(""); // �߿������������У���ƱK��ͼ���ĸ����ݣ������ǿ����ߣ��ͣ���
		TimeSeries series2 = new TimeSeries(""); // ��Ӧʱ��ɽ�������

		// ����ͼ
		TimeSeries seriesAvg5 = new TimeSeries("5�վ���");
		TimeSeries seriesAvg10 = new TimeSeries("10�վ���");
		TimeSeries seriesAvg30 = new TimeSeries("30�վ���");

		for (int i = startPos; i < data.length; i++) {

			Date date = new Date();
			try {
				date = df.parse(data[i][0]);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			series.add(new Day(date), Double.parseDouble(data[i][1]), Double.parseDouble(data[i][2]),
					Double.parseDouble(data[i][3]), Double.parseDouble(data[i][4]));

			series2.add(new Day(date), Double.parseDouble(data[i][5]));

			// ����ͼ
			seriesAvg5.add(new Day(date), Double.parseDouble(data[i][6]));
			seriesAvg10.add(new Day(date), Double.parseDouble(data[i][7]));
			seriesAvg30.add(new Day(date), Double.parseDouble(data[i][8]));
		}

		// ����K�����ݵ����ݼ�
		final OHLCSeriesCollection seriesCollection = new OHLCSeriesCollection();
		seriesCollection.addSeries(series);

		// �����ɽ������ݵļ���
		TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection(); // �����ɽ������ݵļ���
		timeSeriesCollection.addSeries(series2);

		// ����ͼ
		TimeSeriesCollection avgLine = new TimeSeriesCollection();
		avgLine.addSeries(seriesAvg5);
		avgLine.addSeries(seriesAvg10);
		avgLine.addSeries(seriesAvg30);

		// ��ȡK�����ݵ����ֵ�����ֵ
		int seriesCount = seriesCollection.getSeriesCount(); // һ���ж��ٸ����У�ĿǰΪһ��
		for (int i = 0; i < seriesCount; i++) {
			int itemCount = seriesCollection.getItemCount(i); // ÿһ�������ж��ٸ�������
			for (int j = 0; j < itemCount; j++) {
				// ȡ��i�������еĵ�j������������ֵ
				if (highValue < seriesCollection.getHighValue(i, j)) {
					highValue = seriesCollection.getHighValue(i, j);
				}
				// ȡ��i�������еĵ�j�����������Сֵ
				if (minValue > seriesCollection.getLowValue(i, j)) {
					minValue = seriesCollection.getLowValue(i, j);
				}
			}
		}

		// ��ȡ���ֵ�����ֵ
		int seriesCount2 = timeSeriesCollection.getSeriesCount(); // һ���ж��ٸ����У�ĿǰΪһ��
		for (int i = 0; i < seriesCount2; i++) {
			int itemCount = timeSeriesCollection.getItemCount(i); // ÿһ�������ж��ٸ�������
			for (int j = 0; j < itemCount; j++) {
				// ȡ��i�������еĵ�j���������ֵ
				if (high2Value < timeSeriesCollection.getYValue(i, j)) {
					high2Value = timeSeriesCollection.getYValue(i, j);
				}
				// ȡ��i�������еĵ�j���������ֵ
				if (min2Value > timeSeriesCollection.getYValue(i, j)) {
					min2Value = timeSeriesCollection.getYValue(i, j);
				}
			}
		}

		final MyCandlestickRender candlestickRender = new MyCandlestickRender();// ����K��ͼ�Ļ�ͼ������������Ϊfinal������Ҫ�������ڲ��������õ�
		candlestickRender.setUseOutlinePaint(true); // ���Ƿ�ʹ���Զ���ı߿��ߣ������Դ��ı߿��ߵ���ɫ�������й���Ʊ�г���ϰ��

		candlestickRender.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_AVERAGE);// ������ζ�K��ͼ�Ŀ�Ƚ����趨
		candlestickRender.setUpPaint(new Color(206, 4, 14));// ���ù�Ʊ���ǵ�K��ͼ��ɫ
		candlestickRender.setDownPaint(new Color(25, 155, 83));// ���ù�Ʊ�µ���K��ͼ��ɫ
		DateAxis x1Axis = new DateAxis();// ����x�ᣬҲ����ʱ����

		x1Axis.setRange(startDate, endDate);// ����ʱ�䷶Χ��ע��ʱ������ֵҪ�����е�ʱ�����ֵҪ��һ��
		if (index == 1) {
			x1Axis.setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());// ����ʱ������ʾ�Ĺ���������������������������������Щû�н��׵�����(�ܶ��˶���֪���д˷���)��ʹͼ�ο���ȥ����
		} else if (index == 2) {
			SegmentedTimeline timeline = new SegmentedTimeline(SegmentedTimeline.DAY_SEGMENT_SIZE, 1, 6);
			timeline.setStartTime(SegmentedTimeline.FIRST_MONDAY_AFTER_1900);
			x1Axis.setTimeline(timeline);
		} else if (index == 3) {
			SegmentedTimeline timeline = new SegmentedTimeline(SegmentedTimeline.DAY_SEGMENT_SIZE, 7, 0);
			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal.setTime(startDate);
			cal2.setTime(endDate);
			Date dateToException;
			cal.add(Calendar.HOUR, 12);
			while (cal.before(cal2)) {
				if (cal.get(Calendar.DAY_OF_MONTH) != 1) {
					dateToException = cal.getTime();
					timeline.addException(dateToException);
				}
				cal.add(Calendar.DATE, 1);
			}
			x1Axis.setTimeline(timeline);
			x1Axis.setTickUnit(new DateTickUnit(DateTickUnit.DAY, 20));// ����ʱ��̶ȵļ����һ������Ϊ��λ
		}
		x1Axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);// ���ñ�ǵ�λ��
		x1Axis.setStandardTickUnits(DateAxis.createStandardDateTickUnits());// ���ñ�׼��ʱ��̶ȵ�λ
		x1Axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));

		NumberAxis y1Axis = new NumberAxis();// �趨y�ᣬ����������
		y1Axis.setRange(minValue * 0.98, highValue * 1.02);// �趨y��ֵ�ķ�Χ�������ֵҪ��һЩ�������ֵҪ��һЩ������ͼ�ο�����������Щ

		XYPlot plot1 = new XYPlot();
		plot1.setDomainAxis(x1Axis);
		plot1.setRangeAxis(y1Axis);

		plot1.setRenderer(0, candlestickRender);
		plot1.setDataset(0, seriesCollection);

		XYLineAndShapeRenderer lineRender = new XYLineAndShapeRenderer();
		lineRender.setBaseShapesVisible(false);

		// k��ͼ
		plot1.setRenderer(1, lineRender);
		plot1.setDataset(1, avgLine);
		lineRender.setSeriesPaint(0, Color.black);
		lineRender.setSeriesPaint(1, Color.blue);
		lineRender.setSeriesPaint(2, new Color(245, 5, 245));

		XYBarRenderer xyBarRender = new XYBarRenderer() {
			public Paint getItemPaint(int i, int j) {// �����ڲ������������յĳɽ�������ͼ����ɫ��K��ͼ����ɫ����һ��
				if (seriesCollection.getCloseValue(i, j) > seriesCollection.getOpenValue(i, j)) {// ���̼۸��ڿ��̼ۣ���Ʊ���ǣ�ѡ�ù�Ʊ���ǵ���ɫ
					return candlestickRender.getUpPaint();
				} else {
					return candlestickRender.getDownPaint();
				}
			}
		};

		double[] margin = { 0, 0.3, 0.3, 0 };
		xyBarRender.setMargin(margin[index]);// ��������ͼ֮��ļ��
		xyBarRender.setShadowVisible(false);
		xyBarRender.setBarPainter(new StandardXYBarPainter());
		NumberAxis y2Axis = new NumberAxis();// ����Y�ᣬΪ��ֵ,��������ã��ο������y������
		y2Axis.setRange(min2Value * 0.9, high2Value * 1.1);
		XYPlot plot2 = new XYPlot(timeSeriesCollection, null, y2Axis, xyBarRender);// �����ڶ�����ͼ���������Ҫ��ʱ��x����Ϊ��nullֵ����ΪҪ���һ����ͼ���������x��

		CombinedDomainXYPlot combineddomainxyplot = new CombinedDomainXYPlot(x1Axis);// ����һ��ǡ��������ͼ�����������x��Ϊ������
		combineddomainxyplot.add(plot1, 2);// ���ͼ��������󣬺���������Ǽ�������������Ӧ��ռ�ݶ�������2/3
		combineddomainxyplot.add(plot2, 1);// ���ͼ��������󣬺���������Ǽ�������������Ӧ��ռ�ݶ�������1/3
		combineddomainxyplot.setGap(10);// ��������ͼ���������֮��ļ���ռ�

		// ����͸��
		plot1.setBackgroundPaint(new Color(255, 255, 255));
		plot2.setBackgroundPaint(new Color(255, 255, 255));

		// String title[] = {"ʱ��ͼ","��K��ͼ","��K��ͼ","��K��ͼ"};
		chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, combineddomainxyplot, false);
		chart.setAntiAlias(true);
		chart.setBackgroundImageAlpha(0f);

		// ͼ��
		LegendTitle legendTitle = new LegendTitle(plot1);
		legendTitle.setPosition(RectangleEdge.BOTTOM);
		chart.addSubtitle(legendTitle);

		this.add(new ChartPanel(chart));
	}

	public ChartPanel getChartPane() {
		return new ChartPanel(chart);
	}

}

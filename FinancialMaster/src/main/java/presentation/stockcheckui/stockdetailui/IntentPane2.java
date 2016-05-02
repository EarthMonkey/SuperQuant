package presentation.stockcheckui.stockdetailui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartPanel;

import VO.StockVO;
import presentation.repaintComponent.IntentPane;
import presentation.repaintComponent.MyComboBox;
import presentation.repaintComponent.MyTabbedPaneUI2;
import presentation.stockcheckui.BarChart;
import presentation.stockcheckui.LineChart;
import presentation.stockmarketui.KLineChart;
import presentation.stockmarketui.SearchBar;

@SuppressWarnings("serial")
public class IntentPane2 extends IntentPane {
	private JPanel panes[];
	private int selectedIndex;
	private String linestr[] = { "���̼�", "��߼�", "��ͼ�", "���̼�", "��Ȩ��", "�ɽ���", "������", "��ӯ��", "�о���" };
	private String data[][];
	private StockVO datavo;

	@SuppressWarnings("unchecked")
	public IntentPane2(String data[][], StockVO datavo, SearchBar searchBar) {
		IntentPane intentPane2 = this;
		this.data = data;
		this.datavo = datavo;

		intentPane2.setPreferredSize(new Dimension(700, 450));
		intentPane2.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(7, 50, 690, 388);
		tabbedPane.setUI(new MyTabbedPaneUI2());
		intentPane2.add(tabbedPane);

		// k��ͼ
		String title[] = { "��K", "����ͼ", "��״ͼ" };
		panes = new JPanel[3];
		for (int i = 0; i < 3; i++) {
			panes[i] = new JPanel();
			tabbedPane.add(title[i], panes[i]);
		}

		showKline();

		panes[1].setLayout(new FlowLayout());

		final MyComboBox lineBox = new MyComboBox();
		lineBox.setFont(new Font("Lantinghei TC", Font.PLAIN, 15));
		lineBox.setPreferredSize(new Dimension(90, 25));
		lineBox.setMaximumRowCount(20);

		for (int i = 0; i < linestr.length; i++) {
			lineBox.addItem(linestr[i]);
		}
		
		JLabel lineLabel = new JLabel("��һ���¸������");
		lineLabel.setFont(new Font("Lantinghei TC", Font.PLAIN, 15));
		lineLabel.setPreferredSize(new Dimension(150, 30));

		JLabel barLabel = new JLabel("��һ������״ͼ");
		barLabel.setFont(new Font("Lantinghei TC", Font.PLAIN, 15));
		barLabel.setPreferredSize(new Dimension(150, 30));

		lineBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {

				panes[selectedIndex].removeAll();
				panes[selectedIndex].add(lineBox);

				if (selectedIndex == 1) {
					panes[1].add(lineLabel);
				} else if (selectedIndex == 2) {
					panes[2].add(barLabel);
				}

				String name = e.getItem().toString();
				showChart(name);
			}
		});

		JLabel remindlabel = new JLabel("�����ڴ�");
		remindlabel.setSize(660, 350);
		panes[2].add(remindlabel);

		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				searchBar.setVisible(false);
				JTabbedPane tab = (JTabbedPane) e.getSource();
				selectedIndex = tab.getSelectedIndex();

				if (selectedIndex == 0) {
					// ��K
					panes[0].removeAll();
					showKline();
				} else if (selectedIndex == 1) {
					// ����ͼ
					panes[1].removeAll();
					panes[1].add(lineBox);
					panes[1].add(lineLabel);

					showChart("���̼�");
				} else {
					// ��״ͼ
					panes[2].removeAll();
					panes[2].add(lineBox);
					panes[2].add(barLabel);

					showChart("���̼�");
				}

			}
		});
	}

	public void showKline() {
		String klineData[][] = datavo.getKLine_data();

		KLineChart kline = new KLineChart(klineData, 1);
		ChartPanel chartPanel = kline.getChartPane();
		chartPanel.setPreferredSize(new Dimension(660, 345));
		panes[0].add(chartPanel);
		panes[0].repaint();
		panes[0].validate();
	}

	public void showChart(String name) {
		int length = data.length;
		String[][] chartData = new String[length][2];

		int index = 0;
		for (int i = 0; i < 9; i++) {
			if (linestr[i].equals(name)) {
				index = i;
				break;
			}
		}

		for (int i = 0; i < length; i++) {
			chartData[i][0] = data[length - i - 1][0];
			chartData[i][1] = data[length - i - 1][index + 1];
		}

		ChartPanel chartPanel = null;

		if (selectedIndex == 1) {
			LineChart lineChart = new LineChart(chartData, name);
			chartPanel = lineChart.getChartPane();
		} else if (selectedIndex == 2) {
			BarChart barChart = new BarChart(chartData, name);
			chartPanel = barChart.getChartPane();
		}

		chartPanel.setPreferredSize(new Dimension(660, 315));

		panes[selectedIndex].add(chartPanel);
		panes[selectedIndex].repaint();
		panes[selectedIndex].validate();
	}
}

package presentation.stockcheckui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.jfree.chart.ChartPanel;

import ENUM.attentionState;
import VO.StockMarketVO;
import VO.StockVO;
import businesslogic.managestockbl.ManageStockBL;
import businesslogic.stockcheckbl.StockMessageBL;
import businesslogicservice.managestockblservice.ManageStockBLService;
import businesslogicservice.stockcheckblservice.StockMessageBLService;
import presentation.repaintComponent.DateChooser;
import presentation.repaintComponent.HeaderCellRenderer;
import presentation.repaintComponent.IntentPane;
import presentation.repaintComponent.MyComboBox;
import presentation.repaintComponent.MyScrollBarUI;
import presentation.repaintComponent.MyTabbedPaneUI2;
import presentation.repaintComponent.MyTableCellRenderer;
import presentation.repaintComponent.TextBubbleBorder;
import presentation.repaintComponent.barPanel;
import presentation.stockmarketui.KLineChart;
import presentation.stockmarketui.SearchBar;

@SuppressWarnings("serial")
public class StockDetail extends JPanel {
	JButton closeBtn;
	JButton miniBtn;
	private JTextField searchTextField;
	private boolean click = false;
	private boolean click1 = false;
	private boolean click2 = false;
	private ManageStockBLService manageStockBL = new ManageStockBL();
	DefaultTableModel tableModel;
	private int rowpos = -1;
	private JLabel timeGotolbl;
	StockMessageBLService Message = new StockMessageBL();
	private StockVO datavo;
	private JPanel panes[];
	private int selectedIndex;
	private String data[][];
	private String linestr[] = { "开盘价", "最高价", "最低价", "收盘价", "后复权价", "成交量", "换手率", "市盈率", "市净率" };
	private JButton likeButton;
	private ImageIcon image2;
	private ImageIcon image3;
	private ImageIcon imageVolume;
	// private ImageIcon imageTurnover;
	// private ImageIcon imagePe;
	// private ImageIcon imagePb;

	private StockMarketVO stockMarketVO;
	private SearchBar searchBar;

	private Color brown = new Color(54, 42, 29);
	private Color green = new Color(37, 120, 38);
	private Color red = new Color(179, 43, 56);
	private Font labelFont = new Font("微软雅黑", Font.PLAIN, 18);
	private attentionState state;
	private JLabel percentLbl;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	// boolean 用来判断跳转回到哪里，true返回股票列表，false返回任意界面
	public StockDetail(final JFrame frame, final String id) {

		// JFrame loadframe = new JFrame();
		// loadframe.setAlwaysOnTop(true);
		// loadframe.setUndecorated(true);
		// loadingPanel loadingPanel = new loadingPanel();
		// loadingPanel.setOpaque(false);
		//
		// loadframe.setSize(120, 120);
		// loadframe.setBackground(new Color(0, 0, 0, 0));
		// loadframe.getContentPane().add(loadingPanel);
		// loadframe.setBounds(frame.getX()+533, frame.getY()+215, 120,120);
		// loadframe.repaint();
		// loadframe.setVisible(true);

		datavo = Message.getStockMessage(id);

		// loadframe.dispose();

		stockMarketVO = datavo.getStockMarketVO();
		data = datavo.getHistory_data();

		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);

		setBorder(null);
		setLayout(null);
		final StockDetail detail = this;

		searchBar = new SearchBar(frame, detail);
		detail.add(searchBar);
		searchBar.setVisible(false);

		JScrollPane contentScroll = new JScrollPane();
		contentScroll.setBounds(0, 51, 730, 540);
		contentScroll.setOpaque(false);
		contentScroll.getViewport().setOpaque(false);
		contentScroll.setBorder(BorderFactory.createEmptyBorder());
		contentScroll.getVerticalScrollBar().setUI(new MyScrollBarUI());

		JPanel content = new JPanel();
		content.setOpaque(false);
		content.setPreferredSize(new Dimension(710, 1220));
		content.setLayout(new FlowLayout(FlowLayout.LEFT, 14, 14));

		IntentPane intentPane1 = new IntentPane();
		intentPane1.setPreferredSize(new Dimension(700, 300));
		intentPane1.setLayout(null);

		IntentPane intentPane2 = new IntentPane();
		intentPane2.setPreferredSize(new Dimension(700, 450));
		intentPane2.setLayout(null);

		IntentPane intentPane3 = new IntentPane();
		intentPane3.setPreferredSize(new Dimension(700, 404));
		intentPane3.setLayout(null);

		JLabel historyLbl = new JLabel("历史数据");
		historyLbl.setBackground(new Color(245, 245, 245));
		historyLbl.setForeground(brown);
		historyLbl.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		historyLbl.setBounds(10, 5, 200, 32);
		intentPane3.add(historyLbl);

		// 另外四个数据图标
		JLabel volumePanel = new JLabel();
		volumePanel.setBounds(430, 74, 60, 60);
		imageVolume = new ImageIcon("src/main/resources/image/volume.png");
		Image tempCircle = imageVolume.getImage().getScaledInstance(volumePanel.getWidth(), volumePanel.getHeight(),
				imageVolume.getImage().SCALE_SMOOTH);
		imageVolume = new ImageIcon(tempCircle);
		volumePanel.setOpaque(false);
		volumePanel.setIcon(imageVolume);
		intentPane1.add(volumePanel);

		JLabel turnoverPanel = new JLabel();
		turnoverPanel.setBounds(518, 74, 60, 60);
		imageVolume = new ImageIcon("src/main/resources/image/turnover.png");
		tempCircle = imageVolume.getImage().getScaledInstance(turnoverPanel.getWidth(), turnoverPanel.getHeight(),
				imageVolume.getImage().SCALE_SMOOTH);
		ImageIcon imageTurnOver = new ImageIcon(tempCircle);
		turnoverPanel.setIcon(imageTurnOver);
		turnoverPanel.setOpaque(false);
		intentPane1.add(turnoverPanel);

		JLabel pePanel = new JLabel();
		pePanel.setBounds(430, 175, 60, 60);
		imageVolume = new ImageIcon("src/main/resources/image/pe.png");
		tempCircle = imageVolume.getImage().getScaledInstance(pePanel.getWidth(), pePanel.getHeight(),
				imageVolume.getImage().SCALE_SMOOTH);
		ImageIcon imagePe = new ImageIcon(tempCircle);
		pePanel.setIcon(imagePe);
		pePanel.setOpaque(false);
		intentPane1.add(pePanel);

		JLabel pbPanel = new JLabel();
		pbPanel.setBounds(518, 175, 60, 60);
		imageVolume = new ImageIcon("src/main/resources/image/pb.png");
		tempCircle = imageVolume.getImage().getScaledInstance(pbPanel.getWidth(), pbPanel.getHeight(),
				imageVolume.getImage().SCALE_SMOOTH);
		ImageIcon imagePb = new ImageIcon(tempCircle);
		pbPanel.setIcon(imagePb);
		pbPanel.setOpaque(false);
		intentPane1.add(pbPanel);

		JLabel amplitudePanel = new JLabel();
		amplitudePanel.setBounds(606, 74, 60, 60);
		imageVolume = new ImageIcon("src/main/resources/image/amplitude.png");
		tempCircle = imageVolume.getImage().getScaledInstance(amplitudePanel.getWidth(), amplitudePanel.getHeight(),
				imageVolume.getImage().SCALE_SMOOTH);
		ImageIcon imageamplitude = new ImageIcon(tempCircle);
		amplitudePanel.setIcon(imageamplitude);
		amplitudePanel.setOpaque(false);
		intentPane1.add(amplitudePanel);

		JLabel quantityPanel = new JLabel();
		quantityPanel.setBounds(606, 175, 60, 60);
		imageVolume = new ImageIcon("src/main/resources/image/quantity.png");
		tempCircle = imageVolume.getImage().getScaledInstance(quantityPanel.getWidth(), quantityPanel.getHeight(),
				imageVolume.getImage().SCALE_SMOOTH);
		ImageIcon imagequantity = new ImageIcon(tempCircle);
		quantityPanel.setIcon(imagequantity);
		quantityPanel.setOpaque(false);
		intentPane1.add(quantityPanel);

		closeBtn = new JButton("X");
		closeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				closeBtn.setForeground(new Color(252, 98, 93));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				closeBtn.setForeground(new Color(216, 216, 216));
			}
		});
		closeBtn.setForeground(new Color(216, 216, 216));
		closeBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		closeBtn.setContentAreaFilled(false);
		closeBtn.setBorder(null);
		closeBtn.setBounds(707, 15, 16, 16);
		add(closeBtn);

		miniBtn = new JButton("—");
		miniBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				miniBtn.setForeground(new Color(253, 188, 64));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				miniBtn.setForeground(new Color(216, 216, 216));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setExtendedState(frame.ICONIFIED); // 最小化
			}
		});
		miniBtn.setForeground(new Color(216, 216, 216));
		miniBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		miniBtn.setContentAreaFilled(false);
		miniBtn.setBorder(null);
		miniBtn.setBounds(680, 14, 16, 16);
		add(miniBtn);

		String[][] data = datavo.getHistory_data();

		// 当前的数据展示
		JLabel openLabel1 = new JLabel("开盘价");
		openLabel1.setBounds(44, 76, 70, 30);
		openLabel1.setFont(labelFont);
		openLabel1.setForeground(brown);
		intentPane1.add(openLabel1);
		JLabel openLabel2 = new JLabel("最低价");
		openLabel2.setBounds(44, 118, 70, 30);
		openLabel2.setFont(labelFont);
		openLabel2.setForeground(brown);
		intentPane1.add(openLabel2);
		JLabel openLabel3 = new JLabel("最高价");
		openLabel3.setBounds(44, 159, 70, 30);
		openLabel3.setFont(labelFont);
		openLabel3.setForeground(brown);
		intentPane1.add(openLabel3);
		JLabel openLabel4 = new JLabel("收盘价");
		openLabel4.setBounds(44, 200, 70, 30);
		openLabel4.setFont(labelFont);
		openLabel4.setForeground(brown);
		intentPane1.add(openLabel4);
		JLabel openLabel5 = new JLabel("后复权价");
		openLabel5.setBounds(44, 241, 90, 30);
		openLabel5.setFont(labelFont);
		openLabel5.setForeground(brown);
		intentPane1.add(openLabel5);

		double high = datavo.getHigh();
		double open = datavo.getOpen() / high;
		double low = datavo.getLow() / high;
		double close = datavo.getClose() / high;
		double adj_price = datavo.getAdj_price() / high;

		// 当前价格条

		barPanel bPanel1 = new barPanel(datavo.getOpen(), high);
		bPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		bPanel1.setBounds(150, 78, (int) (210 * Math.pow(open, 12)), 26);
		JLabel label5 = new JLabel(datavo.getOpen() + "");
		label5.setBounds(15, 6, 20, 18);
		label5.setForeground(new Color(105, 76, 36));
		bPanel1.add(label5);
		intentPane1.add(bPanel1);

		barPanel bPanel2 = new barPanel(datavo.getLow(), high);
		bPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		bPanel2.setBounds(150, 120, (int) (210 * Math.pow(low, 12)), 26);
		JLabel label1 = new JLabel(datavo.getLow() + "");
		label1.setBounds(15, 6, 20, 18);
		label1.setForeground(new Color(105, 76, 36));
		bPanel2.add(label1);
		intentPane1.add(bPanel2);

		barPanel bPanel3 = new barPanel(high, high);
		bPanel3.setLayout(new FlowLayout(FlowLayout.LEFT));
		bPanel3.setBounds(150, 161, 210, 26);
		JLabel label2 = new JLabel(datavo.getHigh() + "");
		label2.setBounds(15, 6, 20, 18);
		label2.setForeground(new Color(105, 76, 36));
		bPanel3.add(label2);
		intentPane1.add(bPanel3);

		barPanel bPanel4 = new barPanel(datavo.getClose(), high);
		bPanel4.setLayout(new FlowLayout(FlowLayout.LEFT));
		bPanel4.setBounds(150, 202, (int) (210 * Math.pow(close, 12)), 26);
		JLabel label3 = new JLabel(datavo.getClose() + "");
		label3.setBounds(15, 6, 20, 18);
		label3.setForeground(new Color(105, 76, 36));
		bPanel4.add(label3);
		intentPane1.add(bPanel4);

		barPanel bPanel5 = new barPanel(datavo.getAdj_price(), high);
		bPanel5.setLayout(new FlowLayout(FlowLayout.LEFT));
		bPanel5.setBounds(150, 243, (int) (210 * Math.pow(adj_price, 12)), 26);
		JLabel label4 = new JLabel(datavo.getAdj_price() + "");
		label4.setBounds(15, 6, 20, 18);
		label4.setForeground(new Color(105, 76, 36));
		bPanel5.add(label4);
		intentPane1.add(bPanel5);

		likeButton = new JButton();
		likeButton.setBounds(640, 11, 26, 23);
		likeButton.setContentAreaFilled(false);
		likeButton.setBorderPainted(false);
		// image2 未关注图标
		image2 = new ImageIcon("src/main/resources/image/heart.png");
		Image temp2 = image2.getImage().getScaledInstance(likeButton.getWidth(), likeButton.getHeight(),
				image2.getImage().SCALE_SMOOTH);
		image2 = new ImageIcon(temp2);
		// image3 已关注图标
		image3 = new ImageIcon("src/main/resources/image/heart-selected.png");
		Image temp3 = image3.getImage().getScaledInstance(likeButton.getWidth(), likeButton.getHeight(),
				image3.getImage().SCALE_SMOOTH);

		image3 = new ImageIcon(temp3);

		state = manageStockBL.isAttented(id);
		if (state == attentionState.No) {
			likeButton.setIcon(image2);
		} else {
			likeButton.setIcon(image3);
		}
		likeButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				searchBar.setVisible(false);
				if (state == attentionState.Yes) {
					likeButton.setIcon(image2);
					state = attentionState.No;
					manageStockBL.deleteStock(id);
				} else {
					likeButton.setIcon(image3);
					state = attentionState.Yes;
					manageStockBL.addStock(id);
				}
			}
		});
		intentPane1.add(likeButton);

		Date today = new Date();
		Date dbefore = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1); // 前一个月
		dbefore = calendar.getTime();

		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		final String start = dt.format(dbefore);
		final JLabel startTimelbl = new JLabel(start);
		startTimelbl.setBounds(456, 15, 90, 22);
		dateChooser1.register(startTimelbl);
		intentPane3.add(startTimelbl);

		DateChooser dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");
		final String end = dt.format(today);
		final JLabel endTimelbl = new JLabel(end);
		endTimelbl.setBounds(586, 15, 90, 22);
		dateChooser2.register(endTimelbl);
		intentPane3.add(endTimelbl);

		JScrollPane stockDetailPane = new JScrollPane();
		stockDetailPane.setBounds(0, 50, 700, 300);
		stockDetailPane.setOpaque(false);
		stockDetailPane.setBorder(BorderFactory.createEmptyBorder());
		stockDetailPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		stockDetailPane.getViewport().setOpaque(false);
		intentPane3.add(stockDetailPane);

		// init the table
		table = new JTable() {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				int modelRow = convertRowIndexToModel(row);
				int modelColumn = convertColumnIndexToModel(column);
				Component comp = super.prepareRenderer(renderer, row, column);
				// if (!isRowSelected(modelRow)) {
				int temp = modelRow;
				double close = Double.parseDouble(this.getModel().getValueAt(temp, 1).toString());
				if (modelColumn == 2 || modelColumn == 3 || modelColumn == 4 || modelColumn == 5) {
					if (Double.parseDouble(this.getModel().getValueAt(modelRow, modelColumn).toString()) > close) {
						comp.setForeground(red);
					} else if (Double
							.parseDouble(this.getModel().getValueAt(modelRow, modelColumn).toString()) == close) {
						comp.setForeground(new Color(62, 56, 49, 240));
					} else {
						comp.setForeground(green);
					}
				} else // 不符合条件的保持原表格样式
					comp.setForeground(new Color(62, 56, 49, 240));
				// }
				return comp;
			}
		};

		table.setRowHeight(26);
		// 使表格居中
		MyTableCellRenderer r = new MyTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		table.setSelectionBackground(new Color(88, 93, 103, 200));
		table.setSelectionForeground(new Color(255, 255, 255, 230));
		table.setOpaque(false);
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		// 选取行
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				searchBar.setVisible(false);
				Point mousepoint;
				mousepoint = e.getPoint();
				rowpos = table.rowAtPoint(mousepoint);
				table.setRowSelectionInterval(rowpos, rowpos);
			}
		});
		stockDetailPane.setViewportView(table);
		table.setBorder(null);
		table.setEnabled(false);
		tableModel = new DefaultTableModel(data,
				new String[] { "日期", "开盘价", "最高价", "最低价", "收盘价", "后复权价", "交易量(股)", "换手率", "市盈率", "市净率" });
		table.setModel(tableModel);

		table.getColumnModel().getColumn(0).setPreferredWidth(118);
		for (int i = 1; i < 6; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(63);
		}
		table.getColumnModel().getColumn(6).setPreferredWidth(118);

		JTableHeader header = table.getTableHeader();
		header.setOpaque(false);
		header.getTable().setOpaque(false);
		header.getTable().setIntercellSpacing(new Dimension(0, getHeight()));
		header.setDefaultRenderer(new HeaderCellRenderer());

		setDragable(frame);

		JButton searchBtn = new JButton();
		searchBtn.setBounds(630, 15, 18, 18);
		searchBtn.setContentAreaFilled(false);
		searchBtn.setBorderPainted(false);
		searchBtn.setBorder(null);
		ImageIcon image1 = new ImageIcon("src/main/resources/image/search.png");
		Image temp1 = image1.getImage().getScaledInstance(searchBtn.getWidth(), searchBtn.getHeight(),
				image1.getImage().SCALE_DEFAULT);
		image1 = new ImageIcon(temp1);
		searchBtn.setIcon(image1);
		searchBtn.setMargin(new Insets(0, 0, 0, 0));
		add(searchBtn);

		searchTextField = new JTextField();
		searchTextField.setFocusable(false);
		searchTextField.setOpaque(false);
		searchTextField.setForeground(new Color(150, 150, 150));
		searchTextField.setCaretColor(new Color(150, 150, 150));
		searchTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
		searchTextField.setText("输入股票代码或名称搜索");
		searchTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				searchTextField.setBorder(new TextBubbleBorder(new Color(150, 150, 150), 1, 30, 0));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!click) {
					searchTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				searchBar.setVisible(false);
				searchTextField.setText("");
				click = true;
				searchTextField.setBorder(new TextBubbleBorder(new Color(150, 150, 150), 1, 30, 0));
				searchTextField.setFocusable(true);
				searchTextField.requestFocus();
			}
		});
		searchTextField.setBounds(462, 11, 196, 27);
		add(searchTextField);
		searchTextField.setColumns(10);

		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					searchBar.setVisible(false);
					detail.setVisible(false);
					String anotherID = searchBar.getID();
					StockDetail another = new StockDetail(frame, anotherID);
					frame.getContentPane().add(another);
					another.setBounds(224, 0, 737, getHeight());
					another.setVisible(true);
					frame.repaint();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

				String key = searchTextField.getText();
				if (key.equals("")) {
					searchBar.setVisible(false);
				} else if (e.getKeyCode() != KeyEvent.VK_DOWN && e.getKeyCode() != KeyEvent.VK_UP) {
					searchBar.showTable(key);
					searchBar.setBounds(476, 38, searchBar.getWidth(), searchBar.getHeight());

					if (searchBar.getRowCount() > 0) {
						searchBar.setVisible(true);
					} else {
						searchBar.setVisible(false);
					}

					rowpos = -1;
				}

				if (searchBar.getRowCount() > 0) {
					if (e.getKeyCode() == KeyEvent.VK_DOWN && rowpos < searchBar.getRowCount() - 1) {
						rowpos++;
						searchBar.setSelect(rowpos, true);
					}

					if (e.getKeyCode() == KeyEvent.VK_UP && rowpos > 0) {
						rowpos--;
						searchBar.setSelect(rowpos, false);
					}
				}
			}
		});

		JLabel namelbl = new JLabel();
		namelbl.setText(datavo.getName() + "(" + id + ")");
		namelbl.setBackground(new Color(245, 245, 245));
		namelbl.setForeground(brown);
		namelbl.setBounds(10, 5, 250, 32);
		namelbl.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		intentPane1.add(namelbl);

		JLabel name = new JLabel("沪深300");
		name.setBounds(70, 14, 80, 26);
		name.setForeground(brown);
		add(name);

		// 个股涨跌幅数据
		JLabel raiseRate = new JLabel();
		double upAndDown = datavo.getUps_and_lows();
		raiseRate.setText(nf.format(upAndDown));
		raiseRate.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		raiseRate.setForeground(new Color(62, 56, 49, 240));
		if (upAndDown > 0) {
			raiseRate.setForeground(red);
		} else if (upAndDown < 0) {
			raiseRate.setForeground(green);
		}
		raiseRate.setBounds(250, 10, 250, 24);
		intentPane1.add(raiseRate);

		// 大盘涨跌量

		JLabel change = new JLabel();
		double changeRange = stockMarketVO.getChangeRange();
		change.setText((changeRange + "").substring(0, 7));
		change.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		change.setForeground(new Color(62, 56, 49, 240));
		if (changeRange > 0) {
			change.setForeground(red);
		} else if (changeRange < 0) {
			change.setForeground(green);
		}
		change.setBounds(231, 14, 60, 24);
		add(change);

		ImageIcon upArrowIcon = new ImageIcon("src/main/resources/image/upArrow.png");
		Image tempArrow = upArrowIcon.getImage().getScaledInstance(14, 9, upArrowIcon.getImage().SCALE_SMOOTH);
		upArrowIcon = new ImageIcon(tempArrow);
		ImageIcon downArrowIcon = new ImageIcon("src/main/resources/image/downArrow.png");
		tempArrow = downArrowIcon.getImage().getScaledInstance(14, 9, downArrowIcon.getImage().SCALE_SMOOTH);
		downArrowIcon = new ImageIcon(tempArrow);

		JLabel upArrow = new JLabel();
		upArrow.setBounds(210, 22, 14, 9);
		upArrow.setOpaque(false);
		if (changeRange > 0) {
			upArrow.setIcon(upArrowIcon);
		} else {
			upArrow.setIcon(downArrowIcon);
		}
		add(upArrow);

		// 大盘现价
		JLabel nowMarket = new JLabel();
		double now = stockMarketVO.getClose();
		nowMarket.setText((now + "").substring(0, 7));
		nowMarket.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		nowMarket.setForeground(new Color(62, 56, 49, 240));
		if (changeRange > 0) {
			nowMarket.setForeground(red);
		} else if (changeRange < 0) {
			nowMarket.setForeground(green);
		}
		nowMarket.setBounds(135, 14, 60, 24);
		add(nowMarket);

		// 大盘涨跌幅
		JLabel marketUpAndDown = new JLabel();
		double marketup = stockMarketVO.getUps_and_downs();
		marketUpAndDown.setText(nf.format(marketup));
		marketUpAndDown.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		marketUpAndDown.setForeground(new Color(62, 56, 49, 240));
		if (changeRange > 0) {
			marketUpAndDown.setForeground(red);
		} else if (changeRange < 0) {
			marketUpAndDown.setForeground(green);
		}
		marketUpAndDown.setBounds(328, 14, 60, 24);
		add(marketUpAndDown);

		JLabel downArrow = new JLabel();
		downArrow.setBounds(305, 22, 14, 9);
		downArrow.setOpaque(false);
		if (changeRange > 0) {
			downArrow.setIcon(upArrowIcon);
		} else {
			downArrow.setIcon(downArrowIcon);
		}
		add(downArrow);

		// 成交量
		JLabel volumeLabel = new JLabel();
		double volume = datavo.getVolume();
		volumeLabel.setText((volume / 10000 + "") + "W");
		volumeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		volumeLabel.setForeground(new Color(62, 56, 49, 240));
		volumeLabel.setBounds(432, 143, 80, 24);
		intentPane1.add(volumeLabel);
		// 换手率
		JLabel turnoverLabel = new JLabel();
		double turnover = datavo.getTurnover();
		turnoverLabel.setText((turnover + "") + "%");
		turnoverLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		turnoverLabel.setForeground(new Color(62, 56, 49, 240));
		turnoverLabel.setBounds(529, 143, 80, 24);
		intentPane1.add(turnoverLabel);
		// 市盈率
		JLabel peLabel = new JLabel();
		double pe = datavo.getPe();
		peLabel.setText((pe + "") + "%");
		peLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		peLabel.setForeground(new Color(62, 56, 49, 240));
		peLabel.setBounds(441, 247, 80, 24);
		intentPane1.add(peLabel);
		// 市净率
		JLabel pbLabel = new JLabel();
		double pb = datavo.getPb();
		pbLabel.setText((pb + "") + "%");
		pbLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		pbLabel.setForeground(new Color(62, 56, 49, 240));
		pbLabel.setBounds(529, 247, 80, 24);
		intentPane1.add(pbLabel);
		// 振幅
		JLabel amplitudeLabel = new JLabel();
		double amplitude = datavo.getTamplitude();
		amplitudeLabel.setText(nf.format(amplitude));
		amplitudeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		amplitudeLabel.setForeground(new Color(62, 56, 49, 240));
		amplitudeLabel.setBounds(616, 143, 80, 24);
		intentPane1.add(amplitudeLabel);
		// 量比
		JLabel quantityLabel = new JLabel();
		double quantity = datavo.getQuantity_relative_ratio();
		quantityLabel.setText(nf.format(quantity));
		quantityLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		quantityLabel.setForeground(new Color(62, 56, 49, 240));
		quantityLabel.setBounds(616, 247, 80, 24);
		intentPane1.add(quantityLabel);

		backBtn = new JButton("back");
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchBar.setVisible(false);
				frame.remove(detail);
				StockList listui = new StockList(frame);
				listui.setBounds(224, 0, 737, getHeight());
				frame.getContentPane().add(listui);
				frame.repaint();
				frame.validate();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				backBtn.setForeground(new Color(72, 77, 78));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backBtn.setForeground(new Color(216, 216, 216));
			}
		});
		backBtn.setForeground(new Color(216, 216, 216));
		backBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		backBtn.setContentAreaFilled(false);
		backBtn.setBorder(null);
		backBtn.setBounds(10, 15, 46, 16);
		add(backBtn);

		JLabel labelz = new JLabel("至");
		labelz.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		labelz.setBounds(554, 13, 25, 22);
		intentPane3.add(labelz);

		final MyComboBox conditionBox = new MyComboBox();
		conditionBox.setOpaque(false);
		conditionBox.setFont(new Font("Lantinghei TC", Font.PLAIN, 16));
		String[] item = { "筛选关键字", "开盘价", "最高价", "最低价", "收盘价", "后复权价", "成交量", "换手率", "市盈率", "市净率" };
		for (int i = 0; i < 10; i++) {
			conditionBox.addItem(item[i]);
		}
		conditionBox.setBorder(null);
		conditionBox.setBounds(10, 362, 120, 25);
		conditionBox.setSelectedIndex(0);
		intentPane3.add(conditionBox);

		belowTextField = new JTextField();
		belowTextField.setOpaque(false);
		belowTextField.setForeground(new Color(150, 150, 150));
		belowTextField.setFocusable(false);
		belowTextField.setColumns(10);
		belowTextField.setCaretColor(new Color(150, 150, 150));
		belowTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
		belowTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				belowTextField.setBorder(new TextBubbleBorder(new Color(150, 150, 150), 1, 30, 0));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!click1) {
					belowTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				belowTextField.setText("");
				click1 = true;
				belowTextField.setBorder(new TextBubbleBorder(new Color(150, 150, 150), 1, 30, 0));
				belowTextField.setFocusable(true);
				belowTextField.requestFocus();
			}
		});
		belowTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!(Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '.')) {
					e.consume();
				}
			}
		});
		belowTextField.setBounds(146, 362, 97, 27);
		belowTextField.setText("输入下限");
		intentPane3.add(belowTextField);

		aboveTextField = new JTextField();
		aboveTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				aboveTextField.setBorder(new TextBubbleBorder(new Color(150, 150, 150), 1, 30, 0));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!click2) {
					aboveTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				searchBar.setVisible(false);
				aboveTextField.setText("");
				click2 = true;
				aboveTextField.setBorder(new TextBubbleBorder(new Color(150, 150, 150), 1, 30, 0));
				aboveTextField.setFocusable(true);
				aboveTextField.requestFocus();
			}
		});
		aboveTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!(Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '.')) {
					e.consume();
				}
			}
		});
		aboveTextField.setOpaque(false);
		aboveTextField.setForeground(new Color(150, 150, 150));
		aboveTextField.setFocusable(false);
		aboveTextField.setColumns(10);
		aboveTextField.setCaretColor(new Color(150, 150, 150));
		aboveTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
		aboveTextField.setBounds(269, 362, 97, 27);
		aboveTextField.setText("输入上限");
		intentPane3.add(aboveTextField);

		timeGotolbl = new JLabel("\u203A");
		timeGotolbl.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				timeGotolbl.setForeground(new Color(72, 77, 78));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				timeGotolbl.setForeground(new Color(150, 150, 150));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				searchBar.setVisible(false);

				String newStart = startTimelbl.getText();
				String newEnd = endTimelbl.getText();
				StockVO datavo = Message.updateStockMessage(newStart, newEnd);
				String[][] data = datavo.getHistory_data();
				tableModel = new DefaultTableModel(data,
						new String[] { "日期", "开盘价", "最高价", "最低价", "收盘价", "后复权价", "交易量(股)", "换手率", "市盈率", "市净率" });
				table.setModel(tableModel);

				table.getColumnModel().getColumn(0).setPreferredWidth(100);
				for (int i = 1; i < 6; i++) {
					table.getColumnModel().getColumn(i).setPreferredWidth(70);
				}

				conditionBox.setSelectedIndex(0);
				belowTextField.setText("输入下限");
				aboveTextField.setText("输入上限");
				
				frame.repaint();
				frame.validate();
			}
		});
		timeGotolbl.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		timeGotolbl.setForeground(new Color(150, 150, 150));
		timeGotolbl.setBounds(682, 13, 25, 22);
		intentPane3.add(timeGotolbl);

		final JLabel conditionGotolbl = new JLabel("\u203A");
		conditionGotolbl.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				conditionGotolbl.setForeground(new Color(72, 77, 78));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				conditionGotolbl.setForeground(new Color(150, 150, 150));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				searchBar.setVisible(false);

				String low = belowTextField.getText();
				String high = aboveTextField.getText();

				int index = conditionBox.getSelectedIndex();
				if (index != 0) {
					StockVO datavo = Message.filterStockMessage(index, low, high);
					String data2[][] = datavo.getHistory_data();
					tableModel = new DefaultTableModel(data2,
							new String[] { "日期", "开盘价", "最高价", "最低价", "收盘价", "后复权价", "交易量(股)", "换手率", "市盈率", "市净率" });
					table.setModel(tableModel);

					table.getColumnModel().getColumn(0).setPreferredWidth(100);
					for (int i = 1; i < 6; i++) {
						table.getColumnModel().getColumn(i).setPreferredWidth(70);
					}

					percentLbl.setText("筛选结果占比：" + nf.format(datavo.getRatio()));

					frame.repaint();
					frame.validate();
				}
			}
		});
		conditionGotolbl.setForeground(new Color(150, 150, 150));
		conditionGotolbl.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		conditionGotolbl.setBounds(382, 362, 25, 22);
		intentPane3.add(conditionGotolbl);

		// 筛选比例
		percentLbl = new JLabel("筛选结果占比: " + nf.format(datavo.getRatio()));
		conditionBox.setFont(new Font("Lantinghei TC", Font.PLAIN, 18));
		percentLbl.setBounds(530, 367, 250, 25);
		intentPane3.add(percentLbl);

		// k线图
		String title[] = { "日K", "折线图", "柱状图" };

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(7, 50, 690, 388);
		tabbedPane.setUI(new MyTabbedPaneUI2());
		intentPane2.add(tabbedPane);

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

		JLabel lineLabel = new JLabel("近一个月浮动情况");
		lineLabel.setFont(new Font("Lantinghei TC", Font.PLAIN, 15));
		lineLabel.setPreferredSize(new Dimension(150, 30));

		JLabel barLabel = new JLabel("近一个月柱状图");
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

		JLabel remindlabel = new JLabel("敬请期待");
		remindlabel.setSize(660, 350);
		panes[2].add(remindlabel);

		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				searchBar.setVisible(false);
				JTabbedPane tab = (JTabbedPane) e.getSource();
				selectedIndex = tab.getSelectedIndex();

				if (selectedIndex == 0) {
					// 日K
					panes[0].removeAll();
					showKline();
				} else if (selectedIndex == 1) {
					// 折线图
					panes[1].removeAll();
					panes[1].add(lineBox);
					panes[1].add(lineLabel);

					showChart("开盘价");
				} else {
					// 柱状图
					panes[2].removeAll();
					panes[2].add(lineBox);
					panes[2].add(barLabel);

					showChart("开盘价");
				}

			}
		});

		// 添加scrollPane
		content.add(intentPane1);
		content.add(intentPane2);
		content.add(intentPane3);
		contentScroll.setViewportView(content);
		contentScroll.getVerticalScrollBar().setUnitIncrement(20);
		add(contentScroll);

		// 点击其他地方使text field不能输入
		frame.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchBar.setVisible(false);
				searchTextField.setFocusable(false);
				searchTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
				searchTextField.setText("输入股票代码或名称搜索");

				belowTextField.setFocusable(false);
				belowTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
				belowTextField.setText("输入下限");

				aboveTextField.setFocusable(false);
				aboveTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
				aboveTextField.setText("输入上限");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	// 边框圆滑
	protected void paintComponent(Graphics g) {
		ImageIcon image = new ImageIcon("src/main/resources/image/right.png");
		g.drawImage(image.getImage(), 0, 0, getSize().width - 1, getSize().height - 1, this);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	}

	// 设置界面可拖动
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;
	private JTable table;
	private JButton backBtn;
	private JTextField belowTextField;
	private JTextField aboveTextField;

	private void setDragable(final JFrame jFrame) {
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent e) {
				isDragged = false;
				jFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
				tmp = new Point(e.getX(), e.getY());
				isDragged = true;
				jFrame.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			}
		});
		this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent e) {
				if (isDragged) {
					loc = new Point(jFrame.getLocation().x + e.getX() - tmp.x,
							jFrame.getLocation().y + e.getY() - tmp.y);
					jFrame.setLocation(loc);
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

package presentation.stockmarketui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

import ENUM.ManageState;
import ENUM.date_enum;
import ENUM.marketKline_enum;
import VO.StockMarketVO;
import businesslogic.stockmarketbl.MarketKLineBL;
import businesslogic.stockmarketbl.StockMarketBL;
import businesslogicservice.stockmarketblservice.MarketKLineBLService;
import businesslogicservice.stockmarketblservice.StockMarketBLService;
import presentation.OptionalStock.OptionalStock;
import presentation.repaintComponent.IntentPane;
import presentation.repaintComponent.MyComboBox;
import presentation.repaintComponent.MyScrollBarUI;
import presentation.repaintComponent.TextBubbleBorder;
import presentation.stockcheckui.PersonalStock;
import presentation.stockcheckui.StockList;

@SuppressWarnings("serial")
public class Marketui extends JPanel {

	JButton marketBtn;
	JButton shockListBtn;
	JButton optionalStockBtn;
	JButton closeBtn;
	JButton miniBtn;
	private JTextField searchTextField;
	private boolean click = false;

	String[][] data;

	private JScrollPane[] scrollPane;
	private JTable[] table;
	private JPanel[] panes;

	private StockMarketBLService stockMarketBL = new StockMarketBL();
	private MarketKLineBLService marketKLineBL = new MarketKLineBL();
	DefaultTableModel TableModel;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public Marketui(final JFrame frame) {
		setBorder(null);

		setLayout(null);
		final Marketui mpanel = this;

		ManageState state = marketKLineBL.update();
		if (state == ManageState.Fail) {
			// ����ʧ��
		}

		JScrollPane contentScroll = new JScrollPane();
		contentScroll.setBounds(224, 51, 730, 540);
		contentScroll.setOpaque(false);
		contentScroll.getViewport().setOpaque(false);
		contentScroll.setBorder(BorderFactory.createEmptyBorder());
		contentScroll.getVerticalScrollBar().setUI(new MyScrollBarUI());

		JPanel content = new JPanel();
		content.setOpaque(false);
		content.setPreferredSize(new Dimension(710, 900));
		content.setLayout(new FlowLayout(FlowLayout.LEFT, 14, 14));

		IntentPane intentPane1 = new IntentPane();
		intentPane1.setPreferredSize(new Dimension(700, 450));
		intentPane1.setLayout(null);

		//////
		// KLineChart kline = new KLineChart(data);
		// ChartPanel chartPanel = kline.getChartPane();
		// chartPanel.setSize(dim1);
		// intentPane1.add(chartPanel);
		// kline.setVisible(true);

		content.add(intentPane1);

		IntentPane intentPane2 = new IntentPane();
		intentPane2.setPreferredSize(new Dimension(700, 450));
		intentPane2.setLayout(null);
		content.add(intentPane2);

		marketBtn = new JButton("   ��������");
		marketBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		marketBtn.setForeground(new Color(248, 179, 29));
		marketBtn.setBounds(0, 68, 224, 44);
		marketBtn.setIcon(new ImageIcon("src/main/resources/image/line-enter.png"));
		marketBtn.setContentAreaFilled(false);
		marketBtn.setBorder(null);
		add(marketBtn);

		shockListBtn = new JButton("   ��������");
		shockListBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				shockListBtn.setForeground(new Color(248, 179, 29));
				shockListBtn.setIcon(new ImageIcon("src/main/resources/image/pie-enter.png"));
				shockListBtn.setContentAreaFilled(false);
				shockListBtn.setBorder(null);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				shockListBtn.setForeground(new Color(252, 241, 224));
				shockListBtn.setIcon(new ImageIcon("src/main/resources/image/pie.png"));
				shockListBtn.setContentAreaFilled(false);
				shockListBtn.setBorder(null);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.remove(mpanel);

				StockList listui = new StockList(frame);
				listui.setBounds(224, 0, getWidth() - 223, getHeight());
				frame.getContentPane().add(listui);

				PersonalStock ppanel = new PersonalStock(frame);
				ppanel.setBounds(0, 0, 225, getHeight());
				frame.getContentPane().add(ppanel);
				frame.repaint();
				frame.setVisible(true);
			}
		});
		shockListBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		shockListBtn.setIcon(new ImageIcon("src/main/resources/image/pie.png"));
		shockListBtn.setForeground(new Color(252, 241, 224));
		shockListBtn.setContentAreaFilled(false);
		shockListBtn.setBorder(null);
		shockListBtn.setBounds(0, 112, 224, 44);
		add(shockListBtn);

		optionalStockBtn = new JButton("   ��ҵ����");
		optionalStockBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				optionalStockBtn.setForeground(new Color(248, 179, 29));
				optionalStockBtn.setIcon(new ImageIcon("src/main/resources/image/rank-enter.png"));
				optionalStockBtn.setContentAreaFilled(false);
				optionalStockBtn.setBorder(null);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				optionalStockBtn.setForeground(new Color(252, 241, 224));
				optionalStockBtn.setIcon(new ImageIcon("src/main/resources/image/rank.png"));
				optionalStockBtn.setContentAreaFilled(false);
				optionalStockBtn.setBorder(null);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.remove(mpanel);
				OptionalStock opanel = new OptionalStock(frame);
				frame.getContentPane().add(opanel);
				opanel.setBounds(0, 0, getWidth(), getHeight());
				frame.repaint();
			}
		});
		optionalStockBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		optionalStockBtn.setForeground(new Color(252, 241, 224));
		optionalStockBtn.setIcon(new ImageIcon("src/main/resources/image/rank.png"));
		optionalStockBtn.setContentAreaFilled(false);
		optionalStockBtn.setBorder(null);
		optionalStockBtn.setBounds(0, 156, 224, 44);
		add(optionalStockBtn);

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
				closeBtn.setForeground(new Color(252, 241, 224));
			}
		});
		closeBtn.setForeground(new Color(216, 216, 216));
		closeBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		closeBtn.setContentAreaFilled(false);
		closeBtn.setBorder(null);
		closeBtn.setBounds(931, 15, 16, 16);
		add(closeBtn);

		miniBtn = new JButton("��");
		miniBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				miniBtn.setForeground(new Color(253, 188, 64));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				miniBtn.setForeground(new Color(252, 241, 224));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setExtendedState(frame.ICONIFIED); // ��С��
			}
		});
		miniBtn.setForeground(new Color(216, 216, 216));
		miniBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		miniBtn.setContentAreaFilled(false);
		miniBtn.setBorder(null);
		miniBtn.setBounds(904, 14, 16, 16);
		add(miniBtn);

		scrollPane = new JScrollPane[7];

		table = new JTable[7];

		// ʹ������
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.LEFT);
//		r.setOpaque(false);

		for (int i = 0; i < 7; i++) {
			table[i] = new JTable(){
				public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {    
			        int modelRow = convertRowIndexToModel(row);    
			        int modelColumn = convertColumnIndexToModel(column);    
			        Component comp = super.prepareRenderer(renderer, row, column);    
//			        if (!isRowSelected(modelRow)) {
			        		int temp = modelRow;
			        		double close = Double.parseDouble(this.getModel().getValueAt(temp++, 4).toString());
			                if (modelColumn==1||modelColumn == 2||modelColumn==3||modelColumn==4) {     
			                	if(Double.parseDouble(this.getModel().getValueAt(modelRow, modelColumn).toString())>close){
			                		comp.setForeground(new Color(179, 43, 56));
			                	}
			                	else if(Double.parseDouble(this.getModel().getValueAt(modelRow, modelColumn).toString()) == close){
			                		comp.setForeground(new Color(62, 56, 49, 240)); 
								}
			                	else {
									comp.setForeground(new Color(37, 120, 38));
								}
			                }
			                else                                                     //�����������ı���ԭ�����ʽ  
			                	comp.setForeground(new Color(62, 56, 49, 240)); 
//			        }  
			        return comp;  
			    }  
			};
			table[i].setRowHeight(26);
			table[i].setDefaultRenderer(Object.class, r);
			table[i].setSelectionBackground(new Color(62, 56, 49, 30));
			table[i].setSelectionForeground(new Color(62, 56, 49, 240));
			table[i].setIntercellSpacing(new Dimension(0, getHeight()));
//			table[i].setOpaque(false);
			table[i].setShowGrid(false);
			
			
			
			

			table[i].setEnabled(false);
			setSelect(table[i]);
			
			JTableHeader header = table[i].getTableHeader();
			header.setOpaque(false);
			header.getTable().setOpaque(false);
			header.getTable().setIntercellSpacing(new Dimension(0, getHeight()));

			scrollPane[i] = new JScrollPane();
			scrollPane[i].setBounds(247, 110, 680, 390);
			scrollPane[i].add(table[i]);
			scrollPane[i].setViewportView(table[i]);
			scrollPane[i].setColumnHeaderView(table[i].getTableHeader());
			scrollPane[i].getColumnHeader().setOpaque(false);
			scrollPane[i].getVerticalScrollBar().setUI(new MyScrollBarUI());
			scrollPane[i].getVerticalScrollBar().setUnitIncrement(20);
			scrollPane[i].setBorder(BorderFactory.createEmptyBorder());
		}

		// K��ͼ
		JTabbedPane KLinePane = new JTabbedPane();
		KLinePane.setBounds(7, 50, 690, 400);

		String kLineTitle[] = { "ʱ��", "��K", "��K", "��K" };

		panes = new JPanel[4];
		for (int i = 0; i < 4; i++) {
			panes[i] = new JPanel();
			KLinePane.add(kLineTitle[i], panes[i]);
		}

		KLinePane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				StockMarketVO stockMarketVO;
				JTabbedPane tab = (JTabbedPane) e.getSource();
				int selectedIndex = tab.getSelectedIndex();
				marketKline_enum[] marketK = marketKline_enum.values();

				// ʱ����ʱûʵ��
				if (selectedIndex == 0) {
					JLabel label = new JLabel("�����ڴ�");
					label.setSize(660, 350);
					panes[selectedIndex].add(label);
				} else {
					stockMarketVO = marketKLineBL.getData(marketK[selectedIndex]);
					data = stockMarketVO.getData();

					KLineChart kline = new KLineChart(data, selectedIndex);
					ChartPanel chartPanel = kline.getChartPane();
					chartPanel.setPreferredSize(new Dimension(660, 350));
					panes[selectedIndex].add(chartPanel);
				}
			}
		});
		intentPane1.add(KLinePane);

		// ���
		JTabbedPane marketPane = new JTabbedPane();
		marketPane.setBounds(7, 50, 680, 370);

		String title[] = { "����", "һ��", "һ����", "����", "һ��", "����", "ʮ��" };

		for (int i = 0; i < 7; i++) {
			marketPane.add(title[i], scrollPane[i]);
		}

		marketPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				StockMarketVO stockMarketVO;
				JTabbedPane tab = (JTabbedPane) e.getSource();
				int selectedIndex = tab.getSelectedIndex();
				date_enum[] date = date_enum.values();
				stockMarketVO = stockMarketBL.getStockMarket("hs300", date[selectedIndex]);
				data = stockMarketVO.getData();
				TableModel = new DefaultTableModel(data, new String[] { "����", "���̼�", "��߼�", "��ͼ�", "���̼�", "�ɽ������ɣ�" });
				table[selectedIndex].setModel(TableModel);
			}
		});
		intentPane2.add(marketPane);

		StockMarketVO stockMarketVO;
		stockMarketVO = stockMarketBL.getStockMarket("hs300", date_enum.Day);
		data = stockMarketVO.getData();
		TableModel = new DefaultTableModel(data, new String[] { "����", "���̼�", "��߼�", "��ͼ�", "���̼�", "�ɽ������ɣ�" });
		table[0].setModel(TableModel);

		final MyComboBox nameBox = new MyComboBox();
		nameBox.setFont(new Font("Lantinghei TC", Font.PLAIN, 22));
		nameBox.setBounds(10, 5, 161, 32);
		nameBox.addItem("����300ָ��");
		nameBox.addItem("��ָ֤��");
		nameBox.addItem("��ָ֤��");
		nameBox.setSelectedIndex(0);
		nameBox.setOpaque(false);
		nameBox.setBorder(null);
		intentPane1.add(nameBox);

		// ���scrollPane
		content.add(intentPane1);
		content.add(intentPane2);
		contentScroll.setViewportView(content);
		contentScroll.getVerticalScrollBar().setUnitIncrement(20);
		add(contentScroll);

		JButton searchBtn = new JButton();
		searchBtn.setBounds(854, 15, 18, 18);
		searchBtn.setContentAreaFilled(false);
		searchBtn.setBorderPainted(false);
		searchBtn.setBorder(null);
		searchBtn.setIcon(new ImageIcon("src/main/resources/image/search.png"));
		searchBtn.setMargin(new Insets(0, 0, 0, 0));
		add(searchBtn);

		searchTextField = new JTextField();
		searchTextField.setFocusable(false);
		searchTextField.setOpaque(false);
		searchTextField.setForeground(new Color(150, 150, 150));
		searchTextField.setCaretColor(new Color(150, 150, 150));
		searchTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
		searchTextField.setText("�����Ʊ�������������");
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
				searchTextField.setText("");
				click = true;
				searchTextField.setBorder(new TextBubbleBorder(new Color(150, 150, 150), 1, 30, 0));
				searchTextField.setFocusable(true);
				searchTextField.requestFocus();
			}
		});
		searchTextField.setBounds(686, 11, 196, 27);
		add(searchTextField);
		searchTextField.setColumns(10);

		// ��������ط�ʹtext field��������
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchTextField.setFocusable(false);
				searchTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
				searchTextField.setText("�����Ʊ�������������");
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

		setDragable(frame);
	}

	// �߿�Բ��
	protected void paintComponent(Graphics g) {
		ImageIcon image = new ImageIcon("src/main/resources/image/background.png");
		g.drawImage(image.getImage(), 0, 0, getSize().width - 1, getSize().height - 1, this);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	// ���ý�����϶�
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;

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

	// ���ñ���ѡ��
	public void setSelect(JTable table) {
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int rowpos = -1;
				Point mousepoint;
				mousepoint = e.getPoint();
				rowpos = table.rowAtPoint(mousepoint);
				table.setRowSelectionInterval(rowpos, rowpos);
			}
		});
	}
}

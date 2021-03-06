package presentation.stockcheckui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import businesslogic.factory.InitFactory;
import businesslogicservice.stockcheckblservice.StockListBLService;
import presentation.repaintComponent.HeaderCellRenderer;
import presentation.repaintComponent.IntentPane;
import presentation.repaintComponent.MyScrollBarUI;
import presentation.repaintComponent.MyTableCellRenderer;
import presentation.repaintComponent.TextBubbleBorder;
import presentation.stockcheckui.stockdetailui.StockDetail;
import presentation.stockmarketui.SearchBar;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class StockList extends JPanel {
	JButton closeBtn;
	JButton miniBtn;
	private JTextField searchTextField;
	private boolean click = false;
	private JTable table;
	DefaultTableModel tableModel;
	private int rowpos = -1;
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;

	InitFactory factory = InitFactory.getFactory();
	StockListBLService stocklistbl = factory.getStockListBL();
	private SearchBar searchBar;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "static-access" })
	public StockList(final JFrame frame) {
		setBorder(null);

		setLayout(null);
		final StockList listui = this;

		searchBar = new SearchBar(frame, listui);
		listui.add(searchBar);
		searchBar.setVisible(false);

		IntentPane intentPane = new IntentPane();
		intentPane.setBounds(13, 63, 707, 522);
		intentPane.setLayout(null);
		add(intentPane);

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

		JScrollPane listPane = new JScrollPane();
		listPane.setBounds(0, 42, 707, 500);
		listPane.setOpaque(false);
		listPane.setBorder(BorderFactory.createEmptyBorder());
		listPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		listPane.getViewport().setOpaque(false);
		intentPane.add(listPane);

		table = new JTable() {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				int modelRow = convertRowIndexToModel(row);
				int modelColumn = convertColumnIndexToModel(column);
				Component comp = super.prepareRenderer(renderer, row, column);
				// if (!isRowSelected(modelRow)) {
				int temp = modelRow;
				double close = Double.parseDouble(this.getModel().getValueAt(temp, 2).toString());
				if (modelColumn == 2 || modelColumn == 3 || modelColumn == 4 || modelColumn == 5) {
					if (Double.parseDouble(this.getModel().getValueAt(modelRow, modelColumn).toString()) > close) {
						comp.setForeground(new Color(179, 43, 56));
					} else if (Double
							.parseDouble(this.getModel().getValueAt(modelRow, modelColumn).toString()) == close) {
						comp.setForeground(new Color(62, 56, 49, 240));
					} else {
						comp.setForeground(new Color(37, 120, 38));
					}
				} else if (modelColumn == 7) {
					String upAndDown = this.getModel().getValueAt(modelRow, 7).toString();
					if (upAndDown.charAt(0) == '-') {
						comp.setForeground(new Color(37, 120, 38));
					} else if (upAndDown.charAt(0) != '-') {
						comp.setForeground(new Color(179, 43, 56));
						if (Double.parseDouble(upAndDown.substring(0, upAndDown.length() - 1)) == 0) {
							comp.setForeground(new Color(62, 56, 49, 240));
						}
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
		table.getTableHeader().setReorderingAllowed(false); // 设置不可整列移动

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
		listPane.setViewportView(table);
		table.setBorder(null);
		table.setEnabled(false);

		String[][] data = stocklistbl.getStockList();

		tableModel = new DefaultTableModel(data,
				new String[] { "股票代码", "股票名称", "开盘价", "最高价", "最低价", "收盘价", "交易量（股）", "涨跌幅" });
		table.setModel(tableModel);

		// 表格双击
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					String id = table.getValueAt(rowpos, 0).toString();
					listToDetail(frame, listui, id);
				}
			}
		});

		JTableHeader header = table.getTableHeader();
		header.setOpaque(false);
		header.getTable().setOpaque(false);
		header.getTable().setIntercellSpacing(new Dimension(0, getHeight()));
		header.setDefaultRenderer(new HeaderCellRenderer());

		setDragable(frame);

		JButton searchBtn = new JButton();
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchBar.setVisible(false);

				String id = searchBar.getID();
				if (!id.equals("")) {
					listToDetail(frame, listui, id);
				}
			}
		});
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
		searchTextField.setText("输入股票代码搜索");
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					String id = searchBar.getID();
					if (!id.equals("")) {
						listToDetail(frame, listui, id);
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				String key = searchTextField.getText();
				showTable(key);

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
		searchTextField.setFocusable(false);
		searchTextField.setOpaque(false);
		searchTextField.setForeground(new Color(150, 150, 150));
		searchTextField.setCaretColor(new Color(150, 150, 150));
		searchTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
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
		searchTextField.setBounds(462, 11, 196, 27);
		add(searchTextField);
		searchTextField.setColumns(10);

		JLabel namelbl = new JLabel("股票列表");
		namelbl.setBackground(new Color(245, 245, 245));
		namelbl.setForeground(new Color(95, 99, 108));
		namelbl.setBounds(10, 4, 151, 32);
		namelbl.setFont(new Font("Lantinghei TC", Font.PLAIN, 22));
		intentPane.add(namelbl);

		// 点击其他地方使text field不能输入
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchBar.setVisible(false);
				searchTextField.setFocusable(false);
				searchTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
				searchTextField.setText("输入股票代码搜索");
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

	public void showTable(String key) {
		String[][] data = stocklistbl.updateStockList(key);
		tableModel = new DefaultTableModel(data,
				new String[] { "股票代码", "股票名称", "开盘价", "最高价", "最低价", "收盘价", "交易量（股）", "涨跌幅" });
		table.setModel(tableModel);
		repaint();
	}

	// 边框圆滑
	protected void paintComponent(Graphics g) {
		ImageIcon image = new ImageIcon("src/main/resources/image/right.png");
		g.drawImage(image.getImage(), 0, 0, getSize().width - 1, getSize().height - 1, this);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	}

	// 设置界面可拖动
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

	public void listToDetail(JFrame frame, JPanel listui, String id) {
		searchBar.setVisible(false);
		frame.remove(listui);
		StockDetail detail = new StockDetail(frame, id);
		frame.getContentPane().add(detail);
		detail.setBounds(224, 0, 737, getHeight());
		frame.repaint();
		frame.validate();
	}
}

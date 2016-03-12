package presentation.stockcheckui;

import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import presentation.OptionalStock.OptionalStock;
import presentation.stockmarketui.Marketui;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PersonalStock extends JPanel {

	JButton marketBtn;
	JButton stockListBtn;
	JButton optionStockBtn;
	DefaultTableModel tableModel;

	/**
	 * Create the panel.
	 */
	public PersonalStock(final JFrame frame) {
		setBorder(null);

		setLayout(null);

		marketBtn = new JButton("\u5927\u76D8\u6570\u636E");
		marketBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				marketBtn.setForeground(new Color(248, 179, 29));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				marketBtn.setForeground(new Color(216, 216, 216));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				Marketui mpanel = new Marketui(frame);
				mpanel.setBounds(0, 0, 960, getHeight());
				frame.getContentPane().add(mpanel);
				frame.repaint();
				frame.setVisible(true);
			}
		});
		marketBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		marketBtn.setForeground(new Color(216, 216, 216));
		marketBtn.setBounds(68, 68, 117, 44);
		marketBtn.setContentAreaFilled(false);
		marketBtn.setOpaque(false);
		marketBtn.setBorder(null);
		add(marketBtn);

		stockListBtn = new JButton("\u4E2A\u80A1\u6570\u636E");
		stockListBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		stockListBtn.setForeground(new Color(248, 179, 29));
		stockListBtn.setContentAreaFilled(false);
		stockListBtn.setBorder(null);
		stockListBtn.setBounds(68, 112, 117, 44);
		add(stockListBtn);

		optionStockBtn = new JButton("\u81EA\u9009\u80A1\u5206\u6790");
		optionStockBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				optionStockBtn.setForeground(new Color(248, 179, 29));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				optionStockBtn.setForeground(new Color(216, 216, 216));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				OptionalStock opanel = new OptionalStock(frame);
				frame.getContentPane().add(opanel);
				opanel.setBounds(0, 0, 960, 600);
				frame.repaint();
			}
		});
		optionStockBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		optionStockBtn.setForeground(new Color(216, 216, 216));
		optionStockBtn.setContentAreaFilled(false);
		optionStockBtn.setBorder(null);
		optionStockBtn.setBounds(75, 156, 117, 44);
		add(optionStockBtn);
		// ʹ�������
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		tableModel = new DefaultTableModel(
				new Object[][] { { "", "", "", "", "", "", "" }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, },
				new String[] { "��Ʊ����", "���̼�", "��߼�", "���̼�", "��ͼ�", "���������ɣ�", "���׽�Ԫ��" });

		setDragable(frame);

	}

	// �߿�Բ��
	protected void paintComponent(Graphics g) {
		ImageIcon image = new ImageIcon("image/left.png");
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
}
package Models;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Component;

public class CashierViewWindow {

	private JFrame frame;
	private final int buttonSize = 22;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CashierViewWindow window = new CashierViewWindow();

					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CashierViewWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("��������� ������");
		frame.setBounds(100, 100, 581, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 2, 0, 0));
		JButton[][] arrayOfButtons = new JButton[2][5];

		JButton sellingButton = new JButton("�������");
		sellingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		arrayOfButtons[0][0] = sellingButton;

		JButton payInButton = new JButton("������");
		arrayOfButtons[0][1] = payInButton;
		
		JButton payOffButton = new JButton("�������");
		arrayOfButtons[0][2] = payOffButton;

		JButton endSessionButton = new JButton("��������� �����");
		arrayOfButtons[0][3] = endSessionButton;

		JButton myShopButton = new JButton("��� �������");
		arrayOfButtons[0][4] = myShopButton;

		JButton retrieveGoodsButton = new JButton("������� ������");
		arrayOfButtons[1][0] = retrieveGoodsButton;

		JButton acceptDeliveryButton = new JButton("������� ��������");
		arrayOfButtons[1][1] = acceptDeliveryButton;

		JButton deleteGoodsButton = new JButton("������� �����");
		arrayOfButtons[1][2] = deleteGoodsButton;

		JButton printDeclarationButton = new JButton("���������� �����");
		arrayOfButtons[1][3] = printDeclarationButton;

		JButton exitButton = new JButton("�����");
		arrayOfButtons[1][4] = exitButton;

		setFocusEvent(arrayOfButtons);

		panel.add(sellingButton);
		panel.add(retrieveGoodsButton);

		panel.add(payInButton);
		panel.add(acceptDeliveryButton);

		panel.add(payOffButton);
		panel.add(deleteGoodsButton);

		panel.add(endSessionButton);
		panel.add(printDeclarationButton);

		panel.add(myShopButton);
		panel.add(exitButton);
	}

	/*
	 * ����� ��� ������������ ������ � ������� ������� � ��� �� ��� ��������� �����
	 * ������ �� ����� ����� ���� ������ �������, ��� �� ���������� ������� ��
	 * ENTER. ���������� ���� ������ ������ 2 �� 4.
	 * 
	 * P.S. ��, ����� ���� ����� ����� �������� �.�. ��� ���������� ������ �����
	 * ������ � ���� ��� ����� ���, � ����� ������ �� �������� ������ ���������
	 * ��������� ������.
	 * 
	 * P.S.S. ���� ������ ���� � ��� �����, ����� ��� � �� �����)
	 */
	private void setFocusEvent(JButton[][] buttons) {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				final int curColumn = i;
				final int curRow = j;

				// ��������� ����������� ������� � ����������� �� buttonSize
				buttons[i][j].setFont(new Font("Tahoma", Font.PLAIN, buttonSize));

				// ���������� ��������� ��� ������� Enter
				buttons[i][j].addKeyListener(enter);

				// ���������� ���� ������ ���������� ��� ������
				buttons[i][j].addFocusListener(new FocusListener() {

					@Override
					public void focusGained(FocusEvent event) {

						buttons[curColumn][curRow].setBackground(SystemColor.activeCaption);

					}

					@Override
					public void focusLost(FocusEvent event) {
						buttons[curColumn][curRow].setBackground(null);

					}

				});

				// ���������� ������������ � ������� �������
				buttons[i][j].addKeyListener(new KeyAdapter() {
					
					@Override
					public void keyPressed(KeyEvent e) {
						switch (e.getKeyCode()) {
						case KeyEvent.VK_RIGHT:
							if ((curColumn + 1) >= buttons.length) {
								buttons[0][curRow].requestFocus();
							} else {
								buttons[curColumn + 1][curRow].requestFocus();
							}

							break;

						case KeyEvent.VK_LEFT:
							if ((curColumn - 1) < 0) {
								buttons[buttons.length - 1][curRow].requestFocus();
							} else {
								buttons[curColumn - 1][curRow].requestFocus();
							}

							break;

						case KeyEvent.VK_DOWN:
							if ((curRow + 1) >= buttons[curColumn].length) {
								buttons[curColumn][0].requestFocus();
							} else {
								buttons[curColumn][curRow + 1].requestFocus();
							}

							break;

						case KeyEvent.VK_UP:
							if ((curRow - 1) < 0) {
								buttons[curColumn][buttons[curColumn].length - 1].requestFocus();
							} else {
								buttons[curColumn][curRow - 1].requestFocus();
							}
							break;
						}
					}
				});

			}
		}

	}

	// KeyListener ����������� �� ENTER
	private KeyListener enter = new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				((JButton) e.getComponent()).doClick();
			}
		}
	};

}

import java.util.ArrayList;
import java.util.Random;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * 練習クラス
 */
class TestDrow extends JFrame {

	public JFrame frame;
	public JPanel panel;
	public ArrayList<Bomb> bombs = new ArrayList<Bomb>();

	public TestDrow() {

		//初期化
		initFrame();

		//初期化
		initPanel();

		//適当に生成
		for (int j = 0; j < 9; j++)
			for (int i = 0; i < 9; i++)
				bombs.add(new Bomb(i * 50, j * 50, this.panel));

		frame.invalidate();
		frame.validate();

	}

	//初期化
	private void initFrame() {

		this.frame = new JFrame();
		this.frame.setBounds(Const.FRAME_X, Const.FRAME_Y, 0, 0);
		this.frame.getContentPane().setPreferredSize(new Dimension(Const.FRAME_W, Const.FRAME_H));
		this.frame.pack();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}

	//初期化
	private void initPanel() {
		this.panel = new JPanel();
		this.panel.setBounds(Const.FRAME_X, Const.FRAME_Y, Const.FRAME_W, Const.FRAME_H);
		this.panel.setBackground(new Color(99, 169, 99));
		this.panel.setLayout(null);
		this.frame.add(this.panel);
	}
}

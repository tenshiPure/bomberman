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

	public ArrayList<Bomb> bombs = new ArrayList<Bomb>();

	public TestDrow() {

		//初期化
		initFrame();

		//初期化
		initPanel();

		//適当に生成
		for (int j = 0; j < 9; j++)
			for (int i = 0; i < 9; i++)
				bombs.add(new Bomb(i * 50, j * 50));
	}

	//初期化
	private void initFrame() {

		Main.frame = new JFrame();
		Main.frame.setBounds(Const.FRAME_X, Const.FRAME_Y, 0, 0);
		Main.frame.getContentPane().setPreferredSize(new Dimension(Const.FRAME_W, Const.FRAME_H));
		Main.frame.pack();
		Main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main.frame.setVisible(true);
	}

	//初期化
	private void initPanel() {
		Main.panel = new JPanel();
		Main.panel.setBounds(Const.FRAME_X, Const.FRAME_Y, Const.FRAME_W, Const.FRAME_H);
		Main.panel.setBackground(new Color(99, 169, 99));
		Main.panel.setLayout(null);
		Main.frame.add(Main.panel);
	}
}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Prac
{
	private JFrame frame;
	private JPanel panel;

	/*
	 * コンストラクタ
	 */
	public Prac(String str)
	{
		initFrame(500, 500, 500, 500);

		initPanel();

		//JButton button = createButton("push here");
		
		JLabel label = createLabel("look here");
	}

	/*
	 * フレームの生成と初期設定
	 */
	private void initFrame(int x, int y, int w, int h)
	{
		this.frame = new JFrame();

		//画面サイズを設定する (x, y, width, height)
		this.frame.setBounds(x, y, w, h);

		//画面を閉じたときにプロセスも終了する
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//画面表示の正否
		this.frame.setVisible(true);
		
		this.frame.addKeyListener(new keyReceiver());
	}

	/*
	 * パネルの生成と初期設定
	 */
	private void initPanel()
	{
		this.panel = new JPanel();
		this.frame.add(this.panel);
	}

	/*
	 * ボタンの生成と初期設定
	 */
	private JButton createButton(String text)
	{
		JButton button = new JButton(text);
		this.panel.add(button);

		return button;
	}

	/*
	 * ラベルの生成と初期設定
	 */
	private JLabel createLabel(String text)
	{
		JLabel label = new JLabel(text);
		this.panel.add(label);

		return label;
	}
}

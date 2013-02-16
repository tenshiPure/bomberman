import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class FieldController implements KeyListener
{
	private JFrame frame;
	private JPanel panel;
	private JLabel label;

	private int keyCode;
	private String keyName;

	/*
	 * コンストラクタ
	 */
	public FieldController()
	{
		//フレームの生成と初期設定
		initFrame(500, 500, 500, 500);
		
		//パネルの生成と初期設定
		initPanel();
		
		//ラベルの生成と初期設定
		this.label = createLabel("look here");
	}
	
	/*
	 * フレームの生成と初期設定
	 */
	private void initFrame(int x, int y, int w, int h)
	{
		//フレームの生成
		this.frame = new JFrame();

		//画面サイズを設定する (x, y, width, height)
		this.frame.setBounds(x, y, w, h);

		//画面を閉じたときにプロセスも終了する
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//画面表示の正否
		this.frame.setVisible(true);
		
		//自身がキーイベントを受け取るので、thisを渡す
		this.frame.addKeyListener(this);
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
	 * ラベルの生成と初期設定
	 */
	private JLabel createLabel(String text)
	{
		JLabel label = new JLabel(text);
		this.panel.add(label);

		return label;
	}

	/*
	 * キーが押されたときに呼ばれるメソッド
	 */
	public void keyPressed(KeyEvent event)
	{
		this.keyCode = event.getKeyCode();
		this.keyName = event.getKeyText(this.keyCode);
		
		this.label.setText(this.keyCode + " : " + this.keyName);
	}

	/*
	 * キーが離されたときに呼ばれるメソッド
	 */
	public void keyReleased(KeyEvent event)
	{
	}

	/*
	 * キーがタイプされたときに呼ばれるメソッド
	 */
	public void keyTyped(KeyEvent event)
	{
	}
}

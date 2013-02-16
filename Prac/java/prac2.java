import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class prac2 implements KeyListener
{
	private int keyCode;
	private String keyName;

	/*
	 * コンストラクタ
	 */
	public prac2()
	{
		JFrame frame = new JFrame();

		//画面サイズを設定する (x, y, width, height)
		frame.setBounds(500, 500, 500, 500);

		//画面を閉じたときにプロセスも終了する
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//画面表示の正否
		frame.setVisible(true);
		
		//イベント受取の設定
		frame.addKeyListener(this);
	}

	/*
	 * キーが押されたときに呼ばれるメソッド
	 */
	public void keyPressed(KeyEvent event)
	{
		this.keyCode = event.getKeyCode();
		this.keyName = event.getKeyText(this.keyCode);

		System.out.println(this.keyCode + " : " + this.keyName);
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



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * ボンバーマンクラス
 */
class Bomberman implements KeyListener
{
	private int x;
	private int y;
	private Field field;

	/*
	 * コンストラクタ
	 */
	public Bomberman(int x, int y, Field field)
	{
		this.x = x;
		this.y = y;
		this.field = field;
	}

	/*
	 * キーが押されたときに呼ばれるメソッド
	 */
	public void keyPressed(KeyEvent event)
	{
		System.out.println("keyCode : " + event.getKeyCode());
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

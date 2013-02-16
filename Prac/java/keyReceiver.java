import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class keyReceiver implements KeyListener
{
	private int keyCode;
	private String keyName;

	/*
	 * コンストラクタ
	 */
	public keyReceiver()
	{
		//System.out.println("construct");
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

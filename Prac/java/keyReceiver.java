import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class keyReceiver implements KeyListener
{
	private int keyCode;
	private String keyName;

	/*
	 * �R���X�g���N�^
	 */
	public keyReceiver()
	{
		//System.out.println("construct");
	}

	/*
	 * �L�[�������ꂽ�Ƃ��ɌĂ΂�郁�\�b�h
	 */
	public void keyPressed(KeyEvent event)
	{
		this.keyCode = event.getKeyCode();
		this.keyName = event.getKeyText(this.keyCode);

		System.out.println(this.keyCode + " : " + this.keyName);
	}


	/*
	 * �L�[�������ꂽ�Ƃ��ɌĂ΂�郁�\�b�h
	 */
	public void keyReleased(KeyEvent event)
	{
	}


	/*
	 * �L�[���^�C�v���ꂽ�Ƃ��ɌĂ΂�郁�\�b�h
	 */
	public void keyTyped(KeyEvent event)
	{
	}
}

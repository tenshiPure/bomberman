import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class prac2 implements KeyListener
{
	private int keyCode;
	private String keyName;

	/*
	 * �R���X�g���N�^
	 */
	public prac2()
	{
		JFrame frame = new JFrame();

		//��ʃT�C�Y��ݒ肷�� (x, y, width, height)
		frame.setBounds(500, 500, 500, 500);

		//��ʂ�����Ƃ��Ƀv���Z�X���I������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//��ʕ\���̐���
		frame.setVisible(true);
		
		//�C�x���g���̐ݒ�
		frame.addKeyListener(this);
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


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Prac
{
	private JFrame frame;
	private JPanel panel;

	/*
	 * �R���X�g���N�^
	 */
	public Prac(String str)
	{
		initFrame(500, 500, 500, 500);

		initPanel();

		//JButton button = createButton("push here");
		
		JLabel label = createLabel("look here");
	}

	/*
	 * �t���[���̐����Ə����ݒ�
	 */
	private void initFrame(int x, int y, int w, int h)
	{
		this.frame = new JFrame();

		//��ʃT�C�Y��ݒ肷�� (x, y, width, height)
		this.frame.setBounds(x, y, w, h);

		//��ʂ�����Ƃ��Ƀv���Z�X���I������
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//��ʕ\���̐���
		this.frame.setVisible(true);
		
		this.frame.addKeyListener(new keyReceiver());
	}

	/*
	 * �p�l���̐����Ə����ݒ�
	 */
	private void initPanel()
	{
		this.panel = new JPanel();
		this.frame.add(this.panel);
	}

	/*
	 * �{�^���̐����Ə����ݒ�
	 */
	private JButton createButton(String text)
	{
		JButton button = new JButton(text);
		this.panel.add(button);

		return button;
	}

	/*
	 * ���x���̐����Ə����ݒ�
	 */
	private JLabel createLabel(String text)
	{
		JLabel label = new JLabel(text);
		this.panel.add(label);

		return label;
	}
}

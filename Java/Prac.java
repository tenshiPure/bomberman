import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Point;

/*
 * 練習クラス
 */
class Prac extends JFrame
{
	public Prac()
	{
		Point point = new Point(3, 5);

		System.out.println("x : " + point.x + " , y : " + point.y);
	}
}

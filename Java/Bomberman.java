import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * ボンバーマンクラス
 */
class Bomberman implements KeyListener
{
	//位置
	private Rectangle rect;

	//描画に使用する画像
	private ImageIcon icon = new ImageIcon("../Image/Bomberman.gif");

	//描画に使用するラベル
	private JLabel label = new JLabel(icon);

	//描画に使用するパネル
	private JPanel panel;

	//フィールドへの参照
	private Field field;

	/*
	 * コンストラクタ
	 */
	public Bomberman(int x, int y, Field field, JPanel panel)
	{
		this.rect = new Rectangle(x * 50, y * 50, Constant.OBJ_SIZE, Constant.OBJ_SIZE);

		this.field = field;
		this.panel = panel;

		//ラベルの表示位置
		this.label.setBounds(this.rect);

		//パネルに追加
		this.panel.add(this.label);
	}

	/*
	 * キーが押されたときに呼ばれるメソッド
	 */
	public void keyPressed(KeyEvent event)
	{
		move(event.getKeyCode());
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

	/*
	 * ボンバーマンのマス目を更新する
	 */
	private void move(int keyCode)
	{
		int new_x = this.rect.x;
		int new_y = this.rect.y;

		if (keyCode == Constant.KEY_L)
		{
			new_x -= Constant.OBJ_SIZE;
		}

		else if (keyCode == Constant.KEY_D)
		{
			new_y += Constant.OBJ_SIZE;
		}

		else if (keyCode == Constant.KEY_U)
		{
			new_y -= Constant.OBJ_SIZE;
		}

		else if (keyCode == Constant.KEY_R)
		{
			new_x += Constant.OBJ_SIZE;
		}

		else
		{
			return;
		}

		Rectangle destination_rect = new Rectangle(new_x, new_y, Constant.OBJ_SIZE, Constant.OBJ_SIZE);

		//移動可能な場合、rect の差し替え
		if (this.field.isMovable(destination_rect))
			this.rect = destination_rect;

		//ラベルの表示位置
		this.label.setBounds(this.rect);

		return;
	}
}

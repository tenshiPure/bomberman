import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * ボンバーマンクラス
 */
class Bomberman implements KeyListener
{
	//位置マス目
	private int x;
	private int y;

	//描画に使用する画像
	private ImageIcon icon;

	//描画に使用するラベル
	private JLabel label;

	//描画に使用するパネル
	private JPanel panel;

	//フィールドへの参照
	private Field field;

	/*
	 * コンストラクタ
	 */
	public Bomberman(int x, int y, Field field, JPanel panel)
	{
		this.x = x;
		this.y = y;
		this.icon = new ImageIcon("../Image/Bomberman.gif");
		this.label = new JLabel(icon);

		this.field = field;
		this.panel = panel;

		//ラベルの表示位置
		this.label.setBounds(Mapper.sToP(1), Mapper.sToP(1), Constant.OBJ_SIZE, Constant.OBJ_SIZE);

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
		if (keyCode == Constant.KEY_L)
		{
			if (isMovable(this.x - 1, this.y))
				this.x -= 1;
		}
		else if (keyCode == Constant.KEY_D)
		{
			if (isMovable(this.x, this.y + 1))
				this.y += 1;
		}
		else if (keyCode == Constant.KEY_U)
		{
			if (isMovable(this.x, this.y - 1))
				this.y -= 1;
		}
		else if (keyCode == Constant.KEY_R)
		{
			if (isMovable(this.x + 1, this.y))
				this.x += 1;
		}

		//ラベルの表示位置
		this.label.setBounds(Mapper.sToP(x), Mapper.sToP(y), Constant.OBJ_SIZE, Constant.OBJ_SIZE);
	}

	/*
	 * 移動の辺り判定を返却する
	 */
	private boolean isMovable(int x, int y)
	{
		//あるマスのオブジェクトを調べる
		String type = this.field.getFieldObjectType(x, y);

		//空白の場合のみ、移動可能
		if (type == "Space")
			return true;

		return false;
	}
}

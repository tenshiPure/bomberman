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
	private int i;
	private int j;

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
	public Bomberman(int i, int j, Field field, JPanel panel)
	{
		this.i = i;
		this.j = j;
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
			if (isMovable(this.i - 1, this.j))
				this.i -= 1;
		}
		else if (keyCode == Constant.KEY_D)
		{
			if (isMovable(this.i, this.j + 1))
				this.j += 1;
		}
		else if (keyCode == Constant.KEY_U)
		{
			if (isMovable(this.i, this.j - 1))
				this.j -= 1;
		}
		else if (keyCode == Constant.KEY_R)
		{
			if (isMovable(this.i + 1, this.j))
				this.i += 1;
		}

		//ラベルの表示位置
		this.label.setBounds(Mapper.sToP(i), Mapper.sToP(j), Constant.OBJ_SIZE, Constant.OBJ_SIZE);
	}

	/*
	 * 移動の辺り判定を返却する
	 */
	private boolean isMovable(int i, int j)
	{
		//あるマスのオブジェクトを調べる
		String type = this.field.getFieldObjectType(i, j);

		//空白の場合のみ、移動可能
		if (type == "Space")
			return true;

		return false;
	}
}

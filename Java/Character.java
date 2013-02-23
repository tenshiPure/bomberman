import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * ボンバーマンクラス
 */
abstract class Character {

	//座標
	public Rectangle rect;

	//描画に使用するラベル
	private JLabel label;

	//描画に使用するパネル
	protected JPanel panel;

	//フィールドへの参照
	protected Field field;

	/*
	 * コンストラクタ
	 */
	public Character(int x, int y, String type, Field field, JPanel panel) {

		//座標設定
		this.rect = new Rectangle(x * Const.OBJ_SIZE, y * Const.OBJ_SIZE, Const.OBJ_SIZE, Const.OBJ_SIZE);

		//参照
		this.field = field;
		this.panel = panel;

		//ラベルを適当な画像で生成
		this.label = new JLabel(new ImageIcon("../Image/" + type + ".gif"));

		//ラベルの表示位置
		this.label.setBounds(this.rect);

		//パネルに追加
		this.panel.add(this.label);
	}

	/*
	 * キャラクタのマス目を更新する
	 */
	protected void move(int keyCode) {

		int new_x = this.rect.x;
		int new_y = this.rect.y;

		if (keyCode == KeyEvent.VK_H || keyCode == Const.VECTOR_L) {
			new_x -= Const.OBJ_SIZE;
		}

		else if (keyCode == KeyEvent.VK_J || keyCode == Const.VECTOR_D) {
			new_y += Const.OBJ_SIZE;
		}

		else if (keyCode == KeyEvent.VK_K || keyCode == Const.VECTOR_U) {
			new_y -= Const.OBJ_SIZE;
		}

		else if (keyCode == KeyEvent.VK_L || keyCode == Const.VECTOR_R) {
			new_x += Const.OBJ_SIZE;
		}

		else {
			return;
		}

		Rectangle destination_rect = new Rectangle(new_x, new_y, Const.OBJ_SIZE, Const.OBJ_SIZE);

		//移動可能な場合、rect の差し替え
		if (this.field.isMovable(destination_rect)) {
			this.rect = destination_rect;
		}

		//ラベルの表示位置
		this.label.setBounds(this.rect);

		return;
	}
}

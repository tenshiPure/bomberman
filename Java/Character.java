import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * 移動キャラクタクラス
 */
abstract class Character {

	//座標
	public Rectangle rect;

	//描画に使用するラベル
	public JLabel label;

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
	protected boolean move(int vector) {

		int new_x = this.rect.x;
		int new_y = this.rect.y;

		if (vector == Const.VECTOR_L) {
			new_x -= Const.OBJ_SIZE;
		}

		else if (vector == Const.VECTOR_D) {
			new_y += Const.OBJ_SIZE;
		}

		else if (vector == Const.VECTOR_U) {
			new_y -= Const.OBJ_SIZE;
		}

		else if (vector == Const.VECTOR_R) {
			new_x += Const.OBJ_SIZE;
		}

		else {
			return false;
		}

		//移動先を矩形で生成する
		Rectangle destination_rect = new Rectangle(new_x, new_y, Const.OBJ_SIZE, Const.OBJ_SIZE);

		//移動判定
		if (isMovable(destination_rect)) {

			//rect の差し替え
			this.rect = destination_rect;

			//ラベルの表示位置
			this.label.setBounds(this.rect);

			return true;
		}

		return false;
	}

	/*
	 * 移動のあたり判定を返却する
	 */
	private boolean isMovable(Rectangle destination_rect) {

		//全壁ループ
		for (int i = 0; i < this.field.walls.size(); i++) {
			//壁のrect と移動先のrect が交差するかをboolean で取得
			if (this.field.walls.get(i).rect.intersects(destination_rect))
				return false;
		}

		//全ボムループ
		for (int i = 0; i < this.field.bombs.size(); i++) {
			//ボムのrect と移動先のrect が交差するかをboolean で取得
			if (this.field.bombs.get(i).rect.intersects(destination_rect))
				return false;
		}

		//どの要素とも交差しなければ、移動可
		return true;
	}

	/*
	 * 移動キャラクタの生死を取得する
	 */
	abstract public boolean isAlive();
}

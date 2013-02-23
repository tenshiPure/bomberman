import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * ボンバーマンクラス
 */
class Bomberman implements KeyListener {

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
	public Bomberman(int x, int y, Field field, JPanel panel) {

		this.rect = new Rectangle(x * Const.OBJ_SIZE, y * Const.OBJ_SIZE, Const.OBJ_SIZE, Const.OBJ_SIZE);

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
	public void keyPressed(KeyEvent event) {
		move(event.getKeyCode());
	}

	/*
	 * キーが離されたときに呼ばれるメソッド
	 */
	public void keyReleased(KeyEvent event) {
	}

	/*
	 * キーがタイプされたときに呼ばれるメソッド
	 */
	public void keyTyped(KeyEvent event) {
	}

	/*
	 * ボンバーマンのマス目を更新する
	 */
	private void move(int keyCode) {

		int new_x = this.rect.x;
		int new_y = this.rect.y;

		if (keyCode == KeyEvent.VK_H) {
			new_x -= Const.OBJ_SIZE;
		}

		else if (keyCode == KeyEvent.VK_J) {
			new_y += Const.OBJ_SIZE;
		}

		else if (keyCode == KeyEvent.VK_K) {
			new_y -= Const.OBJ_SIZE;
		}

		else if (keyCode == KeyEvent.VK_L) {
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

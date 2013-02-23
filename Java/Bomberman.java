import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * ボンバーマンクラス
 */
class Bomberman extends Character implements KeyListener {

	/*
	 * コンストラクタ
	 */
	public Bomberman(int x, int y, Field field, JPanel panel) {

		super(x, y, "Bomberman", field, panel);
	}

	/*
	 * ボンバーマンの生死を取得する
	 */
	public boolean isAlive() {

		//全敵ループ
		for (int i = 0; i < this.field.enemies.size(); i++) {
			//敵のrect とボンバーマンのrect が交差するかをboolean で取得
			if (this.field.enemies.get(i).rect.intersects(this.rect))
				return false;
		}

		//どの敵とも交差しなければ、生きている
		return true;
	}

	/*
	 * キーが押されたときに呼ばれるメソッド
	 */
	public void keyPressed(KeyEvent event) {

		//ボンバーマンの移動
		super.move(event.getKeyCode());

		//生死判定
		if (!isAlive()) {
			//クロージング
			this.field.gameClose();
		}
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
}

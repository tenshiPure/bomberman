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
	public Bomberman(int x, int y, Field field) {

		super(x, y, "Bomberman", field);
	}

	/*
	 * ボムを足下に生成
	 */
	private void createBomb() {

		this.field.bombs.add(new Bomb(this.rect.x, this.rect.y));
	}

	/*
	 * @override
	 * ボンバーマンの生死を取得する
	 */
	public boolean isAlive() {

		//全敵ループ
		for (int i = 0; i < this.field.enemies.size(); i++) {
			//敵のrect とボンバーマンのrect が交差するかをboolean で取得
			if (this.field.enemies.get(i).rect.intersects(this.rect))
				return false;
		}

		//全炎ループ
		for (int i = 0; i < this.field.fires.size(); i++) {
			//炎のrect とボンバーマンのrect が交差するかをboolean で取得
			if (this.field.fires.get(i).rect.intersects(this.rect))
				return false;
		}

		//どのrect とも交差しなければ、生きている
		return true;
	}

	/*
	 * キーが押されたときに呼ばれるメソッド
	 */
	public void keyPressed(KeyEvent event) {

		if (event.getKeyCode() == KeyEvent.VK_H) {
			//左移動
			this.move(Const.VECTOR_L);
		}

		else if (event.getKeyCode() == KeyEvent.VK_J) {
			//下移動
			this.move(Const.VECTOR_D);
		}

		else if (event.getKeyCode() == KeyEvent.VK_K) {
			//上移動
			this.move(Const.VECTOR_U);
		}

		else if (event.getKeyCode() == KeyEvent.VK_L) {
			//右移動
			this.move(Const.VECTOR_R);
		}

		else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
			//ボムを足下に生成
			createBomb();
		}

		else {
			return;
		}

		//ボンバーマンの生死判定
		if (!isAlive()) {
			//クロージング
			this.field.gameClose("Game Over!");
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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * 敵クラス
 */
class Enemy extends Character {

	/*
	 * コンストラクタ
	 */
	public Enemy(int x, int y, Field field) {

		super(x, y, "Enemy", field);
	}

	/*
	 * @override
	 * 敵の生死を取得する
	 */
	public boolean isAlive() {

		//全炎ループ
		for (int i = 0; i < this.field.fires.size(); i++) {
			//炎のrect と敵のrect が交差するかをboolean で取得
			if (this.field.fires.get(i).rect.intersects(this.rect))
				return false;
		}

		//どのrect とも交差しなければ、生きている
		return true;
	}
}

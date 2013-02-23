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
	public Enemy(int x, int y, Field field, JPanel panel) {

		super(x, y, "Enemy", field, panel);
	}
}

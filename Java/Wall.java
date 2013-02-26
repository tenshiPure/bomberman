import java.awt.*;
import javax.swing.*;

/*
 * 壁
 */
class Wall {

	//座標
	public Rectangle rect;

	//ラベルを適当な画像で生成
	private JLabel label = new JLabel(new ImageIcon("../Image/Wall.gif"));

	/*
	 * コンストラクタ
	 */
	public Wall(int x, int y) {

		//座標設定
		this.rect = new Rectangle(x * Const.OBJ_SIZE, y * Const.OBJ_SIZE, Const.OBJ_SIZE, Const.OBJ_SIZE);

		//ラベルの表示位置
		this.label.setBounds(this.rect);

		//パネルに追加
		Main.panel.add(this.label);
	}
}

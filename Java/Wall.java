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

	//描画するパネル
	private JPanel panel;

	/*
	 * コンストラクタ
	 */
	public Wall(int x, int y, JPanel panel) {

		//座標設定
		this.rect = new Rectangle(x * Const.OBJ_SIZE, y * Const.OBJ_SIZE, Const.OBJ_SIZE, Const.OBJ_SIZE);

		//参照
		this.panel = panel;

		//ラベルの表示位置
		this.label.setBounds(this.rect);

		//パネルに追加
		this.panel.add(this.label);
	}
}

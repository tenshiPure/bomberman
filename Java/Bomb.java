import java.awt.*;
import javax.swing.*;

/*
 * ボム
 */
class Bomb {

	//座標
	public Rectangle rect;

	//ラベルを適当な画像で生成
	private JLabel label = new JLabel(new ImageIcon("../Image/Bomb.gif"));

	//描画するパネル
	private JPanel panel;

	/*
	 * コンストラクタ
	 */
	public Bomb(int x, int y, JPanel panel) {

		//座標設定
		this.rect = new Rectangle(x, y, Const.OBJ_SIZE, Const.OBJ_SIZE);

		//参照
		this.panel = panel;

		//ラベルの表示位置
		this.label.setBounds(this.rect);

		//パネルに追加
		this.panel.add(this.label);
	}
}


import java.awt.*;
import javax.swing.*;

/*
 * 壁
 */
class Wall {
	//位置マス目
	public Rectangle rect;

	//描画に使用する画像
	private ImageIcon icon = new ImageIcon("../Image/Wall.gif");

	//描画に使用するラベル
	private JLabel label = new JLabel(icon);

	//描画するパネル
	private JPanel panel;

	/*
	 * コンストラクタ
	 */
	public Wall(int x, int y, JPanel panel) {

		this.rect = new Rectangle(x * Const.OBJ_SIZE, y * Const.OBJ_SIZE, Const.OBJ_SIZE, Const.OBJ_SIZE);
		this.panel = panel;

		//ラベルの表示位置
		this.label.setBounds(this.rect);

		//パネルに追加
		this.panel.add(this.label);
	}
}

import java.awt.*;
import javax.swing.*;

/*
 * ボム
 */
class Bomb {

	//座標
	public Rectangle rect;

	//爆発までの残り時間
	public int remainingCount = 4;

	//ラベルを適当な画像で生成
	public JLabel label = new JLabel(new ImageIcon("../Image/Bomb.gif"));

	/*
	 * コンストラクタ
	 */
	public Bomb(Rectangle rect) {

		//座標設定
		this.rect = new Rectangle(rect);

		//ラベルの表示位置
		this.label.setBounds(this.rect);

		//パネルに追加
		Main.panel.add(this.label);
	}

	/*
	 * ボムの残り時間を更新
	 */
	public void countDown() {

		this.remainingCount -= 1;
	}
}

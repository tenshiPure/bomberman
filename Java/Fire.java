import java.awt.*;
import javax.swing.*;

/*
 * 炎
 */
class Fire {

	//座標
	public Rectangle rect;

	//消滅までの残り時間
	public int remainingCount = 2;

	//ラベルを適当な画像で生成
	public JLabel label = new JLabel(new ImageIcon("../Image/Fire.gif"));

	/*
	 * コンストラクタ
	 */
	public Fire(Rectangle rect) {

		//座標設定
		this.rect = rect;

		//ラベルの表示位置
		this.label.setBounds(this.rect);

		//パネルに追加
		Main.panel.add(this.label);
	}

	/*
	 * 炎の残り時間を更新
	 */
	public int countDown() {

		//750 ごとに1000 減る
		this.remainingCount -= 1;

		return this.remainingCount;
	}
}

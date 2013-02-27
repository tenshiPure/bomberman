import java.util.ArrayList;
import java.util.Random;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * タイマー
 */
class MyTimer implements ActionListener {

	//フィールドへの参照
	private Field field;

	/*
	 * コンストラクタ
	 */
	public MyTimer(Field field) {

		this.field = field;
	}

	/*
	 * タイマーの発信するアクションイベントを受け取るメソッド(ActionListener)
	 */
	public void actionPerformed(ActionEvent e) {

		//全敵を動かす
		moveAllEnemies();

		//炎に触れている敵を解放・消去して、生存している敵の数を得る
		int enemies_num = this.field.isAliveAllEnemies();

		//敵が全滅していればクロージング
		if (enemies_num == 0) {
			Main.gameClose("You Win!");
		}

		//ボムのカウントダウン
		countDownBombs();

		//ボムの爆発
		bombsExplosion();

		//炎のカウントダウン
		countDownFires();

		//Todo:隣人の隣人
		//ボンバーマンの生死判定
		if (!this.field.bomberman.isAlive()) {
			//クロージング
			Main.gameClose("Game Over!");
		}
	}

	/*
	 * 全敵を動かす
	 */
	private void moveAllEnemies() {

		Random rand = new Random();

		//全敵ループ
		for (int i = 0; i < this.field.enemies.size(); i++) {

			//ランダムに方向を決定する
			int vector = rand.nextInt(4);

			//移動
			if (vector == 0) {
				this.field.enemies.get(i).move(Const.VECTOR_L);
			}
			else if (vector == 1) {
				this.field.enemies.get(i).move(Const.VECTOR_D);
			}
			else if (vector == 2) {
				this.field.enemies.get(i).move(Const.VECTOR_U);
			}
			else if (vector == 3) {
				this.field.enemies.get(i).move(Const.VECTOR_R);
			}
		}
	}

	/*
	 * ボムのカウントダウン
	 */
	private void countDownBombs() {

		//全ボムループ
		for (int i = 0; i < this.field.bombs.size(); i++) {
			//個々の残り時間を更新
			this.field.bombs.get(i).countDown();
		}
	}

	/*
	 * ボムの爆発
	 */
	private void bombsExplosion() {

		//全ボムループ
		for (int i = 0; i < this.field.bombs.size(); i++) {

			//ボムの残り時間が無い場合
			if (this.field.bombs.get(i).remainingCount <= 0) {

				//炎生成
				this.field.createFires(this.field.bombs.get(i).rect);

				//ボムの解放
				this.field.releaseObject("bombs", i);
			}
		}
	}

	/*
	 * 炎のカウントダウン
	 */
	private void countDownFires() {

		//残り時間
		int remainingCount;

		//全炎ループ
		for (int i = 0; i < this.field.fires.size(); i++) {
			//個々の残り時間を更新
			remainingCount = this.field.fires.get(i).countDown();

			//残り時間が限界を迎えた場合
			if (remainingCount == 0) {
				//炎の解放
				this.field.releaseObject("fires", i);
			}
		}
	}
}

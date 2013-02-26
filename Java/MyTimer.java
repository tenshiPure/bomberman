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

		//ボムのカウントダウン
		countDownBombs();

		//炎のカウントダウン
		countDownFires();

		//Todo:隣人の隣人
		//ボンバーマンの生死判定
		if (!this.field.bomberman.isAlive()) {
			//クロージング
			Main.gameClose("Game Over!");
		}

		//全敵の生死判定と解放・消去
		if (isAliveAllEnemies() == 0) {
			//クロージング
			Main.gameClose("You Win!");
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
			this.field.enemies.get(i).move(vector);
		}
	}

	/*
	 * ボムのカウントダウン
	 */
	private void countDownBombs() {

		//残り時間
		int remainingCount;

		//全ボムループ
		for (int i = 0; i < this.field.bombs.size(); i++) {
			//個々の残り時間を更新
			remainingCount = this.field.bombs.get(i).countDown();

			//残り時間が限界を迎えた場合
			if (remainingCount == 0) {

				//炎生成
				createFires(this.field.bombs.get(i).rect);

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

	/*
	 * 炎生成
	 */
	private void createFires(Rectangle center) {

		//爆心地
		this.field.fires.add(new Fire(center.x, center.y));

		//調査を矩形で生成する
		Rectangle destination_rect = null;

		//左方向
		for (int i = 1; i <= 2; i++) {

			destination_rect = new Rectangle(center.x - (i * 50), center.y, Const.OBJ_SIZE, Const.OBJ_SIZE);

			if (!isWall(destination_rect)) {
				break;
			}

			//壁でなければ、炎生成
			this.field.fires.add(new Fire(center.x - (i * 50), center.y));
		}

		//下方向
		for (int i = 1; i <= 2; i++) {

			destination_rect = new Rectangle(center.x, center.y + (i * 50), Const.OBJ_SIZE, Const.OBJ_SIZE);

			if (!isWall(destination_rect)) {
				break;
			}

			//壁でなければ、炎生成
			this.field.fires.add(new Fire(center.x, center.y + (i * 50)));
		}

		//上方向
		for (int i = 1; i <= 2; i++) {

			destination_rect = new Rectangle(center.x, center.y + (i - 50), Const.OBJ_SIZE, Const.OBJ_SIZE);

			if (!isWall(destination_rect)) {
				break;
			}

			//壁でなければ、炎生成
			this.field.fires.add(new Fire(center.x, center.y - (i * 50)));
		}

		//右方向
		for (int i = 1; i <= 2; i++) {

			destination_rect = new Rectangle(center.x + (i * 50), center.y, Const.OBJ_SIZE, Const.OBJ_SIZE);

			if (!isWall(destination_rect)) {
				break;
			}

			//壁でなければ、炎生成
			this.field.fires.add(new Fire(center.x + (i * 50), center.y));
		}
	}

	/*
	 * 壁かどうかを判定する
	 */
	private boolean isWall(Rectangle destination_rect) {

		//全壁ループ
		for (int i = 0; i < this.field.walls.size(); i++) {
			//壁のrect と調査先のrect が交差するかをboolean で取得
			if (this.field.walls.get(i).rect.intersects(destination_rect))
				return false;
		}

		//どの壁とも交差しなければ、移動可
		return true;
	}

	/*
	 * 全敵の生死判定と解放・消去
	 */
	public int isAliveAllEnemies() {

		//全敵ループ
		for (int i = 0; i < this.field.enemies.size(); i++) {
			//敵の生死判定
			if (!this.field.enemies.get(i).isAlive()) {
				//死亡した敵の解放と消去
				this.field.releaseObject("enemies", i);
			}
		}

		return this.field.enemies.size();
	}
}

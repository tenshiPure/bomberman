import java.util.ArrayList;
import java.util.Random;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * 障害物を管理するフィールド
 */
class Field {

	//ボンバーマン
	public Bomberman bomberman;

	//壁のリスト
	public ArrayList<Wall> walls = new ArrayList<Wall>();

	//敵のリスト
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	//ボムのリスト
	public ArrayList<Bomb> bombs = new ArrayList<Bomb>();

	//炎のリスト
	public ArrayList<Fire> fires = new ArrayList<Fire>();

	/*
	 * コンストラクタ
	 */
	public Field() {

		//ボンバーマンの生成
		createBomberman();

		//周りの壁を生成する
		createSideWalls();

		//通路の壁を生成する
		createAisleWalls();

		//敵を生成する
		createEnemies();

		//画面の再描画
		Main.panel.repaint();
	}

	/*
	 * ボンバーマンを生成する
	 */
	private void createBomberman() {

		this.bomberman = new Bomberman(Const.BOMBERMAN_X, Const.BOMBERMAN_Y, this);

		//ボンバーマンがキーイベントを受け取るのでフレームに渡す
		Main.frame.addKeyListener(this.bomberman);
	}

	/*
	 * 周りの壁を生成する
	 */
	private void createSideWalls() {

		for (int y = 0; y <= Const.MAX_Y; y++) {
			for (int x = 0; x <= Const.MAX_X; x++) {
				if (x == 0 || y == 0 || x == Const.MAX_X || y == Const.MAX_Y) {
					//左端、右端、上端、下端の場合、壁生成
					this.walls.add(new Wall(x, y));
				}
			}
		}
	}

	/*
	 * 通路の壁を生成する
	 */
	private void createAisleWalls() {

		for (int y = 0; y <= Const.MAX_Y; y++) {
			for (int x = 0; x <= Const.MAX_X; x++) {
				if (x % 2 == 0 && y % 2 == 0) {
					//縦横のマス目が両方偶数の場合、壁生成
					this.walls.add(new Wall(x, y));
				}
			}
		}
	}

	/*
	 * 敵を生成する
	 */
	private void createEnemies() {

		//敵の生成
		this.enemies.add(new Enemy(7, 9, this));
		this.enemies.add(new Enemy(3, 7, this));
		this.enemies.add(new Enemy(9, 5, this));
	}

	/*
	 * ボムを生成する
	 */
	public void createBomb(Rectangle rect) {

		this.bombs.add(new Bomb(rect));

		//画面の再描画
		Main.panel.repaint();
	}

	/*
	 * 炎を生成する
	 */
	public void createFires(Rectangle center) {

		//爆心地に炎を生成する
		this.fires.add(new Fire(center));

		//壁まで炎をのばすために生成マスを矩形で表現する
		Rectangle destination_rect = null;

		int num;

		//左方向
		for (int i = 1; i <= 2; i++) {

			destination_rect = new Rectangle(center.x - (i * 50), center.y, Const.OBJ_SIZE, Const.OBJ_SIZE);

			if (isWall(destination_rect)) {
				break;
			}
			num = isBomb(destination_rect);
			if (num != -1) {
				bombsExplosion(this.bombs.get(num).rect, num);
				break;
			}

			this.fires.add(new Fire(destination_rect));
		}

		//下方向
		for (int i = 1; i <= 2; i++) {

			destination_rect = new Rectangle(center.x, center.y + (i * 50), Const.OBJ_SIZE, Const.OBJ_SIZE);

			if (isWall(destination_rect)) {
				break;
			}
			num = isBomb(destination_rect);
			if (num != -1) {
				bombsExplosion(this.bombs.get(num).rect, num);
				break;
			}

			this.fires.add(new Fire(destination_rect));
		}

		//上方向
		for (int i = 1; i <= 2; i++) {

			destination_rect = new Rectangle(center.x, center.y - (i * 50), Const.OBJ_SIZE, Const.OBJ_SIZE);

			if (isWall(destination_rect)) {
				break;
			}
			num = isBomb(destination_rect);
			if (num != -1) {
				bombsExplosion(this.bombs.get(num).rect, num);
				break;
			}

			this.fires.add(new Fire(destination_rect));
		}

		//右方向
		for (int i = 1; i <= 2; i++) {

			destination_rect = new Rectangle(center.x + (i * 50), center.y, Const.OBJ_SIZE, Const.OBJ_SIZE);

			if (isWall(destination_rect)) {
				break;
			}
			num = isBomb(destination_rect);
			if (num != -1) {
				bombsExplosion(this.bombs.get(num).rect, num);
				break;
			}

			this.fires.add(new Fire(destination_rect));
		}

		//画面の再描画
		Main.panel.repaint();
	}

	/*
	 * 壁かどうかを判定する
	 */
	private boolean isWall(Rectangle destination_rect) {

		//全壁ループ
		for (int i = 0; i < this.walls.size(); i++) {
			//壁のrect と調査先のrect が交差するかをboolean で取得
			if (this.walls.get(i).rect.intersects(destination_rect)) {
				return true;
			}
		}

		return false;
	}

	/*
	 * ボムかどうかを判定する
	 */
	private int isBomb(Rectangle destination_rect) {

		//全ボムループ
		for (int i = 0; i < this.bombs.size(); i++) {
			//ボムのrect と調査先のrect が交差するかをboolean で取得
			if (this.bombs.get(i).rect.intersects(destination_rect)) {
				return i;
			}
		}

		return -1;
	}

	/*
	 * ボムの爆発
	 */
	public void bombsExplosion(Rectangle rect, int i) {

		//ボムの解放
		this.releaseObject("bombs", i);

		//炎生成
		createFires(rect);
	}

	/*
	 * 全敵の生死判定と解放・消去
	 */
	public int isAliveAllEnemies() {

		//全敵ループ
		for (int i = 0; i < this.enemies.size(); i++) {
			//敵の生死判定
			if (!this.enemies.get(i).isAlive()) {
				//死亡した敵の解放と消去
				this.releaseObject("enemies", i);
			}
		}

		return this.enemies.size();
	}

	/*
	 * オブジェクトを解放し、画面から消去する
	 */
	public void releaseObject(String type, int i) {

		//Todo:ださい
		if (type == "bombs") {
			this.bombs.get(i).label.setVisible(false);
			this.bombs.remove(i);
		}
		else if (type == "fires") {
			this.fires.get(i).label.setVisible(false);
			this.fires.remove(i);
		}
		else if (type == "enemies") {
			this.enemies.get(i).label.setVisible(false);
			this.enemies.remove(i);
		}

		//画面の再描画
		Main.panel.repaint();
	}
}

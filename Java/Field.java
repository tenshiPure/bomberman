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

	//壁のリスト
	public ArrayList<Wall> walls = new ArrayList<Wall>();

	//ボンバーマン
	public Bomberman bomberman;

	//ボムのリスト
	public ArrayList<Bomb> bombs = new ArrayList<Bomb>();

	//炎のリスト
	public ArrayList<Fire> fires = new ArrayList<Fire>();

	//敵のリスト
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	/*
	 * コンストラクタ
	 */
	public Field() {

		//周りの壁を生成する
		createSideWalls();

		//通路の壁を生成する
		createAisleWalls();

		//ボンバーマンの生成
		createBomberman();

		//敵を生成する
		createEnemies();
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
	 * ボンバーマンを生成する
	 */
	private void createBomberman() {

		this.bomberman = new Bomberman(Const.BOMBERMAN_X, Const.BOMBERMAN_Y, this);

		//ボンバーマンがキーイベントを受け取るのでフレームに渡す
		Main.frame.addKeyListener(this.bomberman);
	}

	/*
	 * 敵を生成する
	 */
	private void createEnemies() {

		//敵の生成
		enemies.add(new Enemy(7, 9, this));
		enemies.add(new Enemy(3, 7, this));
		enemies.add(new Enemy(9, 5, this));
	}

	/*
	 * オブジェクトを解放し、画面から消去する
	 */
	public void releaseObject(String type, int i) {

		if (type == "bombs") {
			bombs.get(i).label.setVisible(false);
			bombs.remove(i);
		}
		else if (type == "fires") {
			fires.get(i).label.setVisible(false);
			fires.remove(i);
		}
		else if (type == "enemies") {
			enemies.get(i).label.setVisible(false);
			enemies.remove(i);
		}
	}
}

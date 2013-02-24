import java.util.ArrayList;
import java.util.Random;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * 障害物を管理するフィールド
 */
class Field implements ActionListener {

	//フレーム
	private JFrame frame;

	//パネル
	private JPanel panel;

	//壁のリスト
	public ArrayList<Wall> walls = new ArrayList<Wall>();

	//ボンバーマン
	private Bomberman bomberman;

	//ボムのリスト
	public ArrayList<Bomb> bombs = new ArrayList<Bomb>();

	//炎のリスト
	public ArrayList<Fire> fires = new ArrayList<Fire>();

	//敵のリスト
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	//タイマー
	private Timer timer;

	/*
	 * コンストラクタ
	 */
	public Field() {

		//フレームの生成と初期設定
		initFrame();
		
		//パネルの生成と初期設定
		initPanel();
		
		//周りの壁を生成する
		createSideWalls();

		//通路の壁を生成する
		createAisleWalls();

		//ボンバーマンの生成
		this.bomberman = new Bomberman(Const.BOMBERMAN_X, Const.BOMBERMAN_Y, this, this.panel);

		//ボンバーマンがキーイベントを受け取るのでフレームに渡す
		this.frame.addKeyListener(this.bomberman);

		//敵を生成する
		createEnemies();

		//タイマーを生成する
		this.timer = new Timer(Const.TIMER_INTERVAL, this);

		//タイマーを動かす
		this.timer.start();
	}

	/*
	 * フレームの生成と初期設定
	 */
	private void initFrame() {

		//フレームの生成
		this.frame = new JFrame();

		//表示位置を設定する
		this.frame.setBounds(Const.FRAME_X, Const.FRAME_Y, 0, 0);

		//実表示領域のサイズを設定する
        this.frame.getContentPane().setPreferredSize(new Dimension(Const.FRAME_W, Const.FRAME_H));
        this.frame.pack();

		//画面を閉じたときにプロセスも終了する
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//画面表示の正否
		this.frame.setVisible(true);
	}

	/*
	 * パネルの生成と初期設定
	 */
	private void initPanel() {

		//パネルの作成
		this.panel = new JPanel();

		//サイズを設定する
		this.panel.setBounds(Const.FRAME_X, Const.FRAME_Y, Const.FRAME_W, Const.FRAME_H);

		//背景色を設定する
		this.panel.setBackground(new Color(99, 169, 99));

		//自動レイアウトを無効
		this.panel.setLayout(null);
		
		//フレームに追加
		this.frame.add(this.panel);
	}

	/*
	 * 周りの壁を生成する
	 */
	private void createSideWalls() {

		for (int y = 0; y <= Const.MAX_Y; y++) {
			for (int x = 0; x <= Const.MAX_X; x++) {
				if (x == 0 || y == 0 || x == Const.MAX_X || y == Const.MAX_Y) {
					//左端、右端、上端、下端の場合、壁生成
					this.walls.add(new Wall(x, y, this.panel));
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
					this.walls.add(new Wall(x, y, this.panel));
				}
			}
		}
	}

	/*
	 * 敵を生成する
	 */
	private void createEnemies() {

		//敵の生成
		this.enemies.add(new Enemy(7, 9, this, this.panel));
		this.enemies.add(new Enemy(3, 7, this, this.panel));
		this.enemies.add(new Enemy(9, 5, this, this.panel));
	}

	/*
	 * 全敵を動かす
	 */
	private void moveAllEnemies() {

		Random rand = new Random();

		//全敵ループ
		for (int i = 0; i < this.enemies.size(); i++) {

			//ランダムに方向を決定する
			int vector = rand.nextInt(4);

			//移動
			this.enemies.get(i).move(vector);
		}
	}

	/*
	 * ボムのカウントダウン
	 */
	private void countDownBombs() {

		//残り時間
		int remainingCount;

		//全ボムループ
		for (int i = 0; i < this.bombs.size(); i++) {
			//個々の残り時間を更新
			remainingCount = this.bombs.get(i).countDown();

			//残り時間が限界を迎えた場合
			if (remainingCount == 0) {

				//炎生成
				createFires(this.bombs.get(i).rect);

				//ボムの解放
				this.releaseObject("bombs", i);
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
		for (int i = 0; i < this.fires.size(); i++) {
			//個々の残り時間を更新
			remainingCount = this.fires.get(i).countDown();

			//残り時間が限界を迎えた場合
			if (remainingCount == 0) {
				//炎の解放
				this.releaseObject("fires", i);
			}
		}
	}

	/*
	 * 炎生成
	 */
	private void createFires(Rectangle center) {

		//爆心地
		this.fires.add(new Fire(center.x, center.y, this.panel));

		//調査を矩形で生成する
		Rectangle destination_rect = null;

		//左方向
		for (int i = 1; i <= 2; i++) {

			destination_rect = new Rectangle(center.x - (i * 50), center.y, Const.OBJ_SIZE, Const.OBJ_SIZE);

			if (!isWall(destination_rect)) {
				break;
			}

			//壁でなければ、炎生成
			this.fires.add(new Fire(center.x - (i * 50), center.y, this.panel));
		}

		//下方向
		for (int i = 1; i <= 2; i++) {

			destination_rect = new Rectangle(center.x, center.y + (i * 50), Const.OBJ_SIZE, Const.OBJ_SIZE);

			if (!isWall(destination_rect)) {
				break;
			}

			//壁でなければ、炎生成
			this.fires.add(new Fire(center.x, center.y + (i * 50), this.panel));
		}

		//上方向
		for (int i = 1; i <= 2; i++) {

			destination_rect = new Rectangle(center.x, center.y + (i - 50), Const.OBJ_SIZE, Const.OBJ_SIZE);

			if (!isWall(destination_rect)) {
				break;
			}

			//壁でなければ、炎生成
			this.fires.add(new Fire(center.x, center.y - (i * 50), this.panel));
		}

		//右方向
		for (int i = 1; i <= 2; i++) {

			destination_rect = new Rectangle(center.x + (i * 50), center.y, Const.OBJ_SIZE, Const.OBJ_SIZE);

			if (!isWall(destination_rect)) {
				break;
			}

			//壁でなければ、炎生成
			this.fires.add(new Fire(center.x + (i * 50), center.y, this.panel));
		}

	}

	/*
	 * 壁かどうかを判定する
	 */
	private boolean isWall(Rectangle destination_rect) {

		//全壁ループ
		for (int i = 0; i < this.walls.size(); i++) {
			//壁のrect と調査先のrect が交差するかをboolean で取得
			if (this.walls.get(i).rect.intersects(destination_rect))
				return false;
		}

		//どの壁とも交差しなければ、移動可
		return true;
	}

	/*
	 * オブジェクトを解放し、画面から消去する
	 */
	private void releaseObject(String type, int i) {

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
	 * タイマーの発信するアクションイベントを受け取るメソッド(ActionListener)
	 */
	public void actionPerformed(ActionEvent e) {

		//全敵を動かす
		moveAllEnemies();

		//ボムのカウントダウン
		countDownBombs();

		//炎のカウントダウン
		countDownFires();

		//ボンバーマンの生死判定
		if (!this.bomberman.isAlive()) {
			//クロージング
			gameClose("Game Over!");
		}

		//全敵の生死判定と解放・消去
		if (isAliveAllEnemies() == 0) {
			//クロージング
			gameClose("You Win!");
		}
	}

	/*
	 * クロージング
	 */
	public void gameClose(String msg) {
		
		//タイマーを止める
		this.timer.stop();

		//ダイアログの表示
		JOptionPane.showMessageDialog(this.panel, msg);

		//終了
		System.exit(0);
	}
}

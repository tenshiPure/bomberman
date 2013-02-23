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
	private ArrayList<Wall> walls = new ArrayList<Wall>();

	//ボンバーマン
	private Bomberman bomberman;

	//敵のリスト
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

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

		//タイマー開始
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
	 * 移動のあたり判定を返却する
	 */
	public boolean isMovable(Rectangle destination_rect) {

		//全壁ループ
		for (int i = 0; i < this.walls.size(); i++) {
			//壁のrect と移動先のrect が交差するかをboolean で取得
			if (this.walls.get(i).rect.intersects(destination_rect))
				return false;
		}

		//どの壁とも交差しなければ、移動可
		return true;
	}

	/*
	 * タイマーの発信するアクションイベントを受け取るメソッド(ActionListener)
	 */
	public void actionPerformed(ActionEvent e) {

		Random rand = new Random();

		//全敵ループ
		for (int i = 0; i < this.enemies.size(); i++) {

			//ランダムに方向を決定する
			int vector = rand.nextInt(4);

			//敵の移動
			this.enemies.get(i).move(vector);
		}

		//生死判定
		if (!isAlive()) {
			JOptionPane.showMessageDialog(this.panel, "Game Over!");
		}
	}

	/*
	 * ボンバーマンの生死を取得する
	 */
	public boolean isAlive() {

		//全敵ループ
		for (int i = 0; i < this.enemies.size(); i++) {
			//敵のrect とボンバーマンのrect が交差するかをboolean で取得
			if (this.enemies.get(i).rect.intersects(this.bomberman.rect))
				return false;
		}

		//どの敵とも交差しなければ、生きている
		return true;
	}
}

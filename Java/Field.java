import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * 障害物を管理するフィールド
 */
class Field {

	//フレーム
	private JFrame frame;

	//パネル
	private JPanel panel;

	//壁のリスト
	private ArrayList<Wall> walls = new ArrayList<Wall>();

	//ボンバーマン
	private Bomberman bomberman;

	/*
	 * コンストラクタ
	 */
	public Field() {

		//フレームの生成と初期設定
		initFrame();
		
		//パネルの生成と初期設定
		initPanel();
		
		//ボンバーマンの生成
		this.bomberman = new Bomberman(1, 1, this, this.panel);

		//ボンバーマンがキーイベントを受け取るのでフレームに渡す
		this.frame.addKeyListener(this.bomberman);

		//周りの壁を生成する
		createSideWalls();

		//通路の壁を生成する
		createAisleWalls();
	}

	/*
	 * フレームの生成と初期設定
	 */
	private void initFrame() {

		//フレームの生成
		this.frame = new JFrame();

		//サイズを設定する
		this.frame.setBounds(Const.FRAME_X, Const.FRAME_Y, Const.FRAME_W, Const.FRAME_H);

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
	 * 移動のあたり判定を返却する
	 */
	public boolean isMovable(Rectangle destination_rect) {

		// 全壁ループ
		for (int i = 0; i < this.walls.size(); i++) {
			//壁のrect とボンバーマンのrect が交差するかをboolean で取得
			if (this.walls.get(i).rect.intersects(destination_rect))
				return false;
		}

		//どの壁とも交差しなければ、移動可
		return true;
	}
}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * 起動クラス
 */
class Main {

	//フレーム
	public static JFrame frame;

	//パネル
	public static JPanel panel;

	//タイマー
	public static Timer timer;

	/*
	 * Guiパーツの初期化とFieldの生成
	 */
	public static void main(String[] args) {

		//フレームの生成と初期設定
		initFrame();
		
		//パネルの生成と初期設定
		initPanel();

		//フィールドの生成
		Field field = new Field();

		//タイマーの生成と起動
		initTimer(field);

		//下書きエリア
		//Prac prac = new Prac();
	}

	/*
	 * フレームの生成と初期設定
	 */
	private static void initFrame() {

		//フレームの生成
		Main.frame = new JFrame();

		//表示位置を設定する
		Main.frame.setBounds(Const.FRAME_X, Const.FRAME_Y, 0, 0);

		//実表示領域のサイズを設定する
        Main.frame.getContentPane().setPreferredSize(new Dimension(Const.FRAME_W, Const.FRAME_H));
        Main.frame.pack();

		//画面を閉じたときにプロセスも終了する
		Main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//画面表示の正否
		Main.frame.setVisible(true);
	}

	/*
	 * パネルの生成と初期設定
	 */
	private static void initPanel() {

		//パネルの作成
		Main.panel = new JPanel();

		//サイズを設定する
		Main.panel.setBounds(Const.FRAME_X, Const.FRAME_Y, Const.FRAME_W, Const.FRAME_H);

		//背景色を設定する
		Main.panel.setBackground(new Color(99, 169, 99));

		//自動レイアウトを無効
		Main.panel.setLayout(null);
		
		//フレームに追加
		Main.frame.add(Main.panel);
	}

	/*
	 * タイマーの生成と初期設定
	 */
	public static void initTimer(Field field) {

		//field参照を持つMyTimerを渡す
		Main.timer = new Timer(Const.TIMER_INTERVAL, new MyTimer(field));

		//タイマーを動かす
		Main.timer.start();
	}

	/*
	 * クロージング
	 */
	public static void gameClose(String msg) {
		
		//タイマーを止める
		Main.timer.stop();

		//ダイアログの表示
		JOptionPane.showMessageDialog(Main.panel, msg);

		//終了
		System.exit(0);
	}
}

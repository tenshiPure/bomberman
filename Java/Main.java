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

	/*
	 * Guiパーツの初期化とFieldの生成
	 */
	public static void main(String[] args) {

		//フレームの生成と初期設定
		initFrame();
		
		//パネルの生成と初期設定
		initPanel();

		//フィールドの作成
		Field field = new Field();

		//お試しエリア（平常時はコメントアウト）
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
}

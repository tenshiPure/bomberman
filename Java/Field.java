import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * 障害物を管理するフィールド
 */
class Field
{
	//フレーム
	private JFrame frame;

	//パネル
	private JPanel panel;

	//フィールドオブジェクトのリスト
	private ArrayList<FieldObject> objects = new ArrayList<FieldObject>();

	//ボンバーマン
	private Bomberman bomberman;

	/*
	 * コンストラクタ
	 */
	public Field()
	{
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

		//開いている部分にスペースを生成して埋める
		fillSpace();
	}

	/*
	 * フレームの生成と初期設定
	 */
	private void initFrame()
	{
		//フレームの生成
		this.frame = new JFrame();

		//サイズを設定する
		this.frame.setBounds(Constant.FRAME_X, Constant.FRAME_Y, Constant.FRAME_W, Constant.FRAME_H);

		//画面を閉じたときにプロセスも終了する
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//画面表示の正否
		this.frame.setVisible(true);
	}

	/*
	 * パネルの生成と初期設定
	 */
	private void initPanel()
	{
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
	private void createSideWalls()
	{
		for (int j = 0; j <= Constant.MAX_J; j++)
			for (int i = 0; i <= Constant.MAX_I; i++)
				if (i == 0 || j == 0 || i == Constant.MAX_I || j == Constant.MAX_J)
				{
					//左端、右端、上端、下端の場合、壁生成
					this.objects.add(new Wall(i, j, this.panel));
				}
	}

	/*
	 * 通路の壁を生成する
	 */
	private void createAisleWalls()
	{
		for (int j = 0; j <= Constant.MAX_J; j++)
			for (int i = 0; i <= Constant.MAX_I; i++)
				if (i % 2 == 0 && j % 2 == 0)
				{
					//縦横のマス目が両方偶数の場合、壁生成
					this.objects.add(new Wall(i, j, this.panel));
				}
	}

	/*
	 * 開いている部分にスペースを生成して埋める
	 */
	private void fillSpace()
	{
		for (int j = 0; j <= Constant.MAX_J; j++)
			for (int i = 0; i <= Constant.MAX_I; i++)
			{
				//フィールドで空いているマスか調べる
				if (getFieldObjectType(i, j) == "empty")
				{
					//壁でもブロックでも無い場合、スペース生成
					this.objects.add(new Space(i, j, this.panel));
				}
			}
	}

	/*
	 * そのマスのオブジェクトを調べる
	 */
	public String getFieldObjectType(int i, int j)
	{
		//全フィールドオブジェクトループ
		for (int n = 0; n < this.objects.size(); n++)
		{
			//引数で指定された座標のオブジェクトを調べる
			if (this.objects.get(n).getI() == i && this.objects.get(n).getJ() == j)
			{
				return this.objects.get(n).getType();
			}
		}

		return "empty";
	}
}

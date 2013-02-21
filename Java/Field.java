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
	private JFrame frame;
	private JPanel panel;
	private ArrayList<FieldObject> objects = new ArrayList<FieldObject>();
	private Bomberman bomberman;

	/*
	 * コンストラクタ
	 */
	public Field()
	{
		//フレームの生成と初期設定
		initFrame(Constant.FRAME_X, Constant.FRAME_Y, Constant.FRAME_W, Constant.FRAME_H);
		
		//パネルの生成と初期設定
		initPanel();
		
		//周りの壁を生成する
		createSideWalls();

		//通路の壁を生成する
		createRandomWalls();

		//開いている部分にスペースを生成して埋める
		fillSpace();

		//ボンバーマンの生成
		this.bomberman = new Bomberman(50, 50, this);

		//ボンバーマンがキーイベントを受け取るのでフレームに渡す
		this.frame.addKeyListener(this.bomberman);
	}

	/*
	 * フレームの生成と初期設定
	 */
	private void initFrame(int x, int y, int w, int h)
	{
		//フレームの生成
		this.frame = new JFrame();

		//サイズを設定する
		this.frame.setBounds(x, y, w, h);

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
		int end_i = Constant.FRAME_W / Constant.FIELD_OBJECT_W;
		int end_j = Constant.FRAME_H / Constant.FIELD_OBJECT_H;

		for (int j = 0; j < end_j; j++)
			for (int i = 0; i < end_i; i++)
				if (i == 0 || j == 0 || i == end_i - 1 || j == end_j - 1)
				{
					//左端、右端、上端、下端の場合、壁生成
					FieldObject wall = new Wall (i * Constant.FIELD_OBJECT_W, j * Constant.FIELD_OBJECT_H,
															Constant.FIELD_OBJECT_W, Constant.FIELD_OBJECT_H,
															this.panel);
					//リストに追加
					this.objects.add(wall);

					//コンソール出力
					wall.outputFieldObject();
				}
	}

	/*
	 * 通路の壁を生成する
	 */
	private void createRandomWalls()
	{
		int end_x = Constant.FRAME_W / Constant.FIELD_OBJECT_W;
		int end_y = Constant.FRAME_H / Constant.FIELD_OBJECT_H;

		for (int y = 0; y < end_y; y++)
			for (int x = 0; x < end_x; x++)
				if (x % 2 == 0 && y % 2 == 0)
				{
					//縦横のマス目が偶数の場合、壁生成
					FieldObject wall = new Wall (x * Constant.FIELD_OBJECT_W, y * Constant.FIELD_OBJECT_H,
															Constant.FIELD_OBJECT_W, Constant.FIELD_OBJECT_H,
															this.panel);
					//リストに追加
					this.objects.add(wall);

					//コンソール出力
					wall.outputFieldObject();
				}
	}

	/*
	 * 開いている部分にスペースを生成して埋める
	 */
	private void fillSpace()
	{
		int end_x = Constant.FRAME_W / Constant.FIELD_OBJECT_W;
		int end_y = Constant.FRAME_H / Constant.FIELD_OBJECT_H;

		String type = "";
		for (int y = 0; y < end_y; y++)
			for (int x = 0; x < end_x; x++)
			{
				type = getFieldObjectType(x, y);
				if (type == "empty")
				{
					//壁でもブロックでも無い場合、スペース生成
					FieldObject space = new Space (x * Constant.FIELD_OBJECT_W, y * Constant.FIELD_OBJECT_H,
															Constant.FIELD_OBJECT_W, Constant.FIELD_OBJECT_H,
															this.panel);
					//リストに追加
					this.objects.add(space);

					//コンソール出力
					space.outputFieldObject();
				}
			}
	}

	/*
	 * そのマスのオブジェクトを調べる
	 */
	private String getFieldObjectType(int x, int y)
	{
		//全フィールドオブジェクトループ
		for (int i = 0; i < this.objects.size(); i++)
		{
			if (this.objects.get(i).x == x * Constant.FIELD_OBJECT_W &&
					this.objects.get(i).y == y * Constant.FIELD_OBJECT_H)
			{
				return this.objects.get(i).type;
			}
		}

		return "empty";
	}
}

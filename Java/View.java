import java.util.ArrayList;
import javax.swing.*;

/*
 * オブジェクトを描画するクラス
 */
class View
{
	private Field field;
	private JPanel panel;

	/*
	 * コンストラクタ
	 */
	public View(Field field, JPanel panel)
	{
		this.field = field;
		this.panel = panel;

		//壁の出力
		outputFieldObjects(this.field.walls);
	}

	/*
	 * フィールドオブジェクトを出力する
	 */
	private void outputFieldObjects(ArrayList<Wall> walls)
	{
		int x;
		int y;
		int w;
		int h;

		ImageIcon icon;
		JLabel label;

		//全壁ループ
		for (int i = 0; i < walls.size(); i++)
		{
			x = walls.get(i).x;
			y = walls.get(i).y;
			w = walls.get(i).w;
			h = walls.get(i).h;

			//画像の用意
			icon = new ImageIcon("../Image/Wall.gif");

			//画像でラベルを作成
			label = new JLabel(icon);

			//ラベルの表示位置
			label.setBounds(x, y, w, h);

			//パネルに追加
			this.panel.add(label);
		}
	}
}

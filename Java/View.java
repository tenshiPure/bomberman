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

		//フィールドオブジェクトの出力
		outputFieldObjects(this.field.objects);
	}

	/*
	 * フィールドオブジェクトを出力する
	 */
	private void outputFieldObjects(ArrayList<FieldObject> objects)
	{
		int x;
		int y;
		int w;
		int h;
		String type;

		//描画に用いるラベル
		JLabel label;
		
		//ラベルに用いる画像
		ImageIcon icon;

		//全オブジェクトループ
		for (int i = 0; i < objects.size(); i++)
		{
			x = objects.get(i).x;
			y = objects.get(i).y;
			w = objects.get(i).w;
			h = objects.get(i).h;
			type = objects.get(i).type;

			//画像の用意
			icon = new ImageIcon("../Image/" + type + ".gif");

			//画像でラベルを作成
			label = new JLabel(icon);

			//ラベルの表示位置
			label.setBounds(x, y, w, h);

			//パネルに追加
			this.panel.add(label);
		}
	}
}

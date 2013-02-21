import javax.swing.*;

/*
 * フィールド設置物インターフェース
 */
abstract class FieldObject
{
	//座標
	int x = 0;
	int y = 0;

	//縦横
	int w = 0;
	int h = 0;
	
	//タイプ
	String type = "";
	
	//描画に使用する画像
	ImageIcon icon;

	//描画に使用するラベル
	JLabel label;

	//描画するパネル
	JPanel panel;

	/*
	 * コンストラクタ
	 * 具現化したクラスのコンストラクタから呼ばれる
	 */
	public FieldObject(int x, int y, int w, int h, String type, JPanel panel)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.type = type;
		this.icon = new ImageIcon("../Image/" + type + ".gif");
		this.label = new JLabel(icon);
		this.panel = panel;

		//ラベルの表示位置
		this.label.setBounds(x, y, w, h);
	}

	/*
	 * 描画
	 */
	public void outputFieldObject()
	{
		//パネルに追加
		this.panel.add(this.label);

		//コンソールに出力
		//System.out.println();
		//System.out.println("-- outputFieldObject --");
		//System.out.println(String.format("%5s ... x : %3d, y : %3d", this.type, this.x, this.y));
	}
}

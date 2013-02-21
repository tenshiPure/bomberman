import javax.swing.*;

/*
 * フィールド設置物抽象クラス
 */
abstract class FieldObject
{
	//位置マス目
	private int i = 0;
	private int j = 0;

	//タイプ
	private String type = "";
	
	//描画に使用する画像
	private ImageIcon icon;

	//描画に使用するラベル
	private JLabel label;

	//描画するパネル
	private JPanel panel;

	/*
	 * コンストラクタ
	 * 具現化したクラスのコンストラクタから呼ばれる
	 */
	public FieldObject(int i, int j, String type, JPanel panel)
	{
		this.i = i;
		this.j = j;
		this.type = type;
		this.icon = new ImageIcon("../Image/" + type + ".gif");
		this.label = new JLabel(icon);
		this.panel = panel;

		//ラベルの表示位置
		this.label.setBounds(Mapper.sToP(i), Mapper.sToP(j), Constant.OBJ_SIZE, Constant.OBJ_SIZE);

		//パネルに追加
		this.panel.add(this.label);
	}

	/*
	 * ゲッター
	 */
	public int getI()
	{
		return this.i;
	}

	/*
	 * ゲッター
	 */
	public int getJ()
	{
		return this.j;
	}

	/*
	 * ゲッター
	 */
	public String getType()
	{
		return this.type;
	}
}

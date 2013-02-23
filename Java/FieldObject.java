import javax.swing.*;

/*
 * フィールド設置物抽象クラス
 */
abstract class FieldObject
{
	//位置マス目
	private int x = 0;
	private int y = 0;

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
	public FieldObject(int x, int y, String type, JPanel panel)
	{
		this.x = x;
		this.y = y;
		this.type = type;
		this.icon = new ImageIcon("../Image/" + type + ".gif");
		this.label = new JLabel(icon);
		this.panel = panel;

		//ラベルの表示位置
		this.label.setBounds(Mapper.sToP(x), Mapper.sToP(y), Constant.OBJ_SIZE, Constant.OBJ_SIZE);

		//パネルに追加
		this.panel.add(this.label);
	}

	/*
	 * ゲッター
	 */
	public int getX()
	{
		return this.x;
	}

	/*
	 * ゲッター
	 */
	public int getY()
	{
		return this.y;
	}

	/*
	 * ゲッター
	 */
	public String getType()
	{
		return this.type;
	}
}

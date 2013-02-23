import java.awt.*;
import javax.swing.*;

/*
 * 壁
 */
class Wall
{
	//位置マス目
	public Rectangle rect;

	//タイプ
	private String type = "Wall";
	
	//描画に使用する画像
	private ImageIcon icon = new ImageIcon("../Image/" + type + ".gif");

	//描画に使用するラベル
	private JLabel label = new JLabel(icon);

	//描画するパネル
	private JPanel panel;

	/*
	 * コンストラクタ
	 */
	public Wall(int x, int y, JPanel panel)
	{
		this.rect = new Rectangle(x * 50, y * 50, Constant.OBJ_SIZE, Constant.OBJ_SIZE);
		this.panel = panel;

		//ラベルの表示位置
		this.label.setBounds(this.rect);

		//パネルに追加
		this.panel.add(this.label);
	}

	/*
	 * ゲッター
	 */
	public int getX()
	{
		return this.rect.x;
	}

	/*
	 * ゲッター
	 */
	public int getY()
	{
		return this.rect.y;
	}

	/*
	 * ゲッター
	 */
	public String getType()
	{
		return this.type;
	}}

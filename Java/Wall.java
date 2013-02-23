import javax.swing.*;

/*
 * 壁
 */
class Wall extends FieldObject
{
	/*
	 * コンストラクタ
	 */
	public Wall(int x, int y, JPanel panel)
	{
		super(x, y, "Wall", panel);
	}
}

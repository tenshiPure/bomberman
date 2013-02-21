import javax.swing.*;

/*
 * 壁
 */
class Wall extends FieldObject
{
	/*
	 * コンストラクタ
	 */
	public Wall(int x, int y, int w, int h, JPanel panel)
	{
		super(x, y, w, h, "Wall", panel);
	}
}

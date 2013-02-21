import javax.swing.*;

/*
 * 壁
 */
class Wall extends FieldObject
{
	/*
	 * コンストラクタ
	 */
	public Wall(int i, int j, JPanel panel)
	{
		super(i, j, "Wall", panel);
	}
}

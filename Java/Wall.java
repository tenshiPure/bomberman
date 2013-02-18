/*
 * 壁
 */
class Wall extends FieldObject
{
	/*
	 * コンストラクタ
	 */
	public Wall(int x, int y, int w, int h)
	{
		super(x, y, w, h, false, "Wall");
	}
}

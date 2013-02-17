/*
 * ブロック
 */
class Block implements FieldObject
{
	int x;
	int y;
	int w;
	int h;
	boolean breakable;
	String type;

	/*
	 * コンストラクタ
	 */
	public Block(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.breakable = true;
		this.type = "block";
	}
}

/*
 * 壁
 */
class Wall implements FieldObject
{
	int x = 0;
	int y = 0;
	int w = 0;
	int h = 0;
	boolean breakable = false;

	public Wall(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.breakable = false;
	}
}

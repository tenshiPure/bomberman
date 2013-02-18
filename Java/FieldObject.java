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
	
	//破壊可能か
	boolean breakable = false;

	//タイプ
	String type = "";
	
	
	public FieldObject(int x, int y, int w, int h, boolean breakable, String type)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.breakable = breakable;
		this.type = type;
	}
}

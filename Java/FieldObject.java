/*
 * フィールド設置物インターフェース
 */
interface FieldObject
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
}

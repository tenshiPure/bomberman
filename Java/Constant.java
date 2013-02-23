class Constant
{
	//フレーム表示座標
	public static final int FRAME_X = 500;
	public static final int FRAME_Y = 100;

	//フレーム縦横
	public static final int FRAME_W = 650;
	public static final int FRAME_H = 650;

	//フィールドオブジェクトの縦横（正方形）
	public static final int OBJ_SIZE = 50;

	//最大マス目（左上が頂点なので、使用可能な最大値は-1）
	public static final int MAX_X = (Constant.FRAME_W / Constant.OBJ_SIZE) - 1;
	public static final int MAX_Y = (Constant.FRAME_H / Constant.OBJ_SIZE) - 1;

	//移動キーのコード
	public static final int KEY_L = 72;
	public static final int KEY_D = 74;
	public static final int KEY_U = 75;
	public static final int KEY_R = 76;
}


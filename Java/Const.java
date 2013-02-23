import javax.swing.event.*;

class Const {
	//フレーム表示座標
	public static final int FRAME_X = 500;
	public static final int FRAME_Y = 100;

	//フレーム縦横
	public static final int FRAME_W = 650;
	public static final int FRAME_H = 650;

	//フィールドオブジェクトの縦横（正方形）
	public static final int OBJ_SIZE = 50;

	//ボンバーマン初期座標
	public static final int BOMBERMAN_X = 1;
	public static final int BOMBERMAN_Y = 1;
	
	//最大マス目（左上が頂点なので、使用可能な最大値は-1）
	public static final int MAX_X = (Const.FRAME_W / Const.OBJ_SIZE) - 1;
	public static final int MAX_Y = (Const.FRAME_H / Const.OBJ_SIZE) - 1;

	//方向
	public static final int VECTOR_L = 0;
	public static final int VECTOR_D = 1;
	public static final int VECTOR_U = 2;
	public static final int VECTOR_R = 3;

	//タイマーの作動間隔
	public static final int TIMER_INTERVAL = 750;
}

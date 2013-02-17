import java.util.ArrayList;

/*
 * 障害物を管理するフィールド
 */
class Field
{
	public ArrayList<Wall> walls = new ArrayList<Wall>();

	/*
	 * コンストラクタ
	 */
	public Field()
	{
		//周りの壁を作成する
		createSideWalls();
	}

	/*
	 * 周りの壁を作成する
	 */
	private void createSideWalls()
	{
		//50
		int wall_start_x = Constant.SIDE_WALL_MARGIN_W;
		int wall_start_y = Constant.SIDE_WALL_MARGIN_H;
		//500
		int wall_end_x = Constant.FRAME_W - (Constant.SIDE_WALL_MARGIN_W * 2);
		int wall_end_y = Constant.FRAME_H - (Constant.SIDE_WALL_MARGIN_H * 2);

		//50
		int wall_w = Constant.FIELD_OBJECT_W;
		int wall_h = Constant.FIELD_OBJECT_H;

		for (int j = wall_start_y; j <= wall_end_y; j+=wall_h)
			for (int i = wall_start_x; i <= wall_end_x; i+=wall_w)
				if (i == wall_start_x || i == wall_end_x || j == wall_start_y || j == wall_end_y)
				{
					System.out.println("i : " + i);
					System.out.println("j : " + j);
					System.out.println();
					this.walls.add(new Wall(i, j, wall_w, wall_h));
				}

		//dump(this.walls);
	}

	/*
	 * 開発補助
	 */
	public void dump(ArrayList<Wall> walls)
	{
		for (int i = 0; i < walls.size(); i++)
		{
			int x = walls.get(i).x;
			int y = walls.get(i).y;
			int w = walls.get(i).w;
			int h = walls.get(i).h;
			
			System.out.println("walls " + i);
			System.out.println("x : " + x + ", y : " + y + ", w : " + w + ", h : " + h);
			System.out.println();
		}
	}
}

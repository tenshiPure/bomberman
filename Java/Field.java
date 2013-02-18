import java.util.ArrayList;
import java.util.Random;

/*
 * 障害物を管理するフィールド
 */
class Field
{
	public ArrayList<FieldObject> objects = new ArrayList<FieldObject>();

	/*
	 * コンストラクタ
	 */
	public Field()
	{
		//周りの壁を生成する
		createSideWalls();

		//通路の壁を生成する
		createRandomWalls();

		//ランダムにブロックを生成する
		createRandomBlocks();

		//開いている部分にスペースを生成して埋める
		fillSpace();
	}

	/*
	 * 周りの壁を生成する
	 */
	private void createSideWalls()
	{
		int end_i = Constant.FRAME_W / Constant.FIELD_OBJECT_W;
		int end_j = Constant.FRAME_H / Constant.FIELD_OBJECT_H;

		for (int j = 0; j < end_j; j++)
			for (int i = 0; i < end_i; i++)
				if (i == 0 || j == 0 || i == end_i - 1 || j == end_j - 1)
				{
					//左端、右端、上端、下端の場合、壁生成
					this.objects.add(new Wall (i * Constant.FIELD_OBJECT_W, j * Constant.FIELD_OBJECT_H,
														Constant.FIELD_OBJECT_W, Constant.FIELD_OBJECT_H));
				}
	}

	/*
	 * 通路の壁を生成する
	 */
	private void createRandomWalls()
	{
		int end_x = Constant.FRAME_W / Constant.FIELD_OBJECT_W;
		int end_y = Constant.FRAME_H / Constant.FIELD_OBJECT_H;

		for (int y = 0; y < end_y; y++)
			for (int x = 0; x < end_x; x++)
				if (x % 2 == 0 && y % 2 == 0)
				{
					//縦横のマス目が偶数の場合、壁生成
					this.objects.add(new Wall (x * Constant.FIELD_OBJECT_W, y * Constant.FIELD_OBJECT_H,
														Constant.FIELD_OBJECT_W, Constant.FIELD_OBJECT_H));
				}
	}

	/*
	 * ランダムにブロックを生成する
	 */
	private void createRandomBlocks()
	{
		Random rand = new Random();

		for (int i = 0; i < Constant.BLOCK_NUM; i++)
		{
			//左端右端を除いたマス目数
			int x = rand.nextInt(Constant.FRAME_W / Constant.FIELD_OBJECT_W - 2) + 1;

			//上端下端を除いたマス目数
			int y = rand.nextInt(Constant.FRAME_H / Constant.FIELD_OBJECT_H - 2) + 1;

			if (x % 2 == 1 || y % 2 == 1)
			{
				//縦横いずれかが奇数の場合、ブロック生成
				this.objects.add(new Block (x * Constant.FIELD_OBJECT_W, y * Constant.FIELD_OBJECT_H,
														Constant.FIELD_OBJECT_W, Constant.FIELD_OBJECT_H));
			}
		}
	}

	/*
	 * 開いている部分にスペースを生成して埋める
	 */
	private void fillSpace()
	{
		int end_x = Constant.FRAME_W / Constant.FIELD_OBJECT_W;
		int end_y = Constant.FRAME_H / Constant.FIELD_OBJECT_H;

		String type = "";
		for (int y = 0; y < end_y; y++)
			for (int x = 0; x < end_x; x++)
			{
				type = getFieldObjectType(x, y);
				if (type == "empty")
				{
					//壁でもブロックでも無い場合、スペース生成
					this.objects.add(new Space (x * Constant.FIELD_OBJECT_W, y * Constant.FIELD_OBJECT_H,
														Constant.FIELD_OBJECT_W, Constant.FIELD_OBJECT_H));
				}
			}
	}

	/*
	 * そのマスのオブジェクトを調べる
	 */
	private String getFieldObjectType(int x, int y)
	{
		//全フィールドオブジェクトループ
		for (int i = 0; i < this.objects.size(); i++)
		{
			if (this.objects.get(i).x == x * Constant.FIELD_OBJECT_W &&
					this.objects.get(i).y == y * Constant.FIELD_OBJECT_H)
			{
				return this.objects.get(i).type;
			}
		}

		return "empty";
	}
}

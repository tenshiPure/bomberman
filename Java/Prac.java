/*
 * 練習クラス
 */
class Prac
{
	public Prac()
	{
		System.out.println("this is prac");
		
		Wall wall = new Wall(1, 1, 1, 1);
		wall.dump();

		Block block = new Block(2, 2, 2, 2);
		block.dump();

		FieldObject obj1 = new Wall(3, 3, 3, 3);
		FieldObject obj2 = new Block(4, 4, 4, 4);
		obj1.dump();
		obj2.dump();
	}
}

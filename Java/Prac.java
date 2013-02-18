/*
 * 練習クラス
 */
class Prac
{
	public Prac()
	{
		System.out.println("this is prac");
		
		Wall wall = new Wall(1, 1, 1, 1);
		System.out.println(wall.x);

		//Block block = new Block(2, 2, 2, 2);
		//System.out.println(block.type);

		FieldObject obj1 = new Wall(3, 3, 3, 3);
		//FieldObject obj2 = new Block(4, 4, 4, 4);
		System.out.println("obj1 : " + obj1.x);
		//System.out.println("obj2 : " + obj2.type);
	}
}

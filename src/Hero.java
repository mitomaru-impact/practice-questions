public class Hero extends Character {
	int maxHp; //現在の最大値
	Sword sword;
	static int money;
	
	//コンストラクタ
	public Hero(String name, int hp, int money) {
		super(name,hp);
		this.sword = new Sword();
		Hero.money = money;
	}
	
	public void attack(Matango m) {
		System.out.println(getName() + "の攻撃！");
		m.hp -= 5;
		System.out.println("5ポイントのダメージを与えた");
	}
	
	public void installation(String swordName, int damage) {
		this.sword.name = swordName;
		this.sword.damage = damage;
	}
	
	final void sleep() {
		setHp(maxHp);
		System.out.println(getName() + "は、眠って回復した！");
	}
	public void sit(int sec) {
		setHp(getHp() +sec);
		System.out.println(getName() + "は、"+ sec +"秒座った");
		System.out.println("HPが"+ sec +"ポイント回復した");
		
	}
	public void slip() {
		setHp(getHp() - 5);
		System.out.println(getName() + "は、転んだ！");
		System.out.println("5のダメージ！");
		
	}
	public void run() {
		setHp(getHp() - 5);
		System.out.println(getName() + "は、逃げた");
		System.out.println("GameOver");
		System.out.println("最終HPは"+ getHp() + "でした");
		
	}
	public String toString() {
		return "名前 : " + getName() + "/HP :" + getHp();
		
	}
	
	public static void setRandomMoney() {
		Hero.money = (int)(Math.random() * 1000);
	}
}

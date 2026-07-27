
public interface Human extends Creature{
	default void talk() {
		System.out.println("私は話している");
	}
	void watch();
	void hear();
}

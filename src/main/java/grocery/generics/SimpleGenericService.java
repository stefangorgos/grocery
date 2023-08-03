package grocery.generics;

public class SimpleGenericService<T extends ISimpleService> {
	public T t;
	
	public SimpleGenericService(T t) {
		this.t = t;
	}

	public String getA() {
		return t.getA();
	}

	public String getB() {
		return t.getB();
	}
	
	public static void main(String args[]) {
		SimpleGenericService<Service1> service1 = new SimpleGenericService<>(new Service1());
		SimpleGenericService<Service2> service2 = new SimpleGenericService<>(new Service2());
		System.out.println(service1.getA());
		System.out.println(service1.getB());
		System.out.println(service2.getA());
		System.out.println(service2.getB());
	}
}

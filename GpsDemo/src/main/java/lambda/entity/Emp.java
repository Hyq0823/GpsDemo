package lambda.entity;

public class Emp {
	private Integer id;
	private String name;

	public Emp(Integer id, String name) {
        super();
		this.id = id;
		this.name = name;
		System.out.println(this);
	}

	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "id: " + id + ", name: " + name;
	}

}
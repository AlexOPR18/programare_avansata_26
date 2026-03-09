public class Designer extends Person {
	private String designTool;
	
	public Designer(String id, String nume, String birthDate, String designTool) {
		super(id, nume, birthDate);
		this.designTool = designTool;
	}
	public String getDesignTool() {
		return designTool;
	}

	@Override
	public String toString() {
		return "Designer{" + getName() + " deseneaza in " + designTool + "}";
	}
}

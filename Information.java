public class Information {
    private String Name;
    private String NameType;
    private Integer Quantity;
    private Float Amount;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNameType() {
        return NameType;
    }

    public void setNameType(String nameType) {
        NameType = nameType;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Float getAmount() {
        return Amount;
    }

    public void setAmount(Float amount) {
        Amount = amount;
    }

    @Override
    public String toString() {
        return "Information{" + "Name='" + Name + '\'' + ", NameType='" + NameType + '\'' + ", Quantity=" + Quantity + ", Amount=" + Amount + '}';
    }

}

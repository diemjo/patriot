package moe.karpador.patriot.mana;

public class PantsuStack implements IPantsuStack{
    private String ownerName ="";
    @Override
    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}

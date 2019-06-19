package XML_to_XLS;

public class Cycle {

    private String Text;

    private int Index;
    public Cycle() {}
    public Cycle(String text, int index) {
        this.Text = text;
        this.Index = index;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        this.Text = text;
    }

    public int getIndex() {
        return Index;
    }

    public void setIndex(int index) {
        this.Index = index;
    }

    @Override
    public String toString() {
        return
                "T='" + Text + '\'' +
                        ", I=" + Index +
                        '}';
    }
}
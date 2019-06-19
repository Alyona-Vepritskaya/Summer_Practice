package XML_to_XLS;

/**
 * Class contains current schedule value and index
 */
public class Cycle {
    /**
     * Value of schedule numerator or denominator
     */
    private String Text;
    /**
     * Index
     */
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
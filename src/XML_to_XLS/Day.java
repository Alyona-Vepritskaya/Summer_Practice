package XML_to_XLS;

/**
 * Class contains day of week
 * and index
 */
public class Day {
    /**
     * Name of the day of the week
     */
    private String Text;
    /**
     * Index
     */
    private int Index;

    public Day() {
    }

    public Day(String text, int index) {
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

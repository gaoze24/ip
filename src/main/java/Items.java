public class Items {
    private String[] items = new String[101];
    private int index;

    public Items() {
        this.index = 1;
    }

    public void storeItem(String item) {
        items[index] = item;
        index++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < index; i++) {
            if (i == index - 1) {
                sb.append(i).append(". ").append(items[i]);
            } else {
                sb.append(i).append(". ").append(items[i]).append("\n");
            }
        }
        return sb.toString();
    }
}

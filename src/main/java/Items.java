public class Items {
    private Item[] items = new Item[101];
    private int index;


    public Items() {
        this.index = 1;
    }

    public void storeItem(String item) {
        items[index] = new Item(index, item);
        index++;
    }

    public void completeItem(int index) {
        items[index].complete();
    }

    public void incompleteItem(int index) {
        items[index].incomplete();
    }

    public String showItem(int index) {
        return items[index].toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < index; i++) {
            if (i == index - 1) {
                sb.append(i).append(". ").append(items[i].toString());
            } else {
                sb.append(i).append(". ").append(items[i].toString()).append("\n");
            }
        }
        return sb.toString();
    }
}

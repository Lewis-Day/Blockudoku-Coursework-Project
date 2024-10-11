package lists;

public class EfficientIntArrayList implements IntList {
    int[] values;
    int len;
    int startingLength = 10;
    int scalingFactor = 2;

    public EfficientIntArrayList() {
        values = new int[startingLength];
        len = 0;
    }

    public boolean contains(int value) {
        // todo implement this properly

        for(int i = 0; i<len; i++){
            if (values[i] == value){
                return true;
            }
        }
        return false;
    }

    public void append(int value) {
        // this is inefficient but leave as is for now
        if (values.length == len){
            boostArray();
        }
        values[len] = value;
        len++;

    }

    private void boostArray(){

        int[] newArray = new int[len*scalingFactor];

        for(int i = 0; i<values.length; i++){
            newArray[i] = values[i];
        }
        values = newArray;
    }

    public int length() {
        // todo: fix this!
        return this.len;
    }
}

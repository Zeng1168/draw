package entity;

public class ShapeRose {


    private Integer size;
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"size\":")
                .append(size);
        sb.append('}');
        return sb.toString();
    }
}

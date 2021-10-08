package models.blogs;

public class BlogTemplate {

    private String templateName;
    private BlogCategory category;
    private String color;
    private byte[] backgroundIMG;

    public BlogTemplate(String templateName, BlogCategory category, String color, byte[] backgroundIMG) {
        this.templateName = templateName;
        this.category = category;
        this.color = color;
        this.backgroundIMG = backgroundIMG;
    }

    public String getTemplateName() {
        return templateName;
    }

    public BlogCategory getCategory() {
        return category;
    }

    public String getColor() {
        return color;
    }

    public byte[] getBackgroundIMG() {
        return backgroundIMG;
    }
}

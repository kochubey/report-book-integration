package logbook.dbl.system;

import javax.persistence.*;

//Вид сведений

@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "SYS_DICTIONARY_ID_SEQ", allocationSize = 0, initialValue = 1)
@Table(name = "SYS_DICTIONARY", uniqueConstraints = {@UniqueConstraint(columnNames = {"CATEGORY_ALIAS", "ARTICLE_VALUE"})})
public class Dictionary extends Base {

    //    Конструкторы
    public Dictionary() {
    }

    public Dictionary(Dictionary parent, String category, String categoryAlias, String name, String articleValue) {
        this.parent = parent;
        this.category = category;
        this.categoryAlias = categoryAlias;
        this.name = name;
        this.articleValue = articleValue;
    }

    //    ID записи
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //    Родительская запись
    @ManyToOne
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
    private Dictionary parent;

    public Dictionary getParent() {
        return parent;
    }

    public void setParent(Dictionary parent) {
        this.parent = parent;
    }

    //  Категория словаря
    @Column(name = "CATEGORY", length = 400, nullable = false)
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "CATEGORY_ALIAS", length = 400, nullable = false)
    private String categoryAlias;

    public String getCategoryAlias() {
        return categoryAlias;
    }

    public void setCategoryAlias(String categoryAlias) {
        this.categoryAlias = categoryAlias;
    }

    @Column(name = "NAME", length = 400, nullable = false)
    private String name;

    public String getArticleValue() {
        return articleValue;
    }

    public void setArticleValue(String articleValue) {
        this.articleValue = articleValue;
    }

    @Column(name = "ARTICLE_VALUE", length = 400, nullable = false)
    private String articleValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
package example;

import javax.persistence.*;

@Entity
@Table(name="dog")
@NamedQueries({
  @NamedQuery(name = "example.dog.findAll", query = "from Dog")
})
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String breed;
    private String color;

    public Dog(){}
    public Dog(Long a, String b, String c){}

    public void setId(Long value){id = value;}
    public void setBreed(String value){breed = value;}
    public void setColor(String value){color = value;}

    public Long getId(){return id;}
    public String getBreed(){return breed;}
    public String getColor(){return color;}
}

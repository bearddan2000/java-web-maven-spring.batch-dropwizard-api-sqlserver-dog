package example.model;

import javax.persistence.*;

@Entity
@Table(name="dog")
public class Dog {
    @Id
    @GeneratedValue
    private Long id;
    private String breed;
    private String color;

    public void setId(Long val){ id = val;}
    public void setBreed(String val){ breed = val;}
    public void setColor(String val){ color = val;}

    public Long getId(){return id;}
    public String getBreed(){return breed;}
    public String getColor(){return color;}
}

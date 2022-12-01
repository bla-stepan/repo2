package groupid.entity;

import javax.persistence.*;

@Entity//объявляет класс как сущность
@Table(name = "CARS")//добавляем имя таблицы
public class Car {

    @Id
    @Column(name = "ID")//имя столбца
    @GeneratedValue(strategy = GenerationType.IDENTITY)//аннотация для генерации ID
    private Long id;

    @Column(name = "PRODUCER")
    private String producer;

    @Column(name = "MODEL")
    private String model;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}

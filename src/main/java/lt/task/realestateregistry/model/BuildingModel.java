package lt.task.realestateregistry.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
public class BuildingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String city;
    private String street;
    private int number;

    private String owner;
    private int size;
    private float marketValue;
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    public BuildingModel() {
    }

    public BuildingModel(String city, String street, int number, String owner, int size, float marketValue, PropertyType propertyType) {
        super();
        this.city = city;
        this.street = street;
        this.number = number;
        this.owner = owner;
        this.size = size;
        this.marketValue = marketValue;
        this.propertyType = propertyType;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setMarketValue(float marketValue) {
        this.marketValue = marketValue;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getOwner() {
        return owner;
    }

    public int getSize() {
        return size;
    }

    public float getMarketValue() {
        return marketValue;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", owner='" + owner + '\'' +
                ", size=" + size +
                ", marketValue=" + marketValue +
                ", propertyType=" + propertyType +
                '}';
    }
}

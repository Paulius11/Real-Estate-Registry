package lt.task.realestateregistry.model;

import org.springframework.stereotype.Service;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Service
public class BuildingPostModel {
    private String city;
    private String street;
    private int number;

    private String owner;
    private int size;
    private float marketValue;
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

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

    @Override
    public String toString() {
        return "BuildingPostModel{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", owner='" + owner + '\'' +
                ", size=" + size +
                ", marketValue=" + marketValue +
                ", propertyType=" + propertyType +
                '}';
    }
}

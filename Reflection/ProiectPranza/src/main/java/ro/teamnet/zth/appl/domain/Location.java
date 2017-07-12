package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by Alexandru.Negura on 7/12/2017.
 */
@Table(name = "locations")
public class Location {

    @Id(name = "location_id")
    private Long id;
    @Column(name = "street_address")
    private String streetAddres;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "city")
    private String city;
    @Column(name = "state_province")
    private String stateProvince;

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", streetAddres='" + streetAddres + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", stateProvince='" + stateProvince + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (!id.equals(location.id)) return false;
        if (!streetAddres.equals(location.streetAddres)) return false;
        if (!postalCode.equals(location.postalCode)) return false;
        if (!city.equals(location.city)) return false;
        return stateProvince.equals(location.stateProvince);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetAddres() {
        return streetAddres;
    }

    public void setStreetAddres(String streetAddres) {
        this.streetAddres = streetAddres;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }
}

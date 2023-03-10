package com.example.doctorservice.dto.response.projection;

import java.util.Objects;

public record DoctorIdProjectionItem(String id) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorIdProjectionItem projection = (DoctorIdProjectionItem) o;
        return Objects.equals(projection.id(), this.id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id());
    }


}

package com.medi.test.meditest.dtos;

public class DomainDto {
    private String domain;

    public DomainDto(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DomainDto domainDto = (DomainDto) o;

        return domain != null ? domain.equals(domainDto.domain) : domainDto.domain == null;
    }

    @Override
    public int hashCode() {
        return domain != null ? domain.hashCode() : 0;
    }
}

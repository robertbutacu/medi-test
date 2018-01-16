package com.medi.test.meditest.Transformers;

public interface ITransformer<T,DTO> {
    DTO toDto(T object);
    T toModel(DTO object);
}

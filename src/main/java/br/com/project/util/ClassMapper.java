package br.com.project.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Objects;

import java.util.ArrayList;
import java.util.List;

public class ClassMapper {

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return parseObject(new ModelMapper(), origin, destination);
    }

    public static <O, D> D parseObject(ModelMapper modelMapper, O origin, Class<D> destination) {
        return modelMapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObject(List<O> origin, Class<D> destination) {
        return parseListObject(null, origin, destination);
    }

    public static <O, D> List<D> parseListObject(ModelMapper modelMapper, List<O> origin, Class<D> destination) {
        List<D> destinationList = new ArrayList<>();

        origin.forEach(o -> destinationList.add(Objects.firstNonNull(modelMapper, new ModelMapper()).map(o, destination)));

        return destinationList;
    }

}

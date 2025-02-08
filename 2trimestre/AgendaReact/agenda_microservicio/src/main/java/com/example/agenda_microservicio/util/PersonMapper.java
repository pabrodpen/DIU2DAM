package com.example.agenda_microservicio.util;

import com.example.tutorials.Tutorials.model.Tutorials;
import com.example.tutorials.Tutorials.model.TutorialsDto;

import java.util.List;
import java.util.stream.Collectors;

public class PersonMapper {

    public static Tutorials tutorialsMapperDtoToEntity(TutorialsDto tutorials){
        return Tutorials.builder()
                .id(tutorials.getId())
                .title(tutorials.getTitle())
                .description(tutorials.getDescription())
                .published(tutorials.getPublished())
                .build();
    }

    public static TutorialsDto tutorialsMapperEntityToDto(Tutorials tutorials){
        return TutorialsDto.builder()
                .id(tutorials.getId())
                .title(tutorials.getTitle())
                .description(tutorials.getDescription())
                .published(tutorials.getPublished())
                .build();
    }


    public static List<Tutorials> tutorialsListMapperDtoToEntity(List<TutorialsDto> tutorialsDtoList) {
        return tutorialsDtoList.stream()
                .map(PersonMapper::tutorialsMapperDtoToEntity)
                .collect(Collectors.toList());
    }


    public static List<TutorialsDto> tutorialsListMapperEntityToDto(List<Tutorials> tutorialsList) {
        return tutorialsList.stream()
                .map(PersonMapper::tutorialsMapperEntityToDto)
                .collect(Collectors.toList());
    }
}

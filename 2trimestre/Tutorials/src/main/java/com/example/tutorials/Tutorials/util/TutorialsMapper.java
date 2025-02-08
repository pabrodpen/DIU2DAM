package com.example.tutorials.Tutorials.util;

import com.example.tutorials.Tutorials.model.TutorialsVO;
import com.example.tutorials.Tutorials.model.TutorialsDto;
import java.util.List;
import java.util.stream.Collectors;

public class TutorialsMapper {

    public static TutorialsVO tutorialsMapperDtoToEntity(TutorialsDto tutorials){
        return TutorialsVO.builder()
                .id(tutorials.getId())
                .title(tutorials.getTitle())
                .description(tutorials.getDescription())
                .published(tutorials.getPublished())
                .build();
    }

    public static TutorialsDto tutorialsMapperEntityToDto(TutorialsVO tutorials){
        return TutorialsDto.builder()
                .id(tutorials.getId())
                .title(tutorials.getTitle())
                .description(tutorials.getDescription())
                .published(tutorials.getPublished())
                .build();
    }


    public static List<TutorialsVO> tutorialsListMapperDtoToEntity(List<TutorialsDto> tutorialsDtoList) {
        return tutorialsDtoList.stream()
                .map(TutorialsMapper::tutorialsMapperDtoToEntity)
                .collect(Collectors.toList());
    }


    public static List<TutorialsDto> tutorialsListMapperEntityToDto(List<TutorialsVO> tutorialsList) {
        return tutorialsList.stream()
                .map(TutorialsMapper::tutorialsMapperEntityToDto)
                .collect(Collectors.toList());
    }
}

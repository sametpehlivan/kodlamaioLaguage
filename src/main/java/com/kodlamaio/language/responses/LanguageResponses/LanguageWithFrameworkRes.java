package com.kodlamaio.language.responses.LanguageResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageWithFrameworkRes implements Serializable
{
    private String name;
    private List<FrameworkRes> frameworks;
}
